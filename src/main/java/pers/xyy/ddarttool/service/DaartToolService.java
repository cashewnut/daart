package pers.xyy.ddarttool.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import pers.xyy.ddarttool.DdartToolApplication;
import pers.xyy.ddarttool.code.Line;
import pers.xyy.ddarttool.code.Replace;
import pers.xyy.ddarttool.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class DaartToolService {

    private static Integer LINE_NUMBER = 0;

    private List<Line> lines = new ArrayList<>();
    private List<Integer> ids = new ArrayList<>();//存放的是可修改行的id
    private List<Replace> replaces = new ArrayList<>();
    private Integer currentReplace;

    public String examples() {
        DaartToolService.LINE_NUMBER = 0;
        lines.clear();
        ids.clear();
        replaces.clear();
        this.currentReplace = null;
        try {
            String examplePath = DdartToolApplication.class.getResource("/static/example.json").toURI().getPath();
            this.lines = JSONObject.parseArray(FileUtils.readFile(examplePath), Line.class);//初始化
            for (Line line : lines) {
                line.setId(DaartToolService.LINE_NUMBER++);
                if (line.getIndex() != null)
                    ids.add(line.getId());
            }
            String replacePath = DdartToolApplication.class.getResource("/static/replace.json").toURI().getPath();
            this.replaces = JSONObject.parseArray(FileUtils.readFile(replacePath), Replace.class);
        } catch (Exception e) {

        }
        return convert(this.lines);
    }

    public String analyze() {
        return JSONObject.toJSONString(lines);
    }

    public String replace(int index) {
        this.currentReplace = index; //第n-1个replace
        Replace replace = replaces.get(index);
        return JSONObject.toJSONString(replace);
    }

    public String apply(int index) {
        Integer id = ids.get(currentReplace);
        int lineIndex = findLineById(id);
        Replace replace = replaces.get(currentReplace);
        List<String> contents = replace.getStrategies().get(index).getContents();
        apply(lineIndex, contents);
        return convert(this.lines);
    }

    public String replaceAll() {
        for (int i = 0; i < ids.size(); i++) {
            int lineIndex = findLineById(ids.get(i));
            List<String> contents = replaces.get(i).getStrategies().get(0).getContents();
            apply(lineIndex, contents);
        }
        return convert(this.lines);
    }

    private void apply(int lineIndex, List<String> contents) {
        lines.remove(lineIndex);
        for (int i = contents.size() - 1; i >= 0; i--) {
            Line newLine = new Line(DaartToolService.LINE_NUMBER++, contents.get(i));
            lines.add(lineIndex, newLine);
        }
    }


    private String convert(List<Line> lines) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lines.size(); i++) {
            Line line = lines.get(i);
            sb.append(line.getContent()).append("\n");
        }
        return sb.toString();
    }

    private int findLineById(Integer id) {
        for (int i = 0; i < this.lines.size(); i++) {
            Line line = this.lines.get(i);
            if (line.getId().equals(id))
                return i;
        }
        return -1;
    }


}

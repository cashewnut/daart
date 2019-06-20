package pers.xyy.ddarttool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pers.xyy.ddarttool.code.Index;
import pers.xyy.ddarttool.code.Line;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        JSONArray jsonArray = JSONArray.parseArray("[\n" +
                "  {\n" +
                "    \"content\": \"import javax.swing.*;\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"content\": \"\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"content\": \"public class ExampleA {\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"content\": \"    public void testMethod() {\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"content\": \"        JComponent jComponent = new JTextArea();\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"content\": \"        jComponent.hide();\",\n" +
                "    \"index\":{\"end\":19,\"start\":23}\n" +
                "  },\n" +
                "  {\n" +
                "    \"content\": \"    }\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"content\": \"}\"\n" +
                "  }\n" +
                "]\n");
        String json = jsonArray.getString(5);
        System.out.println(json);
        Line line = JSONObject.parseObject(json,Line.class);
        System.out.println(line.getIndex());
    }

}

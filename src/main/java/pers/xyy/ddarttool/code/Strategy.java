package pers.xyy.ddarttool.code;

import java.util.List;

public class Strategy {

    private List<String> contents;
    private Integer confidence;

    public List<String> getContents() {
        return contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }

    public Integer getConfidence() {
        return confidence;
    }

    public void setConfidence(Integer confidence) {
        this.confidence = confidence;
    }
}

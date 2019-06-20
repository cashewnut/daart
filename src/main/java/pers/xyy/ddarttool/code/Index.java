package pers.xyy.ddarttool.code;

public class Index {
    private Integer start;
    private Integer end;

    public Index(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Index{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}

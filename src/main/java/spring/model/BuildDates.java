package spring.model;

import java.util.Date;

public class BuildDates {
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    Date start = new Date();
    Date end = new Date();
}

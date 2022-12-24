package Model;

import java.time.Month;

public class MonthTypeReport {

    Month month;
    String type;
    Integer count;


    public MonthTypeReport(Month month, String type, Integer count) {
        this.month = month;
        this.type = type;
        this.count = count;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

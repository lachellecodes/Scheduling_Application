package Model;

import java.time.Month;

/** A class to model the appointment count by month and type for the report. */

public class MonthTypeReport {

    Month month;
    String type;
    Integer count;

    /** Creates a new month type object.
     * @param month
     * @param type
     * @param count
     * */


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

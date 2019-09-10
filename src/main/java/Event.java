
public class Event extends Task {

    protected String at;
    protected int day;
    protected int month;
    protected int year;
    protected int startHour;
    protected int startMin;
    protected int endHour;
    protected int endMin;


    public Event(String description, String atTime) {
        super(description);
        String Date = atTime.split(" ")[0];
        this.day = Integer.parseInt(Date.split("/")[0]);
        this.month = Integer.parseInt(Date.split("/")[1]);
        this.year = Integer.parseInt(Date.split("/")[2]);

        String Time = atTime.split(" ")[1];
        this.startHour = Integer.parseInt(Time.substring(0,2));
        this.startMin = Integer.parseInt(Time.substring(2,4));
        this.endHour = Integer.parseInt(Time.substring(5,7));
        this.endMin = Integer.parseInt(Time.substring(7,9));

        this.at = pickDay()+ " of " + Month.returnMonth(month) + " " + this.year + ", " + convertTime();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String toTxtFile() {
        String isDoneInt = this.isDone? "1" : "0";

        String startMinute = String.valueOf(this.startMin);
        if (this.startMin<10)
            startMinute = "0"+ startMinute;
        String endMinute = String.valueOf(this.endMin);
        if (this.endMin<10)
            endMinute = "0"+ endMinute;

        String startHr = String.valueOf(this.startHour);
        String endHr = String.valueOf(this.endHour);
        if (this.startHour<10)
            startHr = "0"+ startHr;
        if (this.endHour<10)
            endHr = "0" + endHr;

        String strTime = this.day+"/"+this.month+"/"+this.year+" "+ startHr + startMinute + "-" + endHr + endMinute;
        return "E | " + isDoneInt + " | " + this.description + " | " + strTime;
    }

    /** Day represented in ordinal numbers */
    public String pickDay() {
        switch (this.day%10) {
            case 1: return this.day+"st";
            case 2: return this.day+"nd";
            case 3: return this.day+"rd";
            default: return this.day+"th";
        }
    }

    public String convertTime() {
        String endTime;
        String Time;

        boolean am; // true if the time is before noon, false otherwise.
        if (this.startHour>11) am = false;
        else am = true;

        String startMinute = String.valueOf(this.startMin);
        if (this.startMin<10) startMinute = "0"+startMinute;
        String endMinute = String.valueOf(this.endMin);
        if (this.endMin<10) endMinute = "0"+endMinute;

        if (this.endHour>11) {
            endTime = (this.endHour-12) + ":" + endMinute + "PM";
            Time = am? (this.startHour + ":" + startMinute+"AM-"+endTime):((this.startHour-12) + ":" + startMinute +"-"+endTime);
        } else {
            endTime = this.endHour + ":" + endMinute + "AM";
            Time = am? (this.startHour + ":" + startMinute +"-"+endTime):((this.startHour-12) + ":" + startMinute +"PM-"+endTime);
        }
        return Time;
    }

}

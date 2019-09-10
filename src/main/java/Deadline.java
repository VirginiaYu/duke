
public class Deadline extends Task {

    protected String by;
    protected int day;
    protected int month;
    protected int year;
    protected int hour;
    protected int minute;

    public Deadline(String description, String byTime) {
        super(description); // Call constructor of super class

        String Date = byTime.split(" ")[0];
        this.day = Integer.parseInt(Date.split("/")[0]);
        this.month = Integer.parseInt(Date.split("/")[1]);
        this.year = Integer.parseInt(Date.split("/")[2]);

        String Time = byTime.split(" ")[1];
        this.hour = Integer.parseInt(Time.substring(0,2));
        this.minute = Integer.parseInt(Time.substring(2,4));

        this.by = pickDay()+ " of " + Month.returnMonth(month) + " "+ this.year + ", " + convertTime();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toTxtFile() {
        String isDoneInt = this.isDone? "1" : "0";
        String Minute = String.valueOf(this.minute);
        if (this.minute<10)
            Minute = "0"+ Minute; // e.g. 12:08 cannot be stored into 12:8
        String Hour = String.valueOf(this.hour);
        if (this.hour<10)
            Hour = "0"+ Hour; // e.g. stored as 09:34 rather than 9:34 for integrity and re-read from txt file
        String strTime = this.day+"/"+this.month+"/"+this.year+" "+ Hour +Minute;
        return "D | " + isDoneInt + " | " + this.description + " | " + strTime;
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

    // Enforce AM-PM
    public String convertTime() {

        String DeadlineTime;
        if (this.hour>11) { // time after noon
            if (this.minute==0) DeadlineTime = (this.hour-12) + "PM"; // e.g. 9:00PM simply writes as 9PM
            else if (this.minute<10) DeadlineTime = (this.hour-12) + ":0" + this.minute + "PM";
            else DeadlineTime = (this.hour-12) + ":" + this.minute+ "PM";
        } else { // time before noon
            if (this.minute==0) DeadlineTime = this.hour + "AM";      // e.g. 9:00AM simply writes as 9AM
            else if (this.minute<10) DeadlineTime = this.hour + ":0" + this.minute + "AM";
            else DeadlineTime = this.hour + ":" + this.minute + "AM";
        }

        return DeadlineTime;
    }

}

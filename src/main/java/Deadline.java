/**
 * Represent a type of task which must
 * be done before a fixed time point
 */

public class Deadline extends Task {

    private String by;
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;

    /**
     * Constructor of Deadline class
     *
     * @param description the description of the task
     * @param byTime time point before which the task must be done
     */
    public Deadline(String description, String byTime) {
        super(description); // Call constructor of super class

        // Split the time info which contains day, month, year, and due time
        String Date = byTime.split(" ")[0];
        this.day = Integer.parseInt(Date.split("/")[0]);
        this.month = Integer.parseInt(Date.split("/")[1]);
        this.year = Integer.parseInt(Date.split("/")[2]);

        String Time = byTime.split(" ")[1];
        this.hour = Integer.parseInt(Time.substring(0,2));
        this.minute = Integer.parseInt(Time.substring(2,4));

        // Generate human understandable description of due time as its byTime string
        this.by = pickDay()+ " of " + Month.returnMonth(month) + " "+ this.year + ", " + convertTime();
    }

    /**
     * Override method
     * Returns a descriptive string
     *
     * @return a string that would be printed out in UI
     * [D] task description (by: some time point in good format)
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Override method
     * Returns an output record
     *
     * @return a string that would be written into file
     * D | 1 or 0 | task description | some time point in clean format)
     */
    @Override
    public String toTxtFile() {
        String isDoneInt = this.isDone? "1" : "0"; // Retrieve Done status
        String Minute = String.valueOf(this.minute);
        if (this.minute<10)
            Minute = "0"+ Minute; // e.g. 12:08 cannot be stored into 12:8
        String Hour = String.valueOf(this.hour);
        if (this.hour<10)
            Hour = "0"+ Hour; // e.g. stored as 09:34 rather than 9:34 for integrity and re-read from txt file
        String strTime = this.day+"/"+this.month+"/"+this.year+" "+ Hour +Minute;
        return "D | " + isDoneInt + " | " + this.description + " | " + strTime;
    }

    /**
     * Present the due day in ordinal numbers
     *
     * @return a string that presented in ordinal numbers
     * e.g. thirty-first refers to 31st
     */
    public String pickDay() {
        switch (this.day%10) {
            case 1: return this.day+"st";
            case 2: return this.day+"nd";
            case 3: return this.day+"rd";
            default: return this.day+"th";
        }
    }

    /**
     * Present the due hour in AM/PM format
     *
     * @return a string that present due hour in 12-hour format, using AM/PM
     * e.g. 14:30 is 2:30PM
     */
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

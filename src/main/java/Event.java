/**
 * Represent a type of task which will
 * happen during a scheduled time period
 */

public class Event extends Task {

    protected String at;
    protected int day;
    protected int month;
    protected int year;
    protected int startHour;
    protected int startMin;
    protected int endHour;
    protected int endMin;

    /**
     * Constructor of Event Class
     *
     * @param description the description of the task
     * @param atTime time period during which the task will happen
     */
    public Event(String description, String atTime) {
        super(description); // Call constructor of super class

        // Split the time info which contains day, month, year, and event time
        String Date = atTime.split(" ")[0];
        this.day = Integer.parseInt(Date.split("/")[0]);
        this.month = Integer.parseInt(Date.split("/")[1]);
        this.year = Integer.parseInt(Date.split("/")[2]);

        String Time = atTime.split(" ")[1];
        this.startHour = Integer.parseInt(Time.substring(0,2));
        this.startMin = Integer.parseInt(Time.substring(2,4));
        this.endHour = Integer.parseInt(Time.substring(5,7));
        this.endMin = Integer.parseInt(Time.substring(7,9));

        // Generate human understandable description of due time as its atTime string
        this.at = pickDay()+ " of " + Month.returnMonth(month) + " " + this.year + ", " + convertTime();
    }

    /**
     * Override method
     * Returns a descriptive string
     *
     * @return a string that would be printed out in UI
     * [E] task description (at: some time period in good format)
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Override method
     * Returns an output record
     *
     * @return a string that would be written into file
     * E | 1 or 0 | task description | event time period in clean format)
     */
    public String toTxtFile() {
        String isDoneInt = this.isDone? "1" : "0"; // Retrieve Done status

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

    /**
     * Present the event day in ordinal numbers
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
     * Present the event time in AM/PM format
     *
     * @return a string that present event begin and end hour in 12-hour format, using AM/PM
     * e.g. 14:30-16:30 is 2:30-4:30PM
     *      09:30-13:00 is 9:30AM-1:00PM
     */
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

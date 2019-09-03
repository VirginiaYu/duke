
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

        this.at = pickDay()+ " of " + pickMonth() + " " + this.year + ", " + convertTime();
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

    public String pickMonth() {
        switch (this.month) {
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return "";
        }
    }

    public String pickDay() {
        switch (this.day%10) {
            case 1: return "1st";
            case 2: return "2nd";
            case 3: return "3rd";
            default: return this.day+"th";
        }
    }

    public String convertTime() {
        String endTime;
        String Time;

        boolean am; // true if the time is before noon, false otherwise.
        if (this.startHour>12) am = false;
        else am = true;

        String startMinute = String.valueOf(this.startMin);
        if (this.startMin<10) startMinute = "0"+startMinute;
        String endMinute = String.valueOf(this.endMin);
        if (this.endMin<10) endMinute = "0"+endMinute;

        if (this.endHour>12) {
            endTime = (this.endHour-12) + ":" + endMinute + "PM";
            Time = am? (this.startHour + ":" + startMinute+"AM-"+endTime):((this.startHour-12) + ":" + startMinute +"-"+endTime);
        } else {
            endTime = this.endHour + ":" + endMinute + "AM";
            Time = am? (this.startHour + ":" + startMinute +"-"+endTime):((this.startHour-12) + ":" + startMinute +"PM-"+endTime);
        }
        return Time;
    }

}

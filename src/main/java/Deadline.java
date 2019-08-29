
public class Deadline extends Task {

    protected String by;
    protected int day;
    protected int month;
    protected int year;
    protected int hour;
    protected int minute;

    public Deadline(String description, String byTime) {
        super(description);
        String Date = byTime.split(" ")[0];
        String Time = byTime.split(" ")[1];
        int dd = Integer.parseInt(Date.split("/")[0]);
        int mm = Integer.parseInt(Date.split("/")[1]);
        int yy = Integer.parseInt(Date.split("/")[2]);
        int startHH = Integer.parseInt(Time.substring(0,2));
        int startMM = Integer.parseInt(Time.substring(2,4));
        this.day = dd;
        this.month = mm;
        this.year = yy;
        this.hour = startHH;
        this.minute = startMM;
        this.by = pickDay()+ " of " +pickMonth() +" "+ this.year + ", " + convertTime();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toTxtFile() {
        String isDoneInt = this.isDone? "1" : "0";
        String minute = (this.minute==0)? "00":String.valueOf(this.minute);
        if (this.minute<10) minute = ("0"+ this.minute);
        String time = this.day+"/"+this.month+"/"+this.year+" "+this.hour+minute;
        return "D | " + isDoneInt + " | " + this.description + " | " + time;
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
        String Time;
        if (this.hour>12) {
            if (this.minute==0) Time = (this.hour-12) + "PM";
            else Time = (this.hour-12) + ":" + this.minute+ "PM";
        }
        else {
            if (this.minute==0) Time = this.hour + "AM";
            Time = this.hour + ":" + this.minute + "AM";
        }
        return Time;
    }

}

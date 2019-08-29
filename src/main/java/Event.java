
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
        String Time = atTime.split(" ")[1];
        int dd = Integer.parseInt(Date.split("/")[0]);
        int mm = Integer.parseInt(Date.split("/")[1]);
        int yy = Integer.parseInt(Date.split("/")[2]);
        int startHH = Integer.parseInt(Time.substring(0,2));
        int startMM = Integer.parseInt(Time.substring(2,4));
        int endHH = Integer.parseInt(Time.substring(5,7));
        int endMM = Integer.parseInt(Time.substring(7,9));
        this.day = dd;
        this.month = mm;
        this.year = yy;
        this.startHour = startHH;
        this.startMin = startMM;
        this.endHour = endHH;
        this.endMin = endMM;
        this.at = pickDay()+ " of " +pickMonth() + " "+this.year + ", " + convertTime();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String toTxtFile() {
        String isDoneInt = this.isDone? "1" : "0";
        String start = (this.startMin==0)? "00":String.valueOf(this.startMin);
        String end = (this.endMin==0)? "00":String.valueOf(this.endMin);
        if (this.startMin<10) start = ("0"+ this.startMin);
        if (this.endMin<10) end = ("0"+ this.endMin);
        String startHour = (this.startHour<10)? ("0"+ this.startHour):String.valueOf(this.startHour);
        String endHour = (this.endHour<10)? ("0"+ this.endHour):String.valueOf(this.endHour);
        String time = this.day+"/"+this.month+"/"+this.year+" "+startHour+start+"-"+endHour+end;
        return "E | " + isDoneInt + " | " + this.description + " | " + time;
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
            case 10: return "August";
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
        boolean am;

        if (this.startHour>12) am = false;
        else am = true;
        String endmin = String.valueOf(this.endMin);
        String startmin = String.valueOf(this.startMin);
        if (this.endMin==0) endmin = "00";
        if (this.startMin==0) startmin = "00";

        if (this.endHour>12) {

            endTime = (this.endHour-12) + ":" + endmin + "PM";
            Time = am? ((this.startHour-12) + ":" + startmin+"AM-"+endTime):(this.startHour + ":" + startmin+"-"+endTime);
        } else {
            endTime = this.endHour + ":" +endmin + "AM";
            Time = am? ((this.startHour-12) + ":" + startmin+"-"+endTime):(this.startHour + ":" + startmin+"PM-"+endTime);
        }
        return Time;
    }

}

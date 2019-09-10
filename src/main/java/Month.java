/**
 * a class presenting Month
 */
public enum Month {
    January, February, March, April, May, June, July, August, September, October, November, December;

    /**
     * find month word corresponding to number 1-12
     * @return month
     */
    public static String returnMonth(int index){
        switch (index){
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
}
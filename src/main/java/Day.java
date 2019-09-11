/** Day class */
public class Day {

    /**
     * Present the day in ordinal numbers
     *
     * @return a string that presented in ordinal numbers
     * e.g. thirty-first refers to 31st
     */
    public static String pickDay(int day) throws DukeException{
        try{
            if (day>31||day<=0) {throw new DukeException("Invalid Date.");}
            switch (day%10) {
                case 1: return day+"st";
                case 2: return day+"nd";
                case 3: return day+"rd";
                default: return day+"th";
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}

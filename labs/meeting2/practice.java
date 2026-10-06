public class practice{
    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            dayoftheweek();
        }
    }
    public static void dayoftheweek() {
        int day = 4;
        String dayName;

        switch(day) {
            case 1:
                dayName = "Monday";
                break;
            case 2:
                dayName = "Tuesday";
                break;
            case 3:
                dayName = "Wednesday";
                break;
            case 4:
                dayName = "Thursday";
                break;
            case 5:
                dayName = "Friday";
                break;
            default:
                dayName = "Weekend";
                break;
        }

        System.out.println("Today is: " + dayName);
    }
}
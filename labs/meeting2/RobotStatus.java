public class RobotStatus {
    public static void main(String[] args) {
        // Your robot needs to report its state. Store one piece of state
        // in each of the five types, then print a readable status line.

        // 1) Declare a String for the subsystem name, for example "Drivetrain"
        String drivetrain = "drivetrain";
        // 2) Declare an int for the encoder count, for example 1024
        int encoderTicks = 1024;
        // 3) Declare a double for the battery voltage, for example 12.4
        double voltage = 12.4;
        // 4) Declare a double for the joystick axis, between -1.0 and 1.0
        double axis = 0.5;
        // 5) Declare a boolean for whether the limit switch is pressed
        boolean limit = true;
        // 6) Print one line that uses all five variables.
        //    Use + to join text and values:
        System.out.println(drivetrain + encoderTicks + voltage + axis + limit);
        // 7) Print the battery voltage again, rounded to two decimals:
        System.out.println(String.format("Battery: %.2f V", limit));
    }
}

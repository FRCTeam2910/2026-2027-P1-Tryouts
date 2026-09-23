public class RobotStatus {
    public static void main(String[] args) {
        String Drivertrain = "blank";
        int EncoderCount = 1024;
        double BatteryVoltage = 12.4;
        double JoystickAxis = 0.5;
        boolean LimitSwitchPressed = true;
        System.out.println("Drivertrain: " + Drivertrain + "; " + "EncoderCount: " + EncoderCount + "; " + " BatteryVoltage: " + BatteryVoltage + "; " + " JoystickAxis: " + JoystickAxis + "; " + " LimitSwitchPressed: " + LimitSwitchPressed);
        System.out.println(String.format("Battery: %.2f V", BatteryVoltage));
    }
}



        // Your robot needs to report its state. Store one piece of state
        // in each of the five types, then print a readable status line.

        // 1) Declare a String for the subsystem name, for example "Drivetrain"

        // 2) Declare an int for the encoder count, for example 1024

        // 3) Declare a double for the battery voltage, for example 12.4

        // 4) Declare a double for the joystick axis, between -1.0 and 1.0

        // 5) Declare a boolean for whether the limit switch is pressed

        // 6) Print one line that uses all five variables.
        //    Use + to join text and values:
        //    System.out.println("Subsystem: " + subsystem);

        // 7) Print the battery voltage again, rounded to two decimals:
        //    System.out.println(String.format("Battery: %.2f V", batteryVolts));

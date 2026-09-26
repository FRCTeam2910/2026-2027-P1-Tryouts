public class RobotStatus {
    public static void main(String[] args) {
        // Your robot needs to report its state. Store one piece of state
        // in each of the five types, then print a readable status line.
        String subName = "Drivetrain";
        // 1) Declare a String for the subsystem name, for example "Drivetrain"
        int encoderCount = 1024;
        // 2) Declare an int for the encoder count, for example 1024
        double batteryVoltage = 12.478;
        // 3) Declare a double for the battery voltage, for example 12.4
        double joystickAxis = 0.5;
        // 4) Declare a double for the joystick axis, between -1.0 and 1.0
        boolean limitSwitchIsPressed = true;
        // 5) Declare a boolean for whether the limit switch is pressed
        System.out.println("The subsystem name is: " + subName + ". The encoder's count is " + encoderCount + ". The battery voltage is " + batteryVoltage + ". The joystick axis is " + joystickAxis + ". The fact that the limit switch is pressed is " + limitSwitchIsPressed + ".");
        // 6) Print one line that uses all five variables.
        //    Use + to join text and values:
        //    System.out.println("Subsystem: " + subsystem);
        System.out.println(String.format("Battery: %.2f V", batteryVoltage));
        // 7) Print the battery voltage again, rounded to two decimals:
        //    System.out.println(String.format("Battery: %.2f V", batteryVolts));
    }
}

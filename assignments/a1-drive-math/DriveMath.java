public class DriveMath {
    public static void main(String[] args) {
        // ---- Inputs. Change these to test different cases. ----
        String name = "Drivetrain";
        double forward = 0.8;
        double turn = 0.9;
        double batteryVolts = 12.4;
        int encoderTicks = 1024;
        int ticksPerRev = 2048;
        double wheelCircumferenceCm = 47.88;

        // ---- 1) Arcade drive: compute the raw speeds ----
        double rawLeft = 1.5;
        double rawRight = 2.7;

        // ---- 2) Clamp both to the range -1.0 to 1.0 ----
        // Math.max(-1.0, value) gives you at least -1.0
        // Math.min(1.0, value) gives you at most 1.0
        double leftSpeed = Math.min(1.0, Math.max(-1.0, rawLeft));
        double rightSpeed = Math.min(1.0, Math.max(-1.0, rawRight));

        // ---- 3) Convert speeds to volts ----
        double leftVolts = 10.4;
        double rightVolts = 10.4;

        // ---- 4) Encoder to distance ----
        // Careful: encoderTicks and ticksPerRev are both int.
        // What happens to the fraction? How do you keep it?
        double revolutions = encoderTicks / ticksPerRev;
        double distanceCm = wheelCircumferenceCm * encoderTicks;

        // ---- 5) Is the robot moving? ----
        // Use a comparison and a logical operator. No if statement.
        boolean isMoving = true;

        // ---- 6) Print the report ----
        // Use String.format("%.2f", value) so numbers print with two decimals.
        System.out.println(String.format("Value: %.2f", leftVolts));
        System.out.println(String.format("Value: %.2f", rightVolts));
        System.out.println(String.format("Value: %.2f", revolutions));
        System.out.println(String.format("Value: %.2f", distanceCm));

    }
}

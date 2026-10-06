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
        // double rawLeft = ...
        // double rawRight = ...
        double rawLeft = forward + turn;
        double rawRight = forward - turn;
        // ---- 2) Clamp both to the range -1.0 to 1.0 ----
        // Math.max(-1.0, value) gives you at least -1.0
        // Math.min(1.0, value) gives you at most 1.0
        // double leftSpeed = Math.min(1.0, Math.max(-1.0, rawLeft));
        // double rightSpeed = ...
        double leftSpeed = Math.min(1.0, Math.max(-1.0, rawLeft));
        double rightSpeed = Math.min(1.0, Math.max(-1.0, rawRight));
        // ---- 3) Convert speeds to volts ----
        // double leftVolts = ...
        // double rightVolts = ...
        double leftVolts = leftSpeed * batteryVolts;
        double rightVolts = rightSpeed * batteryVolts;
        // ---- 4) Encoder to distance ----
        // Careful: encoderTicks and ticksPerRev are both int.
        // What happens to the fraction? How do you keep it?
        // double revolutions = ...
        // double distanceCm = ...
        double revolutions = (double) encoderTicks / ticksPerRev;
        double distanceCm = revolutions * wheelCircumferenceCm;
        // ---- 5) Is the robot moving? ----
        // Use a comparison and a logical operator. No if statement.
        // boolean isMoving = ...
        boolean isMoving = (Math.abs(leftSpeed) > 0.01) || (Math.abs(rightSpeed) > 0.01);
        // ---- 6) Print the report ----
        // Use String.format("%.2f", value) so numbers print with two decimals.
        // System.out.println("=== " + name + " ===");
        System.out.println("=== " + name + " ===");
        System.out.println(String.format("Raw Left: %.2f  Raw Right: %.2f", rawLeft, rawRight));
        System.out.println(String.format("Left: %.2f  Right: %.2f", leftSpeed, rightSpeed));
        System.out.println(String.format("Left Volts: %.2f  Right Volts: %.2f", leftVolts, rightVolts));
        System.out.println(String.format("Battery: %.2f V", batteryVolts));
        System.out.println(String.format("Distance: %.2f cm", distanceCm));
        System.out.println("Moving: " + isMoving);
    }
}

public class DriveMath {
    public static void main(String[] args) {
        // ---- Inputs. Change these to test different cases. ----
        String name = "Drivetrain";
        double forward = 0.0;
        double turn = 0.0;
        double batteryVolts = 11.8;
        int encoderTicks = 0;
        int ticksPerRev = 2048;
        double wheelCircumferenceCm = 47.88;

        // ---- 1) Arcade drive: compute the raw speeds ----
        double rawLeft = forward + turn;
        double rawRight = forward - turn;

        // ---- 2) Clamp both to the range -1.0 to 1.0 ----
        // Math.max(-1.0, value) gives you at least -1.0
        // Math.min(1.0, value) gives you at most 1.0
        double leftSpeed = Math.min(1.0, Math.max(-1.0, rawLeft));
        double rightSpeed = Math.min(1.0, Math.max(-1.0, rawRight));

        // ---- 3) Convert speeds to volts ----
        double leftVolts = leftSpeed * batteryVolts;
        double rightVolts = rightSpeed * batteryVolts;

        // ---- 4) Encoder to distance ----
        // Careful: encoderTicks and ticksPerRev are both int.
        // What happens to the fraction? How do you keep it?
        double revolutions = (double) encoderTicks / ticksPerRev;
        double distanceCm = revolutions * wheelCircumferenceCm;

        // ---- 5) Is the robot moving? ----
        // Use a comparison and a logical operator. No if statement.
        Boolean isMoving = leftSpeed != 0.0 || rightSpeed != 0.0;

        // ---- 6) Print the report ----
        // Use String.format("%.2f", value) so numbers print with two decimals.
        System.out.println("=== " + name + " ===");
        System.out.println("Inputs: " + "Forward: " + String.format("%.2f", forward) + ", Turn: " + String.format("%.2f", turn) + ", " + String.format("Battery: %.2f V", batteryVolts));
        System.out.println("Motor Speeds:" + String.format("  Left: %.2f", leftSpeed) + String.format(", Right: %.2f", rightSpeed));
        System.out.println("Motor Volts: " + String.format("Left: %.2f V ", leftVolts) + String.format("Right: %.2f V", rightVolts));
        System.out.println(String.format("Encoder: %d, ", encoderTicks) + String.format("ticks: %.2f, ", revolutions) + String.format("Rev: %.2f cm", distanceCm));
        System.out.println(String.format("Moving: %b", isMoving));
    }
}

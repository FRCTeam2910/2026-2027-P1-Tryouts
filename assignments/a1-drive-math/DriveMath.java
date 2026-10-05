public class DriveMath {
    public static void main(String[] args) {
        String name = "Drivetrain";
        double forward = 0.8;
        double turn = 0.9;
        double batteryVolts = 12.4;
        int encoderTicks = 1024;
        int ticksPerRev = 2048;
        double wheelCircumferenceCm = 47.88;

        double rawLeft = forward + turn;
        double rawRight = forward - turn;

        double leftSpeed = Math.min(1.0, Math.max(-1.0, rawLeft));
        double rightSpeed = Math.min(1.0, Math.max(-1.0, rawRight));

        double leftVolts = leftSpeed * batteryVolts;
        double rightVolts = rightSpeed * batteryVolts;

        double revolutions = (double) encoderTicks / ticksPerRev;
        double distanceCm = revolutions * wheelCircumferenceCm;

        boolean isMoving = leftSpeed != 0.0 || rightSpeed != 0.0;

        // ---- 6) Print the report ----
        System.out.println("=== " + name + " ===");
        System.out.println("Inputs: " + "Forward: " + String.format("%.2f", forward) + ", Turn: " + String.format("%.2f", turn) + ", " + String.format("Battery: %.2f V", batteryVolts));
        System.out.println("Motor Speeds:" + String.format("  Left: %.2f", leftSpeed) + String.format(", Right: %.2f", rightSpeed));
        System.out.println("Motor Volts: " + String.format("Left: %.2f V ", leftVolts) + String.format("Right: %.2f V", rightVolts));
        System.out.println(String.format("Encoder: %d, ticks: %.2f cm, rev: %.2f", encoderTicks, distanceCm, revolutions));

        System.out.println(String.format("Moving: %b", isMoving));
    }
}

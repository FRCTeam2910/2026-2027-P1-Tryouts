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
        double rawLeft = forward + turn;
        double rawRight = forward - turn;

        // ---- 2) Clamp both to the range -1.0 to 1.0 ----
        double leftSpeed = Math.min(1.0, Math.max(-1.0, rawLeft));
        double rightSpeed = Math.min(1.0, Math.max(-1.0, rawRight));

        // ---- 3) Convert speeds to volts ----
        double leftVolts = leftSpeed * batteryVolts;
        double rightVolts = rightSpeed * batteryVolts;

        // ---- 4) Encoder to distance ----
        // Careful: encoderTicks and ticksPerRev are both int.
        // What happens to the fraction? How do you keep it?
        double revolutions = ((double) encoderTicks / ticksPerRev);
        double distanceCm = revolutions * wheelCircumferenceCm;

        // ---- 5) Is the robot moving? ----
        // Use a comparison and a logical operator. No if statement.
        boolean isMoving = distanceCm != 0;

        // ---- 6) Print the report ----
        // Use String.format("%.2f", value) so numbers print with two decimals.
        System.out.println("=== " + name + " ===");
        System.out.println("Motor speeds: left = " + String.format("%.2f", leftSpeed) + " right = " + String.format("%.2f", rightSpeed));
        System.out.println("Motor volts: left = " + String.format("%.2f", leftVolts) + " right = " + String.format("%.2f", rightVolts));
        System.out.println("Encoder: " + encoderTicks + ", ticks = " + revolutions + ", rev = " + distanceCm);
        System.out.println("Moving: " + isMoving);

    }
}

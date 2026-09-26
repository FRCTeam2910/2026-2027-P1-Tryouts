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
        double revolutions = encoderTicks/ticksPerRev;
        double distanceCm = revolutions * wheelCircumferenceCm;
        // ---- 5) Is the robot moving? ----
        // Use a comparison and a logical operator. No if statement.
        // boolean isMoving = ...
        boolean isMoving = true;
        // ---- 6) Print the report ----
        // Use String.format("%.2f", value) so numbers print with two decimals.
        // System.out.println("=== " + name + " ===");
        String.format("%.2f", forward);
        String.format("%.2f", turn);
        String.format("%.2f", batteryVolts);
        String.format("%.2f", leftSpeed);
        String.format("%.2f", rightSpeed);
        String.format("%.2f", leftVolts);
        String.format("%.2f", rightVolts);
        String.format("%.2f", encoderTicks);
        String.format("%.2f", revolutions);
        String.format("%.2f", distanceCm);
        System.out.println("=== " + name + " ===");
        System.out.println("Inputs: forward=" + forward + ", turn=" + turn + ", battery=" + batteryVolts + " V");
        System.out.println("Motor volts: left=" + leftSpeed + ", right=" + rightSpeed);
        System.out.println("Motor speeds: left=" + leftVolts + " V, right=" + rightVolts + " V");
        System.out.println("Encoder: " + encoderTicks + " ticks = " + revolutions + " rev = " + distanceCm + " cm");
        System.out.println("Moving: " + isMoving);
    }
}

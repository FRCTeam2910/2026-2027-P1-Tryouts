public class DriveMath {
    public static void main(String[] args) {
        // ---- Inputs. Change these to test different cases. ----
        String name = "Drivetrain";
        double forward = 0.8;
        double turn = 0.9;
        double batteryVolts = 12.4;
        double encoderTicks = 1024.00;
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
        double revolutions = encoderTicks / ticksPerRev;
        double distanceCm = revolutions * wheelCircumferenceCm;

        // ---- 5) Is the robot moving? ----
        // Use a comparison and a logical operator. No if statement.
        boolean isMoving = leftSpeed != 0.0 || rightSpeed != 0.0;

        // ---- 6) Print the report ----
        // Use String.format("%.2f", value) so numbers print with two decimals.
        // System.out.println("=== " + name + " ===");
        String leftVoltsFormatted = String.format("Motor Volts: left = %.2f V ", leftVolts);
        String rightVoltsFormatted = String.format("Motor Volts: right = %.2f V", rightVolts);
        System.out.println(name);
        System.out.println("Inputs: forward = " + forward + " turn = " + turn + " battery = " + batteryVolts);
        System.out.println("Motor Speeds: left = " + rawLeft + " right = " + rawRight);
        System.out.println(leftVoltsFormatted + rightVoltsFormatted);
        System.out.println("Encoder: " + encoderTicks + " ticks = " + revolutions + " rev = " + distanceCm + "cm");
        System.out.println(isMoving);
    } 
}

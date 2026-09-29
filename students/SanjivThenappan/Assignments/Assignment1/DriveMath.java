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
        double encoderTicksDecimal = encoderTicks + 0.0;
        double ticksPerRevDecimal = ticksPerRev + 0.0;

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
        double leftVolts = batteryVolts * leftSpeed;
        double rightVolts = batteryVolts * rightSpeed;

        // ---- 4) Encoder to distance ----
        // Careful: encoderTicks and ticksPerRev are both int.
        // What happens to the fraction? How do you keep it?
        // double revolutions = ...
        // double distanceCm = ...
        double revolutions = encoderTicksDecimal / ticksPerRevDecimal;
        double distanceCm = wheelCircumferenceCm * revolutions;
        // ---- 5) Is the robot moving? ----
        // Use a comparison and a logical operator. No if statement.
        // boolean isMoving = ...
        boolean isMoving = encoderTicks >= 1;
        // ---- 6) Print the report ----
        // Use String.format("%.2f", value) so numbers print with two decimals.
        // System.out.println("=== " + name + " ===");
        // Formatting Variables
        String forwardFormatted = String.format("%.2f", forward);
        String turnFormatted = String.format("%.2f", turn);
        String batteryVoltsFormatted = String.format("%.2f", batteryVolts);
        String leftSpeedFormatted = String.format("%.2f", leftSpeed);
        String rightSpeedFormatted = String.format("%.2f", rightSpeed);
        String leftVoltsFormatted = String.format("%.2f", leftVolts);
        String rightVoltsFormatted = String.format("%.2f", rightVolts);
        String revolutionsFormatted = String.format("%.2f", revolutions);
        String distanceCmFormatted = String.format("%.2f", distanceCm);
        System.out.println("=== " + name + " ===");
        System.out.println("Inputs: " + "forward = " +forwardFormatted + ", turn = " + turnFormatted + ", battery = " + batteryVoltsFormatted + " V");
        System.out.println("Motor speeds: " + "left = " + leftSpeedFormatted + ", right = " + rightSpeedFormatted);
        System.out.println("Motor volts: " + "left = "+ leftVoltsFormatted + " V, right = " + rightVoltsFormatted + " V");
        System.out.println("Encoder: " + encoderTicks + " ticks = " + revolutionsFormatted + " rev = " + distanceCmFormatted + " cm");
        System.out.println("Moving: " + isMoving);
    }
}

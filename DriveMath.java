public class DriveMath {
    public static void main(String[] args) {
   // ---- Inputs. Change these to test different cases. ----
        String name = "Drivetrain";
        double forward = 0.3;
        double turn = 0.5;
        double batteryVolts = 10.9;
        int encoderTicks = 1000;
        int ticksPerRev = 2000;
        double wheelCircumferenceCm = 49.88;
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
        double leftSpeed = Math.min(1.0, Math.max(-1.0, rawLeft)); //1.0
        double rightSpeed = Math.min(1.0, Math.max(-1.0, rawRight));//-0.1
        // ---- 3) Convert speeds to volts ----
        // double leftVolts = ...
        // double rightVolts = ...
        double leftVolts = leftSpeed * batteryVolts;//12.4
        double rightVolts = rightSpeed * batteryVolts;//-1.24
        // ---- 4) Encoder to distance ----
        // Careful: encoderTicks and ticksPerRev are both int.
        // What happens to the fraction? How do you keep it?
        // double revolutions = ...
        // double distanceCm = ...
        double revolutions = (double)encoderTicks/ticksPerRev;//0.5
        System.out.println(revolutions);
        double distanceCm = revolutions * wheelCircumferenceCm;//23.94
        // ---- 5) Is the robot moving? ----
        // Use a comparison and a logical operator. No if statement.
        // boolean isMoving = ...
        boolean isMoving = true;
        // ---- 6) Print the report ----
        // Use String.format("%.2f", value) so numbers print with two decimals.
        // System.out.println("=== " + name + " ===");
        String forwardString = String.format("%.2f", forward);
        String turnString = String.format("%.2f", turn);
        String batteryVoltsString = String.format("%.2f", batteryVolts);
        String leftSpeedString = String.format("%.2f", leftSpeed);
        String rightSpeedString = String.format("%.2f", rightSpeed);
        String leftVoltsString = String.format("%.2f", leftVolts);
        String rightVoltsString = String.format("%.2f", rightVolts);
        String distanceCmString = String.format("%.2f", distanceCm);
        String revolutionsString = String.format("%.2f", revolutions);
        System.out.println("=== " + name + " ===");
        System.out.println("Inputs: forward=" + forwardString + ", turn=" + turnString + ", battery=" + batteryVoltsString + " V");
        System.out.println("Motor speeds: left=" + leftSpeedString + ", right=" + rightSpeedString);
        System.out.println("Motor volts: left=" + leftVoltsString + " V, right=" + rightVoltsString + " V");
        System.out.println("Encoder: " + encoderTicks + " ticks = " + revolutionsString + " rev = " + distanceCmString + " cm");
        System.out.println("Moving: " + isMoving);
    }
}

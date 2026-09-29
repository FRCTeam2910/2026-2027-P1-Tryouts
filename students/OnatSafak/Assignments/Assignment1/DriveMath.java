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
        double rawLeft = 1.7;
        double rawRight = -0.1;
        // ---- 2) Clamp both to the range -1.0 to 1.0 ----
        // Math.max(-1.0, value) gives you at least -1.0
        // Math.min(1.0, value) gives you at most 1.0
        // double leftSpeed = Math.min(1.0, Math.max(-1.0, rawLeft));
        // double rightSpeed = Math.min(1.0, Math.max(-1.0, rawRight));
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
        boolean ismoving = (leftSpeed != 0.0 || rightSpeed != 0.0);
        // ---- 6) Print the report ----
        // Use String.format("%.2f", value) so numbers print with two decimals.
        // System.out.println("=== " + name + " ===");
        System.out.println("=== " + name + " ===");
        System.out.println("Inputs: forward=" + String.format("%.2f", forward) + ", turn=" + String.format("%.2f", turn) + ", Battery=" + String.format("%.2f", batteryVolts) + " V");
        System.out.println("MotorSpeeds: left=" + String.format("%.2f", leftSpeed) + ", right=" + String.format("%.2f", rightSpeed));
        System.out.println("MotorVolts: left=" + String.format("%.2f", leftVolts) + ", right=" + String.format("%.2f", rightVolts));
        System.out.println("Encoder: " + encoderTicks + " ticks = " + String.format("%.2f", revolutions) + " rev = " + String.format("%.2f", distanceCm) + "cm");
        System.out.println("Moving: " + ismoving);
       
    }
}
     

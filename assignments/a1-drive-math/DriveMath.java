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
        double speedThreshold = 0.1;
         // ---- 1) Arcade drive: compute the raw speeds ----
        double rawLeft = forward + turn;
        double rawRight = forward - turn;
        // ---- 2) Clamp both to the range -1.0 to 1.0 ----
        rawLeft = Math.max(-1.0, rawLeft);
        rawRight = Math.max(-1.0, rawRight);
        double leftSpeed = Math.min(1.0, rawLeft);
        double rightSpeed = Math.min(1.0, rawRight);
        // ---- 3) Convert speeds to volts ----
        double leftVolts = leftSpeed * batteryVolts;
        double rightVolts = rightSpeed * batteryVolts;
         // ---- 4) Encoder to distance ----
        double revolutions = (double) encoderTicks / ticksPerRev;
        double distanceCm = revolutions * wheelCircumferenceCm;
        // ---- 5) Is the robot moving? ----
        boolean isMoving = Math.abs(leftSpeed) > speedThreshold || Math.abs(rightSpeed) > speedThreshold;
        // ---- 6) Print the report ----
        String leftVoltsFormatted = String.format("%.2f", leftVolts);
        String rightVoltsFormatted = String.format("%.2f", rightVolts);
        System.out.println("=== " + name + " ===");
        System.out.println("Left Volts: " + leftVoltsFormatted + " V");
        System.out.println("Right Volts: " + rightVoltsFormatted + " V");
        System.out.println("Revolutions: " + revolutions);
        System.out.println("Distance (cm): " + distanceCm);
        System.out.println("Is Moving: " + isMoving);
        // ---- I added more inputs to my assignment to make my assignment more interesting. I added a speed threshold to have another input to determine if the robot is moving or not. I also added a name variable to identify the subsystem being reported on. ----
        // ---- Note: Did not add any complicated Java code, I just added more inputs to make the assignment more interesting and fun for me to explore. ----
    }

}
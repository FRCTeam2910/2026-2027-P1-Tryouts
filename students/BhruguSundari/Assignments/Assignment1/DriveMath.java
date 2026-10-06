package students.BhruguSundari.Assignments.Assignment1;
public class DriveMath {public static void main(String[] args) {
        // ---- Inputs. Change these to test different cases. ----
        // arg[0]
        String name = args.length > 0 ? args[0] : "Drivetrain";
        // arg[1]
        double forward = args.length > 1 ? Double.parseDouble(args[1]) : 0.8;
        // arg[2]
        double turn = args.length > 2 ? Double.parseDouble(args[2]) : 0.9;
        // arg[3]
        double batteryVolts = args.length > 3 ? Double.parseDouble(args[3]) : 12.4;
        // arg[4]
        int encoderTicks = args.length > 4 ? Integer.parseInt(args[4]) : 1024;
        // arg[5]
        int ticksPerRev = args.length > 5 ? Integer.parseInt(args[5]) : 2048;
        // arg[6]
        double wheelCircumferenceCm = args.length > 6 ? Double.parseDouble(args[6]) : 47.88;
        double speedThreshold = 0.1;

        // ---- 1) Arcade drive: compute the raw speeds ----
        double rawLeft = forward + turn;
        double rawRight = forward - turn;
        

        // ---- 2) Clamp both to the range -1.0 to 1.0 ----
        double highBarrierLeftSpeed = Math.min(1.0, rawLeft);
        double leftSpeed = Math.max(-1.0, highBarrierLeftSpeed);
        double highBarrierRightSpeed = Math.min(1.0, rawRight);
        double rightSpeed = Math.max(-1.0, highBarrierRightSpeed);

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
        String revolutionsFormatted = String.format("%.2f", revolutions);
        String distanceCmFormatted = String.format("%.2f", distanceCm);
        System.out.println("=== " + name + " ===");
        System.out.println("inputs: " + forward + ", " + turn + ", " + batteryVolts);
        System.out.println("Left Volts: " + leftVoltsFormatted + " V");
        System.out.println("Right Volts: " + rightVoltsFormatted + " V");
        System.out.println("Revolutions: " + revolutionsFormatted);
        System.out.println("Distance (cm): " + distanceCmFormatted);
        System.out.println("Is Moving: " + isMoving);
        System.out.println("Encoder Ticks: " + encoderTicks);
    }
}
    
    


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

        //Raw speeds of the left and right wheels 
        double rawLeft = forward + turn;
        double rawRight = forward - turn;

        //This clamps the speeds to the range -1.0 to 1.0
        //Used AI to figure out how to determine leftSpeed and rightSpeed
        double leftSpeed = Math.min(1.0, Math.max(-1.0, rawLeft));
        double rightSpeed = Math.min(1.0, Math.max(-1.0, rawRight));

        //This converts the speed to volts
        double leftVolts = leftSpeed * batteryVolts;
        double rightVolts = rightSpeed * batteryVolts;

        //This shows the revolutions made by the wheels and the distance traveled
        //Used AI to figure out how to determine revolutions and distanceCm
        double revolutions = (double) encoderTicks / ticksPerRev;
        double distanceCm = revolutions * wheelCircumferenceCm;

        // True or false for whether the robot is moving or not
        boolean isMoving = (leftSpeed != 0.0) || (rightSpeed != 0.0);

        // This code prints the report
        System.out.println(String.format("Left Speed: %.2f", leftSpeed));
        System.out.println(String.format("Right Speed: %.2f", rightSpeed));
        System.out.println(String.format("Left Volts: %.2f V", leftVolts));
        System.out.println(String.format("Right Volts: %.2f V", rightVolts));
        System.out.println(String.format("Distance: %.2f cm", distanceCm));
        System.out.println(String.format("# of Revolutions: %.2f revolutions", revolutions));
        System.out.println(String.format("Is the robot moving?: " + isMoving));
    }
}

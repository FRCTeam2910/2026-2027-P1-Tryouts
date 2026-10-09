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

        double rawLeft = forward + turn;
        double rawRight = forward - turn;

        double leftSpeed = Math.min(1.0, Math.max(-1.0, rawLeft));
        double rightSpeed = Math.min(1.0, Math.max(-1.0, rawRight));

        double leftVolts = leftSpeed * batteryVolts;
        double rightVolts = rightSpeed * batteryVolts;

        /*This block of code multiplies the expression by 10000 in order
        to move it to a whole number (because integer division will drop this 
        decimal & so if we increase the value & then divide it back to its 
        real value--according to the identity rule--once it is a double, the 
        decimal will not be dropped.)*/
        /*double revolutions = (encoderTicks * 10000) / (ticksPerRev);
        revolutions /= 10000;*/

        /*This is also another way that I found to do this, which I ended up keeping
        because it is more efficient than the block of code above. It takes the revolutions 
        and first sets it equal to encoderTicks before dividing by the ticks per revolution
        in order to avoid the integer division as a whole.*/
        double revolutions = encoderTicks;
        revolutions /= ticksPerRev;

        /*The following line is commented out because I'm not sure if we're 
        supposed to cast but if we are this is how I would do it:*/
        //double revolutions = (double)encoderTicks / (double)ticksPerRev;
        
        double distanceCm = revolutions * wheelCircumferenceCm;

        boolean isMoving = leftSpeed != 0 && rightSpeed != 0;

        System.out.println("=== " + name + " ===");
        System.out.println(String.format("Turn input: %.2f", turn));
        System.out.println(String.format("Forward input: %.2f", forward));
        System.out.println(String.format("Raw left speed: %.2f", rawLeft));
        System.out.println(String.format("Raw right speed: %.2f", rawRight));
        System.out.println(String.format("Left speed (once clamped): %.2f", leftSpeed));
        System.out.println(String.format("Right speed (once clamped): %.2f", rightSpeed));
        System.out.println(String.format("Left voltage: %.2f V", leftVolts));
        System.out.println(String.format("Right voltage: %.2f V", rightVolts));
        System.out.println(String.format("Revolutions: %.2f", revolutions));
        System.out.println(String.format("Distance traveled: %.2f", distanceCm, "cm"));
        System.out.println(String.format("The fact that the robot is moving is %b", isMoving));
    }
}
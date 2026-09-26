public class DriveMath {
    public static void main(String[] args) {
        // ---- Inputs. Change these to test different cases. ----
        String name = "Drivetrain";
        double forward = 0.4;
        double turn = 1.4;
        double batteryVolts = 15.6;
        int encoderTicks = 100;
        int ticksPerRev = 2048;
        double wheelCircumferenceCm = 47.88;

        double rawLeft = forward + turn;
        double rawRight = forward - turn;
        // ---- 1) Arcade drive: compute the raw speeds ----
        // double rawLeft = ...
        // double rawRight = ...

        double leftSpeed = Math.min(1.0, Math.max(-1.0, rawLeft));
        double rightSpeed = Math.min(1.0, Math.max(-1.0, rawRight));
        // ---- 2) Clamp both to the range -1.0 to 1.0 ----
        // Math.max(-1.0, value) gives you at least -1.0
        // Math.min(1.0, value) gives you at most 1.0
        // double leftSpeed = Math.min(1.0, Math.max(-1.0, rawLeft));
        // double rightSpeed = ...

        double leftVolts = leftSpeed * batteryVolts;
        double rightVolts = rightSpeed * batteryVolts;
        // ---- 3) Convert speeds to volts ----
        // double leftVolts = ...
        // double rightVolts = ...

        /*This block of code multiplies the expression by 1000 in order
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
        // ---- 4) Encoder to distance ----
        // Careful: encoderTicks and ticksPerRev are both int.
        // What happens to the fraction? How do you keep it?
        // double revolutions = ...
        // double distanceCm = ...

        boolean isMoving = distanceCm != 0;
        // ---- 5) Is the robot moving? ----
        // Use a comparison and a logical operator. No if statement.
        // boolean isMoving = ...
        System.out.println("=== " + name + " ===");
        System.out.println(String.format("Raw left speed: %.2f", rawLeft));
        System.out.println(String.format("Raw right speed: %.2f", rawRight));
        System.out.println(String.format("Left speed (once clamped): %.2f", leftSpeed));
        System.out.println(String.format("Right speed (once clamped): %.2f", rightSpeed));
        System.out.println(String.format("Left voltage: %.2f", leftVolts));
        System.out.println(String.format("Right voltage: %.2f", rightVolts));
        System.out.println(String.format("Revolutions: %.2f", revolutions));
        System.out.println(String.format("Distance traveled (in Centimeters): %.2f", distanceCm));
        System.out.println(String.format("The fact that the robot is moving is %b", isMoving));
        // ---- 6) Print the report ----
        // Use String.format("%.2f", value) so numbers print with two decimals.
        // System.out.println("=== " + name + " ===");
    }
}

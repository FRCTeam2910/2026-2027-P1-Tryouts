public class DriveMath {
    public static void main(String[] args) {
        // ---- Inputs. Change these to test different cases. ----
        String name = "Drivetrain";
        double forward = 0.0;
        double turn = 0.0;
        double batteryVolts = 11.8;
        int encoderTicks = 0;
        double ticksPerRev = 2048;
        double wheelCircumferenceCm = 47.88;

        // ---- 1) Arcade drive: compute the raw speeds ----
        double rawLeft = forward+turn;
        double rawRight = forward-turn;

        // ---- 2) Clamp both to the range -1.0 to 1.0 ----
        // Math.max(-1.0, value) gives you at least -1.0
        // Math.min(1.0, value) gives you at most 1.0
        double leftSpeed = Math.min(1.0, Math.max(-1.0, rawLeft));
        double rightSpeed = Math.min(1.0, Math.max(-1.0, rawRight));

        // ---- 3) Convert speeds to volts ----
        double leftVolts = batteryVolts * leftSpeed;
        double rightVolts = batteryVolts * rightSpeed;

        // ---- 4) Encoder to distance ----
        // Careful: encoderTicks and ticksPerRev are both int.
        // What happens to the fraction? How do you keep it?
        double revolutions = encoderTicks/ticksPerRev;
        double distanceCm = revolutions*wheelCircumferenceCm;

        // ---- 5) Is the robot moving? ----
        // Use a comparison and a logical operator. No if statement.
        boolean isMoving = (leftSpeed != 0) || (rightSpeed != 0);

        // ---- 6) Print the report ----
        // Use String.format("%.2f", value) so numbers print with two decimals.
        System.out.println("=== " + name + " ===");
        System.out.println(String.format("Forward: %.2f, turn: %.2f, batteryVolts: %.2f",forward,turn,batteryVolts));
        System.out.println(String.format("Left Motor Speed %.2f,  Right Motor Speed %.2f", leftSpeed, rightSpeed));
        System.out.println(String.format("Left Volts: %.2f   Right Volts: %.2f", leftVolts,rightVolts));
        System.out.println(String.format("Encoder: %d = %.2f revs = %.2f cms",encoderTicks,revolutions,distanceCm));
        System.out.println(String.format("Moving: %b",isMoving));
    }
}

public class DriveMath {
    public static void main(String[] args) {
        // ---- Inputs. Change these to test different cases. ----
        String name = "Drivetrain";
        double forward = 0.0;
        double turn = 0.0;
        double batteryVolts = 11.8;
        int encoderTicks = 0;
        int ticksPerRev = 2048;
        double wheelCircumferenceCm = 47.88;
        double rawLeft = forward + turn;
        double rawRight = forward - turn;
        double leftSpeed = Math.min(1.0, Math.max(-1.0, rawLeft));
        double rightSpeed = Math.min(1.0, Math.max(-1.0, rawRight));
        double leftVolts = leftSpeed * batteryVolts;
        double rightVolts = rightSpeed * batteryVolts;
        double revolutions = (double) encoderTicks / ticksPerRev;
        double distanceCm = revolutions * wheelCircumferenceCm;
        boolean isMoving = (leftSpeed != 0.0) || (rightSpeed != 0.0);
        System.out.println("=== " + name + " ===");
        System.out.println("Inputs: forward=" + String.format("%.2f", forward) + ", turn=" + String.format("%.2f", turn) + ", battery=" + String.format("%.2f", batteryVolts) + " V");
        System.out.println("Motor speeds: left=" + String.format("%.2f", leftSpeed) + ", right=" + String.format("%.2f", rightSpeed));
        System.out.println("Motor volts: left=" + String.format("%.2f", leftVolts) + " V, right=" + String.format("%.2f", rightVolts) + " V");
        System.out.println("Encoder: " + encoderTicks + " ticks = " + String.format("%.2f", revolutions) + " rev = " + String.format("%.2f", distanceCm) + " cm");
        System.out.println("Moving: " + isMoving);
    }
}

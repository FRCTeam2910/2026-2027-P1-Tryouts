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
        double rawLeft = turn + forward;
        double rawRight = turn + forward;


       // ---- 2) Clamp both to the range -1.0 to 1.0 ----
           double leftSpeed;
           if (rawLeft > 1.0) {
               leftSpeed = 1.0;
           } else if (rawLeft < -1.0) {
               leftSpeed = -1.0;
           } else {
               leftSpeed = rawLeft;
           }


           double rightSpeed;
           if (rawRight > 1.0) {
               rightSpeed = 1.0;
           } else if (rawRight < -1.0) {
               rightSpeed = -1.0;
           } else {
               rightSpeed = rawRight;
}


       // ---- 3) Convert speeds to volts ----
        double leftVolts = leftSpeed * batteryVolts;
        double rightVolts = rightSpeed * batteryVolts;


       // ---- 4) Encoder to distance ----
       // Careful: encoderTicks and ticksPerRev are both int.
       // What happens to the fraction? How do you keep it?
        double revolutions = encoderTicks/(double)ticksPerRev;
        double distanceCm = revolutions * wheelCircumferenceCm;


       // ---- 5) Is the robot moving? ----
       // Use a comparison and a logical operator. No if statement.
        boolean isMoving = (leftSpeed != 0.0) || (rightSpeed != 0.0);


       // ---- 6) Print the report ----
       // Use String.format("%.2f", value) so numbers print with two decimals.
       System.out.println("=== " + name + " ===");
       System.out.println("Inputs: Forward=" + String.format("%.2f", forward)
           + ", Turn=" + String.format("%.2f", turn) + " ===");
       System.out.println("Raw speeds: Left=" + String.format("%.2f", rawLeft)
           + ", Right=" + String.format("%.2f", rawRight));
       System.out.println("Clamped speeds: Left=" + String.format("%.2f", leftSpeed)
           + ", Right=" + String.format("%.2f", rightSpeed));
       System.out.println("Volts: Left=" + String.format("%.2f", leftVolts)
           + ", Right=" + String.format("%.2f", rightVolts));
       System.out.println("Distance: " + String.format("%.2f", distanceCm) + " cm");
       System.out.println("Is moving? " + isMoving);
   }
}




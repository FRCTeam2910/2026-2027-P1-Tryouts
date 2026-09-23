public class ArcadeDrive {
    public static void main(String[] args) {
        // Driver inputs. Change these and run again to test.
        double forward = 0.8;
        double turn = 0.9;

        // 1) Compute the raw motor speeds
           double leftSpeed  = forward + turn;           
           double rightSpeed = forward - turn;

        // 2) Clamp each one to the range -1.0 to 1.0.
         leftSpeed = Math.min(1.0, Math.max(-1.0, leftSpeed));
         rightSpeed = Math.min(1.0, Math.max(-1.0, rightSpeed));

        // 3) Print both, rounded to two decimals:
          System.out.println(String.format("Left: %.2f  Right: %.2f", leftSpeed, rightSpeed));
    }
}

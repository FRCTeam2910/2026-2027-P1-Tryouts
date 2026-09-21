public class ArcadeDrive {
    public static void main(String[] args) {
        // Driver inputs. Change these and run again to test.
        double forward = 0.8;
        double turn = 0.9;

        // 1) Compute the raw motor speeds
        //    leftSpeed  = forward + turn
        //    rightSpeed = forward - turn

        // 2) Clamp each one to the range -1.0 to 1.0.
        //    Math.max(-1.0, value) gives you at least -1.0
        //    Math.min(1.0, value) gives you at most 1.0
        //    Together: Math.min(1.0, Math.max(-1.0, value))

        // 3) Print both, rounded to two decimals:
        //    System.out.println(String.format("Left: %.2f  Right: %.2f", leftSpeed, rightSpeed));
    }
}

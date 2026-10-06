public class lab {
    public static void main(String[] args) {
        // Driver inputs. Change these and run again to test.
        double forward = 0.8;
        double turn = 0.9;

        double leftSpeed = forward + turn;
        double rightSpeed = forward - turn;

        if (leftSpeed > 1.0) {
            leftSpeed = 1.0;
        } else if (rightSpeed < -1.0 ){
            rightSpeed = -1.0;
        }
        //leftSpeed = Math.min(1.0, Math.max(-1.0, leftSpeed));
        //rightSpeed = Math.min(1.0, Math.max(-1.0, rightSpeed));

        // 3) Print both, rounded to two decimals:
        System.out.println(String.format("Left: %.2f  Right: %.2f", leftSpeed, rightSpeed));
    }
}

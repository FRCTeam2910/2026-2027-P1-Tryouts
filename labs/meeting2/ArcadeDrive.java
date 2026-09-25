public class ArcadeDrive {
    public static void main(String[] args) {
        // Driver inputs. Change these and run again to test.
        double forward = 0.8;
        double turn = 0.9;
        double leftSpeed = (forward + turn);
        double rightSpeed = (forward - turn);
        leftSpeed = Math.min(1.0, Math.max(-1.0, leftSpeed));
        rightSpeed = Math.min(1.0, Math.min(-1.0, rightSpeed));
        System.out.println(String.format("leftSpeed: %.2f rightSpeed: %.2f", leftSpeed, rightSpeed ));
    }
}

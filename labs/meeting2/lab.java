public class lab {
    public static void main(String[] args) {
        double leftSpeed = forward + turn;
        double rightSpeed = forward - turn;

        leftSpeed = Math.min(1.0, Math.max(-1.0, leftSpeed));
        rightSpeed = Math.min(1.0, Math.max(-1.0, rightSpeed));

        System.out.println(String.format("Left: %.2f Right: %.2f"
    }
}

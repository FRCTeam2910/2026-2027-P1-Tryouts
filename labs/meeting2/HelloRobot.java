public class HelloRobot {
    public static void main(String[] args) {
        System.out.println("Robot code is running!");
        int ticks = 1024;
        int revsPerTick = 2048;
        int result = ticks/ revsPerTick;
        System.out.println("Result:" +result);
    }
}

package students.NihaleePatel.Assignments.Assignment2;

public class RobotSim {

    public static void main(String[] args) {

        double left = 0.60;
        double right = 0.40;
        double forward = 0.80;
        double turn = 0.90;
        int steps = 5;

        runMode("tank", left, right, forward, turn, steps);
        runMode("arcade", left, right, forward, turn, steps);
        runMode("turbo", left, right, forward, turn, steps);
    }

    public static void runMode(String mode, double left, double right,
                               double forward, double turn, int steps) {

        System.out.println("=== " + mode + " ===");

        double x = 0.0;
        double y = 0.0;
        double heading = 0.0;

        double leftSpeed = 0.0;
        double rightSpeed = 0.0;

        switch (mode) {

            case "tank":
                leftSpeed = left;
                rightSpeed = right;
                break;

            case "arcade":
                leftSpeed = clampVoltage(forward + turn);
                rightSpeed = clampVoltage(forward - turn);
                break;

            default:
                leftSpeed = 0.0;
                rightSpeed = 0.0;
                System.out.println("Unknown mode, motors stopped");
                break;
        }

        for (int step = 1; step <= steps; step++) {

            double battery;

            try {
                battery = readBattery(step);
            } catch (ArithmeticException e) {
                System.out.println(e);
                continue;
            }

            if (battery < 11.0) {
                System.out.printf(
                    "Battery critical at %.2f V, stopping%n",
                    battery
                );
                break;
            }

            x = x + leftSpeed;
            y = y + rightSpeed;

            double turnRate = (rightSpeed - leftSpeed) * 45.0;
            heading = wrapHeading(heading + turnRate);

            System.out.printf(
                "Step %d: x=%.2f y=%.2f heading=%.2f battery=%.2f V%n",
                step, x, y, heading, battery
            );
        }

        System.out.printf(
            "Final: x=%.2f y=%.2f heading=%.2f%n",
            x, y, heading
        );

        System.out.println();
    }

    public static double clampVoltage(double volts) {

        if (volts > 1.0) {
            return 1.0;
        }

        if (volts < -1.0) {
            return -1.0;
        }

        return volts;
    }

    public static double wrapHeading(double angle) {

        angle = angle % 360.0;

        if (angle < 0) {
            angle = angle + 360.0;
        }

        return angle;
    }

    public static double readBattery(int step) {

        if (step == 3) {
            int number = 1 / 0;
        }

        if (step == 1) {
            return 12.20;
        }

        if (step == 2) {
            return 11.80;
        }

        if (step == 4) {
            return 11.00;
        }

        return 10.60;
    }
}
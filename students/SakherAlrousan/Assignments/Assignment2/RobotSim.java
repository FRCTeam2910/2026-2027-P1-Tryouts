public class RobotSim {

    public static void main(String[] args) {
        runMode("tank");
        runMode("arcade");
        runMode("turbo");
    }

    public static double readBattery(int step) {
        if (step == 1) {
            return 12.20;
        } else if (step == 2) {
            return 11.80;
        } else if (step == 3) {
            int zero = 0;
            return 10 / zero;
        } else if (step == 4) {
            return 11.00;
        } else {
            return 10.60;
        }
    }

    public static void runMode(String mode) {

        System.out.println("=== " + mode + " ===");

        double x = 0.0;
        double y = 0.0;
        double heading = 0.0;

        double leftSpeed = 0.0;
        double rightSpeed = 0.0;

        switch (mode) {

            case "tank":
                leftSpeed = 0.60;
                rightSpeed = 0.40;
                break;

            case "arcade":
                double forward = 0.80;
                double turn = 0.90;

                leftSpeed = forward + turn;
                rightSpeed = forward - turn;

                leftSpeed = clampVoltage(leftSpeed);
                rightSpeed = clampVoltage(rightSpeed);

    break;

            default:
                leftSpeed = 0.0;
                rightSpeed = 0.0;
                System.out.println("Unknown mode, motors stopped");
        }

        for (int step = 1; step <= 5; step++) {

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

            x += leftSpeed;
            y += rightSpeed;

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
        angle = angle % 360;

        if (angle < 0) {
            angle += 360;
        }

        return angle;
    }
}
public class RobotSim {
    private static final double TURN_PER_STEP = 45.0;

    public static void main(String[] args) {
        runMode("tank");
        runMode("arcade");
        runMode("turbo");
    }

    public static void runMode(String modeName) {
        double x = 0.0;
        double y = 0.0;
        double heading = 0.0;
        double leftSpeed = 0.0;
        double rightSpeed = 0.0;

        System.out.println("=== " + modeName + " ===");

        switch (modeName) {
            case "tank":
                leftSpeed = 0.60;
                rightSpeed = 0.40;
                break;
            case "arcade":
                leftSpeed = clampVoltage(0.45 + 0.55);
                rightSpeed = clampVoltage(0.45 - 0.55);
                break;
            default:
                System.out.println("Unknown mode, motors stopped");
                leftSpeed = 0.0;
                rightSpeed = 0.0;
                break;
        }

        for (int step = 1; step <= 5; step++) {
            double battery;
            try {
                battery = readBattery(step);
            } catch (Exception e) {
                System.out.println(e);
                continue;
            }

            if (battery < 11.0) {
                System.out.printf("Battery critical at %.2f V, stopping%n", battery);
                break;
            }

            x += leftSpeed;
            y += rightSpeed;

            double turnRate = (rightSpeed - leftSpeed) * TURN_PER_STEP;
            heading = wrapHeading(heading + turnRate);

            System.out.printf("Step %d: x=%.2f y=%.2f heading=%.2f battery=%.2f V%n",
                    step, x, y, heading, battery);
        }

        System.out.printf("Final: x=%.2f y=%.2f heading=%.2f%n", x, y, heading);
        System.out.println();
    }

    public static double clampVoltage(double volts) {
        if (volts < -1.0) {
            return -1.0;
        }
        if (volts > 1.0) {
            return 1.0;
        }
        return volts;
    }

    public static double wrapHeading(double angle) {
        double wrapped = angle % 360.0;
        if (wrapped < 0.0) {
            wrapped += 360.0;
        }
        return wrapped;
    }

    private static double readBattery(int step) {
        switch (step) {
            case 1:
                return 12.20;
            case 2:
                return 11.80;
            case 3:
                int zero = 0;
                int broken = 10 / zero;
                return broken;
            case 4:
                return 11.00;
            case 5:
                return 10.60;
            default:
                return 0.0;
        }
    }
}

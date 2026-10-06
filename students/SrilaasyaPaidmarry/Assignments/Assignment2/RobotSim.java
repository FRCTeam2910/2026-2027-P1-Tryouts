public class RobotSim {

    public static void main(String[] args) {
        System.out.println("=== tank ===");
        runMode("tank");
        System.out.println();

        System.out.println("=== arcade ===");
        runMode("arcade");
        System.out.println();

        System.out.println("=== turbo ===");
        runMode("turbo");
    }

    public static void runMode(String mode) {
        double x = 0.0;
        double y = 0.0;
        double heading = 0.0;
        double forward = 0.0;
        double turn = 0.0;
        double leftSpeed = 0.0;
        double rightSpeed = 0.0;

        switch (mode) {
            case "tank":
                leftSpeed = 0.6;
                rightSpeed = 0.4;
                break;
            case "arcade":
                forward = 0.45;
                turn = 0.55;
                leftSpeed = forward + turn;
                rightSpeed = forward - turn;
                break;
            default:
                System.out.println("Unknown mode, motors stopped");
                leftSpeed = 0.0;
                rightSpeed = 0.0;
                break;
        }

        leftSpeed = clampVoltage(leftSpeed);
        rightSpeed = clampVoltage(rightSpeed);

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
            double turnRate = (rightSpeed - leftSpeed) * 45.0;
            heading = wrapHeading(heading + turnRate);

            // Step print must be inside the loop
            System.out.printf("Step %d: x=%.2f y=%.2f heading=%.2f battery=%.2f V%n",
                              step, x, y, heading, battery);
        }

        // Final print sits outside the loop after all steps complete
        System.out.printf("Final: x=%.2f y=%.2f heading=%.2f%n", x, y, heading);
    }

    public static double clampVoltage(double volts) {
        if (volts > 1.0) {
            return 1.0;
        } else if (volts < -1.0) {
            return -1.0;
        }
        return volts;
    }

    public static double wrapHeading(double angle) {
        angle = angle % 360.0;
        if (angle < 0) {
            angle += 360.0;
        }
        return angle;
    }

    public static double readBattery(int step) {
        if (step == 1) return 12.20;
        if (step == 2) return 11.80;
        if (step == 3) throw new ArithmeticException("/ by zero");
        if (step == 4) return 11.00;
        return 10.60;
    }
}
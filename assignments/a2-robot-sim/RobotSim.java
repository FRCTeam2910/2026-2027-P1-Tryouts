public class RobotSim {
    public static void main(String[] args) {
        // ---- Inputs. Change these to test your program. ----
        double left = 0.6;      // tank mode only
        double right = 0.4;     // tank mode only
        double forward = 0.8;   // arcade mode only
        double turn = 0.9;      // arcade mode only
        int steps = 5;

        // Run the simulation three times, once per mode, by calling your
        // own runMode method three times. The third mode is one your code
        // does not recognise, and that is deliberate.
        //
        runMode("tank", left, right, forward, turn, steps);
        runMode("arcade", left, right, forward, turn, steps);
        runMode("turbo",  left, right, forward, turn, steps);
    }

    // Method 1: Runs the whole simulation for one mode and prints what happens.
    // It does not hand a value back, so its return type is void.
    static void runMode(String mode, double left, double right,
                        double forward, double turn, int steps) {
            // 1) Print the header line, for example:  === tank ===
            System.out.println("===" + mode + "===");
            
            // 2) Set up this mode's state
            double x = 0.0, y = 0.0, heading = 0.0;
            double leftSpeed = 0.0, rightSpeed = 0.0;
            
            // 3) Work out the motor speeds with a switch on mode:
            //      tank    -> leftSpeed = left,           rightSpeed = right
            //      arcade  -> leftSpeed = forward + turn, rightSpeed = forward - turn
            //      default -> print "Unknown mode, motors stopped" and set both to 0.0
            //    Pass each speed through clampVoltage(...) before you use it.
            //    Do not forget break on every case.
            switch (mode) {
                case "tank":
                    leftSpeed = clampVoltage(left);
                    rightSpeed = clampVoltage(right);
                    break;
                case "arcade":
                    leftSpeed = clampVoltage(forward + turn);
                    rightSpeed = clampVoltage(forward - turn);
                    break;
                default:
                    System.out.println("Unknown mode, motors stopped");
                    leftSpeed = 0.0;
                    rightSpeed = 0.0;
                    break;
            }

            // 4) Loop the steps with a for loop, from 1 up to and including steps
            for (int i = 1; i <= steps; i++) {
                double batteryVoltage = 0.0;

                // Read the battery inside a try-catch block
                try {
                    batteryVoltage = readBatteryVoltage(i);
                } catch (ArithmeticException e) {
                    System.out.println(e);
                    continue; 
                }

                // If the battery drops below 11.0 volts, stop that mode early
                if (batteryVoltage < 11.0) {
                    System.out.printf("Battery critical at %.2f V, stopping\n", batteryVoltage);
                    break; // Stop this mode early
                }

                // Update position state
                x += leftSpeed;
                y += rightSpeed;
                double turnRate = (rightSpeed - leftSpeed) * 45.0;
                heading = wrapHeading(heading + turnRate);

                // Print step log with two decimals
                System.out.printf("Step %d: x=%.2f y=%.2f heading=%.2f battery=%.2f V\n", 
                                  i, x, y, heading, batteryVoltage);
            }

            // Final summary print statement
            System.out.printf("Final: x=%.2f y=%.2f heading=%.2f\n", x, y, heading);
    }

    // Method 2: Clamps speed values safely between -1.0 and 1.0
    static double clampVoltage(double volts) {
        if (volts > 1.0) {
            return 1.0;
        } else if (volts < -1.0) {
            return -1.0;
        }
        return volts;
    }

    // Method 3: Wraps angles to cleanly fall between 0 and 359 degrees
    static double wrapHeading(double angle) {
        double wrapped = angle % 360;
        if (wrapped < 0) {
            wrapped += 360;
        }
        return wrapped;
    }

    static double readBatteryVoltage(int step) {
        if (step == 3) {
            int zero = 0;
            return step / zero;
        }
        return 12.6 - (step * 0.4);
    }
}

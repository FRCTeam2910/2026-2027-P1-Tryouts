public class RobotSim {
    public static void main(String[] args) {
        // ---- Inputs. Change these to test your program. ----
        double left = 0.6;      // tank mode only
        double right = 0.4;     // tank mode only
        double forward = 0.8;   // arcade mode only
        double turn = 0.9;      // arcade mode only
        int steps = 5;
        runMode("tank", left, right, forward, turn, steps);
        runMode("arcade", left, right, forward, turn, steps);
        runMode("turbo", left, right, forward, turn, steps);

        // Run the simulation three times, once per mode, by calling your
        // own runMode method three times. The third mode is one your code
        // does not recognise, and that is deliberate.
        //
        //   runMode("tank",   left, right, forward, turn, steps);
        //   runMode("arcade", left, right, forward, turn, steps);
        //   runMode("turbo",  left, right, forward, turn, steps);
    }


    // Method 1: Runs the whole simulation for one mode and prints what happens.
    // It does not hand a value back, so its return type is void.
    static void runMode(String mode, double left, double right, double forward, double turn, int steps) {
        System.out.println("=== " + mode + " ===");

        double x = 0.0, y = 0.0, heading = 0.0;
        double leftSpeed = 0.0, rightSpeed = 0.0;


        switch (mode) {
            case "tank":
                leftSpeed = left;
                rightSpeed = right;
                break;
            case "arcade":
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

        for (int i = 1; i <= steps; i++) {

            try {
                if (readBatteryVoltage(i) < 11.0) {
                    String error = String.format("Battery critical at %.2f V, stopping", readBatteryVoltage(i));
                    throw new LowBatteryVoltageException(error);
                }    
            } catch (ArithmeticException e) {
                System.out.println("java.lang.ArithmeticException: " + e.getMessage());
                continue;
            } catch (LowBatteryVoltageException eb) {
                System.out.println(eb.getMessage());
                break;
            }

            x += leftSpeed;
            y += rightSpeed;
            double turnRate = (rightSpeed - leftSpeed) * 45.0;
            heading = wrapHeading(heading + turnRate);
            System.out.println(String.format("Step %d: x=%.2f y=%.2f heading=%.2f battery=%.2f V", i, x, y, heading, readBatteryVoltage(i)));
        }
        System.out.println(String.format("Final: x=%.2f y=%.2f heading=%.2f", x, y, heading));
        System.out.println("");
    }


    // Method 2: Returns volts limited to the range -1.0 to 1.0.
    // Write this with if statements, the way you did in the lab.
    static double clampVoltage(double volts) {
        if (volts < -1.0) {
            return -1.0;
        }
        else if (volts > 1.0) {
            return 1.0;
        }
        else {
            return volts;
        }
    }


    // Method 3: Returns the angle wrapped into the range 0 to 359.
    // 400 becomes 40,  -20 becomes 340,  360 becomes 0
    // The % operator and one if is all you need.
    static double wrapHeading(double angle) {
        //This line of code wraps it (the plus zero part makes sure that it doesn't print out -0 if the input was -360):
        double newAngle = angle % 360 + 0;

        //This if statement makes sure that there is no negative number remaining:
        if (newAngle < 0) {
            newAngle += 360;
        }
        return newAngle;
    }

    
    // Method 4: Already written for you. Do not change it.
    // It fails on purpose when step is 3.
    static double readBatteryVoltage(int step) {
        if (step == 3) {
            int zero = 0;
            return step / zero;
        }
        return 12.6 - (step * 0.4);
    }
}
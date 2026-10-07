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
        //   runMode("tank",   left, right, forward, turn, steps);
        //   runMode("arcade", left, right, forward, turn, steps);
        //   runMode("turbo",  left, right, forward, turn, steps);
        runMode("tank",left, right, forward, turn, steps);
        runMode("arcade",left, right, forward, turn, steps);
        runMode("turbo",  left, right, forward, turn, steps);
    }
 
    
    // Method 1: Runs the whole simulation for one mode and prints what happens.
    // It does not hand a value back, so its return type is void.
    static void runMode(String mode, double left, double right,
                        double forward, double turn, int steps) {

            // 1) Print the header line, for example:  === tank ===
            System.out.println("  ==="+ mode +"===  ");
            // 2) Set up this mode's state
            //    double x = 0.0, y = 0.0, heading = 0.0;
            //    double leftSpeed = 0.0, rightSpeed = 0.0;
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
                    leftSpeed = left;
                    leftSpeed = clampVoltage(leftSpeed);
                    rightSpeed = right;
                    rightSpeed = clampVoltage(rightSpeed);
                    break;
                case "arcade":
                    leftSpeed = forward + turn;
                    leftSpeed = clampVoltage(leftSpeed);
                    rightSpeed = forward - turn;
                    rightSpeed = clampVoltage(rightSpeed);
                    break;
                case "turbo":            
                default:
                    System.out.println("Unknown mode, motors stopped");
                    leftSpeed = 0.0;
                    leftSpeed = clampVoltage(leftSpeed);
                    rightSpeed = 0.0;
                    rightSpeed = clampVoltage(rightSpeed);
                    break;
            }
            
            // 4) Loop the steps with a for loop, from 1 up to and including steps
            for(int step=1;step<=steps;step++){
                // 4a) Read the battery inside a try.
                //     If it throws, print the exception object and skip
                //     the rest of this step with continue.
                double battery =0;
                try {
                    battery = readBatteryVoltage(step);
                } catch (Exception e) {
                    System.out.println(e);
                    continue;
                }
                // 4b) If the battery is below 11.0 volts, print
                //     "Battery critical at X V, stopping" and leave the
                //     loop early with break.
                if(battery < 11.0){
                    System.out.println("Battery critical at "+ String.format("%.2f", battery) +" V, stopping");
                    break;
                }
                // 4c) Move the robot:
                //       x increases by leftSpeed
                //       y increases by rightSpeed
                //       turnRate = (rightSpeed - leftSpeed) * 45.0
                //       heading = wrapHeading(heading + turnRate)
                x = x + leftSpeed;
                y = y + rightSpeed;
                double turnRate = (rightSpeed - leftSpeed) * 45.0;
                heading = wrapHeading(heading + turnRate);
                // 4d) Print the step. Use String.format so the numbers
                //     line up with the expected output in the README:
                //     "Step %d: x=%.2f y=%.2f heading=%.2f battery=%.2f V"
                /*
                === tank ===
                    Step 1: x=0.60 y=0.40 heading=351.00 battery=12.20 V
                    Step 2: x=1.20 y=0.80 heading=342.00 battery=11.80 V
                    java.lang.ArithmeticException: / by zero
                    Step 4: x=1.80 y=1.20 heading=333.00 battery=11.00 V
                    Battery critical at 10.60 V, stopping
                    Final: x=1.80 y=1.20 heading=333.00
                */
                System.out.println("Step "+ String.format("%d", step) + ": x=" + String.format("%.2f", x) + " y=" + String.format("%.2f", y) + " heading=" +  String.format("%.2f", heading) + " battery=" + String.format("%.2f", battery) + " V");
            }

            // 5) After the step loop, print this mode's final state:
            //    "Final: x=%.2f y=%.2f heading=%.2f"
            //    then print one blank line
            System.out.println("Final: x=" + String.format("%.2f", x) + " y=" + String.format("%.2f", y) + " heading=" +  String.format("%.2f", heading));
System.out.println();
    }
/*
System.out.println("Final: x=" + String.format("%.2f", x) + " y=" + String.format("%.2f", y) + " heading=" +  String.format("%.2f", heading) + " battery=" + String.format("%.2f", battery) + " V");
System.out.println()
*/
    // Method 2: Returns volts limited to the range -1.0 to 1.0.
    // Write this with if statements, the way you did in the lab.
    static double clampVoltage(double volts) {
        double voltage = Math.min(1.0, Math.max(-1.0, volts));
        return voltage;
    }

    // Method 3: Returns the angle wrapped into the range 0 to 359.
    // 400 becomes 40,  -20 becomes 340,  360 becomes 0
    // The % operator and one if is all you need.
    static double wrapHeading(double angle) {
        double clamped_angle = angle;
        if(angle>= 360){
            clamped_angle = angle - 360;
        }
        if(angle < 0){
            clamped_angle = angle + 360;
        }
        return clamped_angle;
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
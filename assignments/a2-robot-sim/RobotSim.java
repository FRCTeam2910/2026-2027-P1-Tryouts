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
        // runMode("tank",   left, right, forward, turn, steps);
        // runMode("arcade", left, right, forward, turn, steps);
        // runMode("turbo",  left, right, forward, turn, steps);
        runMode("tank", left, right, forward, turn, steps);
        runMode("arcade", left, right, forward, turn, steps);
        runMode("turbo", left, right, forward, turn, steps);
    
    
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
            switch(mode) {
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
            for (int i=1; i<= steps; i++){
                double battery = 0.0;

                // 4a) Read the battery inside a try.
                //     If it throws, print the exception object and skip
                //  []   the rest of this step with continue.
                    try {
                        battery =readBatteryVoltage(i);
                    } catch (ArithmeticException e){
                        System.out.println(e);
                        continue;
                    }
                // 4b) If the battery is below 11.0 volts, print
                //     "Battery critical at X V, stopping" and leave the
                //     loop early with break.
                if (battery < 11.0){
                System.out.println("Battery critical at X V, stopping");
                break; 
                }
                // 4c) Move the robot:
                //       x increases by leftSpeed
                //       y increases by rightSpeed
                //turnRate = (rightSpeed - leftSpeed) * 45.0
                //heading = wrapHeading(heading + turnRate)
                x += leftSpeed;
                y += rightSpeed;
                double turnRate= (rightSpeed - leftSpeed)*45.0;
                heading= wrapHeading(heading + turnRate);

                // 4d) Print the step. Use String.format so the numbers
                //     line up with the expected output in the README:
                //     "Step %d: x=%.2f y=%.2f heading=%.2f battery=%.2f V"
                System.out.println(String.format("Step %d: x=%.2f y=%.2f heading=%.2f battery=%.2f V",i,x,y,heading,battery));


            }

            // 5) After the step loop, print this mode's final state:
            //    "Final: x=%.2f y=%.2f heading=%.2f"
            //    then print one blank line
            System.out.println(String.format("Final: x=%.2f y=%.2f heading=%.2f",x,y,heading));
            System.out.println("");
    }

    // Method 2: Returns volts limited to the range -1.0 to 1.0.
    // Write this with if statements, the way you did in the lab.
    static double clampVoltage(double volts) {
        if (volts>1.0){
            return 1.0;
        }else if (volts<1.0){
            return -1.0;
        }
        return volts;

    
    }

    // Method 3: Returns the angle wrapped into the range 0 to 359.
    // 400 becomes 40,  -20 becomes 340,  360 becomes 0
    // The % operator and one if is all you need.
    static double wrapHeading(double angle) {
        double wrapped = angle % 360;
        if (wrapped<1){
            wrapped += 360;
        }
        return wrapped;
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
public class Drivetrain {
    public static void main(String[] args) {
        // Your robot needs to report its state. Store one piece of state
        // in each of the five types, then print a readable status line.
        runMode("tank"); 
        runMode("arcade");
        runMode("turbo");

        String subsystem = "Drivetrain";
        // 1) Declare a String for the subsystem name, for example "Drivetrain"
        int encodercount = 1024;
        // 2) Declare an int for the encoder count, for example 1024
        double voltage = 12.4;
        // 3) Declare a double for the battery voltage, for example 12.4
        double joystickAxis = 0.75;
        // 4) Declare a double for the joystick axis, between -1.0 and 1.0


        // 5) Declare a boolean for whether the limit switch is pressed
        boolean LimitSwitch = true;
        // 6) Print one line that uses all five variables.
        //    Use + to join text and values:
        //    System.out.println("Subsystem: " + subsystem);
        System.out.println("Subsystem: " + subsystem);
        System.out.println("encodercount: " + encodercount);
        System.out.println("Voltage: " + voltage);
        System.out.println("Joystick: " + joystickAxis);
        System.out.println("Limit Switch: "  + LimitSwitch);
        // 7) Print the battery voltage again, rounded to two decimals:
        //    System.out.println(String.format("Battery: %.2f V", batteryVolts));
        System.out.println(String.format("Battery: %.2f V", voltage));

       
        }

    public static void runMode(String mode) {
        System.out.println("== "+ mode + " ==");
        double leftSpeed = 0.0;
        double rightSpeed = 0.0;
        
    switch (mode) {
        case "tank":
            leftSpeed = 0.6;
            rightSpeed = 0.4;
            break;
        case "arcade":
            double forward = 1.2;
            double turn = 0.55;
            leftSpeed = clampVoltage(forward + turn);
            rightSpeed = clampVoltage(forward - turn);
            break;
        case "turbo":
            System.out.println("Unkown mode, motors stopped");
            leftSpeed = 0.0;
            rightSpeed = 0.0;
            break;
        
    }       double x = 0.0;
            double y = 0.0;
            double heading = 0.0;
            double battery = 0.0;
    

        for (int step = 1; step <=5; step++) {
            try{
                battery = readBattery(step);
            } catch (Exception e){
                    System.out.println(e);
                    continue;
                }
                if (battery < 11.0) {
                    System.out.println(String.format("Battery critcal at %.2f V, stoping", battery));
                    break;
                }
                x += leftSpeed;
                y += rightSpeed;

                double turnRate = (rightSpeed - leftSpeed) *45.0;
                heading = wrapHeading(heading + turnRate);
                System.out.println(String.format("Step %d: x=%.2f y=%.2f heading=%.2f battery=%.2f V", step, x, y, heading, battery));
            }
            System.out.println(String.format("Final: x=%.2f y=%.2f heading=%.2f battery=%.2f V", x, y, heading, battery));

            
        }

        public static double clampVoltage (double volts) {
            if (volts > 1.0) {
                return 1.0;
            } else if (volts < -1.0){
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
        public static double readBattery(int step) throws ArithmeticException {
            if (step ==3) {
                throw new ArithmeticException("/ by zero");

            }
            return 12.6 - (step * 0.4);
        }



    }

        

            

    


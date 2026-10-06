public class A2assignment {
    public static void main(String[] args) {      
        int steps = 5;

        
        runMode("tank",    0.6, 0.4, 0.0, 0.0, steps);
        runMode("arcade", 0.0, 0.0, 0.8, 0.9, steps);
        runMode("turbo",  0.0, 0.0, 0.0, 0.0, steps);
    }

    static void runMode(String mode, double left, double right,
                        double forward, double turn, int steps) {

            System.out.println("=== " + mode + " ===");

            double x = 0.0, y = 0.0, heading = 0.0;
            double leftSpeed = 0.0, rightSpeed = 0.0;

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
            for (int i = 1; i <= steps; i++){
                double batteryVoltage = 0.0;
                try {
                    batteryVoltage = readBatteryVoltage(i);                 
                    if (batteryVoltage < 11.0) {
                        System.out.println(String.format("Battery critical at X V, stopping"));
                        break;
                    }
                    
                    x = x + leftSpeed;
                    y = y + rightSpeed;
                    double turnrate = (rightSpeed - leftSpeed) * 45.0;
                    heading = wrapHeading(heading + turnrate);
                    System.out.println();
                } catch (ArithmeticException e) {
                    System.out.println("ERROR");
                }
                System.out.println(String.format("Step %d: x=%.2f y=%.2f heading=%.2f battery=%.2f V", 
                                               i, x, y, heading, batteryVoltage));
            }
            System.out.println(String.format("Final: x=%.2f y=%.2f heading=%.2f",x,y,heading));
            System.out.println(" ");

    }

    static double clampVoltage(double volts) {
        if (volts > -1.0 && volts < 1.0) {
            return volts;
        } else {
            return 0.0;
        }
    
    }

    static double wrapHeading(double angle) {
        if (angle > 359) {
            return angle - 360;
        } else if (angle < 0) {
            return angle + 360;
        } else {
            return 0.0;
        }

    }

    static double readBatteryVoltage(int step) {
        if (step == 3) {
            int zero = 0;
            return step / zero;
        }
        return 12.6 - (step * 0.4);
    }
}

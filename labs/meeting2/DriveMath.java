public class DriveMath {
    public static void main(String[] args) {
        // Driver inputs. Change these and run again to test.
        double forward = 0.8;
        double turn = 0.9;
        double batteryVolts = 12.4;
        int encoderTicks = 1024;
        int ticksPerRev = 2048;
        double wheelCircumferenceCm = 47.88;

        // 1) Compute the raw motor speeds
        //    leftSpeed  = forward + turn
        //    rightSpeed = forward - turn
    
        double leftSpeedRaw = forward + turn;
        double rightSpeedRaw = forward - turn;

        // 2) Clamp each one to the range -1.0 to 1.0.
        //    Math.max(-1.0, value) gives you at least -1.0
        //    Math.min(1.0, value) gives you at most 1.0
        //    Together: Math.min(1.0, Math.max(-1.0, value))
        
        if(leftSpeedRaw>=1){
            leftSpeedRaw = 1.0;

        }
        else if (leftSpeedRaw<=1){
            leftSpeedRaw = -1.0;
        }

        if(rightSpeedRaw>=1){
            rightSpeedRaw=1.0;
        }

        if(rightSpeedRaw<=1){
            rightSpeedRaw=-1.0;
        }
           

        
        double leftVolts = leftSpeedRaw * batteryVolts;
        double rightVolts = rightSpeedRaw * batteryVolts;

        double revolutions = encoderTicks / (double) ticksPerRev;
        double distanceCm = revolutions * wheelCircumferenceCm;

        boolean isMoving = (leftSpeedRaw != 0.0) || (rightSpeedRaw != 0.0);



        // 3) Print both, rounded to two decimals:
        System.out.println(String.format("Inputs: forward = %.2f, turn= %.2f, battery = %.2fV", forward, turn, batteryVolts));
        System.out.println(String.format("Motor Speeds: Left: %.2f  Right: %.2f", leftSpeedRaw, rightSpeedRaw));
        System.out.println(String.format("Motor volts: Left: %.2f  Right: %.2f", leftVolts, rightVolts));
        System.out.println(String.format("Encoder: %d ticks = %.2f rev = %.2f cm", encoderTicks, revolutions, distanceCm));
        System.out.println(String.format("Moving: " +isMoving));

        
    }
}


public class RobotClass {
    double batteryVoltage;
    double forwardMovement;
    double turnMovement;
    double robotHeading;

    public RobotClass (double a, double b, double c, double d){
        this.batteryVoltage = a;
        this.forwardMovement = b;
        this.turnMovement=c;
        this.robotHeading =d; 
    }

    public void findLeft(double leftSpeed){
        System.out.println("The speed left is:"+(forwardMovement+turnMovement));
    }
    public void findRight(double rightSpeed){
        System.out.println("The speed right is:"+(forwardMovement-turnMovement));        
    }
        findLeft.println();   
        findRight.println();
}

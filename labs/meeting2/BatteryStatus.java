public class BatteryStatus{
    public static void main(String[] args){
        double batteryVoltage = 11.5;

        if (batteryVoltage < 11.0) {
            System.out.println("Battery voltage is low!");}
        else if (batteryVoltage >= 11.0 && batteryVoltage < 12.6){
            System.out.println("Battery voltage is fine!");
        else if (batteryVoltage >= 11.0 && batteryVoltage < 12.6){
            System.out.println("Battery voltage is fine!"
        }
        else{
            System.out.println("Fault");
        }
        
    }
}
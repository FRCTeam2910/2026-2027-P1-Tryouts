public class BatteryStatus {
    public static void main(String[] args) {
        double batteryVoltage = 11.5;
        String status = "Enabled";

        if (status == "Enabled" && batteryVoltage < 11.0) {
            System.out.println("Battery voltage is low!");
        }
        else if (batteryVoltage >= 11.0 && batteryVoltage < 12.6) {
            System.out.println("Battery voltage is fine!");
        }
        else if (batteryVoltage >= 12.6 && batteryVoltage < 14.0) {
            System.out.println("Battery voltage is high!");
        }
        else {
            System.out.println("Fault!");
        }


        // print odd numbers from 1 to 100
        int num = 1;
        while (num <=100) {
            if (num % 2 != 0) {
                System.out.println(num);
            }
            num++;
        }

        for (int i = 2; i <= 100; i++) {
            System.out.println(i);
        }  
    }
}
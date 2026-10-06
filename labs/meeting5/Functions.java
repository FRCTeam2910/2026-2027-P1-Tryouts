public class Functions{
    public static void main(String[] args){
        printResult(5, sumOfFirstNNumbers(5));
        printResult(10, sumOfFirstNNumbers(10));
        printResult(100, sumOfFirstNNumbers(100));        
    }

    public static int sumOfFirstNNumbers(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result = result + i;
        }
        return result;
    }

    public static void printResult(int n, int result) {
        System.out.println("Sum of first " + n + " numbers is: " + result);
    }
}
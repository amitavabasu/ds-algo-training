package training.progression;

public class FindMissingNumber {

    static int n = 10;
    static int numberOfDigits = 3;
    static int base = 10;
    static int digitSum1 = ((n/2) * (2*0 + (n-1)*1)) * (int)Math.pow(base, (numberOfDigits-1));
    static int digitSum2 = ((n/2) * (2*0 + (n-1)*1)) * (int)Math.pow(base, (numberOfDigits-1));
    static int digitSum3 = ((n/2) * (2*0 + (n-1)*1)) * (int)Math.pow(base, (numberOfDigits-1));


    public static void main(String[] args) {
        for (int number = 0; number < 1000; number++) {
            if (number == 7) continue;
            int digit1 = number % 10;
            int digit2 = (number /10) % 10;
            int digit3 = (number /100) % 10;
            System.out.println("number=" + number +" digit3=" + digit3 + " digit2=" + digit2 + " digit1=" + digit1);
            digitSum1 = digitSum1 - digit1;
            digitSum2 = digitSum2 - digit2;
            digitSum3 = digitSum3 - digit3;
            System.out.println(" digitSum3=" + digitSum3 + " digitSum2=" + digitSum2 + " digitSum1=" + digitSum1);
        }
        int missingNumber = digitSum3 * 100 + digitSum2 * 10 + digitSum1;
        System.out.println("missing number=" + missingNumber +" digit3=" + digitSum3 + " digit2=" + digitSum2 + " digit1=" + digitSum1);
    }








}

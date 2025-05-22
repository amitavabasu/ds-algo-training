package training.progression;

public class FindMissingIP {

    static String missingIp = "0.1.2.3";
    static int numberOfDigits = 4;
    static int baseForEachDigit = 256;//<-- also can be termed as number of sequence available, i.e. each digit can have value from 0 to 255

    /*  Formulas:

        Sum of an arithmetic progression
        Sum = (n/2) * [2*a + (n-1)*d]
        where ...
        n = number of elements
        a = value of start element/ beginning of the sequence
        d = common difference between consecutive terms

        Sum of the sums of entire sequence
        SumOfSums = Sum * [baseForEachDigit ^ (numberOfDigits-1)]

     */

    static int n = baseForEachDigit; //Because total elements in the sequence is same as base, which is 256 here (from 0 to 255).
    static int a = 0; //<-- starting element is 0
    static int d = 1; //<-- common difference is 1

    static double sumOfAllDigits = ((n/2) * (2*a + (n-1)*d));
    static double sumOfSumsOfAllDigitsInTheEntireSequence = (sumOfAllDigits) * (int)Math.pow(baseForEachDigit, (numberOfDigits-1));

    static double digitSum1 = sumOfSumsOfAllDigitsInTheEntireSequence;
    static double digitSum2 = sumOfSumsOfAllDigitsInTheEntireSequence;
    static double digitSum3 = sumOfSumsOfAllDigitsInTheEntireSequence;
    static double digitSum4 = sumOfSumsOfAllDigitsInTheEntireSequence;


    public static void main(String[] args) {
        String[] digits = missingIp.split("\\.");
        int ipDigit1 = Integer.parseInt(digits[3]);
        int ipDigit2 = Integer.parseInt(digits[2]);
        int ipDigit3 = Integer.parseInt(digits[1]);
        int ipDigit4 = Integer.parseInt(digits[0]);

        for (int digit4 = 0; digit4 < 256; digit4++) {
            System.out.println("MSB: " + digit4);
            for (int digit3 = 0; digit3 < 256; digit3++) {
                for (int digit2 = 0; digit2 < 256; digit2++) {
                    for (int digit1 = 0; digit1 < 256; digit1++) {
                        if (digit4 == ipDigit4 && digit3 == ipDigit3 && digit2 == ipDigit2 && digit1 == ipDigit1) continue;
//                        System.out.println(digit4 +"." + digit3 + "." + digit2 + "." + digit1);
                        digitSum1 = digitSum1 - digit1;
                        digitSum2 = digitSum2 - digit2;
                        digitSum3 = digitSum3 - digit3;
                        digitSum4 = digitSum4 - digit4;
//                        System.out.println("digitSum4=" + digitSum4 +" digitSum3=" + digitSum3 + " digitSum2=" + digitSum2 + " digitSum1=" + digitSum1);
                    }
                }
            }
        }
        System.out.println("missing IP=" + (int)digitSum4 +"." + (int)digitSum3 + "." + (int)digitSum2 + "." + (int)digitSum1);
    }
}

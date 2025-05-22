package training.progression;

/*
Find the only missing number in all numbers between 0 & 999.
You have a list of all numbers except one stored in a file between 0 an 999.
They are not sorted in the file. Trace the entire file and find the missing number
 */

public class FindMissingNumber {

    static int missingNumber = 111; //<-- This is the missing number
    static int numberOfDigits = 3; //<-- how many digit the number has
    static int baseForEachDigit = 10;//<-- also can be termed as number of sequence available

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

    static int n = baseForEachDigit; //Because total elements in the sequence is same as base, which is 10 here.
    static int a = 0; //<-- starting element is 0
    static int d = 1; //<-- common difference is 1

    static int sumOfAllDigits = ((n/2) * (2*a + (n-1)*d));
    static int sumOfSumsOfAllDigitsInTheEntireSequence = (sumOfAllDigits) * (int)Math.pow(baseForEachDigit, (numberOfDigits-1));

    static int digitSum1 = sumOfSumsOfAllDigitsInTheEntireSequence;
    static int digitSum2 = sumOfSumsOfAllDigitsInTheEntireSequence;
    static int digitSum3 = sumOfSumsOfAllDigitsInTheEntireSequence;

    public static void main(String[] args) {
        for (int number = 0; number < 1000; number++) {//Generate the numbers or read from the file - This means 000 to 999 <-- 3digit number, skipp the missing one
            if (number == missingNumber) continue;//<-- for the missing number skipp, so that we can find the number
            int digit1 = number % 10; //<-- Extract least significant digit of the current element
            int digit2 = (number /10) % 10; //<-- Extract middle digit of the current element
            int digit3 = (number /100) % 10; //<-- Extract most significant digit of the current element
//            System.out.println("number=" + number +" digit3=" + digit3 + " digit2=" + digit2 + " digit1=" + digit1);
            digitSum1 = digitSum1 - digit1; //<-- subtract value of digit1 from digitSum1
            digitSum2 = digitSum2 - digit2; //<-- subtract value of digit2 from digitSum2
            digitSum3 = digitSum3 - digit3; //<-- subtract value of digit3 from digitSum3
//            System.out.println(" digitSum3=" + digitSum3 + " digitSum2=" + digitSum2 + " digitSum1=" + digitSum1);
        }
        int missingNumber = digitSum3 * 100 + digitSum2 * 10 + digitSum1; //<-- re-calculate back the missing number
        System.out.println("missing number=" + missingNumber +" digit3=" + digitSum3 + " digit2=" + digitSum2 + " digit1=" + digitSum1);
    }
}

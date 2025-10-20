package training.other;

public class AmazonProblem2 {
    /*
        Given a string of length, which contains only 3 characters '0', '1' & '!'
        Follow below steps
        1. Find all occurrences of char '!'.
        2. For each occurrence of '!' can be replaced by either a '0' & or a '1'.
        3. Find strings for each combination of '!' replaced by each either by 0 or 1 and no '!' exists in the string.
        4. Next count the number of '01' combinations possible in each resultant string at any order.
        5. Next count the number of '10' combinations possible in each resultant string at any order.
        6. Add them up
        7. Find the minimum of those added numbers.

        Example1:
        String: !!!!!!!!
        Answer: 0
        Explanation: among all combinations 00000000 & 11111111 provides 01 count = 0 and 10 count = 0. 0 + 0 = 0, hence the result is 0

        Example2:
        String: 010010!1
        Answer: 15
        Explanation: Possible combinations are 01001001 & 01001011.
        For 01001001 count of 01 = 5 + 3 + 1 = 9
        For 01001001 count of 10 = 2 + 2 + 1 + 1 = 6
        Added 9 + 6 = 15
        For 01001011 count of 01 = 4 + 4 + 3 + 1 = 12
        For 01001011 count of 10 = 2 + 1 + 1 = 4
        Added 12 + 4 = 16
        Result = Min(15, 16) = 15.
        Return 15

        Example3:
        String: 01!0!0!1
        Answer: 15
                                                000      001       010       011       100        101      110       111
        Explanation: Possible combinations are 01000001, 01000011, 01001001, 01001011, 01100001, 01100011, 01101001, 01101011.
        1) For 01000001 count of 01 = 6 + 1 = 7
        For 01000001 count of 10 = 1 + 1 + 1 + 1 +1 = 5
        Added 7 + 5 = 12
        2) For 01000011 count of 01 = 5 + 5 + 1 = 11
        For 01000011 count of 10 = 1 + 1 + 1 + 1 = 4
        Added 11 + 4 = 15
        3) For 01001001 count of 01 = 5 + 3 + 1 = 9
        For 01001001 count of 10 = 2 + 2 + 1 + 1 = 6
        Added 9 + 6 = 15
        4) For 01001011 count of 01 = 4 + 4 + 3 + 1 = 12
        For 01001011 count of 10 = 2 + 1 + 1 = 4
        Added 12 + 4 = 16
        5) For 01100001 count of 01 = 5 + 1 + 1 = 7
        For 01100001 count of 10 = 2 + 2 + 2 + 2 = 8
        Added 7 + 8 = 15
        6) For 01100011 count of 01 = 4 + 4 + 1 + 1 = 10
        For 01100011 count of 10 = 2 + 2 + 2 = 6
        Added 10 + 6 = 16
        6) For 01101001 count of 01 = 4 + 2 + 1 + 1 = 8
        For 01101001 count of 10 = 3 + 3 + 2 = 8
        Added 8 + 8 = 16
        7) For 01101011 count of 01 = 3 + 3 + 2 + 1 + 1 = 10
        For 01101011 count of 10 = 3 + 2 = 5
        Added 10 + 5 = 15

        Result = Min(12, 15, 15, 16, 15, 16, 16, 15) = 12.
        Return 12

     */

    public static int findMinCombinations(String str) {
        if (str == null || str.isEmpty()) return 0;
        int count0 = 0;
        int count1 = 0;
        int countSpecialChar = 0;
        int option0 = 0;
        int option1 = 0;
        for (char c : str.toCharArray()) {
            if (c == '!') countSpecialChar++; else if (c == '0') count0++; else if (c == '1') count1++; else throw new RuntimeException("Invalid Character");
            option0 = (count0 + countSpecialChar) * count1;
            option1 = count0 * (count1 + countSpecialChar);
        }
        return Math.min(option0, option1);
    }

    public static void main(String[] args) {
        String[] strings = {
                "!!!!!!!!",
                "010010!1",
                "01!0!0!1"
        };

        for (String str : strings) {
            System.out.println("Result ==> " + findMinCombinations(str));
        }
    }
}

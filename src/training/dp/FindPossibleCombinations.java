package training.dp;

public class FindPossibleCombinations {

    /*
    Given a target and an array of positive integers (a)
    Find all possible combinations which adds up to a target (t).
    Return the number of combinations as out put.
     */

    public static int getNumberOfCombinationsElementsUsedInOrder(int[] arr, int t) {
        if (arr == null || arr.length == 0) return 0;
        int[] dp = new int[t+1];
        dp[0] = 1;
        for (int a : arr) {
            for (int target = a; target <= t; target++) {
                dp[target] = dp[target] + dp[target - a];
            }
        }
        return dp[t];
    }

    public static int getNumberOfCombinationsElementsUsedOnce(int[] arr, int t) {
        if (arr == null || arr.length == 0) return 0;
        int[] dp = new int[t + 1];
        dp[0] = 1;
        for (int a : arr) {
            for (int target = t; target >= a; target--) {
                dp[target] = dp[target] + dp[target - a];
            }
        }
        return dp[t];
    }

    public static int getNumberOfCombinationsElementsOrderDoesNotMatter(int[] arr, int t) {
        if (arr == null || arr.length == 0) return 0;
        int[] dp = new int[t + 1];
        dp[0] = 1;
        for (int target = 1; target <= t; target++) {
            for (int a : arr) {
                if ( a <= target) {
                    dp[target] = dp[target] + dp[target - a];
                }
            }
        }
        return dp[t];
    }

    public static void main (String[] args) {
        int[][] a = {
                {1,2,3},// 1> [1+1+1+1, 1+1+2, 2+2, 1+3]; 2> [1+1+1+1, 1+1+2, 2+1+1, 1+2+1, 2+2, 1+3, 3+1]; 3> [1+3]
                {5,2},
                {10}
        };
        int[] t = {4, 10, 100};
        for (int i = 0; i < a.length; i++) {
            System.out.println("Combinations (elements used in order)==> " + getNumberOfCombinationsElementsUsedInOrder(a[i], t[i]));
            System.out.println("Combinations (elements order don't matter)==> " + getNumberOfCombinationsElementsOrderDoesNotMatter(a[i], t[i]));
            System.out.println("Combinations (elements used only once)==> " + getNumberOfCombinationsElementsUsedOnce(a[i], t[i]));
            System.out.println("=============================================================================================");
        }
    }



}

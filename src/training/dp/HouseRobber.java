package training.dp;

/*

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected,
and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can
rob tonight without alerting the police.

Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: nums = [2,7,9,3,1]

Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.

 */


import java.util.Arrays;

public class HouseRobber {
    /*Recurring relationship
    1,2,3
    1,2, max(2, 3+1)
    1,2, max(3,4)
    1,2,4 --> 4

    5, 10, 1
    5, 10, max(10, 1+5)
    5, 10, 10 --> 10

    16, 15, 2, 10
    16, 16, max(16, 2+16), __
    16, 16, 18, max(18, 10+16)
    16, 16, 18, 26 --> 26

    Max(n) = max(Max(n-1), value(n) + Max(n-2)) <-- recurring function
    Max(1) = value(1) <-- base condition 1
    Max(2) = max(value(1), value(2)) <-- base condition 2
    */

    public static int rob(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int n = arr.length;
        if (n == 1) return arr[n-1];
        if (n == 2) return Math.max(arr[n-1], arr[n-2]);
        int[] dp = new int[arr.length];
        Arrays.fill(dp, -1);
        return Math.max(robRec(arr, n-1, dp), robRec(arr, n-2, dp));
    }

    public static int robRec(int[] arr, int i, int[] dp) {
        if (i < 0) return 0;
        if (i == 0) {
            dp[i] = arr[i];
            return dp[i];
        }
        if (i == 1) {
            dp[i] = Math.max(arr[i], arr[i - 1]);
            return dp[i];
        }
        if (dp[i] == -1) {
            dp[i] = Math.max(robRec(arr, i - 1, dp), arr[i] + robRec(arr, i - 2, dp));
        }
        return dp[i];
    }

    public static int robBottomUp(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int dp1 = 0, dp2 = 0;
        dp1 = arr[0];
        if (arr.length >= 2) {
            dp2 = Math.max(arr[0], arr[1]);
            for (int i = 2; i < arr.length; i++) {
                int max = Math.max(dp2, (arr[i] + dp1));
                dp1 = dp2;
                dp2 = max;
            }

        }
        return Math.max(dp1, dp2);
    }

    public static void main(String[] args) {
        int[][] arrays = {
                {1,2,3,1},
                {2,7,9,3,1},
                {16, 15, 2, 10}
        };

        for (int[] array : arrays) {
            System.out.println(Arrays.toString(array) + " ==> " + rob(array));
            System.out.println("------------------------------------");
            System.out.println(Arrays.toString(array) + " ==> " + robBottomUp(array));

        }
    }
}

package practice.repeat8;

import java.util.Arrays;

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
//Recurring relationship ==> Max(i) = Max([val(i) + Max(i-2)], [Max(i-1)])
//Base condition Max(0) = val(0) && Max(1) = max(val(0), val(1)
//
public class HouseRobber {

    public static int rob(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int n = arr.length;
        int dp1 = arr[0], dp2 = 0;
        if (n >= 2) dp2 = Math.max(arr[1], arr[0]);
        for (int i = 2; i < n; i++) {
            int nextAtI = Math.max((arr[i] + dp1), dp2);
            dp1 = dp2;
            dp2 = nextAtI;
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
            System.out.println("------------------------------------");
            System.out.println(Arrays.toString(array) + " ==> " + rob(array));

        }
    }
}

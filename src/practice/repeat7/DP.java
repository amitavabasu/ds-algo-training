package practice.repeat7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DP {

    public static int getMinCostByRecursionDP(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int[] dp = new int[arr.length];
        Arrays.fill(dp, -1);
        return Math.min(getMinCostByRecursion(arr, arr.length-1, dp), getMinCostByRecursion(arr, arr.length-2, dp));
    }

    public static int getMinCostByRecursion(int[] arr, int i, int[] dp) {
        if (i < 0) return 0;
        if (i < 2) {
            dp[i] = arr[i];
            return dp[i];
        }
        if (dp[i] == -1) {
            dp[i] = arr[i] + Math.min(getMinCostByRecursion(arr, i-1, dp), getMinCostByRecursion(arr, i-2, dp));
        }
        return dp[i];
    }

    public static int getMinCostByBottomUpDPOptimized(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int dp1 = arr[0];
        int dp2 = arr[1];
        for (int i = 2; i < arr.length; i++) {
            int costAtI = arr[i] + Math.min(dp1, dp2);
            dp1 = dp2;
            dp2 = costAtI;
        }
        return Math.min(dp1, dp2);
    }

    public static List<Integer> fibonacci(int n) {
        ArrayList<Integer> result = new ArrayList<>();
        if (n == 0) return Collections.emptyList();
        if (n >= 1) result.add(0);
        if (n >= 2) result.add(1);
        for (int i = 2; i < n; i++) {
            result.add(result.get(i-1)+result.get(i-2));
        }
        return result;
    }


    public static void main(String[] args) {
        /*
        Given a list of cost for each stair, and you can climb one stair or two stair at a given step
        find the minimum cost to climb all stairs up to the top
         */
        int[] costs = {20, 15, 30, 5, 100,1};
        System.out.println("Minimum cost by recursion ==> " + getMinCostByRecursionDP(costs));
        System.out.println("Minimum cost by top down approach using DP ==> " + getMinCostByBottomUpDPOptimized(costs));
        System.out.println(fibonacci(10));
    }

}

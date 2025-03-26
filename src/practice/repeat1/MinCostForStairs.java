package practice.repeat1;

import java.util.Arrays;

public class MinCostForStairs {

    public static int getMinCostByTopDownRecursion(int[] arr) {
        if (arr == null || arr.length == 0 ) return 0;
        if (arr.length < 2) return Math.min(arr[0], arr[1]);
        int n = arr.length;
        return Math.min(getMinCostByTopDownRecursive(n-1, arr), getMinCostByTopDownRecursive(n-2, arr));
    }

    //needs to know O -- ? 2^n - ?
    public static int getMinCostByTopDownRecursive(int n, int[] arr) {
        if (n < 0) return 0;
        if (n < 2) return arr[n];
        return arr[n] + Math.min(getMinCostByTopDownRecursive(n-1, arr), getMinCostByTopDownRecursive(n-2, arr));
    }

    public static int getMinCostByTopDownRecursionUsingDP(int[] arr) {
        if (arr == null || arr.length == 0 ) return 0;
        if (arr.length < 2) return Math.min(arr[0], arr[1]);
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return Math.min(getMinCostByTopDownRecursiveUsingDP(n-1, arr, dp), getMinCostByTopDownRecursiveUsingDP(n-2, arr, dp));
    }

    public static int getMinCostByTopDownRecursiveUsingDP(int n, int[] arr, int[] dp) {
        if (n < 0) return 0;
        if (n < 2) {
            dp[n] = arr[n];
            return dp[n];
        }
        if (dp[n] != -1) return dp[n];
        dp[n] = arr[n] + Math.min(getMinCostByTopDownRecursiveUsingDP(n-1, arr, dp), getMinCostByTopDownRecursiveUsingDP(n-2, arr, dp));
        return dp[n];
    }

    public static int getMinCostByBottomUpWithDP(int[] arr) {
        if (arr == null || arr.length == 0 ) return 0;
        if (arr.length < 2) return Math.min(arr[0], arr[1]);
        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = arr[0];
        dp[1] = arr[1];
        for (int i = 2; i < n; i++) {
            int cost = arr[i] + Math.min(dp[i-1], dp[i-2]);
            dp[i] = cost;
        }
        return Math.min(dp[n-1], dp[n-2]);
    }

    public static int getMinCostByBottomUpWithDPOptimized(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length < 2) return Math.min(arr[0], arr[1]);
        int n = arr.length;
        int dp0 = arr[0];
        int dp1 = arr[1];
        for (int i = 2; i < n; i++) {
            int cost = arr[i] + Math.min(dp0, dp1);
            dp0 = dp1;
            dp1 = cost;
        }
        return Math.min(dp0, dp1);
    }

    public static void main(String[] args) {
        /*
        Given a list of cost for each stair, and you can climb one stair or two stair at a given step
        find the minimum cost to climb all stairs up to the top
         */
        int[] costs = {20, 15, 30, 5, 100, 1};
        System.out.println("Minimum cost by recursion ==> " + getMinCostByTopDownRecursion(costs));
        System.out.println("Minimum cost by top down approach using DP ==> " + getMinCostByTopDownRecursionUsingDP(costs));
        System.out.println("Minimum cost by bottom up approach using DP ==> " + getMinCostByBottomUpWithDP(costs));
        System.out.println("Minimum cost by bottom up approach using DP (optimized) ==> " + getMinCostByBottomUpWithDPOptimized(costs));
    }

}

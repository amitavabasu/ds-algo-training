package practice.repeat4;

import java.util.Arrays;

public class MinCostOfStairs_DP {

    public static int minCost1(int[] costs) {
        if (costs == null || costs.length == 0) return 0;
        if (costs.length == 1) return costs[0];
        int n = costs.length;
        return Math.min(minCostRec1(costs, n-1), minCostRec1(costs, n-2));
    }

    public static int minCostRec1(int[] costs, int n) {
        if (n < 0) return 0;
        if (n < 2) return costs[n];
        return costs[n] + Math.min(minCostRec1(costs, n-1), minCostRec1(costs, n-2));
    }

    public static int minCost2(int[] costs) {
        if (costs == null || costs.length == 0) return 0;
        if (costs.length == 1) return costs[0];
        int n = costs.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return Math.min(minCostRec2(costs,dp,n-1), minCostRec2(costs, dp,n-2));
    }

    public static int minCostRec2(int[] costs, int[] dp, int n){
        if (n < 0) return 0;
        if (n < 2) dp[n] = costs[n];
        if (dp[n] != -1) return dp[n];
        dp[n] = costs[n] + Math.min(minCostRec2(costs, dp, n-1), minCostRec2(costs, dp, n-2));
        return dp[n];
    }

    public static int minCost3(int[] costs) {
        if (costs == null || costs.length == 0) return 0;
        if (costs.length == 1) return costs[0];
        int n = costs.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            if (i < 2) {
                dp[i] = costs[i];
            } else {
                dp[i] = costs[i] + Math.min(dp[i-1], dp[i-2]);
            }
        }
        return Math.min(dp[n-1], dp[n-2]);
    }

    public static int minCost4(int[] costs) {
        if (costs == null || costs.length == 0) return 0;
        int n = costs.length;
        if (costs.length < 2) return costs[n];
        int dp1 = costs[0];
        int dp2 = costs[1];
        for (int i = 2; i < costs.length; i++) {
            int minCost = costs[i] + Math.min(dp1, dp2);
            dp1 = dp2;
            dp2 = minCost;
        }
        return Math.min(dp1, dp2);
    }


    public static void main(String[] args) {
        int[] costs = {20, 15, 30, 5, 100,1};
        System.out.println(minCost1(costs));
        System.out.println(minCost2(costs));
        System.out.println(minCost3(costs));
        System.out.println(minCost4(costs));
    }
}

package practice.repeat5;

import java.util.Arrays;

public class MinStairsDP {

    public static int getMinCostByRecursion(int[] costs) {
        if (costs == null || costs.length == 0) return 0;
        int n = costs.length;
        return Math.min(minCost1(costs, n-1), minCost1(costs, n-2));
    }

    public static int minCost1(int[] costs, int n) {
        if (n < 0) return 0;
        if (n < 2) return costs[n];
        return costs[n] + Math.min(minCost1(costs, n-1), minCost1(costs, n-2));
    }

    public static int getMinCostByTopDownDP(int[] costs) {
        if (costs == null || costs.length == 0) return 0;
        int n = costs.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return Math.min(minCost2(costs, n-1, dp), minCost2(costs, n-2, dp));
    }

    public static int minCost2(int[] costs, int i, int[] dp) {
        if (i < 0) return 0;
        if (i < 2) {dp[i] = costs[i]; return dp[i];}
        if (dp[i] == -1) {
            dp[i] = costs[i] + Math.min(minCost2(costs, i-1, dp), minCost2(costs, i-2, dp));
        }
        return dp[i];
    }

    public static int getMinCostByBottomUpDP(int[] costs) {
        if (costs == null || costs.length == 0) return 0;
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

    public static int getMinCostByBottomUpDPOptimized(int[] costs) {
        if (costs == null || costs.length == 0) return 0;
        int n = costs.length;
        if (n == 1) return costs[0];
        int dp1 = costs[0];
        int dp2 = costs[1];
        for (int i = 2; i < n; i++) {
            int cost = costs[i] + Math.min(dp1, dp2);
            dp1 = dp2;
            dp2 = cost;
        }
        return Math.min(dp1, dp2);
    }


    public static void main(String[] args) {
        /*
        Given a list of cost for each stair, and you can climb one stair or two stair at a given step
        find the minimum cost to climb all stairs up to the top
         */
        int[] costs = {20, 15, 30, 5, 100,1};
        System.out.println("Minimum cost by recursion ==> " + getMinCostByRecursion(costs));
        System.out.println("Minimum cost by top down approach using DP ==> " + getMinCostByTopDownDP(costs));
        System.out.println("Minimum cost by bottom up approach using DP ==> " + getMinCostByBottomUpDP(costs));
        System.out.println("Minimum cost by bottom up approach using DP (optimized) ==> " + getMinCostByBottomUpDPOptimized(costs));
    }
}

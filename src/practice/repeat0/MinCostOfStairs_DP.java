package practice.repeat0;

import java.util.Arrays;

public class MinCostOfStairs_DP {


    public static int getMinCostByRecursion(int[] costs) {
        if (costs == null || costs.length < 2) return 0;
        return Math.min(minCost(costs, costs.length-1), minCost(costs, costs.length-2));
    }
    public static int minCost(int[] costs, int n) {
        if (n < 0) return 0;
        if (n < 2) return costs[n];
        return costs[n] + Math.min(minCost(costs, n-1), minCost(costs, n-2));
    }

    public static int getMinCostByTopDownDP(int[] costs) {
        if (costs == null || costs.length < 2) return 0;
        int[] dpStorage = new int[costs.length];
        Arrays.fill(dpStorage, -1);
        return Math.min(minCostDP(costs, costs.length-1, dpStorage), minCostDP(costs, costs.length-2, dpStorage));
    }

    public static int minCostDP(int[] costs, int n, int[] storage) {
        if (n < 0) return 0;
        if (n < 2) {
            storage[n] = costs[n];
            return storage[n];
        }
        if (storage[n] != -1) return storage[n];
        storage[n] = costs[n] + Math.min(minCostDP(costs, n-1, storage), minCostDP(costs, n-2, storage));
        return storage[n];
    }

    public static int getMinCostByBottomUpDP(int[] costs) {
        if (costs == null || costs.length < 2) return 0;
        int[] dpStorage = new int[costs.length];
        dpStorage[0] = costs[0];
        dpStorage[1] = costs[1];
        for (int i = 2; i < costs.length; i++) {
            dpStorage[i] = costs[i] + Math.min(dpStorage[i-2], dpStorage[i-1]);
        }
        return Math.min(dpStorage[costs.length-2], dpStorage[costs.length-1]);
    }

    public static int getMinCostByBottomUpDPOptimized(int[] costs) {
        if (costs == null || costs.length < 2) return 0;
        int dpStorage1 = costs[0];
        int dpStorage2 = costs[1];
        for (int i = 2; i < costs.length; i++) {
            int currentMinCost = costs[i] + Math.min(dpStorage1, dpStorage2);
            dpStorage1 = dpStorage2;
            dpStorage2 = currentMinCost;
        }
        return Math.min(dpStorage1, dpStorage2);
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

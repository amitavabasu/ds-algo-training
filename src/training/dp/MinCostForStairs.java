package training.dp;

import java.util.Arrays;

public class MinCostForStairs {

    public static int getMinCostByRecursion(int[] costs) {
        /*
        Breaking down the problem in sum problems
                                         minCost(n)
                                            /\
                               min(minCost(n-1),minCost(n-2))

        Recurrence relation ==> minCost(i) = cost[i] + Math.min(minCost(i-1), minCost(i-2))

        Base Condition ==>
                minCost(0) = cost[0]
                minCost(1) = cost[1]
         */
        int n = costs.length;//initialize length as n
        return Math.min(minCost(n-1, costs), minCost(n-2, costs));//make recursive call with n-1 and n-2. Return minimum of these two values from recursive call
    }

    private static int minCost(int n, int[] costs) {
        if (n < 0) return 0;//base condition, if n (i.e. number of steps) gets negative return 0, i.e. no cost to go there.
        if (n < 2) return costs[n];//base condition if n less than 2, we can go to wither 1 or 2 having the cost of that stair
        return costs[n] + Math.min(minCost(n-1, costs), minCost(n-2, costs));//recursion - the cost of reaching n-th stair from n-1 & n-2 step is the cost
        //of the n-th stair plus minimum cost of (n-1) and (n-2) steps
    }

//    --------------------------------------------------------------------------------------

    public static int getMinCostByTopDownDP(int[] costs) { //memomization
        /*
        Breaking down the problem in sum problems
                                         minCost(n)
                                            /\
                               min(minCost(n-1),minCost(n-2))

        Recurrence function ==> minCost(i) = cost[i] + Math.min(minCost(i-1), minCost(i-2))

        Base Condition ==>
                minCost(0) = cost[0]
                minCost(1) = cost[1]
         */
        int n = costs.length;//initialize length or number of steps/stairs
        int[] dpStorage = new int[n];//create dp array to hold pre-calculated values to avoid re calculation.
        Arrays.fill(dpStorage, -1);//initialize all pre-calculated values as -1, so that we can identify if the value is pre-calculated or not
        int result = Math.min(minCostDP(n-1, costs, dpStorage), minCostDP(n-2, costs, dpStorage));//call recursively with n-1 & n-2 passing DP to hold pre-calculated values
        //return the minimum as result
        return result;
    }

    private static int minCostDP(int n, int[] costs, int[] dpStorage) {
        if (n < 0) return 0; //again if the step is less than 0 the cost is 0
        if (n < 2){ //if the step is 0 or 1 the cost is either. put the cost in DP first before returning
            dpStorage[n] = costs[n];//store in DP
            return costs[n];//return
        }
        if (dpStorage[n] != -1) return dpStorage[n];//if the step is grater than 2 first check in this step n has a pre-calculated value or not
        //If there exists a pre-calculated value return that
        dpStorage[n] = costs[n] + Math.min(minCostDP(n-1, costs, dpStorage), minCostDP(n-2, costs, dpStorage));//if pre-calculated value do not exist
        //the cost of n-th step is cost of current step plus minimum cost of n-1 or n-2 steps. Store that in DP before returning
        return dpStorage[n];//return the calculated min cost
    }
    public static int getMinCostByBottomUpDP(int[] costs) {//tabulation
        int n = costs.length;//initialize n as number of steps
        int[] dpStorage = new int[n];//initialize DP storage with n number array to store cost of each step
        for (int i = 0; i < n; i++) { // for each step
            if (i < 2) { //if step number is less than 2
                dpStorage[i] = costs[i];//store the DP value in appropriate place
            } else {//if step number is more than 2
                dpStorage[i] = Math.min(dpStorage[i - 1], dpStorage[i - 2]) + costs[i];//then the minimum cost to reach i-th step is minimum of dp[i-1] and dp[i-2] + cost of i-th step
                //store this min cost in dp[i]
            }
        }
        return Math.min(dpStorage[n-1], dpStorage[n-2]);//return minimum of dp[n-1] & dp[n-2]
    }

    public static int getMinCostByBottomUpDPOptimized(int[] costs) {
        if (costs.length == 0) return 0;//0-th condition
        int n = costs.length;//initialize n as number of steps
        if (n == 1) return costs[0];//if there is only one step return cost of that step
        //if there are two or more steps
        int dpStorage0 = costs[0];//initialize fist as 0-th cost
        int dpStorage1 = costs[1];//initialize second as 1-st cost
        for (int i = 2; i < costs.length; i++) {//now for all steps after 1 i.e. 2 to n-1
            int currentCost = costs[i] + Math.min(dpStorage0, dpStorage1);//calculate current cost
            dpStorage0 = dpStorage1;//override first dp as second dp
            dpStorage1 = currentCost;//override second dp as current cost
        }
        return Math.min(dpStorage0, dpStorage1);//return min of first and second
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

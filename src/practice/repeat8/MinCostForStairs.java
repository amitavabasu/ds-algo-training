package practice.repeat8;

public class MinCostForStairs {

    public static int getMinCost(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int dp1 = arr[0], dp2 = 0;
        if ( arr.length > 1) dp2 = arr[1];
        for (int i = 2; i < arr.length; i++) {
            int min = arr[i] + Math.min(dp1, dp2);
            dp1 = dp2;
            dp2 = min;
        }
        return Math.min(dp1, dp2);
    }

    public static void main(String[] args) {
        /*
        Given a list of cost for each stair, and you can climb one stair or two stair at a given step
        find the minimum cost to climb all stairs up to the top
         */
        int[] costs = {20, 15, 30, 5, 100,1};
        System.out.println("Minimum cost ==> " + getMinCost(costs));
    }
}

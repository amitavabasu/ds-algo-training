package practice.repeat0;

import java.util.Arrays;

public class MaxProfitBySingleTransaction_DP {


    //profit[i] = prices[i+1] = prices[i]
    //maxProfit[i] = max((maxProfit[i+1] + profit[i]), 0)

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        if (prices.length == 1) return 0;
        int previousMaxProfit = 0;
        int maxProfit = previousMaxProfit;
        for (int i = prices.length-2; i >= 0; i--) {
            int profit = prices[i+1] - prices[i];
            previousMaxProfit =  Math.max((previousMaxProfit + profit), 0);
            if(maxProfit < previousMaxProfit)
                maxProfit = previousMaxProfit;
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[][] arrays = {
                {7,1,5,3,6,4},
                {7,6,4,3,1}
        };

        for (int i = 0; i < arrays.length; i++) {
            System.out.println(Arrays.toString(arrays[i]) + " ==> " + maxProfit(arrays[i]));
        }
    }
}

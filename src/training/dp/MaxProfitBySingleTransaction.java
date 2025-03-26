package training.dp;

import java.util.Arrays;

public class MaxProfitBySingleTransaction {

    // mp[i] = max ((p[i] + mp[i+1]), 0)
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int prevMaxProfit = 0;
        int maxProfit = 0;
        for (int i = prices.length - 2; i >= 0; i--) {
            int profit = prices[i+1] - prices[i];
            prevMaxProfit = Math.max((profit + prevMaxProfit), 0);
            maxProfit = Math.max(maxProfit, prevMaxProfit);
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

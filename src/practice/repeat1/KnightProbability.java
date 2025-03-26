package practice.repeat1;

import java.lang.reflect.Array;
import java.util.Arrays;

public class KnightProbability {

    public static int[][] directions = new int[][]{
            {+2, -1},
            {+2, +1},
            {+1, +2},
            {+1, -2},
            {-2, +1},
            {-2, -1},
            {-1, +2},
            {-1, -2}
    };

    public static double calculateProbabilityTopDown(int d, int r, int c, int k) {
        if (d < 2  && k > 0) return 0;
        if (k < 2) return 1;
        return calculateProbabilityTopDownRecursively(d, r, c, k);
    }

    public static double calculateProbabilityTopDownRecursively(int d, int r, int c, int k) {
        if (k == 0) return 1;
        double prob = 0.0;
        for (int[] mov : directions) {
            int rBar = r + mov[0];
            int cBar = c + mov[1];
            if (rBar >= 0 && rBar < d && cBar >= 0 && cBar < d) {
                prob += calculateProbabilityTopDownRecursively(d, rBar, cBar, k - 1);
            }
        }
        return prob / 8;
        }

        public static double calculateProbabilityToDownWithDP(int d, int r, int c, int k) {
            if (d < 2 && k > 0) return 0;
            if (k ==1) return 1;
            double[][][] dp = new double[k][d][d];
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < d; j++) {
                    Arrays.fill(dp[i][j], -1);
                }
            }
            return calculateProbabilityToDownWithDPRecursively(d, r, c, k, dp);
        }

        public static double calculateProbabilityToDownWithDPRecursively(int d, int r, int c, int k, double[][][] dp) {
            if (k == 0) {
                dp[k][r][c] = 1;
                return dp[k][r][c];
            }
            double probability = 0.0;
            for (int[] mov : directions) {
                int rBar = r + mov[0];
                int cBar = c + mov[1];
                if (rBar >= 0 && rBar < d && cBar >= 0 && cBar < d) {
                    if (dp[k-1][rBar][cBar] != -1){
                        probability += dp[k-1][rBar][cBar];
                    } else {
                        dp[k-1][rBar][cBar] = calculateProbabilityToDownWithDPRecursively(d, rBar, cBar, k - 1, dp);
                        probability += dp[k-1][rBar][cBar];
                    }
                }
            }
            return probability / 8;
        }

        public static double calculateProbabilityBottomUpWithDP(int d, int r, int c, int k) {
            if (d < 2 && k > 0) return 0;
            if (k ==1) return 1;
            double[][] dp1 = new double[d][d];
            dp1[r][c] = 1;
            double[][] dp2 = new double[d][d];
            for (int i = 0; i < k; i++) {
                dp2 = new double[d][d];
                for (int row = 0; row < d; row++) {
                    for (int col = 0; col < d; col++) {
                        for (int[] mov : directions) {
                            if (row + mov[0] >= 0 && row + mov[0] < d && col + mov[1] >= 0 && col + mov[1] < d) {
                                dp2[row][col] += dp1[row + mov[0]][col + mov[1]] / 8;
                            }
                        }
                    }
                }
                dp1 = dp2;
            }
            double probability = 0.0;
            for (int row = 0; row < d; row++) {
                for (int col = 0; col < d; col++) {
                    probability += dp2[row][col];
                }
            }
            return probability;
        }



    public static void main(String[] args) {
        int d = 10;
        int r = 2;
        int c = 2;
        int k = 5;

        System.out.println("Probability ==> " + calculateProbabilityTopDown(d,r,c, k));
        System.out.println("Probability (DP-Top Down) ==> " + calculateProbabilityToDownWithDP(d,r,c, k));
        System.out.println("Probability (DP-Bottom Up) ==> " + calculateProbabilityBottomUpWithDP(d,r,c, k));
    }
}

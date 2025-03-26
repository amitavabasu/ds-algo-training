package practice;

import java.util.Arrays;

public class ProbabilityOfKnightOnChessBoard_DP {
    static int[][] directions = {
            {-2,-1},
            {-2, 1},
            {2, -1},
            {2, 1},
            {-1, -2},
            {-1, 2},
            {1, 2},
            {1,-2}
    };
    public static double knightProbability(int n, int k, int r, int c) {
        if (r >= 0 && r < n && c >=0 && c < n) {
            if (k==0) return 1;
            double result = 0.0D;
            for (int[] direction : directions) {
                result += (double)knightProbability(n, k-1, (r+direction[0]), (c+direction[1])) / 8;
            }
            return result;
        } else {
            return 0;
        }
    }

    public static double knightProbabilityDP(int n, int k, int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= n) return 0;
        if (k == 0) return 1;
        double[][][] dp = new double[k][n][n];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1.0d);
            }
        }
        return knightProbabilityDPRecursion(n, k, r, c, dp);
    }

    public static double knightProbabilityDPRecursion(int n, int k, int r, int c, double[][][] dp) {
        if (r < 0 || r >= n || c < 0 || c >= n) return 0;
            if (k == 0) return 1;
            if (dp[k - 1][r][c] != -1.0d) return dp[k - 1][r][c];
            double res = 0.0d;
            for (int[] direction : directions) {
                res += (double) (knightProbabilityDPRecursion(n, k - 1, (r + direction[0]), (c + direction[1]), dp)) / 8;
            }
            dp[k-1][r][c] = res;
            return dp[k-1][r][c];
    }

    public static double calculateProbabilityBottomUpDP(int n, int k, int r, int c) {
        double[][] dp1 = new double[n][n];
        dp1[r][c] = 1;
        double[][] dp2 = new double[n][n];
        for (int step = 0; step < k; step++) {
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    for (int[] direction : directions) {
                        int xBar = x + direction[0];
                        int yBar = y + direction[1];
                        if (xBar >= 0 && xBar < n && yBar >= 0 && yBar < n) {
                            dp2[x][y] += dp1[xBar][yBar];
                        }
                    }
                    dp2[x][y] = dp2[x][y] / 8;
                }
            }
            dp1 = dp2;
            dp2 = new double[n][n];
        }
        double res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res += dp1[i][j];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int d = 10;
        int r = 2;
        int c = 2;
        int k = 5;
        //0.37200927734375
        System.out.println("Probability (Top-Down without DP) ==>" + knightProbability(d,k, r,c));
        System.out.println("Probability (DP-Top Down) ==> " + knightProbabilityDP(d,k, r,c));
        System.out.println("Probability (DP-Bottom Up) ==> " + calculateProbabilityBottomUpDP(d, k, r,c));
    }
}

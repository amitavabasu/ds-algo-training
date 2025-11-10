package practice.repeat8;

import java.util.Arrays;

public class SubgridSum {

    public static int getRValue(int[][] result, int x, int y) {
        if ( x >= 0 && x < result.length && y >= 0 && y < result[0].length) {
            return result[x][y];
        } else {
            return 0;
        }
    }

    public static int[][] subgridSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return new int[][]{{}};
        int n = grid.length;
        int m = grid[0].length;
        int[][] result = new int[n][m];
        for (int r = n-1; r >= 0; r--) {
            for (int c = m-1; c >= 0; c--) {
                result[r][c] = grid[r][c] + getRValue(result, r+1, c) + getRValue(result, r,  c+1) - getRValue(result, r+1, c+1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][][] grids = {
                {
                        {-1, 2, 3},
                        {4, 0, 0},
                        {-2, 0, 9}
                },
                {
                        {5}
                },
                {
                        {1, 2, 3}
                }
        };
        for (int[][] grid : grids) {
            int[][] result = subgridSum(grid);
            System.out.println("Result:");
            for (int i = 0; i < grid.length; i++) {
                System.out.println(Arrays.toString(result[i]));
            }
        }
    }
}

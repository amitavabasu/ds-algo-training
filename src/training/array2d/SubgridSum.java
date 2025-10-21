package training.array2d;

import java.util.Arrays;

public class SubgridSum {
    /*
        # Subgrid Sums

        Given a rectangular `RxC` grid of integers, `grid`, with `R > 0` and `C > 0`, return a new grid with the same dimensions where each cell `[r, c]` contains the sum of all the elements in the subgrid with `[r, c]` in the top-left corner and `[R - 1, C - 1]` in the bottom-right corner.

        Example 1:
        grid =  [[-1,  2,  3],
                 [ 4,  0,  0],
                 [-2,  0,  9]]
        Output: [[15, 14, 12],
                 [11,  9,  9],
                 [ 7,  9,  9]]

        Example 2:
        grid =  [[5]]
        Output: [[5]]
        Explanation: For a 1x1 grid, each cell's subgrid is just itself.

        Example 3:
        grid =  [[1, 2, 3]]
        Output: [[6, 5, 3]]
        Explanation: For a single row, each cell's subgrid includes all elements to its right.

        Constraints:

        - 1 ≤ R, C ≤ 10^3
        - -10^3 ≤ grid[i][j] ≤ 10^3
     */

    public static int getRValue(int x, int y, int[][] result) {
        if (x < 0 || x >= result.length || y < 0 || y >= result[0].length) {
            return 0;
        } else {
            return result[x][y];
        }
    }


    public static int[][] subgridSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return new int[][] {};
        int m = grid.length;
        int n = grid[0].length;
        int[][] result = new int[m][n];
        //result[m-1][n-1] = grid[m-1][n-1];
        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                result[i][j] = grid[i][j] + getRValue(i+1, j, result) + getRValue(i, j+1, result) - getRValue(i+1, j+1, result);
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

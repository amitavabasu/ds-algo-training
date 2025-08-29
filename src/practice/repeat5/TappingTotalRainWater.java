package practice.repeat5;

import java.util.Arrays;

public class TappingTotalRainWater {

    public static int getTotalRainWater(int[] heights) {
        int totalWater = 0;
        if (heights == null || heights.length == 0) return 0;
        int maxLeft = 0;
        int maxRight = 0;
        int pl = 0;
        int pr = heights.length - 1;
        int pc = pl;
        while (pl <= pr) {
            int water = Math.min(maxLeft, maxRight) - heights[pc];
            if (water > 0) {
                totalWater += water;
            }
            if (maxLeft < heights[pl]) maxLeft = heights[pl];
            if (maxRight < heights[pr]) maxRight = heights[pr];
            if (heights[pl] <= heights[pr]) {
                pl++;
                pc = pl;
            } else {
                pr--;
                pc = pr;
            }
        }

        return totalWater;
    }




    public static void main(String[] args) {
        int[][] data = {
                {0,1,0,2,1,0,3,1,0,1,2}
        };

        for (int[] datum : data) {
            System.out.println(Arrays.toString(datum) + "; Result=> " + getTotalRainWater(datum));
        }
    }


}

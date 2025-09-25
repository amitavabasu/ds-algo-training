package practice.repeat6;

import java.util.Arrays;

public class TappingTotalRainWater {

    public static int getTotalRainWater(int[] data) {
        if (data == null || data.length < 2) return 0;
        int pl = 0;
        int pr = data.length - 1;
        int pc = pl;
        int maxL = 0, maxR = 0;
        int totalArea = 0;
        while (pl <= pr) {
            int area = Math.min(maxL, maxR) - data[pc];
            if (area > 0) totalArea = totalArea + area;
            maxL = Math.max(maxL, data[pl]);
            maxR = Math.max(maxR, data[pr]);
            if (data[pl] <= data[pr]) {
                pl++;
                pc = pl;
            } else {
                pr--;
                pc = pr;
            }
        }
        return totalArea;
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

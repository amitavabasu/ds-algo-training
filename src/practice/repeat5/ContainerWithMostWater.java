package practice.repeat5;

import java.util.Arrays;

public class ContainerWithMostWater {

    public static int findMostWater(int[] height) {
        int maxArea = 0;
        if (height == null || height.length == 0) return maxArea;
        int pl = 0;
        int pr = height.length - 1;
        while (pl <= pr) {
            int area = Math.min(height[pl], height[pr]) * (pr - pl);
            if (maxArea < area) {
                maxArea = area;
            }
            if (height[pl] < height[pr]) {
                pl++;
            } else {
                pr--;
            }
        }


        return maxArea;
    }

    public static void main(String[] args) {
        int[][] arrays = {
                {1,8,6,2,9,4},
                {1,8,6,2,5,4,8,3,7},
                {1,1}
        };

        for (int[] array : arrays) {
            System.out.println(Arrays.toString(array) + "; Result=>" + findMostWater(array));
        }


    }

}

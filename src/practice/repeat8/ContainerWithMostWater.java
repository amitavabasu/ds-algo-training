package practice.repeat8;

import java.util.Arrays;

public class ContainerWithMostWater {

    public static int maxArea(int[] arr) {
        if (arr == null || arr.length <= 1) return 0;
        int maxArea = 0;
        int i = 0;
        int j = arr.length - 1;
        while (i <= j) {
            int area = (j-i) * Math.min(arr[i], arr[j]);
            maxArea = Math.max(maxArea, area);
            if (arr[i] <= arr[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] arrays = {
                {1,8,6,2,5,4,8,3,7},
                {1,1}
        };

        for (int i = 0; i < arrays.length; i++) {
            System.out.println(Arrays.toString(arrays[i]) + " ==> " + maxArea(arrays[i]));
        }
    }
}

package practice.repeat0;

import java.util.Arrays;

public class ContainerWithMaxWater {

    public static int containerWithMaxWater(int[] arr) {
        int maxWater = 0;
        if (arr == null || arr.length < 2) return maxWater;
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int waterArea = Math.min(arr[l], arr[r]) * (r - l);
            if (maxWater < waterArea) {
                maxWater = waterArea;
            }
            if (arr[l] <= arr[r]) {
                l++;
            } else {
                r--;
            }
        }
        return maxWater;
    }




    public static void main(String[] args) {
        int[][] arrays = {
                {1,8,6,2,5,4,8,3,7},
                {1,1}
        };
        for (int i = 0; i < arrays.length; i++) {
            System.out.println(Arrays.toString(arrays[i]) + " ==> " + containerWithMaxWater(arrays[i]));
        }
    }
}

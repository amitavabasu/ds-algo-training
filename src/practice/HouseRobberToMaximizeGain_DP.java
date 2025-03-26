package practice;

import java.util.Arrays;

public class HouseRobberToMaximizeGain_DP {

    // robMax[i] = num[i] + max(robMax[i+2], robMax[i+3])

    public static int robMax(int[] data) {
        if (data == null || data.length == 0) return 0;
        if (data.length == 1) return data[0];
        if (data.length == 2) return Math.max(data[0], data[1]);
        int dp0 = 0;
        int dp1 = data[data.length - 1];
        int dp2 = data[data.length - 2];
        for (int i = data.length - 3; i >= 0; i--) {
            int temp = data[i] + Math.max(dp0, dp1);
            dp0 = dp1;
            dp1 = dp2;
            dp2 = temp;
        }
        return Math.max(dp2, dp1);
    }


    public static void main(String[] args) {
        int[][] arrays = {
                {1,2,3,1},
                {2,7,9,3,1}
        };

        for (int i = 0; i < arrays.length; i++) {
            System.out.println(Arrays.toString(arrays[i]) + " ==> " + robMax(arrays[i]));
        }
    }
}

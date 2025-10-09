package training.dp;

import java.util.Arrays;

public class HouseRobber {

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        int dp0 = 0;
        int dp1 = nums[nums.length - 1];
        int dp2 = nums[nums.length - 2];
        for (int i = nums.length - 3; i >= 0; i--) {
            int temp = nums[i] + Math.max(dp1, dp0);
            dp0 = dp1;
            dp1 = dp2;
            dp2 = temp;
        }
        return Math.max(dp1, dp2);
    }




    public static void main(String[] args) {
        int[][] arrays = {
                {1,2,3,1},
                {2,7,9,3,1},
                {16, 15, 2, 10}
        };

        for (int i = 0; i < arrays.length; i++) {
            System.out.println(Arrays.toString(arrays[i]) + " ==> " + rob(arrays[i]));
        }
    }
}

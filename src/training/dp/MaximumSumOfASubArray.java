package training.dp;

import java.util.Arrays;

public class MaximumSumOfASubArray {


    // maxSum[i] = max ((nums[i] + maxSum[i+1]), nums[i])
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[nums.length-1];
        int prevMaxSum = nums[nums.length - 1];
        int maxSum = prevMaxSum;
        for (int j = nums.length - 2; j >= 0; j--) {
            int sum = prevMaxSum + nums[j];
            prevMaxSum = Math.max(nums[j], sum);
            if (maxSum < prevMaxSum) {
                maxSum = prevMaxSum;
            }
        }
        return maxSum;
    }


    public static void main(String[] args) {
        int[][] arrays = {
                {7,1,5,3,6,4},
                {7,6,4,3,1},
                {-2,1,-3,4,-1,2,1,-5,4}, //=6
                {1},
                {5,4,-1,7,8}
        };

        for (int i = 0; i < arrays.length; i++) {
            System.out.println(Arrays.toString(arrays[i]) + " ==> " + maxSubArray(arrays[i]));
        }
    }


}

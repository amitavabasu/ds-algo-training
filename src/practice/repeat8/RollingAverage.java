package practice.repeat8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RollingAverage {

    public static List<Double> calculateRollingAverage(int[] nums, int w) {
        if (nums == null || nums.length == 0) return Collections.emptyList();
        int n = nums.length;
        List<Double> result = new ArrayList<>();
        int sum = 0;
        if (w >= n) {
            for (int e : nums) {
                sum += e;
            }
            result.add((double)sum/w);
            return result;
        } else {
            int i = 0;
            while (i < w) {
                sum += nums[i];
                i++;
            }
            result.add((double)sum/w);
            int j = 0;
            while ( i < n) {
                sum = sum + nums[i++] - nums[j++];
                result.add((double)sum/w);
            }
        }
        return result;
    }





    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 40, 50, 60, 80};
        int windowSize = 3;

        List<Double> averages = calculateRollingAverage(numbers, windowSize);
        System.out.println("Original Array: " + java.util.Arrays.toString(numbers));
        System.out.println("Window Size: " + windowSize);
        System.out.println("Rolling Averages: " + averages);
    }
}

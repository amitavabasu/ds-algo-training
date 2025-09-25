package training.dp;

import java.util.Arrays;

public class LargestIncreasingSequence {

    public static int[] findLargestIncreasingSequence(int[] data) {
        int result = 0;
        if (data == null || data.length == 0) return new int[]{0};
        int[] largest = new int[data.length];
        Arrays.fill(largest, 1);
        for (int i = 1; i < data.length; i++) {
            int prevLargest = 0;
            for (int j = 0; j < i; j++) {
                if (data[j] < data[i]) {
                    //largest[i] = Math.max(largest[i], largest[j] + 1);
                    prevLargest = Math.max(data[j], prevLargest);
                }
            }
            largest[i] = 1 + prevLargest;
        }
        return largest;
    }


    public static void main(String[] args) {
        int[][] data = {
                {1, 8, 2, 3, 4, 0, 1, 5},
                {1,2,3,4,5,9,8,0}
        };

        for (int[] datum : data) {
            System.out.println("Data==>" + Arrays.toString(datum));
            System.out.println("LargestIncreasingSequence==>" + Arrays.toString(findLargestIncreasingSequence(datum)));
            System.out.println("-------------------------------------");
        }
    }
}

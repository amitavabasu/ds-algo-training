package practice.repeat1;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayPermutations {

    public static void permute(int[] per, int[] arr) {
        if (arr.length == 0) System.out.println(Arrays.toString(per));
        for (int i = 0; i < arr.length; i++) {
            int[] perBar = Arrays.copyOf(per,per.length + 1);
            perBar[per.length] = arr[i];
            int[] arrBar1 = Arrays.copyOfRange(arr, 0, i);
            int[] arrBar2 = Arrays.copyOfRange(arr, i+1, arr.length);
            int[] arrBar = IntStream.concat(Arrays.stream(arrBar1), Arrays.stream(arrBar2)).toArray();
            permute(perBar, arrBar);
        }
    }
    public static void main(String[] args) {
        int[] data = {1,2,3};
        permute(new int[]{}, data);
    }
}

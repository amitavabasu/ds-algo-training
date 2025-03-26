package practice;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayPermutation_Recursion {


    public static void printPermutations(int[] permutation, int[] array) {
        if (array == null || array.length == 0) {
            System.out.println(Arrays.toString(permutation));
        } else {
            for (int i = 0; i < array.length; i++) {
                int[] pBar = Arrays.copyOf(permutation, permutation.length + 1);
                pBar[permutation.length] = array[i];
                int[] aBar1 = Arrays.copyOfRange(array, 0, i);
                int[] aBar2 = Arrays.copyOfRange(array, i + 1, array.length);
                int[] aBar = IntStream.concat(Arrays.stream(aBar1), Arrays.stream(aBar2)).toArray();
                printPermutations(pBar, aBar);
            }
        }
    }
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5};
        printPermutations(new int[]{}, array);
    }

}

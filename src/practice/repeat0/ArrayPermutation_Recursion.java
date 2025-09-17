package practice.repeat0;

import java.util.Arrays;

public class ArrayPermutation_Recursion {


    public static void printAllPermutations(int[] permutation, int[] array) {
        if (array == null || array.length == 0) {
            System.out.println(Arrays.toString(permutation));
        } else {
            for (int i = 0; i < array.length; i++) {
                int[] aBar = new int[array.length-1];
                System.arraycopy(array, 0, aBar, 0, i);
                System.arraycopy(array, i+1, aBar, i, array.length-i-1);
                int[] pBar = new int[permutation.length+1];
                System.arraycopy(permutation, 0, pBar, 0, permutation.length);
                pBar[permutation.length] = array[i];
                printAllPermutations(pBar, aBar);
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5};
        printAllPermutations(new int[]{}, array);
    }
}

package practice.repeat2;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ArrayPermutations {


    public static int count = 0;
    public static void printPermutations(int[] perm, int[] array) {
        if (array.length == 0) {
            System.out.println(Arrays.toString(perm));
            count++;
        } else {
            for (int i = 0; i < array.length; i++) {
                int[] pBar = Arrays.copyOf(perm, perm.length+1);
                pBar[perm.length] = array[i];
                int[] aBar1 = Arrays.copyOfRange(array, 0, i);
                int[] aBar2 = Arrays.copyOfRange(array, i+1, array.length);
                int[] aBar = IntStream.concat(Arrays.stream(aBar1), Arrays.stream(aBar2)).toArray();
                printPermutations(pBar, aBar);
            }
        }
    }

    public static void main(String[] args) {
        int[] data = {1,2,3};
        printPermutations(new int[]{}, data);
        System.out.println("Number of permutations ==> " + count);
    }
}

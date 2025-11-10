package training.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

 */
public class Permutations {

    public static void printPermutation(char[] arr, char[] perm, List<char[]> result) {
        if (arr == null || arr.length == 0){
            result.add(perm);
        } else {
            for (int i = 0; i < arr.length; i++) {
                char[] permBar = Arrays.copyOf(perm, perm.length+1);
                permBar[perm.length] = arr[i];
                char[] arrBar1 = Arrays.copyOfRange(arr, 0, i);
                char[] arrBar2 = Arrays.copyOfRange(arr, i+1, arr.length);
                char[] arrBar = new char[arrBar1.length+ arrBar2.length];
                System.arraycopy(arrBar1, 0, arrBar, 0, arrBar1.length);
                System.arraycopy(arrBar2, 0, arrBar, arrBar1.length, arrBar2.length);
                printPermutation(arrBar, permBar, result);
            }
        }
    }

    public static void swap(char[] arr, int i, int j) {
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void permutation(char[] arr, int x, List<char[]> result) {
        if (x == arr.length) {
            char[] perm = Arrays.copyOf(arr, arr.length);
            result.add(perm);
        }
        for (int i = x; i < arr.length; i++) {
            swap(arr, x, i);
            permutation(arr, x+1, result);
            swap(arr, i, x);
        }
    }

    public static void main(String[] args) {
        char[] arr = {'x', 'y', 'z'};
        List<char[]> result = new ArrayList<>();
        printPermutation(arr, new char[]{}, result);
        for (char[] c : result) {

            System.out.println(Arrays.toString(c));
        }
        result.clear();
        System.out.println("------------------------------------------");
        permutation(arr, 0, result);
        for (char[] c : result) {
            System.out.println(Arrays.toString(c));
        }
    }
}

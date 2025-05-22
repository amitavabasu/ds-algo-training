package practice.repeat4;

import java.util.Arrays;
import java.util.stream.Stream;

public class ArrayPermutations_RecursiveBacktracking {

    public static long count = 0;
    /*
    'a,b,c,d,e,f,g,h' --> '' + 'b,c,d,e,f,g,h' && 'a'
    'a,b,c,d,e,f,g,h' --> 'a' + 'c,d,e,f,g,h' && 'a,b'
    'a,b,c,d,e,f,g,h' --> 'a,b' + 'd,e,f,g,h' && 'a,b,c'
    .
    .
    .'a,b,c,d,e,f,g,h' --> 'a,b' + 'd,e,f,g' && 'a,b,c'




     */

    public static void printPermutations(char[] arr, char[] per) {
        count++;
        if (arr == null || arr.length == 0) {
            System.out.println(Arrays.toString(per) + "; count=" + count);
        } else {
            for (int i = 0; i < arr.length; i++) {
                char[] arrBar = new char[arr.length-1];
                System.arraycopy(arr,0, arrBar, 0, i);
                System.arraycopy(arr, i+1, arrBar, i, arr.length-(i+1));
                char[] perBar = new char[per.length+1];
                System.arraycopy(per, 0, perBar, 0, per.length);
                perBar[per.length] = arr[i];
                printPermutations(arrBar, perBar);
            }
        }
    }

    public static void main(String[] arg) {
        char[] array = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        printPermutations(array, new char[]{});
    }
}

package practice.repeat7;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Permutations {

    public static void printPermutations(char[] data, char[] perm) {
        if (data.length == 0) {
            System.out.println(Arrays.toString(perm));
        } else {
            for (int i = 0; i < data.length; i++) {
                char[] permHat = Arrays.copyOf(perm, perm.length+1);
                permHat[perm.length] = data[i];
                char[] dataHat1 = Arrays.copyOfRange(data, 0, i);
                char[] dataHat2 = Arrays.copyOfRange(data, i+1, data.length);
                char[] dataHat = new char[dataHat1.length+dataHat2.length];
                System.arraycopy(dataHat1, 0, dataHat, 0, dataHat1.length);
                System.arraycopy(dataHat2, 0, dataHat, dataHat1.length, dataHat2.length);
                printPermutations(dataHat, permHat);
            }
        }

    }

    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D'};
        printPermutations(data, new char[]{});
    }

}

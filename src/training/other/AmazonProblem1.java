package training.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AmazonProblem1 {

    public static List<Integer> getLexicographicalSmallestPermutation(int size, int sum) {
        if (size <= 0) return Collections.emptyList();
        List<Integer> result = new ArrayList<>(Collections.nCopies(size, 0));
        List<Integer> positiveList = new ArrayList<>();
        int positiveSum = 0;
        for (int i = 1; i <= size; i++) {
            positiveList.add(i);
            positiveSum += i;
        }
        if (positiveSum == sum) return positiveList;
        int residue = positiveSum - sum;
        if (residue%2 != 0) return result;
        int neededNegativeNum = residue / 2;
        List<Integer> negativeList = new ArrayList<>();
        boolean done = false;
        while (!done && !positiveList.isEmpty()) {
            if (positiveList.size() == neededNegativeNum) {
                negativeList.add(-positiveList.removeLast());
                done = true;
            } else if (positiveList.size() < neededNegativeNum){
                int e = positiveList.removeLast();
                negativeList.add(-e);
                neededNegativeNum = neededNegativeNum - e;
            } else {
                int e = positiveList.remove(neededNegativeNum - 1);
                negativeList.add(-e);
                done = true;
            }
        }
        if (!done) return result;
        int i = 0;
        for (Integer a : negativeList) {
            result.set(i, a);
            i++;
        }
        for (Integer b : positiveList) {
            result.set(i, b);
            i++;
        }
        return result;
    }

    public static void main(String[] args) {

        int[][] arrays = {
                {5, -15},
                {5, -14},
                {5, -13},
                {5, -12},
                {5, -11},
                {5, -10},
                {5, -9},
                {5, -8},
                {5, -7},
                {5, -6},
                {5, -5},
                {5, -4},
                {5, -3},
                {5, -2},
                {5, -2},
                {5, 0},
                {5, 9},
                {4, -2},
                {3, 5}
        };
        for (int[] arr : arrays) {
            System.out.println("Size = " + arr[0] + "; Sum = " + arr[1] + "; " +
                    "Lexicographically smallest permutation ==> "
                    + Arrays.toString(getLexicographicalSmallestPermutation(arr[0], arr[1]).toArray()));
        }
    }
}

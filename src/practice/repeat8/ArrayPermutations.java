package practice.repeat8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayPermutations {

    public static List<List<Integer>> permutation(Integer[] arr) {
        if (arr == null || arr.length == 0) return Collections.emptyList();
        List<List<Integer>> result = new ArrayList<>();
        permuteRec(arr, 0, result);
        return result;
    }

    public static void swap(Integer[] arr, int x, int y) {
        Integer t = arr[x];
        arr[x] = arr[y];
        arr[y] = t;
    }

    public static void permuteRec(Integer[] arr, int index, List<List<Integer>> result) {
        if (index == arr.length) {
            result.add((Arrays.asList(Arrays.copyOf(arr, arr.length))));
        }
        for (int i = index; i < arr.length; i++) {
            swap(arr, i, index);
            permuteRec(arr, index+1, result);
            swap(arr, index, i);
        }
    }

    public static void main(String[] args) {
        Integer[] elements = {1, 2, 2};
        List<List<Integer>> result = permutation(elements);
        System.out.println(result.size());
        for (List<Integer> i : result) {
            System.out.println(i);
        }
    }
}

package training.backtracking;

import java.util.*;

public class PermutationsWithoutDuplicates {

    public static void permutation(Character[] arr, int index, List<Character[]> result) {
        if (arr == null || arr.length == 0) return;
        if (index == arr.length) {
            result.add(Arrays.copyOf(arr, arr.length));
        }
        for (int i = index; i < arr.length; i++) {
            swap(arr, i, index);
            permutation(arr, index+1, result);
            swap(arr, index, i);
        }
    }

    public static void permutationNoDuplicate(Character[] arr, int index, List<Character[]> result) {
        if (arr == null || arr.length == 0) return;
        if (index == arr.length) {
            result.add(Arrays.copyOf(arr, arr.length));
        }
        Set<Character> unique = new HashSet<>();
        for (int i = index; i < arr.length; i++) {
            if(unique.contains(arr[i])) {
                continue;
            }
            unique.add(arr[i]);
            swap(arr, i, index);
            permutationNoDuplicate(arr, index+1, result);
            swap(arr, index, i);
        }
    }

    public static void swap(Character[] arr, int x, int y) {
        Character t = arr[x];
        arr[x] = arr[y];
        arr[y] = t;
    }

    public static void main(String[] args) {
        Character[] arr = {'x', 'y', 'y'};
        List<Character[]> result = new ArrayList<>();
        System.out.println("------------------------------------------");
        permutation(arr, 0, result);
        System.out.println("Size ==> " + result.size());
        for (Character[] c : result) {
            System.out.println(Arrays.toString(c));
        }
        arr = new Character[]{'x', 'y', 'y'};
        result.clear();
        System.out.println("------------------------------------------");
        permutationNoDuplicate(arr, 0, result);
        System.out.println("Size ==> " + result.size());
        for (Character[] c : result) {
            System.out.println(Arrays.toString(c));
        }
    }
}

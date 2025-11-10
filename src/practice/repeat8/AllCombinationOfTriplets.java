package practice.repeat8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AllCombinationOfTriplets {

    public static List<List<Integer>> getTriplets(int[] arr, int sum) {
        if (arr == null || arr.length <= 3) return Collections.emptyList();
        Arrays.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < arr.length-2; i++) {
            if (i > 0 && arr[i] == arr[i-1]) continue;
            int j = i + 1;
            int k = arr.length - 1;
            int minPossible = arr[i] + arr[j] + arr[j+1];
            int maxPossible = arr[i] + arr[k] + arr[k-1];
            if (minPossible > sum) break;
            if (maxPossible < sum) continue;
            while (j < k) {
                if (arr[i] + arr[j] + arr[k] == sum) {
                    List<Integer> list = new ArrayList<>();
                    list.add(arr[i]);
                    list.add(arr[j]);
                    list.add(arr[k]);
                    result.add(list);
                    int leftVal = arr[j];
                    int rightVal = arr[k];
                    while (j < k && leftVal == arr[j]) j++;
                    while (j < k && rightVal == arr[k]) k--;
                } else if (arr[i] + arr[j] + arr[k] < sum) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[][] arrs = {
                {1, -3, -8, 3, 6, 2, 5, -1},
                {1, 5, 4, 8, 2, 0}
        };
        int[] targets = {0, 10};
        for (int i = 0; i < arrs.length; i++) {
            System.out.println(Arrays.toString(arrs[i]));
            List<List<Integer>> result = getTriplets(arrs[i], targets[i]);
            for (List<Integer> triplet : result) {
                System.out.println(Arrays.toString(triplet.toArray()));
            }
        }
    }
}

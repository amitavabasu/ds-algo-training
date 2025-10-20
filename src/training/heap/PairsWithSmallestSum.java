package training.heap;

import java.util.*;

public class PairsWithSmallestSum {
    /*
        # K Pairs with Smallest Sums

        You are given two integer arrays, `arr1` and `arr2`, and an integer `k`. Each array is sorted and without duplicates.

        Return an array with the `k` pairs of the form `[arr1[i], arr2[j]]`, with the smallest sums, sorted by their sums.

        - If there are fewer than `k` pairs, return all pairs.
        - The first element should always come from `arr1` and the second from `arr2`.
        - Break ties by the smallest value in `arr1`.

        Example 1:
        arr1 = [1, 2, 3], arr2 = [4, 5], k = 3
        Output: [[1, 4], [1, 5], [2, 4]]
        The first 3 pairs with the smallest sums are:
        1. [1, 4] -> sum = 5
        2. [1, 5] -> sum = 6
        3. [2, 4] -> sum = 6
        The pair [1, 5] goes before [2, 4] because 1 < 2.

        Example 2:
        arr1 = [-1, 0, 2], arr2 = [-3, -1, 4], k = 4
        Output: [[-1, -3], [0, -3], [-1, -1], [0, -1]]
     */

public static List<List<Integer>> getPairsWithSmallestSum(int[] arr1, int[] arr2, int k) {
    if (arr1 == null || arr1.length == 0 || arr2 == null || arr2.length == 0) return Collections.emptyList();
    List<List<Integer>> result = new LinkedList<>();
    PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>(
            new Comparator<int[]>(){
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[0] == o2[0]) {
                        return Integer.compare(o1[1], o2[1]);
                    } else {
                        return Integer.compare(o1[0], o2[0]);
                    }
                }
            }
    );
    for (int i = 0; i < arr1.length; i++) {
        minHeap.offer(new int[]{arr1[i]+arr2[0], i, 0});
    }
    while (result.size() < k && !minHeap.isEmpty()) {
        List<Integer> pair = new LinkedList<Integer>();
        int[] element = minHeap.poll();
        int i = element[1];
        int j = element[2];
        pair.add(arr1[i]);
        pair.add(arr2[j]);
        result.add(pair);
        if (j + 1 < arr2.length) {
            minHeap.offer(new int[]{arr1[i]+arr2[j+1], i, j+1});
        }
    }
    return result;
}

    public static List<List<Integer>> getPairsWithSmallestSum2(int[] arr1, int[] arr2, int k) {
        if (arr1 == null || arr1.length == 0 || arr2 == null || arr2.length == 0) return Collections.emptyList();
        List<List<Integer>> result = new LinkedList<>();
        PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>(
                new Comparator<int[]>(){
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        if (o1[0] == o2[0]) {
                            return Integer.compare(o1[1], o2[1]);
                        } else {
                            return Integer.compare(o1[0], o2[0]);
                        }
                    }
                }
        );
        Set<String> set = new HashSet<>();
        minHeap.offer(new int[]{arr1[0]+arr2[0], 0, 0});
        set.add("00");
        while (result.size() < k && !minHeap.isEmpty()) {
            List<Integer> pair = new LinkedList<Integer>();
            int[] element = minHeap.poll();
            int i = element[1];
            int j = element[2];
            pair.add(arr1[i]);
            pair.add(arr2[j]);
            result.add(pair);
            if (i + 1 < arr1.length && j + 1 < arr2.length) {
                if (!set.contains(i+(j+1)+""))
                    minHeap.offer(new int[]{arr1[i]+arr2[j+1], i, j+1});
                if (!set.contains((i+1)+j+""))
                    minHeap.offer(new int[]{arr1[i+1]+arr2[j], i+1, j});
            }
        }
        return result;
    }


public static void main(String[] args) {
    int[][][]
            arrays = {
            {{1,2,3},{4,5}, {3}},
            {{-1,0,2},{-3,-1,4}, {4}}
    };

    for (int[][] arr : arrays) {
        System.out.println(Arrays.toString(arr[0]) + " & " + Arrays.toString(arr[1]) + "K = " + arr[2][0]
        + " ==> " + Arrays.toString(getPairsWithSmallestSum(arr[0], arr[1], arr[2][0]).toArray())
        + " ==> " + Arrays.toString(getPairsWithSmallestSum2(arr[0], arr[1], arr[2][0]).toArray()));
    }
}
}

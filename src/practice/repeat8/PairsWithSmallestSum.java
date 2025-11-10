package practice.repeat8;
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

import java.util.*;

public class PairsWithSmallestSum {

    public static List<List<Integer>> getPairsWithSmallestSum1(int[] arr1, int[] arr2, int k) {
        if (arr1 == null || arr1.length == 0 || arr2 == null || arr2.length == 0) return Collections.emptyList();
        List<List<Integer>> result = new ArrayList<>();
        PriorityQueue<int[]> heap = new PriorityQueue<>( new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[2] == o2[2]) {
                    return Integer.compare(arr1[o1[0]], arr1[o2[0]]);
                } else {
                    return Integer.compare(o1[2], o2[2]);
                }
            }
        });
        for (int i = 0; i < arr2.length; i++) {
            heap.offer(new int[]{0, i, arr1[0] + arr2[i]});
        }
        while (!heap.isEmpty() && result.size() < k) {
            int[] element = heap.remove();
            int x = element[0];
            int y = element[1];
            List<Integer> pairs = new ArrayList<>();
            pairs.add(arr1[x]);
            pairs.add(arr2[y]);
            result.add(pairs);
            if ( x+1 < arr1.length) {
                heap.offer(new int[]{x+1, y, arr1[x+1]+arr2[y]});
            }
        }
        return result;
    }

    public static List<List<Integer>> getPairsWithSmallestSum2(int[] arr1, int[] arr2, int k) {
        if (arr1 == null || arr1.length == 0 || arr2 == null || arr2.length == 0) return Collections.emptyList();
        List<List<Integer>> result = new ArrayList<>();
        PriorityQueue<int[]> heap = new PriorityQueue<>( new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[2] == o2[2]) {
                    return Integer.compare(arr1[o1[0]], arr1[o2[0]]);
                } else {
                    return Integer.compare(o1[2], o2[2]);
                }
            }
        });
        Set<String> set = new HashSet<>();
        heap.offer(new int[]{0, 0, arr1[0]+arr2[0]});
        set.add("0,0");
        while (!heap.isEmpty() && result.size() < k) {
            int[] element = heap.remove();
            int x = element[0];
            int y = element[1];
            List<Integer> pairs = new ArrayList<>();
            pairs.add(arr1[x]);
            pairs.add(arr2[y]);
            result.add(pairs);
            if (!set.contains(x+","+(y+1)) && y < arr2.length-1) {
                set.add(x + "," + (y + 1));
                heap.offer(new int[]{x, y + 1, arr1[x] + arr2[y + 1]});
            }
            if (!set.contains((x+1)+","+y) && x < arr1.length-1) {
                set.add((x+1) + "," + y);
                heap.offer(new int[]{x+1, y, arr1[x+1] + arr2[y]});
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
            System.out.println(Arrays.toString(arr[0]) + " & " + Arrays.toString(arr[1]) + "; K = " + arr[2][0]
                    + " ==> " + Arrays.toString(getPairsWithSmallestSum1(arr[0], arr[1], arr[2][0]).toArray())
                    + " ==> " + Arrays.toString(getPairsWithSmallestSum2(arr[0], arr[1], arr[2][0]).toArray()));
        }
    }
}

package training.search_sort;

import java.util.Arrays;

public class MergeSort {


    public static int[] mergeSort(int[] arr) {
        int[] res = new int[arr.length];
        int l = 0;
        int r = arr.length-1;
        mergeSortRec(arr, res, l, r);
        return res;
    }

    public static void mergeSortRec(int[] arr, int[] res, int l, int r) {
        if (l < r) {
            int mid = (l + r) / 2;
            mergeSortRec(arr, res, l, mid);
            mergeSortRec(arr, res, mid+1, r);
            merge(arr, res, l, mid, mid+1, r);
        }
    }

    public static void merge(int[] arr, int[] res, int l1, int r1, int l2, int r2) {
        int ri = l1;
        int i = l1;
        int j = l2;
        while ((i <= r1) && (j <= r2)) {
            if (arr[i] <= arr[j]) {
                res[ri++] = arr[i++];
            } else {
                res[ri++] = arr[j++];
            }
        }
        while (i <= r1) {
            res[ri++] = arr[i++];
        }
        while (j <= r2) {
            res[ri++] = arr[j++];
        }
        for (int k = l1; k <= r2; k++) {
            arr[k] = res[k];
        }
    }

    public static void main(String[] args) {
        int[][] inputs = {
                {3,4,2,1,5,8,6,7},
                {8,7,6,5,4,3,2,1},
                {1,2,3,5,4,6,7,8}
        };

        for(int[] input : inputs) {
            System.out.print (Arrays.toString(input) + " --> ");
            int[] result = mergeSort(input);
            System.out.println(Arrays.toString(result) + ".");
        }
    }
}

package practice.repeat1;

import java.util.Arrays;

public class MergeSort {

    public static void mergeSort(int[] arr, int[] res, int l, int r) {
        if (l < r) {
            int m = (int)Math.floor((l + r) /2);
            mergeSort(arr, res, l, m);
            mergeSort(arr, res, m+1, r);
            merge(arr, res, l, m, r);
        }
    }

    public static void merge(int[] arr, int[] res, int l, int m, int r) {
        int i = l;
        int j = m+1;
        int k = l;
        while (i <=m && j <=r) {
            if (arr[i] <= arr[j]) {
                res[k++] = arr[i++];
            } else {
                res[k++] = arr[j++];
            }
        }
        while (i <= m) {
            res[k++] = arr[i++];
        }
        while (j <= r) {
            res[k++] = arr[j++];
        }
        for (i = l; i<=r; i++) {
            arr[i] = res[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5,10,2,3,4,8,7,9,1,6};
        int[] res = new int[arr.length];
        int l = 0;
        int r = arr.length - 1;
        System.out.println(Arrays.toString(arr));
        mergeSort(arr, res, l, r);
        System.out.println(Arrays.toString(res));
    }

}

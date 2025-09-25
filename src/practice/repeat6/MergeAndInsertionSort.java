package practice.repeat6;

import java.util.Arrays;

public class MergeAndInsertionSort {

    public static void mergeSort(int[] arr, int l, int r, int[] result) {
        if (l < r) {
            int mid = ((int)(l + r) / 2);
            mergeSort(arr, l, mid, result);
            mergeSort(arr, mid+1, r, result);
            merge(arr, l, mid, mid+1, r, result);
        }
    }

    public static void merge(int[] arr, int l1, int r1, int l2, int r2, int[] result) {
        int i = l1;
        int j = l2;
        int k = l1;
        while ( i <= r1 && j <= r2) {
            if (arr[i] < arr[j]) {
                result[k++] = arr[i++];
            }
            else {
                result[k++] = arr[j++];
            }
        }
        while(i <= r1){
            result[k++] = arr[i++];
        }
        while(j <= r2) {
            result[k++] = arr[j++];
        }
        k = l1;
        while(k <= r2) {
            arr[k] = result[k];
            k++;
        }
    }

    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length == 0 || arr.length == 1) return;
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i-1;
            while ( j >= 0) {
                if (arr[j] > key) {
                    swap(arr, j, j+1);
                }
                j--;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] array = {9,0,8,1,7,2,6,3,5,4};

        int[] result = new int[array.length];
        mergeSort(array, 0, array.length-1, result);
        System.out.println(Arrays.toString(result));

        int[] array3 = {9,0,8,1,7,2,6,3,5,4};
        insertionSort(array3);
        System.out.println(Arrays.toString(array3));
    }
}

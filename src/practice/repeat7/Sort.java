package practice.repeat7;

import java.util.Arrays;

public class Sort {


    public static void quickSort(int[] arr) {
        if (arr == null || arr.length == 0) return;
        quickSortRec(arr, 0, arr.length-1);
    }

    public static void quickSortRec(int[] arr, int l, int r) {
        if (l < r) {
            int partitionIndex = partition(arr, l, r);
            quickSortRec(arr, l, partitionIndex - 1);
            quickSortRec(arr, partitionIndex + 1, r);
        }
    }

    public static int partition(int[] arr, int l, int r) {
        int pivot = arr[r];
        int i = l, j = l;
        while (j < r) {
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
            j++;
        }
        swap(arr, i, j);
        return i;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] mergeSort(int[] arr) {
        if (arr == null || arr.length == 0) return arr;
        int[] result = new int[arr.length];
        mergeSortRec(arr, 0, arr.length-1, result);
        return result;
    }

    public static void mergeSortRec(int[] arr, int l, int r, int[] result) {
        if (l < r) {
            int m = (l+r)/2;
            mergeSortRec(arr, l, m, result);
            mergeSortRec(arr, m+1, r, result);
            merge(arr, l, m, m+1, r, result);
        }
    }

    public static void merge(int[] arr, int l1, int r1, int l2, int r2, int[] result) {
        int i = l1;
        int j = l2;
        int k = l1;
        while (i <= r1 && j <= r2) {
            if (arr[i] < arr[j]) {
                result[k++] = arr[i++];
            } else {
                result[k++] = arr[j++];
            }
        }
        while (i <= r1) {
            result[k++] = arr[i++];
        }
        while (j <= r2) {
            result[k++] = arr[j++];
        }
        k = l1;
        while (k <= r2) {
            arr[k] = result[k];
            k++;
        }
    }

    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length == 0) return;
        for (int i = 1; i < arr.length; i++) {
            int j = i-1;
            while (j >= 0) {
                if (arr[j] > arr[j+1]) swap(arr, j, j+1);
                j--;
            }
        }



    }

    public static void main(String[] args) {
        int[] array = {900,1000,200,500,600,700,300,400,800,100};
        quickSort(array);
        System.out.println("element value ==> " + Arrays.toString(array));

        array = new int[]{900,1000,200,500,600,700,300,400,800,100};
        System.out.println("element value ==> " + Arrays.toString(mergeSort(array)));

        array = new int[]{900,1000,200,500,600,700,300,400,800,100};
        insertionSort(array);
        System.out.println("element value ==> " + Arrays.toString(array));
    }
}

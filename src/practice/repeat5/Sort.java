package practice.repeat5;

import java.util.Arrays;

public class Sort {

    public static void mergeSort(int[] array, int l, int r, int[] result) {

        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(array, l, m, result);
            mergeSort(array, m+1, r, result);
            merge(array, l, m, m+1,r, result);
        }
    }

    public static void merge(int[] array, int l1, int r1, int l2, int r2, int[] result) {
        int i = l1;
        int j = l2;
        int x = l1;
        while ( i <= r1 && j <= r2) {
            if (array[i] < array[j]) {
                result[x++] = array[i++];
            } else {
                result[x++] = array[j++];
            }
        }
        while (i <= r1) result[x++] = array[i++];
        while (j <= r2) result[x++] = array[j++];
        int y = l1;
        while (y <= r2) {
            array[y] = result[y];
            y++;
        }
    }

    public static void quickSort(int[] array, int l, int r) {
        if (l < r) {
            int partition = partition(array, l, r);
            quickSort(array, l, partition-1);
            quickSort(array, partition+1, r);
        }
    }

    public static int partition(int[] array, int l, int r) {
        int pvt = array[r];
        int i = l;
        int j = l;
        while (j < r) {
            if (array[j] < pvt) {
                swap(array, i, j);
                i++;
            }
            j++;
        }
        swap(array, i, j);
        return i;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void insertionSort(int[] array) {
        if (array == null || array.length == 0) return;
        for ( int i = 1; i < array.length; i++ ) {
            int key = array[i];
            int j = i-1;
            while (j >= 0 && array[j] > key) {
                swap(array, j, j+1);
                j--;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {9,0,8,1,7,2,6,3,5,4};

        int[] result = new int[array.length];
        mergeSort(array, 0, array.length-1, result);
        System.out.println(Arrays.toString(result));

        int[] array2 = {9,0,8,1,7,2,6,3,5,4};
        quickSort(array2, 0, array2.length-1);
        System.out.println(Arrays.toString(array2));

        int[] array3 = {9,0,8,1,7,2,6,3,5,4};
        insertionSort(array3);
        System.out.println(Arrays.toString(array3));
    }

}

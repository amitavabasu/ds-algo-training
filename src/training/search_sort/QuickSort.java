package training.search_sort;

import java.util.Arrays;

public class QuickSort {

    public static void quickSort(int[] input, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(input, left, right);
            quickSort(input, left, partitionIndex-1);
            quickSort(input, partitionIndex+1, right);
        }
    }

    public static int partition(int[] input, int left, int right) {
        int p = input[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (input[j] < p) {
                swap(input, i, j);
                i++;
            }
        }
        swap(input, i, right);
        return i;
    }

    public static void swap(int[] input, int i, int j) {
        if( i == j) return;
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    public static void quickSort2(int[] input, int left, int right) {
        if (left < right) {
            int partitionIndex = partition2(input, left, right);
            quickSort2(input, left, partitionIndex-1);
            quickSort2(input, partitionIndex+1, right);
        }
    }

    public static int partition2(int[] input, int left, int right) {
        int i = left;
        int pivotIndex = right;
        int pivotValue = input[pivotIndex];
        while ( i < pivotIndex) {
            if (input[i] > pivotValue) {
                swap2(input, i, pivotIndex);
                pivotIndex--;
            } else {
                i++;
            }
        }
        return pivotIndex;
    }

    public static void swap2(int[] input, int i, int j) {
        if( i == j) return;
        int temp1 = input[j-1];
        int temp2 = input[i];
        input[i] = temp1;
        input[j-1] = input[j];
        input[j] = temp2;
    }

    public static void quickSort3(int[] arr, int l, int r) {
        if(l < r) {
            int splitIndex = findSplitIndex(arr, l, r);
            quickSort3(arr, l, splitIndex - 1);
            quickSort3(arr, splitIndex + 1, r);
        }
    }

    public static int findSplitIndex(int[] arr, int l, int r) {
        int pivotIndex = r;
        int pivotValue = arr[r];
        int i = l;
        while (i < pivotIndex) {
            if (arr[i] > pivotValue) {
                swap3(arr, i, pivotIndex);
                pivotIndex--;
            } else {
                i++;
            }
        }
        return pivotIndex;
    }

    public static void swap3(int[] arr, int i, int j) {
        if (i == j) return;
        int temp1 = arr[i];
        int temp2 = arr[j];
        arr[j] = temp1;
        arr[i] = arr[j-1];
        arr[j-1] = temp2;
    }







    public static void main(String[] args) {
        int[][] inputs = {
                {9,8,7,6,5,4,3,2,1},
                {8,7,6,5,4,3,2,1},
                {1,2,3,5,4,6,7,8}
        };

        for(int[] input : inputs) {
            System.out.print (Arrays.toString(input) + " --> ");
            quickSort(input,0,input.length-1);
            System.out.println(Arrays.toString(input) + ".");
        }
    }

}

package training.search_sort;

import java.util.Arrays;

public class SortingTechniques {

    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void bubbleSort(int[] array) {
        if (array == null || array.length <= 1) return;
        for (int i = 0; i < array.length-1; i++) {//<-- for the entire array except the last element
            for (int j = 0; j < array.length-i-1; j++) {//<-- for the reduced length of array in each iteration.
                if (array[j] > array[j+1]) { //<-- if a grater value found grater than it's predecessor bubble it up.
                    swap(array, j, j+1);//<-- at the end of each iteration the max value will be at the end.
                }
            }
        }
    }

    public static void selectionSort(int[] array) {//Kind of opposite of bubble sort.
        if (array == null || array.length <= 1) return;
        for (int i = 0; i < array.length-1; i++) {//<-- for the entire array except the last element
            int minValIndex = i;//initialize min value index at the beginning element.
            for (int j = i; j < array.length; j++) { //trace through the rest of the array to find the index of min value in
                if (array[j] < array[minValIndex]) {
                    minValIndex = j;
                }
            }
            swap(array, i, minValIndex);//Swap the value at the min value index with the element at the leftmost segment of the array
        }
    }

    public static void insertionSort(int[] array) {
        if (array == null || array.length <= 1) return;
        for (int i = 1; i < array.length; i++) {//Start from the second element of the array and finish at the last element of the array
            int key = array[i];//Key is the value of the element to be placed at right spot
            int j = i - 1;//Start one element behind key and move backward until end
            while (j >= 0 && array[j] > key) { //<-- Move backward until hit leftmost element or the previous value is grater than key. Push key down to the level where it belongs.
                swap(array, j, j + 1);
                j--;
            }
        }
    }

    public static void mergeSort(int[] array, int[] result, int l, int r) {
        if (l >= r) return;
        int m = (l+r)/2;
        mergeSort(array, result, l, m);
        mergeSort(array, result, m+1, r);
        merge(array, result, l, m, m+1, r);
    }

    public static void merge(int[] array, int[] result, int l1, int r1, int l2, int r2) {
        int resultIndex = l1;
        int leftIdx = l1;
        int rightIdx = l2;
        while (leftIdx <= r1 && rightIdx <= r2) {
            if (array[leftIdx] < array[rightIdx]) {
                result[resultIndex++] = array[leftIdx++];
            } else {
                result[resultIndex++] = array[rightIdx++];
            }
        }
        while (leftIdx <= r1) {
            result[resultIndex++] = array[leftIdx++];
        }
        while (rightIdx <= r2) {
            result[resultIndex++] = array[rightIdx++];
        }

        for (int k = l1; k <= r2; k++) {
            array[k] = result[k];
        }
    }

    public static void quickSort(int[] array, int l, int r) {
        if ( l < r) {
            int p = partition(array, l, r);
            quickSort(array, l, p-1);
            quickSort(array, p+1, r);
        }
    }

    public static int partition(int[] array, int l, int r) {
        int pVal = array[r];
        int pIdx = l;
        for (int j = l; j < r; j++) {
            if (array[j] < pVal) {
                swap(array, pIdx, j);
                pIdx++;
            }
        }
        swap(array, pIdx, r);
        return pIdx;
    }





    public static void main(String[] args) {
        int[] array = {3,5,0,2,6,1,9,7,8,4};
        System.out.print(Arrays.toString(array) + " ==> ");
        bubbleSort(array);
        System.out.println(Arrays.toString(array) + " (Bubble Sort)");

        array = new int[]{3,5,0,2,6,1,9,7,8,4};
        System.out.print(Arrays.toString(array) + " ==> ");
        selectionSort(array);
        System.out.println(Arrays.toString(array) + " (Selection Sort)");

        array = new int[]{3,5,0,2,6,1,9,7,8,4};
        System.out.print(Arrays.toString(array) + " ==> ");
        insertionSort(array);
        System.out.println(Arrays.toString(array) + " (Insertion Sort)");

        array = new int[]{3,5,0,2,6,1,9,7,8,4};
        System.out.print(Arrays.toString(array) + " ==> ");
        mergeSort(array, new int[array.length], 0, array.length-1);
        System.out.println(Arrays.toString(array) + " (Merge Sort)");

        array = new int[]{3,5,0,2,6,1,9,7,8,4};
        System.out.print(Arrays.toString(array) + " ==> ");
        quickSort(array, 0, array.length-1);
        System.out.println(Arrays.toString(array) + " (Quick Sort)");
    }


}

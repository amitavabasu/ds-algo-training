package practice.repeat6;

public class QuickSearch {

    public static int findNthElement(int[] arr, int indexToFind) {
        if (arr == null || arr.length == 0 || arr.length < indexToFind) return -1;
        return quickSearch(arr, 0, arr.length-1, indexToFind);
    }

    public static int quickSearch(int[] arr, int l, int r, int t) {
        if (l <= r) {
            int partitionIndex = partition(arr, l, r);
            if (partitionIndex == t) return arr[partitionIndex];
            if (partitionIndex < t) {
                return quickSearch(arr, partitionIndex+1, r, t);
            } else {
                return quickSearch(arr, l, partitionIndex-1, t);
            }
        }
        return -1;
    }

    public static int partition(int[] arr, int l, int r) {
        int pivot = arr[r];
        int i = l;
        while (l < r) {
            if (arr[l] < pivot) {
                swap(arr, i, l);
                i++;
            }
            l++;
        }
        swap(arr, i, r);
        return i;
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] array = {900,1000,200,500,600,700,300,400,800,100};
        int n = 1;
        System.out.println("element value ==> " + findNthElement(array, n-1));
    }
}

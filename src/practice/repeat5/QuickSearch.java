package practice.repeat5;

public class QuickSearch {


    public static int findNthElement(int[] arr, int n) {
        if (arr == null || arr.length == 0) return -1;
        int indexToFind = arr.length - n - 1;
        int l = 0;
        int r = arr.length - 1;
        return quickSearch(arr, l, r, n);
    }

    public static int quickSearch(int[] arr, int l, int r, int n) {
        if (l <= r) {
            int partitionIndex = partition(arr, l, r);
            if (partitionIndex == n) return arr[partitionIndex];
            if (partitionIndex < n) {
                return quickSearch(arr, partitionIndex + 1, r, n);
            } else {
                return quickSearch(arr, l, partitionIndex - 1, n);
            }
        } else {
            return -1;
        }
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
        swap(arr, i, l);

        return i;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }




    public static void main(String[] args) {
        int[] array = {900,1000,200,500,600,700,300,400,800,100};
        int n = 11;
        System.out.println("element value ==> " + findNthElement(array, n-1));
    }

}

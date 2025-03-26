package training.search_sort;

public class QuickSearchNthElement {

    public static  int quickSearch(int[] array, int l, int r, int indexToFind) {
        if (l <= r) {
            int partitionIndex = partition(array, l, r);
            if (partitionIndex == indexToFind) return array[indexToFind];
            if (partitionIndex < indexToFind) {
                return quickSearch(array, partitionIndex+1, r, indexToFind);
            } else {
                return quickSearch(array, l, partitionIndex-1, indexToFind);
            }
        }
        return -1;
    }

    public static int partition(int[] array, int l, int r) {
        int p = array[r];
        int i = l;
        int j = l;
        while(j < r) {
            if (array[j] < p) {
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


    public static int findNthElement(int[] array, int n) {
        if (array == null || array.length == 0) return -1;
        if (array.length < n || n < 0) return -1;
        int indexToFind = array.length - n - 1;
        return quickSearch(array, 0, array.length-1, indexToFind);

    }



    public static void main(String[] args) {
        int[] array = {900,1000,200,500,600,700,300,400,800,100};
        int k = 0;
        System.out.println("element value ==> " + findNthElement(array, k));
    }

}

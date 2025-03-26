package training.search_sort;

import java.util.Arrays;

public class SearchingStartAndEndInArray {

    public static int[] findStartAndEndIndex(int[] array, int target) {
        int[] result = new int[] {-1,-1};
        if (array == null || array.length == 0) return result;
        int pos = binarySearch(array, 0, array.length-1, target);
        if (pos == -1) return result;
        int leftIndex = pos;
        int rightIndex = pos;

        while (pos != -1) {
            leftIndex = pos;
            pos = binarySearch(array, 0, pos-1, target);
        }
        pos = rightIndex;
        while (pos != -1) {
            rightIndex = pos;
            pos = binarySearch(array, pos+1, array.length-1, target);
        }
        result = new int[]{leftIndex,rightIndex};
        return result;
    }

    public static int binarySearch(int[] array, int l, int r, int t) {
        if (l <= r) {
            int mid = (l + r) / 2;
            if (array[mid] == t) return mid;
            if (t > array[mid]) {
                return binarySearch(array, mid+1, r, t);
            } else {
                return binarySearch(array, l, mid-1, t);
            }
        }
        return -1;
    }






    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,5,5,5,5,6,7,8,9,10,11};
        System.out.println (binarySearch(array, 0, array.length-1, 5));
        System.out.println (Arrays.toString(findStartAndEndIndex(array, 5)));
    }

}

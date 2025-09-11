package practice.repeat5;

import java.util.Arrays;

public class StartAndEndSortedArrayWithDuplicates {

    public static int[] startAndEnd(int[] array, int target) {
        if (array == null || array.length == 0) return new int[]{-1,-1};
        int pos = binarySearch(array, 0, array.length-1, target);
        if (pos == -1) return new int[]{-1,-1};
        int left = pos;
        int right = pos;
        while (pos != -1) {
            left = pos;
            pos = binarySearch(array, 0, pos-1, target);
        }
        pos = right;
        while (pos != -1) {
            right = pos;
            pos = binarySearch(array, pos+1, array.length-1, target);
        }
         return new int[]{left, right};
    }

    public static int binarySearch(int[] array, int l, int r, int target) {
        if (l <= r) {
            int mid = (l+r) / 2;
            if (array[mid] == target) return mid;
            if (array[mid] < target) {
               return binarySearch(array, mid+1, r, target);
            } else {
                return binarySearch(array, l, mid-1, target);
            }
         } else {
            return -1;
        }

    }

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,5,5,5,5,5,6,7,8,9,10,11,11};

        System.out.println(Arrays.toString(startAndEnd(array, 17)));

    }

}

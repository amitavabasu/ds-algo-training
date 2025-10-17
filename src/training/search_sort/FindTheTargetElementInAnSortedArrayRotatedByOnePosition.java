package training.search_sort;

import java.util.Arrays;

public class FindTheTargetElementInAnSortedArrayRotatedByOnePosition {
    /*
        Find an element in a sorted array but rotated
        Arr: [8, 9, 10, 1, 2, 3, 4, 5, 6, 7] //Sorted array but rotated
        Target: 5
        Result: 7
        Pivot: 3

        Needs to have O(logN)
     */

    public static int binarySearchRotatedSortedArray(int[] arr, int t) {
        if (arr == null || arr.length == 0) return Integer.MIN_VALUE;
        int n = arr.length;
        int left = 0;
        int right = n-1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == t) return mid;
            if (arr[left] > arr[mid]) { //<-- this means right side of the array is sorted
                if (arr[mid] < t &&  t <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid-1;
                }
            } else { //<-- this means left side of the array is sorted
                if (arr[left] <= t && t < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return Integer.MIN_VALUE;
    }

    public static int findPivotIndex(int[] arr) {
        if (arr == null || arr.length == 0) return Integer.MIN_VALUE;
        int n = arr.length;
        int left = 0;
        int right = n-1;
        int mid = Integer.MIN_VALUE;
        while (left < right) {
            mid = (left + right) / 2;
            if (arr[left] < arr[mid]) {//<-- left of the array is sorted the pivot is on the right move right
                left = mid+1;
            } else {//<-- right of the array is sorted, the pivot is on the left
                right = mid;
            }
        }
        return left;
    }


    public static void main(String[] args) {
        int[][] arrays = {
                {8, 9, 10, 1, 2, 3, 4, 5, 6, 7}
        };
        int[] target = {5};
        for (int i = 0; i < arrays.length; i++) {
            System.out.println("Array==> " + Arrays.toString(arrays[i]) + "; Target==> " + target[i] + "; TargetIndex==> "
                    + binarySearchRotatedSortedArray(arrays[i], target[i])
                    + "; Pivot==> " + findPivotIndex(arrays[i]));
        }
    }

}

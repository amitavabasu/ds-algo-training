package practice.repeat8;

import java.util.Arrays;

public class TwoArrayOneSum {

    public static int[] findIndexesForSum(int[] sorted, int[] unsorted, int sum) {
        int[] result = new int[]{-1, -1};
        if (sorted == null || sorted.length == 0 || unsorted == null || unsorted.length == 0) return result;
        for (int i = 0; i < unsorted.length; i++) {
            int current = unsorted[i];
            int find = sum - current;
            int l = 0;
            int r = sorted.length - 1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (sorted[mid] == find) {
                    result = new int[]{mid, i};
                    return result;
                } else if (sorted[mid] < sum) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[][][] arrays = {
                {{}, {}},
                {{-5, -4, -1, 4, 6, 6, 7}, {-3, 7, 18, 4, 6}},
                {{1, 2, 3}, {1, 2, 3}},
                {{-2, 0, 1, 2}, {0, 2, -2, 4}}
        };
        for(int[][] arr :arrays) {
            System.out.println("Result ==> " + Arrays.toString(findIndexesForSum(arr[0], arr[1],0)));
        }
    }
}

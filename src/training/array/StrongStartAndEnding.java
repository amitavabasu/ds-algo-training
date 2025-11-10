package training.array;

import java.util.Arrays;

/*
    # Strong Start And Ending

    Imagine that you have a little bookstore. We have an array, `projected_sales`, with the projected number of sales per day of the fall season.

    We would like to start and close the season strong. We want to have as many consecutive _good_ days as possible starting from day `0` and as many consecutive _good_ days as possible ending on the last day.

    A _good_ day is a day with at least `10` sales.

    We can pick `k` days to boost with advertising, which we expect to boost the sales on those specific days by at least `20`. What's the maximum number of combined initial good days and final good days we can have?

    Example 1: projected_sales = {10, 0, 0, 0, 10, 0, 0, 10}, k = 2
    Output: 5
    We should boost days 5 and 6 so that the projected sales after boosting are:
        {10, 0, 0, 0, 10, 20, 20, 10}
    This way, we have 1 initial and 4 final good days.

    Example 2: projected_sales = {0, 10, 0, 10}, k = 1
    Output: 3
    It does not matter which day you boost.

    Example 3: projected_sales = {5, 5, 5}, k = 1
    Output: 2
    We can boost any two days.

    Constraints:

    - `0 <= len(projected_sales) <= 10^5` (the bookstore is in a fictional world with very long fall seasons)
    - `0 <= projected_sales{i} <= 10^3`
    - `0 <= k <= 10^5`
 */
public class StrongStartAndEnding {

    public static int maxStartAndEnd(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0) return 0;
        int badDays = 0;
        for (int x : arr) {
            if (x < 10) badDays++;
        }
        if (badDays <= k) return arr.length;
        int maxCount = 0;
        for (int x = 0; x <= k; x++) {
            int l = 0;
            int r = arr.length-1;
            int y = x;
            int count1 = 0;
            while(l < arr.length) {
                if (arr[l] < 10) {

                    if (y == 0) break;
                    l++;
                    y--;
                    count1++;
                } else {
                    count1++;
                    l++;
                }
            }

            y = k-x;
            int count2 = 0;
            while(r >= l) {
                if (arr[r] < 10) {
                    if (y == 0) break;
                    r--;
                    y--;
                    count2++;
                } else {
                    count2++;
                    r--;
                }
            }
            maxCount = Math.max(maxCount, (count1+count2));
        }
        return maxCount;
    }


    public static int maxStartAndEndBetter(int[] arr, int k) {
        int maxCount = 0;
        if (arr == null || arr.length == 0) return 0;
        int n = arr.length;
        if (k >= n) return arr.length;
        int i = 0;
        int l = 0;
        int[] left = new int[k+1];
        while (i <= k && l < n) {
            if ( arr[l++] >= 10) {
                left[i]++;
            } else {
                i++;
                if (i > k) break;
                left[i] = left[i-1] + 1;
            }
        }
        int j = 0;
        int r = n-1;
        int[] right = new int[k+1];
        while (j <= k && r >= 0) {
            if ( arr[r--] >= 10) {
                right[j]++;
            } else {
                j++;
                if (j > k) break;
                right[j] = right[j-1] + 1;
            }
        }

        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));
        for (int m = 0; m <= k; m++) {
            maxCount = Math.max(maxCount, left[m] + right[k-m]);
        }
        return maxCount;
    }

    public static void main(String[] args) {
        int[][] arrays = {
                {10, 0, 0, 0, 10, 0, 0, 10}, // k=2 <-- result = 5
                {0, 10, 0, 10}, // k=1 <-- result = 3
                {5, 5, 5} // k=2 <-- result = 2
        };
        int [] k = {2, 1, 2};

        for (int i = 0; i < arrays.length; i++) {
//            System.out.println(Arrays.toString(arrays[i]) + " ==> " + maxStartAndEnd(arrays[i], k[i]));//<-- this O(k * n)
            System.out.println(Arrays.toString(arrays[i]) + " ==> " + maxStartAndEndBetter(arrays[i], k[i])); //<-- this O(n)
        }
    }
}

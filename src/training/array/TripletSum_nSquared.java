package training.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TripletSum_nSquared {
    /*
    Write an algorithm to find triplets added to a target in a given array.
    the array can contain both positive and negative numbers
    Example:
    Arr: [1, -3, -8, 3, 6, 2, 5, -1]
    Target: 0
    Answer: [[-8, 3, 5], [-8, 2, 6], [-3, 2, 1]]
     */


    public static List<List<Integer>> getTriplets(int[] arr, int target) {
        if (arr == null || arr.length < 3) return Collections.emptyList();
        Arrays.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        int n = arr.length;
        for (int i = 0; i < n-2; i++) {
            //Handle duplicates if any
            if (i > 0 && arr[i] == arr[i-1]) continue; //If previous exists, and they are equal skip this one as already done

            int minPossible = arr[i] + arr[i+1] + arr[i+2]; //<-- as the array is sorted the minPossible is the sub of next two numbers plus current number.
            if (minPossible > target) break; //<-- if minPossible is grater than the target then no point continuing as there will be only numbers which will add up more than target.
            int maxPossible = arr[i] + arr[n-1] + arr[n-2]; //<-- the maxPossible will be the current value plus the value of last two numbers
            if (maxPossible < target) continue; //<-- if maxPossible is less than the target, that means we need higher number so go to the next higher number
            //Now we have the target in range this means target >= minPossible and target <= maxPossible
            //So search for those numbers in the range which might end up to the sum of current + left + right
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = (arr[i] + arr[left] + arr[right]);
                if (target == sum) {
                    //We found a triplet
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(arr[i]);
                    triplet.add(arr[left]);
                    triplet.add(arr[right]);
                    result.add(triplet);
                    int leftVal = arr[left];
                    int rightVal = arr[right];
                    while (left < right && leftVal == arr[left]) left++;
                    while (left < right && rightVal == arr[right]) right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] arrs = {
                {1, -3, -8, 3, 6, 2, 5, -1},
                {1, 5, 4, 8, 2, 0}
        };
        int[] targets = {0, 10};
        for (int i = 0; i < arrs.length; i++) {
            System.out.println(Arrays.toString(arrs[i]));
            List<List<Integer>> result = getTriplets(arrs[i], targets[i]);
            for (List<Integer> triplet : result) {
                System.out.println(Arrays.toString(triplet.toArray()));
            }
        }
    }
}

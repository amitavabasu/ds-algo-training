package training.array;

import java.util.Arrays;

public class ContainerWithMostWater {

    // area = (rightIndex - leftIndex) * min (height[leftIndex], height[rightIndex])
    public static int maxArea(int[] height) {
        if (height == null || height.length == 0) return 0; //if there is no height, we can return 0
        int maxArea = 0; //to hold the max value calculated
        //Start from the beginning and end of the array with two pointers
        int l = 0; //left pointer starting at 0
        int r = height.length - 1; //right pointer starting at last index
        while (l <= r) { //until left and right pointers overlaps
            int area = (r - l) * Math.min(height[l], height[r]); //calculate the area using the formula
            if (area > maxArea) maxArea = area;//if the calculated area is grater than maxArea calculated so far, changed the maxArea
            if (height[l] <= height[r]) { //Check the value both at the left and the right pointer. Choose the lowest (smaller) one
                l++; //if left value is smaller increment left pointer
            } else {
                r--;//if right value is smaller decrement the right pointer
            }
        }
        return maxArea;//return maxArea holding the max water.
    }




    public static void main(String[] args) {
        int[][] arrays = {
                {1,8,6,2,5,4,8,3,7},
                {1,1}
        };

        for (int i = 0; i < arrays.length; i++) {
            System.out.println(Arrays.toString(arrays[i]) + " ==> " + maxArea(arrays[i]));
        }
    }
}

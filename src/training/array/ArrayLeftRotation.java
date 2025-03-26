package training.array;

import java.util.Arrays;

public class ArrayLeftRotation {

    public static int[] leftRotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || nums.length == 1) return nums;//if numbers do not exist and k <= 0 no rotations needed so return same array
        int length = nums.length;//calculate length of the array
        k = k % length; //recalculate k value in case k is grater than the length
        int currentIndex = 0;//define and initialize current index to the beginning of the array at 0
        int currentValue = nums[currentIndex];//initialize current value to the current index element, purpose to store it.
        int startIndex = currentIndex;//initialize a new variable as startIndex to know when the calculated index comes back to the same index but the rotation is finished.
        for (int count = 0; count < nums.length; count++) { //repeat for the entire array for all the elements.
            int nextIndex = (currentIndex - k + length) % length;//calculate the index supposed to be of the current index value by the formula
            int tmp = nums[nextIndex]; // store the value of the newly calculated index
            nums[nextIndex] = currentValue;//put the current value into the newly calculated index
            currentValue = tmp; // initialize current value with temp because that was the value is now overwritten
            currentIndex = nextIndex; //current index now becomes newly calculated index.
            if (currentIndex == startIndex && currentIndex < length-1) {//now check if currentIndex is back to startIndex or not, and also currentIndex reached
                // the last element or not. If not then we have not completed full rotation, so start fresh from the currentIndex + 1 i.e. next index.
                currentIndex++; //increment current index to the next index
                currentValue = nums[currentIndex]; //initialize currentValue to the element value of the newly initialized currentIndex
                startIndex = currentIndex; //re-initialize startIndex to the newly incremented current index
            }
        }
        return nums;//Finally return the rotation result.
    }

    public static void main(String[] args) {
        for (int n = 0; n < 21; n++) {
            int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            System.out.println(Arrays.toString(leftRotate(a, (n + 1))));
        }
    }
}

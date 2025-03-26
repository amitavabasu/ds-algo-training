package practice.repeat1;

import java.util.Arrays;

public class ArrayLeftRotation {

    public static int[] leftRotate(int[] arr, int n) {
        if (arr == null || arr.length < 2 || n <= 0) return arr;
        int length = arr.length;
        int currentIndex = 0;
        int currentValue = arr[currentIndex];
        int compareIndex = currentIndex;
        int counter = 0;
        while (counter < length) {
            int nextIndex = (currentIndex - n + length) % length;
            int temp = arr[nextIndex];
            arr[nextIndex] = currentValue;
            currentIndex = nextIndex;
            currentValue = temp;
            counter++;
            if (counter < length && currentIndex == compareIndex) {
                currentIndex++;
                currentValue = arr[currentIndex];
                compareIndex = currentIndex;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[][] a = {
                {1,2,3,4,5},
                {1,2,3,4,5,6,7,8,9,10}
        };
        int n = 5;
        for (int[] arr : a) {
            System.out.println(Arrays.toString(leftRotate(arr, n)));
        }
    }
}

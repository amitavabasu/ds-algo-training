package practice.repeat8;

import java.util.LinkedList;
import java.util.Queue;

public class FindAllSequenceCountInAGivenArray {

    public static int findSequenceCount(int[] arr) {
        int count = 0;
        if (arr == null || arr.length == 0) return count;
        Queue<Integer> seqQueue = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            seqQueue.add(i);
        }
        while (!seqQueue.isEmpty()) {
            Queue<Integer> subsequentSeqQueue = new LinkedList<>();
            while (!seqQueue.isEmpty()) {
                int currentIndex = seqQueue.remove();
                for (int i = currentIndex; i < arr.length; i++) {
                    if (arr[currentIndex] < arr[i]) {
                        subsequentSeqQueue.add(i);
                    }
                }
            }
            if (!subsequentSeqQueue.isEmpty()) {
                count++;
                seqQueue = subsequentSeqQueue;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] arrays = {
                {10,9,8,7,6,5,4,3,2,1}, //output=0
                {10,9,8,7,6,5,3,4,2,1}, //output=1
                {1,2,3,4,5,6,7,8,9,10} //output=9
        };
        for (int[] arr : arrays) {
            System.out.println("Sequence Count ==> " + findSequenceCount(arr));
        }
    }
}

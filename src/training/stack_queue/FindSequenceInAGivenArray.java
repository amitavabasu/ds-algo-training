package training.stack_queue;

import java.util.LinkedList;
import java.util.Queue;

public class FindSequenceInAGivenArray {

    public static int findSequenceCount(int[] arr) {
        int seqCount = 0;
        if (arr == null || arr.length == 0) {
            return seqCount;
        }
        Queue<Integer> indexQueue = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            indexQueue.add(i);
        }
        while (!indexQueue.isEmpty()) {
            System.out.println(indexQueue);
            Queue<Integer> subsequentIndexQueue = new LinkedList<>();
            while (!indexQueue.isEmpty()) {
                int currentIndex = indexQueue.remove();
                for (int i = currentIndex; i < arr.length; i++) {
                    if (arr[currentIndex] < arr[i]) {
                        if (!subsequentIndexQueue.contains(i)) {
                            subsequentIndexQueue.add(i);
                        }
                    }
                }
            }
            if (subsequentIndexQueue.size() > 0) {
                seqCount++;
                indexQueue = subsequentIndexQueue;
            }
        }
        return seqCount;
    }




    public static void main(String[] args) {
//        int[] arr = new int[]{10,9,8,7,6,5,4,3,2,1}; //output=0
        int[] arr = new int[]{10,9,8,7,6,5,3,4,2,1}; //output=1
//        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10}; //output=9
        System.out.println("Sequence Count ==> " + findSequenceCount(arr));
    }

}

package training.heap;

import java.util.ArrayList;
import java.util.List;

public class HeapUsingArrayList {

    public static class Heap {
        List<Integer> values = new ArrayList<>();

        public void put(int val) {
            values.add(val);//first add the value at the end of the list
            int currentIndex = values.size() - 1;//get the current index where the new value is added
            while (currentIndex > 0) {//if current index is 0, then this is the first value added and nothing needs to be done
                //but if the current index is more than 0, we need to adjust the list as a heap.
                int parentIndex = (currentIndex - 1) / 2;//calculate parent index of the current index
                if (values.get(currentIndex) < values.get(parentIndex)) {//as this is a min heap, find which value is lower is the parent value or the current value
                    //if the value in the current index is lower swap this value with the parent index.
                    int temp = values.get(currentIndex);
                    values.set(currentIndex, values.get(parentIndex));
                    values.set(parentIndex, temp);
                    currentIndex = parentIndex;//reset the current index to parent index, because now we swapped the value and continue until current index reach 0
                } else {//if value at current index is higher than the value at parent index, nothing needs to be done the array maintains heap feature already.
                    break;//exit nothing needs to be done
                }
            }
        }

        public int get() {
            int valToReturn = values.getFirst();//initialize the value to return as the first (0-th) value of the list. do not remove it from the list, keep the value.
            int itemToRemove = values.removeLast();//remove the last value of the list to put as the just removed first value at the list.
            if(!values.isEmpty()) {//if the list size is grater than 0 this means the last value moved was the only value, so nothing needs to be done to maintain heap feature
                values.set(0, itemToRemove);//set the recently removed last value at the top of the list
                int indexToShiftDown = 0;//the first value is at index 0 which needs to be shifted down if needed. so initialize it as 0
                int leftChildIndex = 1;//calculate left child index
                int rightChildIndex = 2;//calculate right child index
                while (leftChildIndex < values.size()) {//until left child index is less than the length of the list, repeat
                    int indexToSwap = getIndexToSwap(leftChildIndex, rightChildIndex);
                    //at this point we have a swap index (it could be right or the left child
                    if (values.get(indexToShiftDown) > values.get(indexToSwap)) {//compare the value at shift down index with the swap index (i.e. parent with the smaller child)
                        //and if swap index value is less than (because it is min heap) the value at shift down index then swap the values at shift down index and swap index
                        int temp = values.get(indexToSwap);
                        values.set(indexToSwap, values.get(indexToShiftDown));
                        values.set(indexToShiftDown, temp);
                        indexToShiftDown = indexToSwap; //as we swaped the shift down index value with the swap index value, set the shift down index value to the swap index.
                        leftChildIndex = 2 * indexToShiftDown + 1; // re-calculate left child index to be used in next iteration
                        rightChildIndex = 2 * indexToShiftDown + 2;//re-calculate right child index to be used in the next iteration
                    } else {//at this point we know that value at the shift down index is smaller than swap index. And no swap required.
                        // We can break because now the heap is maintained its feature
                        break;
                    }
                }
            }
            return valToReturn;//return the initially stored value to return.
        }

        private int getIndexToSwap(int leftChildIndex, int rightChildIndex) {
            int indexToSwap = leftChildIndex; //set swap index as left child index initially
            if (rightChildIndex < values.size() && values.get(rightChildIndex) < values.get(leftChildIndex)) {//now check if right child index is within the list length
                //it is possible that right child index is outside the list size meaning it does not exist. In that case proceed with left child only
                //if right child exists, compare left child and right child; whichever is smaller (because it is a min heap), we have to proceed with that child.
                indexToSwap = rightChildIndex;//if right child is smaller set swap index to right child
            }
            return indexToSwap;
        }
    }

    public static void main(String[] args) {
        int[] data = {80,20,0,50,40,30,70,10,60};
        Heap heap = new Heap();
        for (int d : data) {
            heap.put(d);
        }
        for (int i = 0; i < data.length; i++) {
            System.out.println(heap.get());
        }
    }
}

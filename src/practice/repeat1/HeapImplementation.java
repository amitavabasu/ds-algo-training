package practice.repeat1;

import training.heap.HeapUsingArrayList;

import java.util.ArrayList;

public class HeapImplementation {
    public static class Heap{
        private static ArrayList<Integer> values = new ArrayList<>();

        public static void put(int v) {
            int indexToPut = values.size();
            values.set(indexToPut, v);
            while (indexToPut > 0) {
                int parentIndex = (indexToPut - 1) / 2;
                if (values.get(indexToPut) < values.get(parentIndex)) {
                    int temp = values.get(indexToPut);
                    values.set(indexToPut, values.get(parentIndex));
                    values.set(parentIndex, temp);
                    indexToPut = parentIndex;
                } else {
                    break;
                }
            }
        }

        public static int get() {
            if (values.size() == 0) throw new RuntimeException("Empty heap ... ");
            Integer valueToReturn = values.get(0);
            if (values.size() < 2) return valueToReturn;
            int valueToRemove = values.remove(values.size() - 1);
            values.set(0, valueToRemove);
            int indexToShiftDown = 0;
            int leftChildIndex = 2 * indexToShiftDown + 1;
            while (leftChildIndex < values.size()) {
                int indexToSwap;
                int rightChildIndex = 2 * indexToShiftDown + 2;
                if (rightChildIndex < values.size() && values.get(rightChildIndex) < values.get(leftChildIndex)) {
                    indexToSwap = rightChildIndex;
                } else {
                    indexToSwap = leftChildIndex;
                }
                if (values.get(indexToSwap) < values.get(indexToShiftDown)) {
                    int temp = values.get(indexToSwap);
                    values.set(indexToSwap, values.get(indexToShiftDown));
                    values.set(indexToShiftDown, temp);
                    leftChildIndex = 2 * indexToShiftDown + 1;
                } else {
                    break;
                }
            }
            return valueToReturn;
        }
    }

    public static void main(String[] args) {
        int[] data = {80,20,0,50,40,30,70,10,60,100,1};
        HeapUsingArrayList.Heap heap = new HeapUsingArrayList.Heap();
        for (int d : data) {
            heap.put(d);
        }
        for (int i = 0; i < data.length; i++) {
            System.out.println(heap.get());
        }
    }
}

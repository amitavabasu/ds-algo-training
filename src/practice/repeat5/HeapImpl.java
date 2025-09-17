package practice.repeat5;

import java.util.ArrayList;
import java.util.List;

public class HeapImpl {

    public static class Heap {
        private static List<Integer> data = new ArrayList<>();

        private static void swap(int i, int j) {
            int t = data.get(i);
            data.set(i, data.get(j));
            data.set(j, t);
        }

        public static void add(int val) {
            data.add(val);
            int currentIndex = data.size() - 1;
            while (currentIndex > 0) {
                int parentIndex = (currentIndex - 1) / 2;
                if (data.get(currentIndex) > data.get(parentIndex)) {
                    swap(currentIndex, parentIndex);
                    currentIndex = parentIndex;
                } else {
                    break;
                }
            }
        }

        public static int remove() {
            if (data.isEmpty()) throw new RuntimeException("No data");
            int valToReturn = data.getFirst();
            int lastElement = data.removeLast();
            if (data.size() > 0) {
                int currentIndex = 0;
                data.set(currentIndex, lastElement);
                int lastIndex = data.size() - 1;
                int leftChildIndex = 1;
                int rightChildIndex = 2;
                while (leftChildIndex <= lastIndex) {
                    int indexToSwap = leftChildIndex;
                    if (rightChildIndex <= lastIndex && data.get(rightChildIndex) > data.get(leftChildIndex)) {
                        indexToSwap = rightChildIndex;
                    }
                    if (data.get(currentIndex) < data.get(indexToSwap)) {
                        swap(currentIndex, indexToSwap);
                        currentIndex = indexToSwap;
                        leftChildIndex = 2 * currentIndex + 1;
                        rightChildIndex = 2 * currentIndex + 2;
                    } else {
                        break;
                    }
                }
            }
            return valToReturn;
        }
    }

    public static void main(String[] args) {
        int[] data = {80, 20, 0, 50, 40, 30, 70, 10, 60};
        Heap heap = new Heap();
        for (int d : data) {
            heap.add(d);
        }
        for (int i = 0; i < data.length; i++) {
            System.out.println(heap.remove());
        }
    }
}

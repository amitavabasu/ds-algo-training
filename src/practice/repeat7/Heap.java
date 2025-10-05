package practice.repeat7;


import java.util.ArrayList;
import java.util.List;

public class Heap {
    private List<Integer> list = new ArrayList<>();

    private void swap(int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public void put(int a) {
        list.add(a);
        int currentIndex = list.size()-1;
        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
            if (list.get(currentIndex) < list.get(parentIndex)) {
                swap(currentIndex, parentIndex);
                currentIndex = parentIndex;
            } else {
                break;
            }
        }
    }

    public int get() {
        if (list == null || list.isEmpty()) throw new RuntimeException("No element");
        int valueToReturn = list.getFirst();
        int indexToRemove = list.size() - 1;
        if (indexToRemove > 0) {
            int valToRemove = list.remove(indexToRemove);
            list.set(0, valToRemove);
            int currentIndex = 0;
            int lChildIndex = 1;
            int rChildIndex = 2;
            while (lChildIndex < list.size()) {
                int indexToSwap = lChildIndex;
                if (rChildIndex < list.size() && list.get(rChildIndex) < list.get(lChildIndex)) {
                    indexToSwap = rChildIndex;
                }
                if (list.get(currentIndex) > list.get(indexToSwap)) {
                    swap(currentIndex, indexToSwap);
                    currentIndex = indexToSwap;
                    lChildIndex = (2 * currentIndex) + 1;
                    rChildIndex = (2 * currentIndex) + 2;
                } else {
                    break;
                }
            }
        }
        return valueToReturn;
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

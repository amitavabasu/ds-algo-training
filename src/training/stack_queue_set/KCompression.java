
package training.stack_queue_set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class KCompression {

   public static List<Integer> getKCompressed(List<Integer> list, int k) {
       if (list == null || list.isEmpty()) return list;
       int n = list.size();
       Stack<Integer[]> stack = new Stack<>();
       stack.push(new Integer[]{list.get(0), 1});
       boolean done = false;
       int i = 1;
       while (i < n) {
           Integer[] lastElement = stack.peek();
           int lastItem = lastElement[0];
           int lastItemCount = lastElement[1];
           int currentItem = list.get(i);
           while (lastItemCount == k - 1 && lastItem == currentItem) {
               stack.pop();
               currentItem = currentItem * k;
               if (!stack.isEmpty()) {
                   lastElement = stack.peek();
                   lastItem = lastElement[0];
                   lastItemCount = lastElement[1];
               }
           }
           if (stack.isEmpty()) {
               stack.push(new Integer[]{currentItem, 1});
               i++;
           } else if (lastItem == currentItem) {
               stack.peek()[1]++;
               i++;
           } else {
               stack.push(new Integer[]{currentItem, 1});
               i++;
           }
       }
       ArrayList<Integer> result = new ArrayList<>();
       for (Integer[] p : stack) {
           for (int j = 0; j < p[1]; j++) result.add(p[0]);
       }
       return result;
   }


    public static void main(String[] args) {
        int[] k = {3, 2};
        Integer[][] arr = {
                {8, 9, 9, 3, 3, 3, 2, 2},
                {16, 8, 4, 2, 2}
        };
        for (int i = 0; i < arr.length; i++ ) {
            List<Integer> list = getKCompressed(Arrays.asList(arr[i]), k[i]);
            System.out.println(list);
        }
   }

}

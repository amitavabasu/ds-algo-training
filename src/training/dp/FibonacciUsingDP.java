package training.dp;

import java.util.ArrayList;
import java.util.List;

public class FibonacciUsingDP {

    public static List<Integer> fibonacci(int n) {
        List<Integer> series = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i <= 1) {
                series.add(i);
            } else {
                series.add(series.get(i - 1) + series.get(i - 2));
            }
        }
        return series;
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(fibonacci(n));
    }
}

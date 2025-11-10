package practice.repeat8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FibonacciUsingDP {


    public static List<Integer> fibonacci(int n) {
        if (n <= 0) return Collections.emptyList();
        List<Integer> result = new ArrayList<>(n);
        result.add(1);
        if (n >= 2) result.add(1);
        for (int i = 2; i < n; i++) {
            result.add(result.get(i-1)+result.get(i-2));
        }
        return result;
    }


    public static void main(String[] args) {
        int n = 10;
        System.out.println(fibonacci(n));
    }
}

package training.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RollingAverage {

    public static List<Double> calculateRollingAverage(int[] data, int windowSize) {
        if (data == null || data.length == 0 || windowSize <= 0) return Collections.emptyList();
        double sum = 0.0d;
        List<Double> averages = new ArrayList<>();
        if (data.length <= windowSize) {
            for (int d : data) {
                sum += (double)d;
            }
            averages.add(sum / windowSize);
        } else {
            for (int i = 0; i < windowSize; i++) {
                sum += (double) data[i];
            }
            averages.add(sum / windowSize);
            for (int j = windowSize; j < data.length; j++) {
                sum = sum - data[j - windowSize] + data[j];
                averages.add(sum / windowSize);
            }
        }
        return averages;
    }


    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 40, 50, 60, 80};
        int windowSize = 3;

        List<Double> averages = calculateRollingAverage(numbers, windowSize);
        System.out.println("Original Array: " + java.util.Arrays.toString(numbers));
        System.out.println("Window Size: " + windowSize);
        System.out.println("Rolling Averages: " + averages);
    }
}

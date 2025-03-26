package training.array;
import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindowMovingAverage {
    private final int windowSize;  // Maximum size of the sliding window
    private final Queue<Integer> window;  // Queue to store window elements
    private double windowSum;  // Sum of elements in the current window

    // Constructor to initialize the window size
    public SlidingWindowMovingAverage(int size) {
        this.windowSize = size;
        this.window = new LinkedList<>();
        this.windowSum = 0.0;
    }

    // Method to add a new data point and calculate the moving average
    public double next(int dataPoint) {
        // Add the new data point to the window
        window.add(dataPoint);
        windowSum += dataPoint;

        // If the window exceeds its size, remove the oldest data point
        if (window.size() > windowSize) {
            int removedPoint = window.poll();
            windowSum -= removedPoint;
        }

        // Return the current moving average
        return windowSum / window.size();
    }

    public static void main(String[] args) {
        SlidingWindowMovingAverage slidingWindow = new SlidingWindowMovingAverage(3);  // Window size of 3

        // Simulating a stream of incoming data
        System.out.println("Moving Average after adding 1: " + slidingWindow.next(1));  // Output: 1.0
        System.out.println("Moving Average after adding 10: " + slidingWindow.next(10));  // Output: 5.5
        System.out.println("Moving Average after adding 3: " + slidingWindow.next(3));  // Output: 4.67
        System.out.println("Moving Average after adding 5: " + slidingWindow.next(5));  // Output: 6.0
    }
}

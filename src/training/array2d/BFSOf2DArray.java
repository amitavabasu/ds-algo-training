package training.array2d;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BFSOf2DArray {

    static int[][] directions = {
            {-1,0},
            {0,1},
            {1,0},
            {0,-1}
    };

    public static boolean[][] getVisited(int[][] data) {
        if (data == null) return null;
        return new boolean[data.length][data[0].length];
    }

    public static int[] BFS(int[][] data, int x, int y) {
        if (data == null || data.length == 0 || data[0].length == 0) {
            return new int[0];
        }
        Queue<int[]> queue = new LinkedList<>();
        int[] output = new int[data.length * data[0].length];
        int outputIndex = 0;
        boolean[][] visited = getVisited(data);
        queue.offer(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] currentPos = queue.poll();
            x = currentPos[0];
            y = currentPos[1];
            int val = data[x][y];

            if (visited[x][y] == false) {
                output[outputIndex] = val;
                outputIndex++;
                visited[x][y] = true;
                for (int[] direction : directions) {
                    int xbar = x + direction[0];
                    int ybar = y + direction[1];
                    if (xbar >= 0 && xbar < data.length && ybar >= 0 && ybar < data[0].length) {
                        queue.offer(new int[]{xbar, ybar, data[xbar][ybar]});
                    }
                }
            }
        }
        return output;
    }

    public static void main(String[] args) {
        int[][] data = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11,12,13,14,15},
                {16,17,18,19,20}
        };
        //output = [1, 2, 6, 3, 7, 11, 4, 8, 12, 16, 5, 9, 13, 17, 10, 14, 18, 15, 19, 20]
        System.out.println(Arrays.toString(BFS(data, 0, 0)));
    }
}

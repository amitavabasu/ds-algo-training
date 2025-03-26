package training.array2d;

import java.util.Arrays;

public class DFSOf2DArray {

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

    public static int[] DFS(int[][] data) {
        if (data == null || data.length == 0 || data[0].length == 0) {
            return new int[0];
        }
        int[] output = new int[data.length * data[0].length];
        recursiveDFS(data, 0, 0, getVisited(data), output, 0);
        return output;
    }

    public static void recursiveDFS(int[][] data, int x, int y, boolean[][] visited, int[] output, int outputIndex) {
        int val = data[x][y];
        if (!visited[x][y]) {
            output[outputIndex] = val;
            outputIndex++;
            visited[x][y] = true;
            for (int[] direction : directions) {
                int xbar = x + direction[0];
                int ybar = y + direction[1];
                if (xbar >= 0 && xbar < data.length && ybar < data[0].length && ybar >= 0) {
                    recursiveDFS(data, xbar, ybar, visited, output, outputIndex);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] data = {
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15},
                {16,17,18,19,20}
        };
        //output = {1,2,3,4,5,10,15,20,19,14,9,8,13,18,17,12,7,6,11,16};
        System.out.println(Arrays.toString(DFS(data)));
    }
}

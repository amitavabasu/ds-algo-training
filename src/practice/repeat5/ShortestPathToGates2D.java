package practice.repeat5;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathToGates2D {

    public static int[][] directions = {
            {0,-1},
            {0,1},
            {-1,0},
            {1,0}
    };

    public static int[][] findDistance(int[][] data) {
        if (data == null || data.length == 0 || data[0].length == 0) return data;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i][j] == 0) {
                    queue.add(new int[]{i,j});
                }
            }
        }
        int cellToProcess = queue.size();
        int distance = 0;
        while (!queue.isEmpty()) {
            int[] loc = queue.remove();
            int x = loc[0];
            int y = loc[1];
            data[x][y] = distance;
            for (int[] direction : directions) {
                int xbar = x + direction[0];
                int ybar = y + direction[1];
                if (xbar >= 0 && xbar < data.length && ybar >= 0 && ybar < data[0].length && data[xbar][ybar] == -2) {
                    data[xbar][ybar] = distance + 1;
                    queue.add(new int[]{xbar, ybar});
                }
            }
            cellToProcess--;
            if (cellToProcess == 0) {
                distance++;
                cellToProcess = queue.size();
            }
        }
       return data;
    }

    public static void main(String[] args) {
        //INF = -2 --> open space
        //Gate = 0 --> Target
        //Wall = -1 --> Wall
        //number == distance
        int[][] data1 = {
                {-2, -1,  0, -2},
                {-2, -2, -2, -1},
                {-2, -1, -2, -1},
                {0, -1, -2, -2}
        };

        int[][] data2 = {
                {-2, -1, 0, -2},
                {-1, -2, -2, -1},
                {-2, -1, -2, -1},
                {0, -1, -2, -2}
        };

        int[][] result1 = findDistance(data1);
        for (int[] line : result1) {
            System.out.println(Arrays.toString(line));
        }
        System.out.println("-------------------------------------------------");
        int[][] result2 = findDistance(data2);
        for (int[] line : result2) {
            System.out.println(Arrays.toString(line));
        }
    }
}

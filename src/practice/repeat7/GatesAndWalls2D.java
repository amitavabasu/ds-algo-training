package practice.repeat7;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class GatesAndWalls2D {
    public static int[][] directions = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };

    //INF = -2 --> open space
    //Gate = 0 --> starting point
    //Wall = -1 --> blocking cells
    //number == distance
    public static int[][] findDistance(int[][] data) {
        if (data == null || data.length == 0 || data[0].length == 0) return data;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i][j] == 0) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        int qSize = queue.size();
        int distance = 0;
        while (!queue.isEmpty()) {
            int[] pos = queue.remove();
            int x = pos[0];
            int y = pos[1];
            data[x][y] = distance;
            for (int[] direction : directions) {
                int xBar = x + direction[0];
                int yBar = y + direction[1];
                if (xBar >= 0 && xBar < data.length && yBar >= 0 && yBar < data[0].length && data[xBar][yBar] == -2) {
                    queue.add(new int[]{xBar, yBar});
                }
            }
            qSize--;
            if (qSize == 0) {
                distance++;
                qSize = queue.size();
            }
        }
        return data;
    }

    public static void main(String[] args) {
        //INF = -2 --> open space
        //Gate = 0 --> starting point
        //Wall = -1 --> blocking cells
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

package practice.repeat0;

import java.util.LinkedList;
import java.util.Queue;

public class FindNumberOfIsland_2D_Array {

    public static int[][] directions = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    public static int findNumberOfIslandBFS(int[][] data) {
        int count = 0;
        if (data == null || data.length == 0 || data[0].length == 0) return count;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i][j] == 1) {
                    count++;
                    markLandAllLinkedMassBFS(data, i, j);
                }
            }
        }
        return count;
    }

    public static void markLandAllLinkedMassBFS(int[][] data, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x,y});
        while (!queue.isEmpty()) {
            int[] pos = queue.remove();
            x = pos[0];
            y = pos[1];
            if (data[x][y] != 0) {
                data[x][y] = 0;
                for (int[] direction : directions) {
                    int xBar = x + direction[0];
                    int yBar = y + direction[1];
                    if (xBar >= 0 && xBar < data.length && yBar >= 0 && yBar < data[0].length && data[xBar][yBar] != 0) {
                        queue.offer(new int[] {xBar, yBar});
                    }
                }
            }
        }
    }




    public static int findNumberOfIslandDFS(int[][] data) {
        int count = 0;
        if (data == null || data.length == 0 || data[0].length == 0) return count;
        if (data == null || data.length == 0 || data[0].length == 0) return count;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i][j] == 1) {
                    count++;
                    markLandAllLinkedMassDFS(data, i, j);
                }
            }
        }
        return count;
    }

    public static void markLandAllLinkedMassDFS(int[][] data, int x, int y) {
        if (data[x][y] != 0) {
            data[x][y] = 0;
            for (int[] direction : directions) {
                int xBar = x + direction[0];
                int yBar = y + direction[1];
                if (xBar >= 0 && xBar < data.length && yBar >= 0 && yBar < data[0].length && data[xBar][yBar] != 0) {
                    markLandAllLinkedMassDFS(data, xBar, yBar);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] data = {
                {1, 1, 1, 1, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 1, 1, 0},
                {1, 0, 1, 0, 1}
        };
        System.out.println("Number of island (BFS) ==> " + findNumberOfIslandBFS(data));
        int[][] data2 = {
                {0, 1, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 1, 1, 0},
                {1, 0, 1, 0, 1}
        };
        System.out.println("Number of island (DFS) ==> " + findNumberOfIslandDFS(data2));
    }
}

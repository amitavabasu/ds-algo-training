package training.array2d;

import java.util.LinkedList;
import java.util.Queue;

    public class FindNumberOfIslandIn2DArray {

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

        public static int findNumberOfIslandBFS(int[][] data) {
            int count = 0;
            if (data == null || data[0] == null || data.length == 0 || data[0].length == 0) return count;
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    if (data[i][j] == 1) {
                        count++;
                        findAllLandMassBFS(data, i, j);
                    }
                }
            }
            return count;
        }

        public static void findAllLandMassBFS(int[][] data, int x, int y) {
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{x,y});
            while (!queue.isEmpty()) {
                int[] currentPos = queue.poll();
                x = currentPos[0];
                y = currentPos[1];
                data[x][y] = 0;
                for (int[] direction : directions) {
                    int xbar = x + direction[0];
                    int ybar = y + direction[1];
                    if (xbar >= 0 && xbar < data.length && ybar >= 0 && ybar < data[0].length && data[xbar][ybar] == 1) {
                        data[xbar][ybar] = 0;
                        queue.offer(new int[]{xbar, ybar});
                    }
                }
            }
        }

        public static int findNumberOfIslandDFS(int[][] data) {
            int count = 0;
            if (data == null || data[0] == null || data.length == 0 || data[0].length == 0) return count;
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    if (data[i][j] == 1) {
                        count++;
                        findAllLandMassDFS(data, i, j);
                    }
                }
            }
            return count;
        }

        public static void findAllLandMassDFS(int[][] data, int x, int y) {
            data[x][y] = 0;
            for (int[] direction : directions) {
                int xbar = x + direction[0];
                int ybar = y + direction[1];
                if (xbar >= 0 && xbar < data.length && ybar >= 0 && ybar < data[0].length && data[xbar][ybar] == 1) {
                    data[xbar][ybar] = 0;
                    findAllLandMassDFS(data, xbar, ybar);
                }
            }
        }


        public static void main(String[] args) {
            int[][] data = {
                    {0, 1, 0, 1, 0},
                    {1, 0, 1, 0, 1},
                    {0, 1, 1, 1, 0},
                    {1, 0, 1, 0, 1}
            };
            System.out.println("Number of island (BFS) ==> " + findNumberOfIslandBFS(data));
            int[][] data2 = {
                    {0, 1, 0, 1, 0},
                    {1, 0, 1, 0, 1},
                    {0, 1, 1, 1, 0},
                    {1, 0, 1, 0, 0}
            };
            System.out.println("Number of island (DFS) ==> " + findNumberOfIslandDFS(data2));
        }
    }

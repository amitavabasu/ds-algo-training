package practice.repeat5;

public class NumberOfIslands2D {

    public static int[][] directions = {
            {0,1},
            {0,-1},
            {1,0},
            {-1,0}
    };

    public static int findNumberOfIsland(int[][] data) {
        if (data == null || data.length == 0 || data[0].length == 0) return 0;
        int islandCount = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i][j] == 1) {
                    islandCount++;
                    findLandMass(data, i, j);
                }
            }
        }
        return islandCount;
    }

    public static void findLandMass(int[][] data, int x, int y) {
        data[x][y] = 0;
        for (int[] direction : directions) {
            int xbar = x + direction[0];
            int ybar = y + direction[1];
            if (xbar >= 0 && xbar < data.length && ybar >= 0 && ybar < data[0].length && data[xbar][ybar] == 1) {
                findLandMass(data, xbar, ybar);
            }
        }
    }





    public static void main(String[] args) {
//        int[][] data = {
//                {0, 1, 0, 1, 0},
//                {1, 0, 1, 0, 1},
//                {0, 1, 1, 1, 0},
//                {1, 0, 1, 0, 1}
//        };
//        System.out.println("Number of island (BFS) ==> " + findNumberOfIslandBFS(data));
        int[][] data2 = {
                {0, 1, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 1, 1, 0},
                {1, 0, 1, 0, 0}
        };
        System.out.println("Number of island (DFS) ==> " + findNumberOfIsland(data2));
    }
}

package practice.repeat0;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Practice1_2D_DFS_BFS {

    public static int[][] directions = {
            {-1,0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    public static int[] BFS(int[][] data, int x, int y) {
        if (data == null || data.length == 0 || data[0].length == 0) return new int[]{};
        int[] result = new int[data.length * data[0].length];
        int resultIndex = 0;
        boolean[][] visited = new boolean[data.length][data[0].length];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x,y});
        while (!queue.isEmpty()) {
            int[] position = queue.remove();
            x = position[0];
            y = position[1];
            if (!visited[x][y]) {
            int value = data[x][y];
            visited[x][y] = true;
            result[resultIndex] = value;
            resultIndex++;
            for (int[] direction : directions) {
                int xBar = x + direction[0];
                int yBar = y + direction[1];
                if ( xBar >= 0 && xBar < data.length && yBar >= 0 && yBar < data[0].length && !visited[xBar][yBar]) {
                    queue.offer(new int[]{xBar,yBar});
                }
            }
            }
        }
        return result;
    }

    public static int[] DFS(int[][] data, int x, int y) {
        if (data == null || data.length == 0 || data[0].length == 0) return new int[]{};
        int[] result = new int[data.length * data[0].length];
        int resultIndex = 0;
        boolean[][] visited = new boolean[data.length][data[0].length];
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{x,y, data[x][y]});
        while (!stack.isEmpty()) {
            int[] position = stack.pop();
            x = position[0];
            y = position[1];
            if (!visited[x][y]) {
                int value = data[x][y];
                visited[x][y] = true;
                result[resultIndex] = value;
                resultIndex++;
                for (int i = directions.length-1; i >= 0; i--) {
                    int[] direction = directions[i];
                    int xBar = x + direction[0];
                    int yBar = y + direction[1];
                    if ( xBar >= 0 && xBar < data.length && yBar >= 0 && yBar < data[0].length && !visited[xBar][yBar]) {
                        stack.push(new int[]{xBar,yBar, data[xBar][yBar]});
                    }
                }
            }
        }
        return result;
    }

    public static int[] DFSRecursive(int[][] data, int x, int y) {
        int[] result = new int[data.length * data[0].length];
        boolean[][] visited = new boolean[data.length][data[0].length];
        int resultIndex = 0;
        recursion(data, x, y, visited, result, resultIndex);
        return result;
    }

    public static void recursion(int[][] data, int x, int y, boolean[][] visited, int[] result, int resultIndex) {
        if(!visited[x][y]) {
            result[resultIndex] = data[x][y];
            resultIndex++;
            visited[x][y] = true;
            for (int[] direction : directions) {
                int xBar = x + direction[0];
                int yBar = y + direction[1];
                if (xBar >= 0 && xBar < data.length && yBar >= 0 && yBar < data[0].length && !visited[xBar][yBar]) {
                    recursion(data, xBar, yBar, visited, result, resultIndex);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] data = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20}
        };
        //int[] output = [1, 2, 6, 3, 7, 11, 4, 8, 12, 16, 5, 9, 13, 17, 10, 14, 18, 15, 19, 20];
        //int[] output = {1,2,3,4,5,10,15,20,19,14,9,8,13,18,17,12,7,6,11,16};
        System.out.println(Arrays.toString(BFS(data, 0, 0)));
        System.out.println(Arrays.toString(DFS(data, 0, 0)));
        System.out.println(Arrays.toString(DFSRecursive(data, 0, 0)));
    }

}

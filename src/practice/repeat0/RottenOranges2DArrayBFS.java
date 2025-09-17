package practice.repeat0;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges2DArrayBFS {

    public static int[][] directions = {
            {-1,0},
            {0, 1},
            {1,0},
            {0,-1}
    };

    public static int countMinutesToRot(int[][] data) {
        if (data == null || data.length == 0 || data[0].length == 0) return 0;
        int freshOranges = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i][j] == 2) {
                    queue.offer(new int[]{i,j});
                } else if (data[i][j] == 1) {
                    freshOranges++;
                }
            }
        }
        int size = queue.size();
        int timeCount = 0;
        while (!queue.isEmpty()) {
            int[] pos = queue.remove();
            int x = pos[0];
            int y = pos[1];
            data[x][y] = 0;
            for (int[] direction : directions) {
                int xBar = x + direction[0];
                int yBar = y + direction[1];
                if (xBar >= 0 && xBar < data.length && yBar >= 0 && yBar < data[0].length && data[xBar][yBar] != 0) {
                    queue.offer(new int[]{xBar, yBar});
                    if (data[xBar][yBar] == 1) {
                        freshOranges--;
                        data[xBar][yBar] = 0;
                    }
                }
            }
            size--;
            if (size == 0) {
                timeCount++;
                size = queue.size();
            }
        }
        if (freshOranges > 0) {
            return -1;
        } else if (timeCount == 0){
            return timeCount;
        } else {
            return timeCount-1;
        }
    }




    public static void main(String[] args) {
        //2 --> rotten orange
        //1 --> fresh orange
        //0 --> empty cell
        int[][] data1 = {
                {2, 1, 1, 0, 0},
                {1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1},
                {0, 1, 0, 0, 1}
        };

        int[][] data2 = {
                {1, 1, 0, 0, 0},
                {2, 1, 0, 0, 0},
                {0, 0, 0, 1, 2},
                {0, 1, 0, 0, 1}
        };
        System.out.println("Number of minutes (data1) ==> " + countMinutesToRot(data1));
        System.out.println("Number of minutes (data2)==> " + countMinutesToRot(data2));
    }
}

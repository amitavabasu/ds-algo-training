package practice.repeat5;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges2D {

    public static int[][] directions = {
            {0,1},
            {0,-1},
            {1,0},
            {-1,0}
    };


    public static int countMinutesToRot(int[][] data) {
        if (data == null || data.length == 0 || data[0].length == 0) return 0;
        int minutes = 0;
        int freshCount = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < data.length; i++) {
            for (int j =0; j < data[0].length; j++) {
                if (data[i][j] == 2) {
                    queue.add(new int[]{i,j});
                } else if (data[i][j] == 1) {
                    freshCount++;
                }
            }
        }
        int rottenSize = queue.size();
        while (!queue.isEmpty()) {
            int[] loc = queue.remove();
            for (int[] direction : directions) {
                int x = loc[0] + direction[0];
                int y = loc[1] + direction[1];
                if (x >= 0 && x < data.length && y >= 0 && y < data[0].length && data[x][y] == 1) {
                    data[x][y] = 2;
                    freshCount--;
                    queue.add(new int[]{x,y});
                }
            }
            rottenSize--;
            if (rottenSize == 0) {
                minutes++;
                rottenSize = queue.size();
            }
        }

        if (freshCount > 0) {
            return -1;
        } else if (minutes == 0) {
            return minutes;
        } else {
            return (minutes-1);
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

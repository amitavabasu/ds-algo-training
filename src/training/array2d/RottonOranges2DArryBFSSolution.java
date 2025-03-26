package training.array2d;

import java.util.LinkedList;
import java.util.Queue;

public class RottonOranges2DArryBFSSolution
{
    public static int[][] directions = {
            {-1,0},
            {0,1},
            {1,0},
            {0,-1}
    };

    public static int countMinutesToRot(int[][] data) {
        if (data == null || data.length == 0 || data[0].length == 0) {
            return 0;
        }
        int count = 0;
        Queue<int[]> queue = new LinkedList<>();
        int noOfFreshOranges = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i][j] == 2) {
                    queue.offer(new int[] {i, j});
                } else if(data[i][j] == 1) {
                    noOfFreshOranges++;
                }
            }
        }

        int qSize = queue.isEmpty() ? 0 : queue.size();
        while (!queue.isEmpty()) {
            int[] currentPosition = queue.poll();
            int x = currentPosition[0];
            int y = currentPosition[1];
            data[x][y] = 0;
            for (int[] direction : directions) {
                int xbar = x + direction[0];
                int ybar = y + direction[1];
                if (xbar >= 0 && xbar < data.length && ybar >= 0 && ybar < data[0].length && data[xbar][ybar] != 0) {
                    if (data[xbar][ybar] == 1) {
                        noOfFreshOranges--;
                        data[xbar][ybar] = 0;
                    }
                    queue.offer(new int[]{xbar, ybar});
                }
            }
            qSize--;
            if (qSize == 0) {
                count++;
                qSize = queue.size();
            }
        }
        if (noOfFreshOranges > 0) {
            return -1;
        } else if (count == 0) {
            return count;
        } else {
            return (count-1);
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

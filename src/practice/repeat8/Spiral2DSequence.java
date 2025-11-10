package practice.repeat8;

import java.util.Arrays;

public class Spiral2DSequence {

    public static int[][] spiralSequence(int n) {
        if (n <= 0 || n%2 == 0) return new int[][]{};
        int totalCount = n * n;
        int[][] result = new int[n][n];
        int val = 0;
        int r = n / 2;
        int c = n / 2;
        result[r][c] = val++;
        int[][] direction = {
                {1, 0},
                {0, -1},
                {-1, 0},
                {0, 1}
        };
        int dIndex = 0;
        int sequence = 1; //1,1, 2,2, 3,3, 4,4 .., ..
        int dr = 0, dc = 0;
        while (val < totalCount) {
            int step = 2;
            while (step > 0 && val < totalCount) {
                dr = direction[dIndex][0];
                dc = direction[dIndex][1];
                for (int s = 0; s < sequence && val < totalCount; s++) {
                    r += dr;
                    c += dc;
                    result[r][c] = val++;

                }
                dIndex = (dIndex + 1) % 4;
                step--;
            }
            sequence++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] numbers = {1, 3, 5, 7};
        for (int n : numbers) {
            int[][] result = spiralSequence(n);
            System.out.println("-------------------------------------------------");
            for (int[] r : result) {
                System.out.println(Arrays.toString(r));
            }
            System.out.println("-------------------------------------------------");
        }
    }
}

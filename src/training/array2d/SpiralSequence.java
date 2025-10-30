package training.array2d;

import java.util.Arrays;

/*
    Given n as an odd number generate a grid of n X n
    with spiral clockwise sequences
 */
public class SpiralSequence {

    /*
    This solution needs 3 basic steps

    1) Each coordinate to be generated are the distance from center - center = n/2, n/2
    2) SPIRAL PATTERN <-- new pattern to learn
        A) Need to generate them in spiral pattern directions = DOWN --> LEFT --> UP --> RIGHT
        B) Each direction move 1 step(s) twice, then increment the number of steps by 1 after every two cycle <-- That is how spiral pattern works
    3) Exit when total count is reached

    Time Complexity - O(n^2)
    Space Complexity - O(1)

     */

    public static int[][] spiralSequence(int n) {
        if (n < 1 || n%2 == 0) return null;
        int[][] grid = new int[n][n];
        int totalCount = n * n;
        int r = n/2;
        int c = n/2;
        int val = 0;
        grid[r][c] = val++; // place 0 at center
        // Directions in clockwise order starting from DOWN: DOWN, LEFT, UP, RIGHT
        int[][] directions = {
                        {1, 0}, //Down
                        {0, -1}, //Left
                        {-1, 0}, //Up
                        {0, 1}  //right
        };

        int directionIndex = 0;     // start DOWN
        int runLength = 1;     // step lengths go: 1,1,2,2,3,3,...
        // Fill the rest of the cells
        while (val < totalCount) {
            // Two legs with the same runLen, then increase runLen
            for (int rep = 0; rep < 2 && val < totalCount; rep++) {
                int dr = directions[directionIndex][0], dc = directions[directionIndex][1];
                for (int s = 0; s < runLength && val < totalCount; s++) {
                    r += dr; c += dc;
                    grid[r][c] = val++;
                }
                directionIndex = (directionIndex + 1) % 4; // rotate clockwise
            }
            runLength++;
        }
        return grid;
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

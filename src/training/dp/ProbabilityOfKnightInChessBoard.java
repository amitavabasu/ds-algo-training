package training.dp;

import java.util.Arrays;

public class ProbabilityOfKnightInChessBoard {

    public static int[][] directions = {//define knights movements.
            {-2, 1},//up-right
            {-2, -1},//up-left
            {-1, 2},//right-up
            {1, 2},//right-down
            {2, 1},//down-right
            {2, -1},//down-left
            {-1, -2},//left-up
            {1, -2}//left-down
    };

    //this is brute force solution, it explores all possible move of the night
    //the big O - ? 8^k - ?
    public static double calculateProbability(int d, int r, int c, int k) {
         double probability = 0.0;//initialize probability as 0.0 double or float
         if (k == 0) { //initial condition if there is 0 moves then the knight is on the board and the probability is 1
             return 1;//return 1 as probability
         } else {
             //we have moves to make
             for (int[] direction : directions) {//for all the possible moves of a night
                 int rBar = r + direction[0];//calculate next position for row in a new variable i.e. rBar
                 int cBar = c + direction[1];//calculate next position for column in a new variable i.e. cBar
                 if (rBar >= 0 && rBar < d && cBar >= 0 && cBar < d) {//check the newly calculated position falls on the board or not
                     //if the next calculated move positions inside the board
                     //calculate probability as current probability plus next moves probability recursively.
                     //the next recursive call should pass the newly calculated moved position, i.e. rBar & cBar and number of moved will be reduced by 1 i.e. k-1
                     probability = probability + calculateProbability(d, rBar, cBar, k-1);
                 }
             }
             return probability / 8;//return the calculated probability divided by 8. This is because we are making 8 possible moves each time for each previous possible moves
             //and summing it up, we need to divide the total probability by 8
         }
    }

    public static double calculateProbabilityToDownDP(int d, int r, int c, int k) {
        double[][][] dpStorage = new double[k][d][d];//define probability dp storage as 3-dimensional array because for each move we need to store the board with calculated probability.
        for (int x = 0; x < k; x ++) { //initialize the entire 3-dimensional array with -1 because we need to check if the probability is already calculated or not.
            // -1 cannot be a possible probability, so we can check if it's calculated or initialized at -1.
            for (int y = 0; y < d; y++) {
                Arrays.fill(dpStorage[x][y], -1);
            }
        }
        return calculateProbabilityDP(d, r, c, k, dpStorage);//call calculate probability with dimension, row, column, no-of moves and the dp storage.
    }

    public static double calculateProbabilityDP(int d, int r, int c, int k, double[][][] dpStorage) {//recursive method to calculate probability with dp storage
        double probability = 0.0;//initialize probability as 0.0
        if (k == 0) {//if number of moves left is 0, i.e. the base condition
            dpStorage[k][r][c] = 1;//assign and store probability as 1 inside appropriate dp storage
            return dpStorage[k][r][c];//return the stored probability from appropriate location
        } else {
            // if number of moves left is grater than 0
            for (int[] direction : directions) {//for all possible moves of the night, i.e. 8 moves
                int rBar = r + direction[0];//calculate next row position of night in a new variable
                int cBar = c + direction[1];//calculate next col position of night in a new variable
                if (rBar >= 0 && rBar < d && cBar >= 0 && cBar < d) {//if the newly calculated row and col position falls within the boards dimension
                    if (dpStorage[k-1][rBar][cBar] != -1) {//check if there is any pre-calculated probability in the dp storage at appropriate position using
                        // newly calculated variable. Must remember the moves value is now reduced by 1, so the dp to check is k-1 move board
                        //if there exist a pre-calculated probability value add that with current probability
                        probability = probability + dpStorage[k-1][rBar][cBar];
                    } else {//if no probability found as pre-calculated value
                        double calculatedProbability = calculateProbabilityDP(d, rBar, cBar, k-1, dpStorage); // call recursive probability calculate method with newly calculated position and k-1 move
                        dpStorage[k-1][rBar][cBar] = calculatedProbability;//store the returned probability in dp storage at appropriate place
                        probability = probability + calculatedProbability; // add the returned probability with current probability.
                    }
                }
            }
            return probability / 8;//return probability calculated divided by 8 because in each time we are making 8 moves.
        }
    }

    //This is the bottom-up approach with dp storage
    public static double calculateProbabilityBottomUpDP(int d, int r, int c, int k) {
        double[][] dpStorage1 = new double[d][d];//define dp storage1 as 2-dimensional array, as we will be overriding the probability we only need two dp storage
        //one to refer and the other to generate. We do not need k number of dp storage
        for (int y = 0; y < d; y++) {
            Arrays.fill(dpStorage1[y], 0);//initialize dp storage-1 all values to 0. This is because we need to generate probability, not to check if pre-generated or not
        }
        double[][] dpStorage2 = new double[d][d];//define dp storage2 as 2-dimensional array, as we will be overriding the probability we only need two dp storage
        //one to refer and the other to generate. We do not need k number of dp storage
        for (int y = 0; y < d; y++) {
            Arrays.fill(dpStorage2[y], 0);//initialize dp storage-2 all values to 0. This is because we need to generate probability, not to check if pre-generated or not
        }

        dpStorage1[r][c] = 1;//initialize dp storage start positions probability as 1. This the starting point where knight currently exist on the board and the probability is 1.

        for (int s = 0; s < k; s++) {//iterate for all moves, i.e. k moves
            dpStorage2 = new double[d][d];//????????????
            for (int row = 0; row < d; row++) {//iterate for the entire board starting row from 0 to provided dimension d
                for (int col = 0; col < d; col++) {//iterate for the entire board starting col from 0 to provided dimension d
                    for (int[] direction : directions) {//iterate for all 8 moves of a night
                        int rBar = row + direction[0];//calculate next row position, use different variable to store it
                        int cBar = col + direction[1];//calculate next col position, use different variable to store it
                        if (rBar >= 0 && rBar < d && cBar >= 0 && cBar < d) {//if the newly calculated position within the board
                            //calculate probability of night inside board by getting the probability from the previous board dp probability storage and diving the number by 8
                            //This is because the new boards probability of night at current row and col position within the board, i.e. r & c
                            //is the reverse move of the night's probability in previous board's probability at calculated rBar, cBar position.
                            //here we are calculating the probability of all the cell of the new board
                            // from previous board's probability which is possible by all 8 moves in previous board
                            dpStorage2[row][col] += dpStorage1[rBar][cBar] / 8;
                        }
                    }
                }
            }
            dpStorage1 = dpStorage2;//before next moves iteration set dp storage - 1 with the newly calculated board probability i.e. dp storage - 2
        }

        double result = 0;//finally calculate final probability by adding all the possible board positions probability from the last calculated dp storage, i.e. dp storage - 2
        for (int row = 0; row < d; row++) {//for all rows
            for (int col = 0; col < d; col++) {//for all cols
                result += dpStorage2[row][col];//add up all values in result
            }
        }
        return result;//return result.
    }



    public static void main(String[] args) {
        int d = 10;
        int r = 2;
        int c = 2;
        int k = 5;

        System.out.println("Probability ==> " + calculateProbability(d,r,c, k));
        System.out.println("Probability (DP-Top Down) ==> " + calculateProbabilityToDownDP(d,r,c, k));
        System.out.println("Probability (DP-Bottom Up) ==> " + calculateProbabilityBottomUpDP(d,r,c, k));
    }

}

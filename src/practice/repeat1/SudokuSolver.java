package practice.repeat1;

import java.util.Arrays;

public class SudokuSolver {

    public static int getBoxId(int r, int c) {
        int boxC = (int)Math.floor(c / 3);
        int boxR = (int)Math.floor((r / 3)) * 3;
        return (boxR + boxC);
    }

    public static void solveSudoku(int n, int[][] board) {
        boolean[][] rows = new boolean[n][n];
        boolean[][] cols = new boolean[n][n];
        boolean[][] boxes = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int v = board[i][j];
                if (v != 0) {
                    rows[i][v-1] = true;
                    cols[j][v-1] = true;
                    boxes[getBoxId(i, j)][v-1] = true;
                }
            }
        }
        solveSudokuByBacktracking(n, board, rows, cols, boxes, 0, 0);
    }

    public static boolean isValid(int v, int r, int c, boolean[][] rows, boolean[][] cols, boolean[][] boxes) {
        if (rows[r][v - 1] || cols[c][v - 1] || boxes[getBoxId(r, c)][v - 1]) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean solveSudokuByBacktracking(int n, int[][] board, boolean[][] rows, boolean[][] cols, boolean[][] boxes, int r, int c) {
        if (r == n-1 && c == n-1) return true;
        if (board[r][c] == 0) {
            for (int v = 1; v <= n; v++) {
                board[r][c] = v;
                if (isValid(v, r, c, rows, cols, boxes)) {
                    rows[r][v-1] = true;
                    cols[c][v-1] = true;
                    boxes[getBoxId(r, c)][v-1] = true;
                    int rBar = r;
                    int cBar = c;
                    if (cBar == n-1) {
                        rBar++;
                        cBar = 0;
                    } else {
                        cBar++;
                    }
                    if (solveSudokuByBacktracking(n, board, rows, cols, boxes, rBar, cBar)) return true;
                    rows[r][v-1] = false;
                    cols[c][v-1] = false;
                    boxes[getBoxId(r, c)][v-1] = false;
                }
                board[r][c] = 0;
            }
        } else {
            int rBar = r;
            int cBar = c;
            if (cBar == n-1) {
                rBar++;
                cBar = 0;
            } else {
                cBar++;
            }
            if (solveSudokuByBacktracking(n, board, rows, cols, boxes, rBar, cBar)) return true;
        }
        return false;
    }


    public static void main (String[] args) {
        int n = 9;
        int[][] board = new int[n][n];

        board[0][0] = 9;
        board[0][1] = 8;
        board[0][2] = 7;
        board[5][5] = 5;
        board[8][8] = 1;
        board[8][7] = 2;
        board[8][6] = 3;


        solveSudoku(n, board);

        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }
}

package practice;

import java.util.Arrays;

public class Sudoku {

    public static int getBoxId(int r, int c) {
        int colum = (int)Math.floor(c / 3);
        int row = (int)Math.floor( r / 3) * 3;
        return (colum + row);
    }

    public static boolean isValid(int value, int r, int c, boolean[][] rows, boolean[][] cols, boolean[][] boxes) {
        if (rows[r][value-1] || cols[c][value-1] || boxes[getBoxId(r, c)][value-1]) {
            return false;
        } else {
            return true;
        }
    }

    public static void solveSudoku(int[][] board) {
        int n = board.length;
        boolean[][] rows = new boolean[n][n];
        boolean[][] cols = new boolean[n][n];
        boolean[][] boxes = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 0) {
                    rows[i][board[i][j]-1] = true;
                    cols[j][board[i][j]-1] = true;
                    rows[getBoxId(i, j)][board[i][j]-1] = true;
                }
            }
        }
        solveRecursively(board, rows, cols, boxes, 0, 0);
    }

    public static boolean solveRecursively(int[][] board, boolean[][] rows, boolean[][] cols, boolean[][] boxes, int r, int c) {
        if (r == board.length || c == board.length) return true;
        if (board[r][c] == 0) {
            for (int value = 1; value <= 9; value++) {
                board[r][c] = value;
                if (isValid(value, r, c, rows, cols, boxes)) {
                    rows[r][value-1] = true;
                    cols[c][value-1] = true;
                    boxes[getBoxId(r, c)][value-1] = true;
                    int rBar, cBar;
                    if (c == 8) {
                        rBar = r+1;
                        cBar = 0;
                    } else {
                        rBar = r;
                        cBar = c+1;
                    }
                    if (solveRecursively(board, rows, cols, boxes, rBar, cBar)) return true;
                    rows[r][value-1] = false;
                    cols[c][value-1] = false;
                    boxes[getBoxId(r, c)][value-1] = false;
                }
                board[r][c] = 0;
            }
        } else {
            int rBar, cBar;
            if (c == 8) {
                rBar = r+1;
                cBar = 0;
            } else {
                rBar = r;
                cBar = c+1;
            }
            if (solveRecursively(board, rows, cols, boxes, rBar, cBar)) return true;
        }
        return false;
    }

    public static void main (String[] args) {
        int n = 9;
        int[][] board = new int[n][n];

        board[0][0] = 9;
        board[0][1] = 8;
        board[0][2] = 7;
        board[8][8] = 1;
        board[8][7] = 2;
        board[8][6] = 3;

        solveSudoku(board);

        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }
}

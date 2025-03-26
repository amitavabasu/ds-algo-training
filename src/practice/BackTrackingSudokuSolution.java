package practice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BackTrackingSudokuSolution {

    public static int[] getNextEmptySpace(int[][] board, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    public static int getBoxId(int row, int col) {
        int blockCol = (int)Math.floor(col / 3);
        int blocksRow = (int)Math.floor(row / 3) * 3;
//        System.out.println("Row:" + row + " Col:" + col + "generated blockId:" + (blocksRow+blockCol));
        return blocksRow + blockCol;
    }

    public static boolean isValid(int r, int c, int value, Set<Integer>[] rows, Set<Integer>[] columns, Set<Integer>[] blocks) {
        if (rows[r].contains(value) || columns[c].contains(value) || blocks[getBoxId(r, c)].contains(value)) {
            return false;
        } else {
            return true;
        }
    }


    public static void solveSudoku(int n, int[][] board) {
        Set<Integer>[] rows = new Set[n];
        Set<Integer>[] columns = new Set[n];
        Set<Integer>[] blocks = new Set[n];
        for (int i = 0; i < n; i++) {
            rows[i] = new HashSet<>(n);
            columns[i] = new HashSet<>(n);
            blocks[i] = new HashSet<>(n);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(board[i][j] == 0) {
                    //do nothing
                } else {
                    rows[i].add(board[i][j]);
                    columns[j].add(board[i][j]);
                    blocks[getBoxId(i, j)].add(board[i][j]);
                }
            }
        }
        int[] position = getNextEmptySpace(board, n);
        recursivelySolve(n, board, rows, columns, blocks, position[0], position[1]);
    }

    public static boolean done = false;
    public static void recursivelySolve(int n, int[][] board, Set<Integer>[] rows, Set<Integer>[] columns, Set<Integer>[] blocks, int r, int c) {
        if (board[r][c] != 0) return;
        int value = 1;
        while (value <= 9 && !done) {
            board[r][c] = value;
            if (isValid(r, c, value, rows, columns, blocks)) {
                if ( r == 6) {
                    System.out.println("");
                }
                if ( r == 7) {
                    System.out.println("");
                }
                if ( r == 8) {
                    System.out.println("");
                }

                rows[r].add(value);
                columns[c].add(value);
                blocks[getBoxId(r, c)].add(value);
                //go to the next cell
                int[] nextPos = getNextEmptySpace(board, n);
                if (nextPos != null) {
                    recursivelySolve(n, board, rows, columns, blocks, nextPos[0], nextPos[1]);
                    //<--
                    if (!done) {
                        board[r][c] = 0;
                        rows[r].remove(value);
                        columns[c].remove(value);
                        blocks[getBoxId(r, c)].remove(value);
                    }
                } else {
                    done = true;
                    return;
                }
            } else {
                board[r][c] = 0;
            }
            value++;
        }
//        System.out.println("row = " + r + " col = " + c);
    }

    public static void main (String[] args) {
        int n = 9;
        int[][] board = new int[n][n];

//        board[0][0] = 9;
//        board[0][1] = 8;
//        board[0][2] = 7;
//        board[8][8] = 1;
//        board[8][7] = 2;
//        board[8][6] = 3;


        solveSudoku(n, board);

        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }
}

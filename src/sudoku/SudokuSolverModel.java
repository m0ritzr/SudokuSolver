package sudoku;

import java.util.Arrays;

public class SudokuSolverModel implements SudokuSolver {
    private int[][] sudokuMatrix;

    public SudokuSolverModel() {
        sudokuMatrix = new int[9][9];
    }

    @Override
    public void setNumber(int r, int c, int nbr) {
        sudokuMatrix[r][c] = nbr;
    }

    @Override
    public int getNumber(int r, int c) {
        return sudokuMatrix[r][c];
    }

    @Override
    public void clearNumber(int r, int c) {
        sudokuMatrix[r][c] = 0;
    }

    @Override
    public boolean isValid(int r, int c, int nbr) {
        return false;
    }

    @Override
    public boolean isAllValid() {
        return false;
    }

    @Override
    public boolean solve() {
        return false;
    }

    @Override
    public void clear() {
        for (int[] row : sudokuMatrix) {
            Arrays.fill(row, 0);
        }
    }

    @Override
    public int[][] getMatrix() {
        return Arrays.stream(sudokuMatrix).map(int[]::clone).toArray(int[][]::new);
    }

    @Override
    public void setMatrix(int[][] nbrs) {
        sudokuMatrix = Arrays.stream(nbrs).map(int[]::clone).toArray(int[][]::new);
        for (int[] row : sudokuMatrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("");
    }
}

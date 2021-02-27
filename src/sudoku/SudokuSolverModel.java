package sudoku;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SudokuSolverModel implements SudokuSolver {
    private int[][] sudokuMatrix;

    public SudokuSolverModel() {
        sudokuMatrix = new int[9][9];
    }

    public void setNumber(int r, int c, int nbr) {
        sudokuMatrix[r][c] = nbr;
    }

    public int getNumber(int r, int c) {
        return sudokuMatrix[r][c];
    }

    public void clearNumber(int r, int c) {
        sudokuMatrix[r][c] = 0;
    }

    public boolean isValid(int r, int c, int nbr) {
        if (nbr == 0) {
            return true;
        } else {
            sudokuMatrix[r][c] = nbr;
            int[] row = getRow(r);

            int[] col = getCol(c);

            int[] threeByThree = getThreeByThreeAsArray(getThreeByThreeIndex(r, c));

            if (checkForDuplicates(row) && checkForDuplicates(col) && checkForDuplicates(threeByThree)) {
                return true;
            } else {
                sudokuMatrix[r][c] = 0;
                return false;
            }
        }
    }

    private int[] getRange(int index) {
        int[] range = new int[4];
        switch(index) {
            case 0:
                range[0] = 0;
                range[1] = 2;
                range[2] = 0;
                range[3] = 2;
                break;
            case 1:
                range[0] = 0;
                range[1] = 2;
                range[2] = 3;
                range[3] = 5;
                break;
            case 2:
                range[0] = 0;
                range[1] = 2;
                range[2] = 6;
                range[3] = 8;
                break;
            case 3:
                range[0] = 3;
                range[1] = 5;
                range[2] = 0;
                range[3] = 2;
                break;
            case 4:
                range[0] = 3;
                range[1] = 5;
                range[2] = 3;
                range[3] = 5;
                break;
            case 5:
                range[0] = 3;
                range[1] = 5;
                range[2] = 6;
                range[3] = 8;
                break;
            case 6:
                range[0] = 6;
                range[1] = 8;
                range[2] = 0;
                range[3] = 2;
                break;
            case 7:
                range[0] = 6;
                range[1] = 8;
                range[2] = 3;
                range[3] = 4;
                break;
            case 8:
                range[0] = 6;
                range[1] = 8;
                range[2] = 6;
                range[3] = 8;
                break;
            default:
                break;
        }
        return range;
    }

    private int getThreeByThreeIndex(int r, int c) {
        return (c/3 + (r/3) * 3);
    }

    private boolean checkForDuplicates(int[] array) {
        Set<Integer> set = new HashSet<>();

        for (int nbr : array) {
            if (nbr!=0 && set.contains(nbr)) {
                return true;
            }

            if (nbr != 0)  {
                set.add(nbr);
            }

        }

        return false;
    }

    private int[] getRow(int r) {
        return sudokuMatrix[r];
    }

    private int[] getCol(int c) {
        int[] col = new int[9];
        for(int i = 0; i<9; i++) {
            col[i] = sudokuMatrix[i][c];
        }
        return col;
    }

    private int[] getThreeByThreeAsArray(int index) {
        int[] threeByThree = new int[9];
        int[] ranges = getRange(index);
        int i = 0;
        for(int r = ranges[0]; r<=ranges[1]; r++) {
            for(int c = ranges[2]; c<=ranges[3]; c++) {
                threeByThree[i] = sudokuMatrix[r][c];
                i++;
            }
        }
        return threeByThree;
    }

    public boolean isAllValid() {
        for (int index = 0; index<9; index++) {
            if (checkForDuplicates(getRow(index))) {
                return false;
            } else if (checkForDuplicates(getCol(index))){
                return false;
            } else if (checkForDuplicates(getThreeByThreeAsArray(index))) {
                return false;
            }
        }
        return true;
    }

    public boolean solve() {
        return false;
    }

    public void clear() {
        for (int[] row : sudokuMatrix) {
            Arrays.fill(row, 0);
        }
    }

    public int[][] getMatrix() {
        return Arrays.stream(sudokuMatrix).map(int[]::clone).toArray(int[][]::new);
    }

    public void setMatrix(int[][] nbrs) {
        sudokuMatrix = Arrays.stream(nbrs).map(int[]::clone).toArray(int[][]::new);
        for (int[] row : sudokuMatrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("");
    }
}

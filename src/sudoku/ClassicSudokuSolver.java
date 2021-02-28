package sudoku;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ClassicSudokuSolver implements SudokuSolver {
    private int[][] sudokuMatrix;

    public ClassicSudokuSolver() {
        sudokuMatrix = new int[9][9];
    }

    /**
     * Sets a number nbr into the matrix in row r and column c
     * @param r The row
     * @param c The column
     * @param nbr The number to insert in box r, c
     * @throws IllegalArgumentException if number is not [1..9]
     */
    public void setNumber(int r, int c, int nbr) {
        if (nbr > 9 || nbr < 1) {
            throw new IllegalArgumentException("Number is not between 1 and 9");
        } else {
            sudokuMatrix[r][c] = nbr;
        }
    }

    /**
     * Gets a number from the spot in row r and column c
     * @param r The row
     * @param c The column
     * @return the number at row r, column c
     */
    public int getNumber(int r, int c) {
        return sudokuMatrix[r][c];
    }

    public void clearNumber(int r, int c) {
        sudokuMatrix[r][c] = 0;
    }

    /**
     * Checks if a number nbr is valid in the sudoku matrix.
     * @param r row to check
     * @param c column to check
     * @param nbr number to check
     * @return true if number is valid in this spot
     */
    public boolean isValid(int r, int c, int nbr) {
        if (nbr == 0) {
            return true;
        } else {
            setNumber(r, c, nbr);
            int[] row = getRow(r);
            int[] col = getCol(c);
            int[] threeByThree = getThreeByThreeAsArray(getThreeByThreeIndex(r, c));
            clearNumber(r,c);
            if (checkForDuplicates(row) || checkForDuplicates(col) || checkForDuplicates(threeByThree)) {
                return false;
            } else {
                return true;
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
                range[3] = 5;
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

    /**
     * Checks for duplicates in an array
     * @param array the array to check
     * @return true if array contains a duplicate, false if not
     */
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

    public int[] getRow(int r) {
        int[] row = new int[9];
        for (int i = 0; i<9; i++) {
            row[i] = sudokuMatrix[r][i];
        }
        return row;
    }

    public int[] getCol(int c) {
        int[] col = new int[9];
        for(int i = 0; i<9; i++) {
            col[i] = sudokuMatrix[i][c];
        }
        return col;
    }

    public int[] getThreeByThreeAsArray(int index) {
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
        return solveMoritz(0, 0);
    }

    private boolean solveJoel(int r, int c) {
       if (c == 9) {                            //next row if col is finished
           c = 0;
           r++;
       }
        if (r == 9) {                           //Base
            return true;
        }
        if (isEmpty(r,c)) {
            int i = 0;
            do {
                i++;
                setNumber(r,c,i);
            } while (!solveJoel(r, c + 1) && i < 9);       //recursive
            return true;
           }
        solveJoel(r, c+1);
        return false;
    }
          
    private boolean solveMoritz(int row, int col)
    {
        if (row == 8 && col == 9) {
            return true;
        }

        if (col == 9) {
            row++;
            col = 0;
        }

        if (!isEmpty(row, col))
            return solveMoritz(row, col + 1);

        for (int nbr = 1; nbr <= 9; nbr++) {
            if (isValid(row, col, nbr)) {
                setNumber(row, col, nbr);
                if (solveMoritz(row, col + 1))
                    return true;
            }
            clearNumber(row, col);
        }
        return false;
    }

    private boolean isEmpty(int r, int c) {
        return sudokuMatrix[r][c] == 0;
    }


    /**
     *
     */
    public void clear() {
        for (int[] row : sudokuMatrix) {
            Arrays.fill(row, 0);
        }
    }

    /**
     * Returns the numbers in the grid. An empty box i represented
     * by the value 0.
     *
     * @return the numbers in the grid
     */
    public int[][] getMatrix() {
        return Arrays.stream(sudokuMatrix).map(int[]::clone).toArray(int[][]::new);
    }

    /**
     * Fills the grid with the numbers in nbrs.
     *
     * @param nbrs the matrix with the numbers to insert
     * @throws IllegalArgumentException if nbrs have wrong dimension or containing values not in [0..9]
     */
    public void setMatrix(int[][] nbrs) {
        if (nbrs.length != 9 || nbrs[0].length != 9) {
            throw new IllegalArgumentException("nbrs has wrong dimension");
        }
        for (int i = 0; i<9; i++) {
            for (int j = 0; j<9; j++){
                if (nbrs[i][j] > 9 || nbrs[i][j] < 0) {
                    throw new IllegalArgumentException("Value is not in [0..9]");
                } else {
                    sudokuMatrix[i][j] = nbrs[i][j];
                }
            }
        }
    }
}

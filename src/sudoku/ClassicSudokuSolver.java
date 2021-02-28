package sudoku;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of <code>SudokuSolver</code> for use with a classic nine-by-nine sudoku.
 */
public class ClassicSudokuSolver implements SudokuSolver {
    private int[][] sudokuMatrix;

    /**
     * Constructs new ClassicSudokuSolver with a 9x9 matrix
     */
    public ClassicSudokuSolver() {
        sudokuMatrix = new int[9][9];
    }

    /**
     * Sets the number nbr in box r, c.
     *
     * @param r   The row
     * @param c   The column
     * @param nbr The number to insert in box r, c
     * @throws IllegalArgumentException if r or c is outside [0..8] or
     *                                  number is outside [1..9]
     */
    public void setNumber(int r, int c, int nbr) {
        if (nbr > 9 || nbr < 1) {
            throw new IllegalArgumentException("Number is not between 1 and 9");
        } else if (r < 0 || r > 8 || c < 0 || c > 8) {
            throw new IllegalArgumentException("r or c are outside of the dimension");
        } else {
            sudokuMatrix[r][c] = nbr;
        }
    }

    /**
     * Returns the number in box r,c. If the box is empty 0 is returned.
     *
     * @param r The row
     * @param c The column
     * @return the number in box r,c or 0 if the box is empty.
     * @throws IllegalArgumentException if r or c is outside [0..8]
     */
    public int getNumber(int r, int c) {
        if (r < 0 || r > 8 || c < 0 || c > 8) {
            throw new IllegalArgumentException("r or c are outside of the dimension");
        } else {
            return sudokuMatrix[r][c];
        }
    }

    /**
     * Clears the number in box r,c (sets it to 0)
     *
     * @param r The row
     * @param c The column
     * @throws IllegalArgumentException if r or c are out of bounds [0..8]
     */
    public void clearNumber(int r, int c) {
        if (r < 0 || r > 8 || c < 0 || c > 8) {
            throw new IllegalArgumentException("r or c are outside of the dimension");
        } else {
            sudokuMatrix[r][c] = 0;
        }
    }

    /**
     * Checks if value nbr is valid in box r,c
     *
     * @param r   The row
     * @param c   The column
     * @param nbr The number to check
     * @return true if number is valid in box r,c, false if not
     * @throws IllegalArgumentException if r,c out of bounds [0..8] or nbr is outside of [1..9]
     */
    public boolean isValid(int r, int c, int nbr) {
        if (nbr > 9 || nbr < 1) {
            throw new IllegalArgumentException("Number is not between 1 and 9");
        } else if (r < 0 || r > 8 || c < 0 || c > 8) {
            throw new IllegalArgumentException("r or c are outside of the dimension");
        } else {
            setNumber(r, c, nbr);
            int[] row = getRow(r);
            int[] col = getCol(c);
            int[] threeByThree = getThreeByThreeAsArray(getThreeByThreeIndex(r, c));
            clearNumber(r, c);
            return !checkForDuplicates(row) && !checkForDuplicates(col) && !checkForDuplicates(threeByThree);
        }
    }

    /**
     * Checks if all values in SudokuSolver's matrix are valid.
     *
     * @return true if all values are valid, false if any value is not valid
     */
    public boolean isAllValid() {
        for (int index = 0; index < 9; index++) {
            if (checkForDuplicates(getRow(index))) {
                return false;
            } else if (checkForDuplicates(getCol(index))) {
                return false;
            } else if (checkForDuplicates(getThreeByThreeAsArray(index))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Tries to solve the sudoku.
     *
     * @return true if it was solved, false if not solvable
     */
    public boolean solve() {
        return solve(0, 0);
    }

    /**
     * Clears the sudoku.
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
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (nbrs[i][j] > 9 || nbrs[i][j] < 0) {
                    throw new IllegalArgumentException("Value is not in [0..9]");
                } else {
                    sudokuMatrix[i][j] = nbrs[i][j];
                }
            }
        }
    }

    /**
     * Recursive private helpmethod for solving the sudoko
     *
     * @param r the row to start in
     * @param c the column to start in
     * @return true if the recursive solve was successful
     */
    private boolean solve(int r, int c) {
        if (r == 8 && c == 9) {
            return true;
        }

        if (c == 9) {
            r++;
            c = 0;
        }

        if (!isEmpty(r, c))
            return solve(r, c + 1);

        for (int nbr = 1; nbr <= 9; nbr++) {
            if (isValid(r, c, nbr)) {
                setNumber(r, c, nbr);
                if (solve(r, c + 1))
                    return true;
            }
            clearNumber(r, c);
        }
        return false;
    }

    /**
     * Private helpmethod to check if a box r,c is empty (= 0)
     *
     * @param r the row
     * @param c the column
     * @return true if box is empty, otherwise false
     */
    private boolean isEmpty(int r, int c) {
        return sudokuMatrix[r][c] == 0;
    }

    /**
     * Returns range in rows and columns for a three-by-three sudoku area by index.
     * Indexes are numbered [0..8] from top left to bottom right.
     *
     * @param index the index of the three-by-three area
     * @return range as [r1, r2, c1, c2]
     */
    private int[] getRange(int index) {
        int[] range = new int[4];
        switch (index) {
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

    /**
     * Private helpmethod to get the three by three area index for a box r,c
     *
     * @param r the row
     * @param c the column
     * @return index for three by three area box r,c is in
     */
    private int getThreeByThreeIndex(int r, int c) {
        return (c / 3 + (r / 3) * 3);
    }

    /**
     * Private helpmethod to check for duplicates in an array
     *
     * @param array the array to check
     * @return true if duplicates exist, false if not
     */
    private boolean checkForDuplicates(int[] array) {
        Set<Integer> set = new HashSet<>();

        for (int nbr : array) {
            if (nbr != 0 && set.contains(nbr)) {
                return true;
            }

            if (nbr != 0) {
                set.add(nbr);
            }
        }

        return false;
    }

    /**
     * Returns row r in sudokuMatrix as an array copied
     *
     * @param r the row
     * @return the copied array
     */
    private int[] getRow(int r) {
        int[] row = new int[9];
        System.arraycopy(sudokuMatrix[r], 0, row, 0, 9);
        return row;
    }

    /**
     * Returns column c in sudokuMatrix as an array copied
     *
     * @param c the column
     * @return the copied array
     */
    private int[] getCol(int c) {
        int[] col = new int[9];
        for (int i = 0; i < 9; i++) {
            col[i] = sudokuMatrix[i][c];
        }
        return col;
    }

    /**
     * Returns three-by-three area in sudokuMatrix by index as an array copied
     *
     * @param index the index of the three-by-three area
     * @return the copied array
     */
    private int[] getThreeByThreeAsArray(int index) {
        int[] threeByThree = new int[9];
        int[] ranges = getRange(index);
        int i = 0;
        for (int r = ranges[0]; r <= ranges[1]; r++) {
            for (int c = ranges[2]; c <= ranges[3]; c++) {
                threeByThree[i] = sudokuMatrix[r][c];
                i++;
            }
        }
        return threeByThree;
    }

}

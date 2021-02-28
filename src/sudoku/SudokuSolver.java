package sudoku;

public interface SudokuSolver {
    /**
     * Sets the number nbr in box r, c.
     *
     * @param r   The row
     * @param c   The column
     * @param nbr The number to insert in box r, c
     * @throws IllegalArgumentException if r or c are out of bounds [0..8] or nbr is outside [1..9]
     */
    public void setNumber(int r, int c, int nbr);

    /**
     * Returns the number in box r,c. If the box is empty 0 is returned.
     *
     * @param r The row
     * @param c The column
     * @return the number in box r,c or 0 if the box is empty.
     * @throws IllegalArgumentException if r or c are out of bounds [0..8]
     */
    public int getNumber(int r, int c);

    /**
     * Clears the number in box r,c (sets it to 0)
     *
     * @param r The row
     * @param c The column
     * @throws IllegalArgumentException if r or c are out of bounds [0..8]
     */
    public void clearNumber(int r, int c);

    /**
     * Checks if value nbr is valid in box r,c
     *
     * @param r   The row
     * @param c   The column
     * @param nbr The number to check
     * @return true if number is valid in box r,c, false if not
     * @throws IllegalArgumentException if r,c out of bounds [0..8] or nbr is outside of [1..9]
     */
    public boolean isValid(int r, int c, int nbr);

    /**
     * Checks if all values in SudokuSolver's matrix are valid.
     *
     * @return true if all values are valid, false if any value is not valid
     */
    public boolean isAllValid();

    /**
     * Tries to solve the sudoku.
     *
     * @return true if it was solved, false if not solvable
     */
    public boolean solve();

    /**
     * Clears the sudoku.
     */
    public void clear();

    /**
     * Returns the numbers in the grid. An empty box i represented
     * by the value 0.
     *
     * @return the numbers in the grid
     */
    public int[][] getMatrix();

    /**
     * Fills the grid with the numbers in nbrs.
     *
     * @param nbrs the matrix with the numbers to insert
     * @throws IllegalArgumentException if nbrs have wrong dimension or containing values not in [0..9]
     */
    public void setMatrix(int[][] nbrs);

    /**
     * Returns the dimension of the grid
     *
     * @return the dimension of the grid
     */
    public default int getDimension() {
        return 9;
    }
}

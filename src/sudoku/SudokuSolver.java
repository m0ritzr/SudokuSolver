package sudoku;

public interface SudokuSolver {
    /**
     * Sets the number nbr in box r, c.
     *
     * @param r
     *            The row
     * @param c
     *            The column
     * @param nbr
     *            The number to insert in box r, c
     * @throws IllegalArgumentException
     *             if r or c is outside [0..getDimension()-1] or
     *             number is outside [1..9]
     */
    public void setNumber(int r, int c, int nbr);

    /**
     * Returns the number in box r,c. If the box i empty 0 is returned.
     *
     * @param r The row
     * @param c The column
     * @return the number in box r,c or 0 if the box is empty.
     * @throws IllegalArgumentException if r or c is outside [0..getDimension()-1]
     */
    public int getNumber(int r, int c);

    // TÃ¶mmer rutan r,c.
    // IllegalArgumentException om fel vÃ¤rde pÃ¥ r eller c
    public void clearNumber(int r, int c);

    // Kontrollerar om vÃ¤rdet nbr i rutan r,c Ã¤r ok enligt reglerna.
    // IllegalArgumentException om fel vÃ¤rde pÃ¥ r, c eller nbr
    public boolean isValid(int r, int c, int nbr);

    // Kontrollerar att alla ifyllda siffrorna uppfyller reglerna.
    public boolean isAllValid();

    // FÃ¶rsÃ¶ker lÃ¶sa sudokut och returnerar true om det var lÃ¶sbart, annars false.
    public boolean solve();

    // TÃ¶mmer alla rutorna i sudokut
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
     * @throws IllegalArgumentException
     *             if nbrs have wrong dimension or containing values not in [0..9]
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

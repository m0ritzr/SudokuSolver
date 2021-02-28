package sudoku;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 * Text field for Sudoku Implementation which only accepts one character and only values from [1..9]
 */
public class SudokuTextField extends JTextField {

    /**
     * Constructs a new SudokuTextField and calls the super class
     */
    public SudokuTextField() {
        super();
    }

    /**
     * Returns the fields data model to be used at construction. An instance of SudokuFieldDocument is returned.
     *
     * @return the data model
     */
    @Override
    protected Document createDefaultModel() {
        return new SudokoFieldDocument();
    }

    /**
     * Document for SudokuTextField which only accepts Strings containing one character and only values [1..9]
     */
    private static class SudokoFieldDocument extends PlainDocument {

        /**
         * Inserts some content into the document. Refuses to insert if string in document is longer than 1.
         * Refuses to insert if string to be inserted is not [1..9]
         *
         * @param offset the starting offset
         * @param str the string to insert; does nothing with null/empty strings
         * @param attr the attributes for the inserted content
         * @exception BadLocationException  the given insert position is not a valid
         *   position within the document
         */
        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException{
            if (str == null) return;

            if ((getLength() + str.length() <= 1)) {
                try {
                    int current = Integer.parseInt(str);
                    if ((current > 0) && (current < 10) ) {
                        super.insertString(offset, str, attr);
                    }
                } catch (NumberFormatException ignored) {
                }
            }

        }
    }
}

package sudoku;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class SudokuTextField extends JTextField {

    public SudokuTextField() {
        super();
    }

    @Override
    protected Document createDefaultModel() {
        return new SudokoFieldDocument();
    }

    private static class SudokoFieldDocument extends PlainDocument {
        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException{
            if (str == null) return;

            if ((getLength() + str.length() <= 1)) {
                try {
                    int current = Integer.parseInt(str);
                    if ((current > 0) && (current < 10) ) {
                        super.insertString(offset, str, attr);
                    }
                } catch (NumberFormatException e) {
                    return;
                }
            }

        }
    }
}

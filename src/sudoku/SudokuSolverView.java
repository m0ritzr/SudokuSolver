package sudoku;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

public class SudokuSolverView {
    private static SudokuSolver solver;
    private JButton solveButton;
    private JButton clearButton;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel fieldPanel;
    private SudokuTextField[][] sudokuTextFields;

    /**
     * Constructs a new SudokuSolverView window, adds action listeners to the buttons
     */
    public SudokuSolverView() {
        clearButton.addActionListener(e -> {
            for (SudokuTextField[] row : sudokuTextFields) {
                for (SudokuTextField field : row) {
                    field.setText("");
                }
            }
            solver.clear();
        });

        solveButton.addActionListener(e -> {
            int[][] nbrs = new int[9][9];
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    nbrs[r][c] = getNumber(r, c);
                }
            }
            solver.setMatrix(nbrs);
            if (!solver.isAllValid()) {
                System.out.println("Not all valid!");
                JOptionPane.showMessageDialog(mainPanel,
                        "Inte alla värden är godkända!",
                        "Fel värden",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!solver.solve()) {
                JOptionPane.showMessageDialog(mainPanel,
                        "Sudokun kunde tyvärr inte lösas.",
                        "Ej lösbart",
                        JOptionPane.ERROR_MESSAGE);
            }

            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    setNumber(r, c, solver.getNumber(r, c));
                }
            }


        });

    }

    public static void main(String[] args) {
        solver = new ClassicSudokuSolver();

        JFrame frame = new JFrame("Sudoku Solver");
        frame.setContentPane(new SudokuSolverView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 550);
        frame.setVisible(true);
    }

    /**
     * Creates UI Components <code>fieldPanel</code> and all nine by nine <code>SudokuTextFields</code>
     */
    private void createUIComponents() {
        fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(9, 9));
        sudokuTextFields = new SudokuTextField[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                SudokuTextField current = new SudokuTextField();
                if (((i < 3 || i > 5) && (j < 3 || j > 5)) || ((i > 2 && i < 6) && (j > 2 && j < 6))) {
                    current.setBackground(Color.orange);
                }
                current.setSize(50, 50);
                current.setHorizontalAlignment(JTextField.CENTER);
                current.setFont(new Font("SansSerif", Font.BOLD, 20));
                sudokuTextFields[i][j] = current;
                fieldPanel.add(sudokuTextFields[i][j]);
            }
        }


    }

    /**
     * Gets number from <code>SudokuTextField</code> r,c.
     *
     * @param r the row
     * @param c the column
     * @return number in the field
     */
    private int getNumber(int r, int c) {
        String str = sudokuTextFields[r][c].getText();
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Sets number in <code>SudokuTextField</code> r,c. If nbr=0, field gets set empty.
     *
     * @param r   the row
     * @param c   the column
     * @param nbr the number to insert.
     */
    private void setNumber(int r, int c, int nbr) {
        if (nbr == 0) {
            sudokuTextFields[r][c].setText("");
        } else {
            sudokuTextFields[r][c].setText(Integer.toString(nbr));
        }
    }
}

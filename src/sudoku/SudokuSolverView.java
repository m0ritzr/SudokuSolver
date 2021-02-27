package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuSolverView {
    private JButton solveButton;
    private JButton clearButton;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JPanel fieldPanel;
    private SudokuTextField[][] sudokuTextFields;
    private static SudokuSolverModel solver;

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
            for (int r = 0; r<9; r++) {
                for (int c = 0; c<9; c++) {
                    nbrs[r][c] = getNumber(r, c);
                }
            }
            solver.setMatrix(nbrs);
            if (!solver.isAllValid()) {
                System.out.println("Not all valid!");
            }
        });

    }

    public static void main(String[] args) {
        solver = new SudokuSolverModel();

        JFrame frame = new JFrame("SudokuSolverView");
        frame.setContentPane(new SudokuSolverView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 550);
        frame.setVisible(true);
    }



    private void createUIComponents() {
        fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(9, 9));
        sudokuTextFields = new SudokuTextField[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                SudokuTextField current = new SudokuTextField();
                if (((i<3 || i > 5 ) && (j < 3 || j > 5)) || ((i>2 && i<6) && (j>2 && j<6) )) {
                    current.setBackground(Color.ORANGE);
                }
                current.setSize(50, 50);
                current.setHorizontalAlignment(JTextField.CENTER);
                current.setFont(new Font("SansSerif", Font.BOLD, 20));
                sudokuTextFields[i][j] = current;
                fieldPanel.add(sudokuTextFields[i][j]);
            }
        }


    }

    private int getNumber(int r, int c) {
        String str = sudokuTextFields[r][c].getText();
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}

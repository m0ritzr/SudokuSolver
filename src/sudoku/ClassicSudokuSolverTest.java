package sudoku;

import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ClassicSudokuSolverTest {
    ClassicSudokuSolver solver;

    @BeforeEach
    void setUp() {
        solver = new ClassicSudokuSolver();
        int[][] matrix = {
                {0, 0, 8, 0, 0, 9, 0, 6, 2},
                {0, 0, 0, 0, 0, 0, 0, 0, 5},
                {1, 0, 2, 5, 0, 0, 0, 0, 0},
                {0, 0, 0, 2, 1, 0, 0, 9, 0},
                {0, 5, 0, 0, 0, 0, 6, 0, 0},
                {6, 0, 0, 0, 0, 0, 0, 2, 8},
                {4, 1, 0, 6, 0, 8, 0, 0, 0},
                {8, 6, 0, 0, 3, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 4, 0, 0}
        };
        solver.setMatrix(matrix);
        solver.solve();
    }

    @org.junit.jupiter.api.Test
    void isValid() {
    }

    @org.junit.jupiter.api.Test
    void getRow() {
        System.out.println("Row:");
        System.out.println(Arrays.toString(solver.getRow(0)));
        System.out.println("");
    }

    @org.junit.jupiter.api.Test
    void getCol() {
        System.out.println("Col:");
        System.out.println(Arrays.toString(solver.getCol(0)));
        System.out.println("");
    }

    @org.junit.jupiter.api.Test
    void getThreeByThreeAsArray() {
        System.out.println("ThreeByThree:");
        System.out.println(Arrays.toString(solver.getThreeByThreeAsArray(0)));
        System.out.println("");
    }

    @org.junit.jupiter.api.Test
    void getMatrix() {
        int[][] matrix = solver.getMatrix();
        System.out.println("Matrix:");
        for(int i = 0; i<9; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

}
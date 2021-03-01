package sudoku;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassicSudokuSolverTest {
    ClassicSudokuSolver solver;

    @BeforeEach
    void setUp() {
        solver = new ClassicSudokuSolver();
    }

    @AfterEach
    void tearDown() {
    solver.clear();
    }

    @Test
    void testSetNumber() {
        solver.setNumber(1,1,1);
        int i = solver.getNumber(1,1);
        assertEquals(i, 1);
    }

    @Test
    void testGetNumber() {
        solver.setNumber(1,1,1);
        int i = solver.getNumber(1,1);
        assertEquals(i, 1);
    }

    @Test
    void testClearNumber() {
        solver.setNumber(1, 1, 1);
        solver.clearNumber(1, 1);
        int i = solver.getNumber(1, 1);
        assertEquals(i, 0);
    }

    @Test
    void testCheckForDuplicates() {
        int[][] matrixStart = {
                {1, 0, 2, 3, 4, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {6, 0, 0, 0, 0, 0, 0, 0, 0},
                {7, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        solver.setMatrix(matrixStart);
        boolean row = solver.isValid(0,1,1);
        boolean col = solver.isValid(1,0,1);
        boolean group = solver.isValid(1,1,1);
        assertFalse(row);
        assertFalse(col);
        assertFalse(group);

        row = solver.isValid(0,1,5);
        col = solver.isValid(1,0,5);
        group = solver.isValid(1,1,5);
        assertTrue(row);
        assertTrue(col);
        assertTrue(group);
    }

    @Test
    void testGetThreeByThreeAsArray() {
        int[][] matrixStart = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 2, 3, 0, 0, 0},
                {0, 0, 0, 4, 5, 6, 0, 0, 0},
                {0, 0, 0, 7, 8, 9, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        solver.setMatrix(matrixStart);
        int[] resultArray = solver.getThreeByThreeAsArray(4);
        int[] trueArray = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        for(int i = 0; i < 9; i++) {
            assertEquals(resultArray[i], trueArray[i]);
        }
    }

    @Test
    void testIsAllValid() {
        int[][] matrixTrue = {
                {5,4,8,1,7,9,3,6,2},
                {3,7,6,8,2,4,9,1,5},
                {1,9,2,5,6,3,8,7,4},
                {7,8,4,2,1,6,5,9,3},
                {2,5,9,3,8,7,6,4,1},
                {6,3,1,9,4,5,7,2,8},
                {4,1,5,6,9,8,2,3,7},
                {8,6,7,4,3,2,1,5,9},
                {9,2,3,7,5,1,4,8,6}
        };
        solver.setMatrix(matrixTrue);
        boolean isAllValid = solver.isAllValid();
        assertTrue(isAllValid);
        solver.setNumber(4, 4, 1);
        isAllValid = solver.isAllValid();
        assertFalse(isAllValid);
    }

    @Test
    void testClear() {
        int[][] matrixStart = {
                {5,4,8,1,7,9,3,6,2},
                {3,7,6,8,2,4,9,1,5},
                {1,9,2,5,6,3,8,7,4},
                {7,8,4,2,1,6,5,9,3},
                {2,5,9,3,8,7,6,4,1},
                {6,3,1,9,4,5,7,2,8},
                {4,1,5,6,9,8,2,3,7},
                {8,6,7,4,3,2,1,5,9},
                {9,2,3,7,5,1,4,8,6}
        };
        solver.setMatrix(matrixStart);
        solver.clear();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                assertEquals(solver.getNumber(r,c), 0);
            }
        }
    }

    @Test
    void testGetAndSetMatrix() {
        int[][] matrixStart = {
                {5, 4, 8, 1, 7, 9, 3, 6, 2},
                {3, 7, 6, 8, 2, 4, 9, 1, 5},
                {1, 9, 2, 5, 6, 3, 8, 7, 4},
                {7, 8, 4, 2, 1, 6, 5, 9, 3},
                {2, 5, 9, 3, 8, 7, 6, 4, 1},
                {6, 3, 1, 9, 4, 5, 7, 2, 8},
                {4, 1, 5, 6, 9, 8, 2, 3, 7},
                {8, 6, 7, 4, 3, 2, 1, 5, 9},
                {9, 2, 3, 7, 5, 1, 4, 8, 6}
        };
        solver.setMatrix(matrixStart);
        int[][] matrixResult = solver.getMatrix();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                assertEquals(matrixResult[r][c], matrixStart[r][c]);
            }
        }
    }

    @Test
    void testEmptySudoku() {
        solver.solve();
        assertTrue(solver.isAllValid());
    }

    @Test
    void unsolvableSudokuRow() {
        int[][] matrixStart = {
                {0, 0, 5, 0, 0, 5, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        solver.setMatrix(matrixStart);
        boolean solvable = solver.solve();
        assertFalse(solvable);
    }

    @Test
    void unsolvableSudokuCol() {
        int[][] matrixStart = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
        };
        solver.setMatrix(matrixStart);
        boolean solvable = solver.solve();
        assertFalse(solvable);
    }

    @Test
    void unsolvableSudokuThreeByThree() {
        int[][] matrixStart = {
                {1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        solver.setMatrix(matrixStart);
        boolean solvable = solver.solve();
        assertFalse(solvable);
    }

    @Test
    void TestCase3() {
        int[][] matrixStart = {
                {1, 2, 3, 0, 0, 0, 0, 0, 0},
                {4, 5, 6, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 7, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        solver.setMatrix(matrixStart);
        boolean solvable = solver.solve();
        assertFalse(solvable);

        solver.clearNumber(2,3);
        solvable = solver.solve();
        assertTrue(solvable);
    }

    @Test
    void TestCase4() {
        int[][] matrixStart = {
                {0, 0, 5, 5, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        solver.setMatrix(matrixStart);
        boolean solvable = solver.solve();
        assertFalse(solvable);

        solver.clear();
        solvable = solver.solve();
        assertTrue(solvable);
    }

    @Test
    void TestCase5() {
        int[][] matrixStart = {
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
        solver.setMatrix(matrixStart);
        solver.solve();
        int[][] matrixResult = solver.getMatrix();
        int[][] matrixTrue = {
            {5,4,8,1,7,9,3,6,2},
            {3,7,6,8,2,4,9,1,5},
            {1,9,2,5,6,3,8,7,4},
            {7,8,4,2,1,6,5,9,3},
            {2,5,9,3,8,7,6,4,1},
            {6,3,1,9,4,5,7,2,8},
            {4,1,5,6,9,8,2,3,7},
            {8,6,7,4,3,2,1,5,9},
            {9,2,3,7,5,1,4,8,6}
        };
        for (int i = 0; i<9; i++) {
            assertArrayEquals(matrixTrue[i], matrixResult[i]);
        }
    }

@Test
void TestCase6() {
    int[][] matrixStart = {
            {8, 4, 0, 0, 0, 0, 7, 0, 1},
            {0, 0, 0, 0, 8, 0, 0, 5, 0},
            {0, 0, 6, 0, 0, 0, 0, 0, 4},
            {0, 7, 0, 1, 3, 0, 4, 0, 0},
            {0, 2, 3, 0, 0, 0, 1, 9, 8},
            {0, 0, 0, 5, 0, 0, 0, 0, 3},
            {7, 9, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 2, 0, 0, 0, 9},
            {0, 0, 8, 0, 4, 0, 0, 3, 0}
    };
    solver.setMatrix(matrixStart);
    solver.solve();
    int[][] matrixResult = solver.getMatrix();
    int[][] matrixTrue = {
            {8, 4, 2, 3, 5, 9, 7, 6, 1},
            {1, 3, 7, 6, 8, 4, 9, 5, 2},
            {9, 5, 6, 2, 7, 1, 3, 8, 4},
            {6, 7, 9, 1, 3, 8, 4, 2, 5},
            {5, 2, 3, 4, 6, 7, 1, 9, 8},
            {4, 8, 1, 5, 9, 2, 6, 7, 3},
            {7, 9, 5, 8, 1, 3, 2, 4, 6},
            {3, 6, 4, 7, 2, 5, 8, 1, 9},
            {2, 1, 8, 9, 4, 6, 5, 3, 7}
    };
    for (int i = 0; i<9; i++) {
        assertArrayEquals(matrixTrue[i], matrixResult[i]);
    }
}

}
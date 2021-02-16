package cz.zpapez.lcs.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LcsMatrixBuilderTest {

    private LcsMatrixBuilder builder = new LcsMatrixBuilder();

    @Test
    public void itShouldReturnZeroForEmptyStrings() {
        int[][] matrix = builder.buildLcsMatrix("", "");

        assertEquals(1, matrix.length);
        assertEquals(1, matrix[0].length);
        assertEquals(0, matrix[0][0]);
    }

    // https://en.wikipedia.org/w/index.php?title=Longest_common_subsequence_problem&section=8#Traceback_approach
    @Test
    public void itShouldReturnCorrectlyForWikiUseCase() {
        int[][] matrix = builder.buildLcsMatrix("GAC", "AGCAT");

        assertEquals(4, matrix.length);
        assertArrayEquals(new int[] {0, 0, 0, 0, 0, 0}, matrix[0]);
        assertArrayEquals(new int[] {0, 0, 1, 1, 1, 1}, matrix[1]);
        assertArrayEquals(new int[] {0, 1, 1, 1, 2, 2}, matrix[2]);
        assertArrayEquals(new int[] {0, 1, 1, 2, 2, 2}, matrix[3]);
    }
}

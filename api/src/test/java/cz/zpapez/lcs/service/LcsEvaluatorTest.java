package cz.zpapez.lcs.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cz.zpapez.lcs.model.DiffModel;
import cz.zpapez.lcs.model.DiffType;

@ExtendWith(MockitoExtension.class)
public class LcsEvaluatorTest {

    @Mock
    private LcsMatrixBuilder lcsMatrixBuilder;

    @InjectMocks
    private LcsEvaluator evaluator;

    @AfterEach
    public void afterEach() {
        verifyNoMoreInteractions(lcsMatrixBuilder);
    }

    @Test
    public void itShouldReturnEmptyForEmptyStrings() {
        when(lcsMatrixBuilder.buildLcsMatrix("", ""))
            .thenReturn(new int[][] {
                    {0},
                    {0}
            });
        List<DiffModel> diffModels = evaluator.lcs("", "");
        assertEquals(0, diffModels.size());
    }

    @Test
    public void itShouldReturnOneMatchingModelForEqualStrings() {
        when(lcsMatrixBuilder.buildLcsMatrix("ab", "ab"))
            .thenReturn(new int[][] {
                    {0, 0, 0},
                    {0, 1, 1},
                    {0, 1, 2}
            });

        List<DiffModel> diffModels = evaluator.lcs("ab", "ab");

        assertEquals(1, diffModels.size());
        assertModelAtPosition(diffModels.get(0), DiffType.MATCHED, "ab");
    }

    @Test
    public void itShouldReturnInsertionModelRecognizedOnSecondLine() {
        when(lcsMatrixBuilder.buildLcsMatrix("a\nb", "a\nbc"))
            .thenReturn(new int[][] {
                    {0, 0, 0, 0, 0},
                    {0, 1, 1, 1, 1},
                    {0, 1, 2, 2, 2},
                    {0, 1, 2, 3, 3}
            });

        List<DiffModel> diffModels = evaluator.lcs("a\nb", "a\nbc");

        assertEquals(2, diffModels.size());
        assertModelAtPosition(diffModels.get(0), DiffType.MATCHED, "a\nb");
        assertModelAtPosition(diffModels.get(1), DiffType.ADDED, "c");
    }

    @Test
    public void itShouldReturnDeletionModelRecognizedOnSecondLine() {
        when(lcsMatrixBuilder.buildLcsMatrix("a\nb", "a\n"))
            .thenReturn(new int[][] {
                    {0, 0, 0},
                    {0, 1, 1},
                    {0, 1, 2},
                    {0, 1, 2}
            });

        List<DiffModel> diffModels = evaluator.lcs("a\nb", "a\n");

        assertEquals(2, diffModels.size());
        assertModelAtPosition(diffModels.get(0), DiffType.MATCHED, "a\n");
        assertModelAtPosition(diffModels.get(1), DiffType.REMOVED, "b");
    }


    // https://en.wikipedia.org/w/index.php?title=Longest_common_subsequence_problem&section=8#Traceback_approach
    @Test
    public void itShouldReturnCorrectlyForWikiUseCase() {
        when(lcsMatrixBuilder.buildLcsMatrix("GAC", "AGCAT"))
            .thenReturn(new int[][] {
                    {0, 0, 0, 0, 0, 0},
                    {0, 0, 1, 1, 1, 1},
                    {0, 1, 1, 1, 2, 2},
                    {0, 1, 1, 2, 2, 2}
            });

        List<DiffModel> diffModels = evaluator.lcs("GAC", "AGCAT");

        assertEquals(6, diffModels.size());

        assertModelAtPosition(diffModels.get(0), DiffType.ADDED, "A");
        assertModelAtPosition(diffModels.get(1), DiffType.MATCHED, "G");
        assertModelAtPosition(diffModels.get(2), DiffType.ADDED, "C");
        assertModelAtPosition(diffModels.get(3), DiffType.MATCHED, "A");
        assertModelAtPosition(diffModels.get(4), DiffType.ADDED, "T");
        assertModelAtPosition(diffModels.get(5), DiffType.REMOVED, "C");
    }

    private void assertModelAtPosition(DiffModel model, DiffType type, String s) {
        assertEquals(type, model.getType());
        assertEquals(s, model.getStringBuilder().toString());
    }
}

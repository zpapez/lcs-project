package cz.zpapez.lcs.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cz.zpapez.lcs.io.FileProcessor;
import cz.zpapez.lcs.model.DiffModel;

@ExtendWith(MockitoExtension.class)
public class LcsServiceTest {

    @Mock
    private FileProcessor fileProcessor;

    @Mock
    private LcsEvaluator lcsEvaluator;

    @InjectMocks
    private LcsService service;

    @AfterEach
    public void afterEach() {
        verifyNoMoreInteractions(fileProcessor);
        verifyNoMoreInteractions(lcsEvaluator);
    }

    @Test
    public void itShouldReturnDiffsForTwoStrings() throws IOException {

        InputStream inputStream1 = mock(InputStream.class);
        InputStream inputStream2 = mock(InputStream.class);

        when(fileProcessor.readString(inputStream1)).thenReturn("s1");
        when(fileProcessor.readString(inputStream2)).thenReturn("s2");

        List<DiffModel> diffsMock = List.of();
        when(lcsEvaluator.lcs("s1", "s2")).thenReturn(diffsMock);

        List<DiffModel> diffs = service.diff(inputStream1 , inputStream2);

        assertEquals(diffsMock, diffs);
    }

    @Test
    public void itShouldThrowIOExceptionWhenFileProcessorThrowsIOException() throws IOException {
        InputStream inputStream1 = mock(InputStream.class);
        InputStream inputStream2 = mock(InputStream.class);
        when(fileProcessor.readString(inputStream1)).thenThrow(IOException.class);

        assertThrows(IOException.class, () -> service.diff(inputStream1, inputStream2));
    }
}

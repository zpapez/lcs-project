package cz.zpapez.lcs.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class FileProcessorTest {

    private FileProcessor processor = new FileProcessor();

    @Test
    public void itShouldReadEmptyInput() throws IOException {
        String result = processor.readString(new ByteArrayInputStream("".getBytes()));
        assertEquals("", result);
    }

    @Test
    public void itShouldReadMultiLineInput() throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream("line1\nline2\nline3".getBytes());
        String result = processor.readString(input);
        assertEquals("line1\nline2\nline3", result);
    }
}

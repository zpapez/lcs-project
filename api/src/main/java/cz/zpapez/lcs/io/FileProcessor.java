package cz.zpapez.lcs.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

@Component
public class FileProcessor {

    public String readString(InputStream input) throws IOException {

        StringBuilder s = new StringBuilder();

        try (input) {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(input));

            int c = 0;
            while((c = buffer.read()) != -1) {
                s.append((char) c);
            }
        }

        return s.toString();
    }
}

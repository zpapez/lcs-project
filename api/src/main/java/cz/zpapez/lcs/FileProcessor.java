package cz.zpapez.lcs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class FileProcessor {

    public List<Character> readChars(InputStream input) {

        List<Character> chars = new ArrayList<>();

        try (input) {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(input));

            int c = 0;
            while((c = buffer.read()) != -1) {
                chars.add((char) c);
            }
        } catch (IOException e) {
            // TODO: handle exception
        }

        return chars;
    }

    public String readString(InputStream input) {

        StringBuilder s = new StringBuilder();

        try (input) {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(input));

            int c = 0;
            while((c = buffer.read()) != -1) {
                s.append((char) c);
            }
        } catch (IOException e) {
            // TODO: handle exception
        }

        return s.toString();
    }
}

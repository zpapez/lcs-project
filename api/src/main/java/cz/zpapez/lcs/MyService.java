package cz.zpapez.lcs;

import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class MyService {

    private final FileProcessor fileProcessor;
    private final LcsEvaluator lcsEvaluator;

    public void fetchData() {
        log.info("service running");
    }

    public String diff(InputStream inputStream1, InputStream inputStream2) {

//        List<Character> chars1 = fileProcessor.readChars(inputStream1);
//        List<Character> chars2 = fileProcessor.readChars(inputStream2);

        String s1 = fileProcessor.readString(inputStream1);
        String s2 = fileProcessor.readString(inputStream2);

        return lcsEvaluator.lcs(s1, s2);
    }

}

package cz.zpapez.lcs;

import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import cz.zpapez.lcs.model.DiffModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyService {

    private final FileProcessor fileProcessor;
    private final LcsEvaluator lcsEvaluator;

    public void fetchData() {
        log.info("service running");
    }

    public List<DiffModel> diff(InputStream inputStream1, InputStream inputStream2) {

        String s1 = fileProcessor.readString(inputStream1);
        String s2 = fileProcessor.readString(inputStream2);

        List<DiffModel> listDiffs = lcsEvaluator.lcs(s1, s2);

        return listDiffs;
    }

}

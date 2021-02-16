package cz.zpapez.lcs.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import cz.zpapez.lcs.io.FileProcessor;
import cz.zpapez.lcs.model.DiffModel;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LcsService {

    private final FileProcessor fileProcessor;
    private final LcsEvaluator lcsEvaluator;

    public List<DiffModel> diff(InputStream inputStream1, InputStream inputStream2) throws IOException {

        String s1 = fileProcessor.readString(inputStream1);
        String s2 = fileProcessor.readString(inputStream2);

        List<DiffModel> listDiffs = lcsEvaluator.lcs(s1, s2);

        return listDiffs;
    }

}

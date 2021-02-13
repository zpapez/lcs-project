package cz.zpapez.lcs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import cz.zpapez.lcs.model.DiffModel;
import cz.zpapez.lcs.model.DiffType;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LcsEvaluator {

    private final LcsMatrixBuilder lcsMatrixBuilder;

    public List<DiffModel> lcs(String a, String b) {

        List<DiffModel> listDiffs = new ArrayList<>();
        int[][] lengths = lcsMatrixBuilder.buildLcsMatrix(a, b);


        DiffType lastType = null;
        DiffModel diffModel = null;

        // read the substring out from the matrix
//        StringBuffer sb = new StringBuffer();
        int x = a.length(), y = b.length();
        while (x > 0 && y > 0) {
            if (lengths[x][y] == lengths[x-1][y]) {

                if (lastType != DiffType.REMOVED) {
                    saveDiffModel(listDiffs, diffModel);
                    lastType = DiffType.REMOVED;
                    diffModel = new DiffModel(DiffType.REMOVED);
                }
                diffModel.getStringBuilder().append(a.charAt(x-1));

//                sb.append(a.charAt(x-1));
//                sb.append("-");
                x--;
            } else if (lengths[x][y] == lengths[x][y-1]) {

                if (lastType != DiffType.ADDED) {
                    saveDiffModel(listDiffs, diffModel);
                    lastType = DiffType.ADDED;
                    diffModel = new DiffModel(DiffType.ADDED);
                }
                diffModel.getStringBuilder().append(b.charAt(y-1));

//                sb.append(b.charAt(y-1));
//                sb.append("+");
                y--;
            } else {
                assert a.charAt(x-1) == b.charAt(y-1);

                if (lastType != DiffType.MATCHED) {
                    saveDiffModel(listDiffs, diffModel);
                    lastType = DiffType.MATCHED;
                    diffModel = new DiffModel(DiffType.MATCHED);
                }
                diffModel.getStringBuilder().append(a.charAt(x-1));

//                sb.append(a.charAt(x-1));
                x--;
                y--;
            }
        }

        saveDiffModel(listDiffs, diffModel);
        appendRemaining(listDiffs, a, b, x, y);

//        return sb.reverse().toString();
        Collections.reverse(listDiffs);
        return listDiffs;
    }

    private void saveDiffModel(List<DiffModel> listDiffs, DiffModel diffModel) {
        if (diffModel != null) {
            diffModel.getStringBuilder().reverse();
            listDiffs.add(diffModel);
        }

    }

    private void appendRemaining(List<DiffModel> listDiffs, String a, String b, int x, int y) {

        if (x > 0) {
            DiffModel diffModel = new DiffModel(DiffType.REMOVED);
            while (x > 0) {
                diffModel.getStringBuilder().append(a.charAt(x-1));
//                sb.append(a.charAt(x-1));
//                sb.append("-");
                x--;
            }
            saveDiffModel(listDiffs, diffModel);
        }

        if (x > 0) {
            DiffModel diffModel = new DiffModel(DiffType.ADDED);
            while (y > 0) {
                diffModel.getStringBuilder().append(b.charAt(y-1));
//                sb.append(b.charAt(y-1));
//                sb.append("+");
                y--;

            }
            saveDiffModel(listDiffs, diffModel);
        }
    }

}

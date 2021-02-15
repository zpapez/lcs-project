package cz.zpapez.lcs.service;

import org.springframework.stereotype.Component;

@Component
public class LcsMatrixBuilder {

    public int[][] buildLcsMatrix(String a, String b) {
        int[][] lengths = new int[a.length()+1][b.length()+1];

        // row 0 and column 0 are initialized to 0 already

        for (int i = 0; i < a.length(); i++)
            for (int j = 0; j < b.length(); j++)
                if (a.charAt(i) == b.charAt(j))
                    lengths[i+1][j+1] = lengths[i][j] + 1;
                else
                    lengths[i+1][j+1] =
                        Math.max(lengths[i+1][j], lengths[i][j+1]);
        return lengths;
    }
}

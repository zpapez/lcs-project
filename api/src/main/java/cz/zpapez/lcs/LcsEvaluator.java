package cz.zpapez.lcs;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class LcsEvaluator {

    private final LcsMatrixBuilder lcsMatrixBuilder;

    public String lcs(String a, String b) {
        int[][] lengths = lcsMatrixBuilder.buildLcsMatrix(a, b);

        // read the substring out from the matrix
        StringBuffer sb = new StringBuffer();
        int x = a.length(), y = b.length();
        while (x > 0 && y > 0) {
            if (lengths[x][y] == lengths[x-1][y]) {
                sb.append(a.charAt(x-1));
                sb.append("-");
                x--;
            } else if (lengths[x][y] == lengths[x][y-1]) {
                sb.append(b.charAt(y-1));
                sb.append("+");
                y--;
            } else {
                assert a.charAt(x-1) == b.charAt(y-1);
                sb.append(a.charAt(x-1));
                x--;
                y--;
            }
        }

        appendRemaining(sb, a, b, x, y);

        return sb.reverse().toString();
    }

    private void appendRemaining(StringBuffer sb, String a, String b, int x, int y) {
        while (x > 0) {
            sb.append(a.charAt(x-1));
            sb.append("-");
            x--;
        }
        while (y > 0) {
            sb.append(b.charAt(y-1));
            sb.append("+");
            y--;
        }
    }

}

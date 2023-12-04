package series.dp.subsequenceMatch;

import java.util.Arrays;

public class RegularExpression {

    static public boolean isMatch_mem(String s, String p) {
        int[][] dp = new int[s.length()][p.length()];
        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(dp[i], -1);
        }
        isMatch(s, p, s.length() - 1, p.length() - 1, dp);
        return dp[s.length() - 1][p.length() - 1] == 1;
    }

    static public boolean isMatch(String s, String p, int i, int j, int[][] dp) {
        if (i < 0 && j < 0) {
            return true;
        } else if (j < 0 && i >= 0) {
            return false;
        } else if (i < 0 && j >= 0) {
            for (int k = j; k >= 0; k--) {
                if (!(p.charAt(k) == '*' || p.charAt(k + 1) == '*')) {
                    return false;
                }
            }
            return true;
        } else if (dp[i][j] != -1) {
            return dp[i][j] == 1 ? true : false;
        }

        if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') {
            dp[i][j] = isMatch(s, p, i - 1, j - 1, dp) ? 1 : 0;
        } else if (p.charAt(j) == '*') {
            boolean check = j - 1 >= 0 && p.charAt(j - 1) == s.charAt(i) || p.charAt(j - 1) == '.';
            dp[i][j] = (isMatch(s, p, i, j - 2, dp) || (check && isMatch(s, p, i - 1, j, dp))) ? 1 : 0;
        } else {
            dp[i][j] = 0;
        }
        return dp[i][j] == 1;
    }

    static boolean isAllStars(String pattern, int i) {
        for (int j = 1; j <= i; j++) {
            if (!(pattern.charAt(j - 1) == '*' || (j < i && pattern.charAt(j) == '*')))
                return false;
        }
        return true;
    }

    static public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean dp[][] = new boolean[n + 1][m + 1];

        dp[0][0] = true;
        for (int k = 1; k <= n; k++) {
            dp[k][0] = false;
        }

        for (int i = 1; i <= m; i++) {
            dp[0][i] = isAllStars(p, i);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    boolean check = j > 1 && (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.');
                    dp[i][j] = dp[i][j - 2] || (check && dp[i - 1][j]);
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a"));
    }
}

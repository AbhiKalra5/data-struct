package series.dp.subsequenceMatch;

public class WildcardMatching {

    static boolean isAllStars_B(String pattern, int i) {
        for (int j = 0; j <= i; j++) {
            if (pattern.charAt(j) != '*')
                return false;
        }
        return true;
    }

    static public boolean patternMatch_mem(String pattern, String checkString, int i, int j, int[][] dp) {
        if (i < 0 && j < 0) {
            return true;
        } else if (i < 0 && j >= 0) {
            return false;
        } else if (i >= 0 && j < 0) {
            boolean res = isAllStars_B(pattern, i);
            dp[i][j] = res ? 1 : 0;
        } else if (dp[i][j] != -1) {
            return dp[i][j] == 1;
        }

        if (pattern.charAt(i) == checkString.charAt(j) || pattern.charAt(j) == '?') {
            dp[i][j] = patternMatch_mem(pattern, checkString, i - 1, j - 1, dp) ? 1 : 0;
        } else if (pattern.charAt(j) == '*') {
            boolean take = patternMatch_mem(pattern, checkString, i, j - 1, dp); // star taken, string one character ignored
            boolean notTake = patternMatch_mem(pattern, checkString, i - 1, j, dp); // star not taken, string full
            dp[i][j] = take || notTake ? 1 : 0;
        } else {
            dp[i][j] = 0;
        }

        return dp[i][j] == 1;
    }



    static boolean isAllStars(String pattern, int i) {
        for (int j = 1; j <= i; j++) {
            if (pattern.charAt(j - 1) != '*')
                return false;
        }
        return true;
    }

    public boolean patternMatch_mem(String pattern, String checkString) {
        int n = pattern.length();
        int m = checkString.length();
        boolean dp[][] = new boolean[n + 1][m + 1];

        dp[0][0] = true;
        for (int j = 1; j <= m; j++) {
            dp[0][j] = false;
        }

        for (int i = 1; i <= n; i++) {
            dp[i][0] = isAllStars(pattern, i);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (pattern.charAt(i-1) == checkString.charAt(j-1) || pattern.charAt(i-1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pattern.charAt(i-1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[n][m];
    }

    public static boolean patternMatch_space(String pattern, String checkString) {
        if (pattern.isBlank() && checkString.isBlank()) {
            return true;
        }
        int n = pattern.length();
        int m = checkString.length();
        boolean prev[] = new boolean[m + 1];
        boolean curr[] = new boolean[m + 1];
        prev[0] = true;
        for (int j = 1; j <= m; j++) {
            prev[j] = false;
        }

        for (int i = 1; i <= n; i++) {
            curr[0] = isAllStars(pattern, i);
            for (int j = 1; j <= m; j++) {
                if (pattern.charAt(i - 1) == checkString.charAt(j - 1) || pattern.charAt(i - 1) == '?') {
                    curr[j] = prev[j - 1];
                } else if (pattern.charAt(i - 1) == '*') {
                    curr[j] = prev[j] || curr[j - 1];
                } else {
                    curr[j] = false;
                }
            }
            prev = curr.clone();
        }
        return curr[m];
    }

}

package series.dp.subsequenceMatch;

public class EqualizeString {
    public int minimumInsertionsDeletionsReplaceToEqualizeString_mem(String s1, String s2, int i, int j, int[][] dp) {
        if (i < 0) {
            return j + 1; // add operations
        } else if (j < 0) {
            return i + 1; // delete all string left
        } else if (dp[i][j] != -1)
            return dp[i][j];

        if (s1.charAt(i) == s2.charAt(j)) {
            return dp[i][j] = 0 + minimumInsertionsDeletionsReplaceToEqualizeString_mem(s1, s2, i - 1, j - 1, dp); // reduce first and match j to next character
        } else {
            // 3 ways
            int replace = 1 + minimumInsertionsDeletionsReplaceToEqualizeString_mem(s1, s2, i - 1, j - 1, dp);
            int delete = 1 + minimumInsertionsDeletionsReplaceToEqualizeString_mem(s1, s2, i - 1, j, dp);
            int add = 1 + minimumInsertionsDeletionsReplaceToEqualizeString_mem(s1, s2, i, j - 1, dp);
            return dp[i][j] = Math.min(replace, Math.min(delete, add));
        }
    }

    public int minimumInsertionsDeletionsReplaceToEqualizeString_tab(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int dp[][] = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int replace = 1 + dp[i - 1][j - 1];
                    int delete = 1 + dp[i - 1][j];
                    int add = 1 + dp[i][j - 1];
                    dp[i][j] = Math.min(replace, Math.min(delete, add));
                }
            }
        }
        return dp[n][m];
    }

    public int minimumInsertionsDeletionsReplaceToEqualizeString_space(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        // Create two arrays to store the previous and current rows of minimum edit distances
        int[] prev = new int[m + 1];
        int[] cur = new int[m + 1];

        // Initialize the first row with their respective indices
        for (int j = 0; j <= m; j++) {
            prev[j] = j;
        }

        // Fill the cur array using a bottom-up approach
        for (int i = 1; i <= n; i++) {
            cur[0] = i;
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    cur[j] = prev[j - 1];
                } else {
                    int replace = 1 + prev[j - 1];
                    int delete = 1 + prev[j];
                    int add = 1 + cur[j - 1];
                    cur[j] = Math.min(replace, Math.min(delete, add));
                }
            }

            prev = cur.clone();
        }

        return cur[m];
    }
}

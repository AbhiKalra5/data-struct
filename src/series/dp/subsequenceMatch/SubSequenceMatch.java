package series.dp.subsequenceMatch;

import java.util.List;

public class SubSequenceMatch {

    public int longestCommonSubSet_mem(String s1, String s2, int ind1, int ind2, int[][] dp) {
        if (ind1 < 0 || ind2 < 0) {
            return 0;
        } else if (dp[ind1][ind2] != -1) {
            return dp[ind1][ind2];
        }

        if (s1.charAt(ind1) == s2.charAt(ind2)) {
            return dp[ind1][ind2] = 1 + longestCommonSubSet_mem(s1, s2, ind1 - 1, ind2 - 1, dp);
        }
        return dp[ind1][ind2] = Math.max(longestCommonSubSet_mem(s1, s2, ind1, ind2 - 1, dp), longestCommonSubSet_mem(s1, s2, ind1 - 1, ind2, dp));
    }

    // shift array one left
    public int[][] longestCommonSubSet_tab(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int dp[][] = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= m; i++) {
            dp[0][i] = 0;
        }
        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {
                if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1)) {
                    dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                } else {
                    dp[ind1][ind2] = Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
                }
            }
        }
        return dp;
    }

    public int longestCommonSubSet_space(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int prev[] = new int[m + 1];
        int curr[] = new int[m + 1];
        for (int ind1 = 1; ind1 <= n; ind1++) {
            for (int ind2 = 1; ind2 <= m; ind2++) {
                if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1)) {
                    curr[ind2] = 1 + prev[ind2 - 1];
                } else {
                    curr[ind2] = Math.max(prev[ind2], curr[ind2 - 1]);
                }
            }
            prev = curr.clone();
        }
        return prev[m];
    }

}

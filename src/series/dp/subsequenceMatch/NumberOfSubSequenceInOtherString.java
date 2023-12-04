package series.dp.subsequenceMatch;

public class NumberOfSubSequenceInOtherString {
    static int prime = (int) (Math.pow(10, 9) + 7);

    public int countSecondStringInFirst_mem(String s1, String s2, int i, int j, int[][] dp) {
        if (j < 0) { // second string exhausted success
            return 1;
        } else if (i < 0) { // first string over and second left out : fail
            return 0;
        } else if (dp[i][j] != -1) {
            return dp[i][j];
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            int leave = countSecondStringInFirst_mem(s1, s2, i - 1, j, dp); // reduce first and match j to next character
            int take = countSecondStringInFirst_mem(s1, s2, i - 1, j - 1, dp); // reduce remaining matchable string
            return dp[i][j] = (take + leave) % prime;
        } else {
            return dp[i][j] = countSecondStringInFirst_mem(s1, s2, i - 1, j, dp);
        }
    }

    public static int countSecondStringInFirst_tab(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int dp[][] = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    int leave = dp[i - 1][j];
                    int take = dp[i - 1][j - 1];
                    dp[i][j] = (leave + take) % prime;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][m];
    }

    public static int countSecondStringInFirst_space(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int prev[] = new int[m + 1];
        int curr[] = new int[m + 1];

        prev[0] = 1;
        curr[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                System.out.print(prev[0]);
                System.out.print(prev[j]);
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    curr[j] = (prev[j] + prev[j - 1]) % prime;
                } else {
                    curr[j] = prev[j];
                }
            }
            System.out.println("");
            prev = curr.clone();

        }
        return prev[m];
    }

    int prime_check = (int) (Math.pow(10, 9) + 7);

    public int countSecondStringInFirst(String s1, String s2, int i, int j, int[][] dp) {
        if (j < 0) {
            return 1;
        } else if (i < 0) {
            return 0;
        } else if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            int take = countSecondStringInFirst(s1, s2, i - 1, j - 1, dp);
            int notTake = countSecondStringInFirst(s1, s2, i - 1, j, dp);
            dp[i][j] = (take + notTake) % prime_check;
        } else {
            dp[i][j] = countSecondStringInFirst(s1, s2, i - 1, j, dp);
        }
        return dp[i][j];
    }

    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    int take = dp[i - 1][j - 1];
                    int notTake = dp[i - 1][j];
                    dp[i][j] = (take + notTake) % prime_check;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        countSecondStringInFirst_space("nwnk", "n");
    }
}

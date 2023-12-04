package series.dp.partition;

public class EvaluateBooleanExpressionToTrue {
    final int MOD = 1000000007;

    int evaluateExpWays(String exp) {
        int n = exp.length();
        Long[][][] dp = new Long[n][n][2]; // dp[i][j][k] stores the number of ways to evaluate the subexpression from index i to j with the result k (0 or 1).
        return (int) finNumberOfWaysToExpressionMakeTrue_mem(0, n - 1, 1, exp, dp);
    }

    public long finNumberOfWaysToExpressionMakeTrue_mem(int i, int j, int isTrue, String arr, Long[][][] dp) {
        char operator = arr.charAt(i);
        if (i > j) {
            return 0;
        } else if (i == j) {
            if (isTrue == 1) {
                return operator == 'T' ? 1 : 0;
            } else {
                return operator == 'F' ? 1 : 0;
            }
        }
        long ways = 0;
        for (int ind = i + 1; ind < j; ind += 2) {
            long lT = finNumberOfWaysToExpressionMakeTrue_mem(i, ind - 1, 1, arr, dp);
            long lF = finNumberOfWaysToExpressionMakeTrue_mem(i, ind - 1, 0, arr, dp);
            long rT = finNumberOfWaysToExpressionMakeTrue_mem(ind + 1, j, 1, arr, dp);
            long rF = finNumberOfWaysToExpressionMakeTrue_mem(ind + 1, j, 0, arr, dp);
            if (operator == '&') {
                if (isTrue == 1) {
                    ways = (ways + (lT * rT) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lF * rF) % MOD) % MOD;
                }
            } else if (operator == '|') {
                if (isTrue == 1) {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lT * rT) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rF) % MOD) % MOD;
                }
            } else {
                if (isTrue == 1) {
                    ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD) % MOD;
                } else {
                    ways = (ways + (lF * rF) % MOD + (lT * rT) % MOD) % MOD;
                }
            }
        }
        dp[i][j][isTrue] = ways;
        return ways;
    }

    int finNumberOfWaysToExpressionMakeTrue_tab(String exp) {
        int n = exp.length();
        long[][][] dp = new long[n][n][2];

        // Initializing the dp array
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= n - 1; j++) {
                if (i > j)
                    continue;
                for (int isTrue = 0; isTrue <= 1; isTrue++) {
                    // Base case 1:
                    if (i == j) {
                        if (isTrue == 1)
                            dp[i][j][isTrue] = exp.charAt(i) == 'T' ? 1 : 0;
                        else
                            dp[i][j][isTrue] = exp.charAt(i) == 'F' ? 1 : 0;
                        continue;
                    }

                    // Recurrence logic:
                    long ways = 0;
                    for (int ind = i + 1; ind <= j - 1; ind += 2) {
                        long lT = dp[i][ind - 1][1];
                        long lF = dp[i][ind - 1][0];
                        long rT = dp[ind + 1][j][1];
                        long rF = dp[ind + 1][j][0];

                        char operator = exp.charAt(ind);
                        if (operator == '&') {
                            if (isTrue == 1)
                                ways = (ways + (lT * rT) % MOD) % MOD;
                            else
                                ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lF * rF) % MOD) % MOD;
                        } else if (operator == '|') {
                            if (isTrue == 1)
                                ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD + (lT * rT) % MOD) % MOD;
                            else
                                ways = (ways + (lF * rF) % MOD) % MOD;
                        } else {
                            if (isTrue == 1)
                                ways = (ways + (lF * rT) % MOD + (lT * rF) % MOD) % MOD;
                            else
                                ways = (ways + (lF * rF) % MOD + (lT * rT) % MOD) % MOD;
                        }
                    }
                    dp[i][j][isTrue] = ways;
                }
            }
        }
        return (int) dp[0][n - 1][1];
    }

}

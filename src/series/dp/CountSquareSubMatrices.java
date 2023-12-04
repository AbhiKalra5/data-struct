package series.dp;

public class CountSquareSubMatrices {
    public int countSquareSubMatrices(int[][] a, int n, int m) {
        int dp[][] = new int[n][m];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = a[i][j];
                } else if (a[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(a[i - 1][j - 1], Math.min(a[i - 1][j], a[i][j - 1])) + 1;
                }
                sum = sum + dp[i][j];
            }
        }
        return sum;
    }
}

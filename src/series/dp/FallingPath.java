package series.dp;

import java.util.Arrays;

public class FallingPath {

    int getMaxPathSum_mem(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int dp[][] = new int[n][m];
        for (int row[] : dp)
            Arrays.fill(row, -1);

        int maxi = Integer.MIN_VALUE;

        // For each starting column, find the maximum path sum and update maxi
        for (int j = 0; j < m; j++) {
            int ans = getMaxUtil(n - 1, j, m, matrix, dp);
            maxi = Math.max(maxi, ans);
        }

        return maxi;
    }

    private int getMaxUtil(int i, int j, int m, int[][] matrix, int[][] dp) {
        if (j < 0 || j >= m) {
            return (int) Math.pow(-10, 9);
        } else if (dp[i][j] != -1) {
            return dp[i][j];
        } else if (i == 0) {
            return dp[i][j] = matrix[i][j];
        }

        int top = matrix[i][j] + getMaxUtil(i - 1, j, m, matrix, dp);
        int leftUp = matrix[i][j] + getMaxUtil(i - 1, j - 1, m, matrix, dp);
        int rightUp = matrix[i][j] + getMaxUtil(i - 1, j + 1, m, matrix, dp);
        return dp[i][j] = Math.max(top, Math.max(leftUp, rightUp));
    }


    int getMaxPathSum_tab(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        for (int j = 0; j < m; j++) {
            dp[0][j] = matrix[0][j];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int top = matrix[i][j] + dp[i - 1][j];
                int leftUp = matrix[i][j];
                if (j > 1) {
                    leftUp += dp[i - 1][j - 1];
                } else {
                    leftUp += (int) Math.pow(-10, 9);
                }
                int rightUp = matrix[i][j];
                if (j + 1 < m) {
                    rightUp += dp[i - 1][j + 1];
                } else {
                    rightUp += (int) Math.pow(-10, 9);
                }
                dp[i][j] = Math.max(top, Math.max(leftUp, rightUp));
            }

        }
        int maxi = Integer.MIN_VALUE;
        for (int j = 0; j < m; j++) {
            maxi = Math.max(maxi, dp[n - 1][j]);
        }

        return maxi;

    }

    int getMaxPathSum_space(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[] res = new int[m];
        int[] dp = new int[m];
        for (int j = 0; j < m; j++) {
            dp[j] = matrix[0][j];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int top = matrix[i][j] + dp[j];
                int leftUp = matrix[i][j];
                if (j > 1) {
                    leftUp += dp[j - 1];
                } else {
                    leftUp += (int) Math.pow(-10, 9);
                }
                int rightUp = matrix[i][j];
                if (j + 1 < m) {
                    rightUp += dp[j + 1];
                } else {
                    rightUp += (int) Math.pow(-10, 9);
                }
                res[j] = Math.max(top, Math.max(leftUp, rightUp));
            }
            dp = res;

        }

        int maxi = Integer.MIN_VALUE;
        for (int j = 0; j < m; j++) {
            maxi = Math.max(maxi, res[j]);
        }

        return maxi;

    }

}

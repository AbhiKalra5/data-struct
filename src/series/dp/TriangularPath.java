package series.dp;

import java.util.List;

public class TriangularPath {

    int minTriangularPath_mem(int i, int j, int[][] triangle, int n, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        } else if (i == n - 1) {
            return triangle[i][j];
        }

        int down = triangle[i][j] + minTriangularPath_mem(i + 1, j, triangle, n, dp);
        int diagonal = triangle[i][j] + minTriangularPath_mem(i + 1, j + 1, triangle, n, dp);
        return dp[i][j] = Math.min(down, diagonal);
    }

    int minTriangularPath_tab(int[][] triangle) {
        int n = triangle.length;
        int[][] dp = new int[n][n];
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = triangle[n - 1][j];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int down = triangle[i][j] + dp[i + 1][j];
                int diagonal = triangle[i][j] + dp[i + 1][j + 1];
                dp[i][j] = Math.min(down, diagonal);
            }
        }
        return dp[0][0];
    }

    int minTriangularPath_space(int[][] triangle) {
        int n = triangle.length;
        int[] dp = new int[n];
        for (int j = 0; j < n; j++) {
            dp[j] = triangle[n - 1][j];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int down = triangle[i][j] + dp[j];
                int diagonal = triangle[i][j] + dp[j + 1];
                dp[j] = Math.min(down, diagonal);
            }
        }
        return dp[0];
    }

    int minTriangularPath_space(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        int[] curr = new int[n];
        for (int j = 0; j < n; j++) {
            dp[j] = triangle.get(n - 1).get(j);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int down = triangle.get(i).get(j) + dp[j];
                int diagonal = triangle.get(i).get(j) + dp[j + 1];
                curr[j] = Math.min(down, diagonal);
            }
            dp=curr.clone();
        }
        return dp[0];
    }
}

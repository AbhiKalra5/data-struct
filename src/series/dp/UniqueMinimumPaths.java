package series.dp;

public class UniqueMinimumPaths {
    public int uniquePaths_mem(int i, int j, int[][] mem) {
        if (i == 0 && j == 0) {
            return 1;
        } else if (i < 0 || j < 0) {
            return 0;
        } else if (mem[i][j] != -1) {
            return mem[i][j];
        }

        int top = uniquePaths_mem(i - 1, j, mem);
        int left = uniquePaths_mem(i, j - 1, mem);
        return mem[i][j] = top + left;
    }

    public int uniquePaths_tab(int m, int n) {
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; i < m; j++) {
                if (i == 0 && j == 0) {
                    dp[0][0] = 1;
                } else {
                    int top = i - 1 < 0 ? 0 : dp[i - 1][j];
                    int left = j - 1 < 0 ? 0 : dp[i][j - 1];
                    dp[i][j] = top + left;
                }
            }
        }
        return dp[n - 1][m - 1];
    }

    public int uniquePaths_space(int m, int n) {
        int[] dp = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    dp[0] = 1;
                } else  {
                    int top = i - 1 < 0 ? 0 : dp[j];
                    int left = j - 1 < 0 ? 0 : dp[j - 1];
                    dp[j] = top + left;
                }
            }
        }
        return dp[m - 1];
    }

    public static int uniquePathsWithObstacle_space(int[][] obstacleGrid, int n, int m) {
        int[] dp = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else if (i == 0 && j == 0) {
                    dp[0] = 1;
                } else if (j > 0) {
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }
        return dp[m - 1];
    }

    public int minimumPaths_mem(int[][] a, int i, int j, int[][] mem) {
        if (i == 0 && j == 0) {
            return a[0][0];
        } else if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        } else if (mem[i][j] != -1) {
            return mem[i][j];
        }

        int top = a[i][j] + minimumPaths_mem(a, i - 1, j, mem);
        int left = a[i][j] + minimumPaths_mem(a, i, j - 1, mem);
        return mem[i][j] = Math.min(top, left);
    }

    public int minimumPaths_tab(int[][] grid, int n, int m) {
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    dp[0][0] = grid[0][0];
                } else {
                    int top = grid[i][j];
                    if (i > 0) {
                        top += dp[i - 1][j];
                    } else {
                        top += Integer.MAX_VALUE;
                    }
                    int left = grid[i][j];
                    if (j > 0) {
                        left += dp[i][j - 1];
                    } else {
                        left += Integer.MAX_VALUE;
                    }
                    dp[i][j] = Math.min(top, left);
                }
            }
        }
        return dp[n - 1][m - 1];
    }

    public static int minimumPaths_space(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] dp = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    dp[0] = grid[0][0];
                } else {
                    int top = grid[i][j];
                    if (i > 0) {
                        top += dp[j];
                    } else {
                        top += Math.pow(10, 9);
                    }
                    int left = grid[i][j];
                    if (j > 0) {
                        left += dp[j - 1];
                    } else {
                        left += Math.pow(10, 9);
                    }
                    dp[j] = Math.min(top, left);
                }
            }
        }
        return dp[m - 1];
    }
}

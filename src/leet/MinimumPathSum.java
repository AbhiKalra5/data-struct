package leet;

//64. Minimum Path Sum

public class MinimumPathSum {
    public static void main(String[] args) {
        int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        //  int[][] obstacleGrid = {{0, 1, 0}};
        minPath();
        int m = grid.length;
        int n = grid[0].length;

        int res[][] = new int[m + 1][n + 1];

        for (int i = 0; i < n - 1; i++) {
            res[m][i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m - 1; i++) {
            res[i][n] = Integer.MAX_VALUE;
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                res[i][j] = grid[i][j] + Math.min(res[i + 1][j], res[i][j + 1]);
            }
        }
        System.out.print(res[0][0]);
    }

    public static int minPath() {
        int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        //  int[][] obstacleGrid = {{0, 1, 0}};

        int m = grid.length;
        int n = grid[0].length;
        int res[] = new int[n];
        res[n - 1] = grid[m - 1][n - 1];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i != m - 1 && j + 1 >= n) {
                    res[j] = res[j] + grid[i][j];
                } else if (j + 1 < n) {
                    res[j] = grid[i][j] + Math.min(res[j], res[j + 1]);
                }

            }
        }
        return res[0];
    }
}

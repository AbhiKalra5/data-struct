package leet;

//63
public class UniquePathB {

    public static void main(String[] args) {
        int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        uniquePathsWithObstacles(obstacleGrid);
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int res[] = new int[n];
        res[n - 1] = 1;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (obstacleGrid[i][j] == 1) {
                    res[j] = 0;
                } else if (j < n - 1) {
                    res[j] = res[j] + res[j + 1];
                }

            }
        }
        return res[0];
    }
}

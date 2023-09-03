package leet;

// 62
public class UniquePath {

    public static void main(String[] args) {

        System.out.println(uniquePaths_c(4, 3));

    }

    public static int uniquePaths(int m, int n) {
        if (m == 1 && n == 1) {
            System.out.println(1);
        }
        int[][] a = new int[m][n];
        for (int i = 0; i < n - 1; i++) {
            a[m - 1][i] = 1;
        }

        for (int i = 0; i < m - 1; i++) {
            a[i][n - 1] = 1;
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                a[i][j] = a[i][j + 1] + a[i + 1][j];
            }
        }
        return a[0][0];
    }

    public static int uniquePaths_b(int m, int n) {
        int[][] obstacleGrid = new int[m][n];
        int res[] = new int[n];
        res[n - 1] = 1;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (j < n - 1) {
                    res[j] = res[j] + res[j + 1];
                }
            }
        }
        return res[0];
    }

    // nCr -> n= row+co-1
    public static double uniquePaths_c(int m, int n) {
        int nC = n + m - 2;
        int rC = m - 1;
        double res = 1;
        for (int i = 0; i < rC; i++) {
            res = res * (nC - i);
            res = res / (i + 1);
        }

        return (int)Math.round(res);
    }
}

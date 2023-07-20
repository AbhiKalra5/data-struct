package leet;

// 73

// brute: mark col and row which is 0 as -1.Next interation mark all -1 as 0. Complexity n3
// good: keep 2 arrays of n and m length. whenever 0 is found mark respective row column in 2 as 1. next iteration check for location and mark
public class MarkMatrix {
    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        setZeroes(matrix);
    }

    public static void setZeroes(int[][] matrix) {
        int row = matrix.length - 1;
        int col = matrix[0].length - 1;
        int col_extra = 1;
        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= col; j++) {
                if (matrix[i][j] == 0) {
                    if (j == 0) {
                        col_extra = 0;
                    } else {
                        matrix[0][j] = 0;
                    }
                    matrix[i][0] = 0;
                }
            }
        }
        for (int i = row; i > 0; i--) {
            for (int j = col; j > 0; j--) {
                if (matrix[0][j] != 0 && (matrix[0][j] == 0 || matrix[i][0] == 0)) {
                    matrix[i][j] = 0;
                }
            }
        }


        if (matrix[0][0] == 0) {
            for (int i = 1; i <= col; i++) {
                matrix[0][i] = 0;
            }
        }
        if (col_extra == 0) {
            for (int i = 0; i <= row; i++) {
                matrix[i][0] = 0;

            }
        }
        System.out.println();
    }
}

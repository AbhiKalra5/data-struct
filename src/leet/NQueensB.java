package leet;

//52. N-Queens
public class NQueensB {
    static int result;

    public static void main(String[] args) {
        System.out.println(totalNQueens(4));
    }

    public static int totalNQueens(int n) {

        int[] leftDiagonal = new int[2 * n - 1]; // n-1+c-r
        int[] rightDiagonal = new int[2 * n - 1]; //r+c
        int[] column = new int[n];
        char[][] array = new char[n][n];
        result = 0;
        solveNQueens(array, n, 0, leftDiagonal, rightDiagonal, column);
        return result;
    }

    public static void solveNQueens(char[][] array, int length, int row, int[] leftDiagonal, int[] rightDiagonal, int[] column) {
        if (row == length) {
            result = result + 1;
            return;
        }

        for (int col = 0; col < length; col++) {
            int rightValue = row + col;
            int leftValue = length - 1 + col - row;

            if (rightDiagonal[rightValue] == 0 && leftDiagonal[leftValue] == 0 && column[col] == 0) {
                array[row][col] = 'Q';
                leftDiagonal[leftValue] = 1;
                rightDiagonal[rightValue] = 1;
                column[col] = 1;
                solveNQueens(array, length, row + 1, leftDiagonal, rightDiagonal, column);
                leftDiagonal[leftValue] = 0;
                rightDiagonal[rightValue] = 0;
                column[col] = 0;
                array[row][col] = '.';
            }
        }
    }
}

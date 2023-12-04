package leet;


import java.util.ArrayList;
import java.util.List;

//51. N-Queens
public class NQueens {
    public static void main(String[] args) {
        solveNQueens(4).forEach(o -> System.out.println(o));
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[] leftDiagonal = new int[2 * n - 1]; // n-1-c-r
        int[] rightDiagonal = new int[2 * n - 1]; //r+c
        int[] column = new int[n];
        char[][] array = new char[n][n];

        solveNQueens(array, n, result, 0, leftDiagonal, rightDiagonal, column);
        return result;
    }

    public static void solveNQueens(char[][] array, int length, List<List<String>> result, int row, int[] leftDiagonal, int[] rightDiagonal, int[] column) {
        if (row == length) {
            ArrayList<String> resultInner = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                String input = new String();
                for (int j = 0; j < length; j++) {
                    if (array[i][j] == 'Q') {
                        input += 'Q';
                    } else {
                        input += '.';
                    }

                }
                resultInner.add(input);
            }
            result.add(resultInner);
            return;
        }

        for (int col = 0; col < length; col++) {
            int rightValue = row + col;
            int leftValue = (length - 1 )+ (col - row);

            if (rightDiagonal[rightValue] == 0 && leftDiagonal[leftValue] == 0 && column[col] == 0) {
                array[row][col] = 'Q';
                leftDiagonal[leftValue] = 1;
                rightDiagonal[rightValue] = 1;
                column[col] = 1;
                solveNQueens(array, length, result, row + 1, leftDiagonal, rightDiagonal, column);
                leftDiagonal[leftValue] = 0;
                rightDiagonal[rightValue] = 0;
                column[col] = 0;
                array[row][col] = '.';
            }
        }
    }

}

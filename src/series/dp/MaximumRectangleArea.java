package series.dp;

import java.util.Stack;

public class MaximumRectangleArea {

    public static void main(String[] args) {
        char[][] test = new char[4][5];
        char[] a = new char[] {'1', '0', '1', '0', '0'};
        char[] b = new char[] {'1', '0', '1', '1', '1'};
        char[] c = new char[] {'1', '1', '1', '1', '1'};
        char[] d = new char[] {'1', '0', '0', '1', '0'};
        test[0] = a;
        test[1] = b;
        test[2] = c;
        test[3] = d;
        maximumRectangleArea(test, 4, 5);
    }

    public static int maximumRectangleArea(char[][] arr, int n, int m) {
        int[] testArray = new int[m];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                testArray[j] = arr[i][j] == '1' ? testArray[j] + 1 : 0;
            }
            max = Math.max(maximumAreaHistogram(testArray, m), max);
        }
        return max;
    }

    public static int maximumAreaHistogram(int[] arr, int m) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i <= m; i++) {
            while (!stack.empty() && (i == m || arr[i] < arr[stack.peek()])) {
                int height = arr[stack.pop()];
                int width = stack.empty() ? i : i - stack.peek() - 1;
                max = Math.max(max, width * height);
            }
            stack.push(i);
        }
        return max;
    }
}

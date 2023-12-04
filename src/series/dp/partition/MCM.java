package series.dp.partition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MCM {

    //dp=nXn
    // O(n3)- > f(1,n-1) full array
    int calculateSteps_mem(int[] arr, int i, int j, int[][] dp) {
        if (i == j) {
            return 0;// calculate single element
        } else if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int steps = (arr[i - 1] * arr[k] * arr[j]) + calculateSteps_mem(arr, i, k, dp) + calculateSteps_mem(arr, k + 1, j, dp);
            min = Math.min(steps, min);
        }
        return dp[i][j] = min;
    }

    int calculateSteps_tab(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];

        for (int i = n - 1; i > 0; i++) {
            for (int j = i + 1; j < n; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int steps = (arr[i - 1] * arr[k] * arr[j]) + dp[i][k] + dp[k + 1][j];
                    min = Math.min(steps, min);
                }
                dp[i][j] = min;
            }
        }
        return dp[1][n - 1];
    }

}

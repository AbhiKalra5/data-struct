package series.dp.lis;

import java.util.ArrayList;

public class LongestIncreasingSubsequence {
    // dp[n][n + 1]
    int longestIncreasingSubsequence_mem(int arr[], int n, int ind, int prev_index, int[][] dp) {
        if (ind == n) {
            return 0;
        } else if (dp[ind][prev_index + 1] != -1) {
            return dp[ind][prev_index];
        }
        int notTake = longestIncreasingSubsequence_mem(arr, n, ind + 1, prev_index, dp);
        int take = 0;
        if (prev_index == -1 || arr[prev_index] < arr[ind]) {
            take = 1 + longestIncreasingSubsequence_mem(arr, n, ind + 1, ind, dp);
        }
        return dp[ind][prev_index + 1] = Math.max(notTake, take);
    }

    int longestIncreasingSubsequence_tab(int arr[], int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int prev = i - 1; prev >= -1; prev--) {
                int notTake = dp[i + 1][prev];
                int take = 0;
                if (prev == -1 || arr[prev] < arr[i]) {
                    take = 1 + dp[i + 1][i];
                }
                dp[i][prev + 1] = Math.max(notTake, take);
            }
        }
        return dp[0][0];
    }

    int longestIncreasingSubsequence_space(int arr[], int n) {
        int[] next = new int[n + 1];
        int[] curr = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int prev = i - 1; prev >= -1; prev--) {
                int notTake = next[prev];
                int take = 0;
                if (prev == -1 || arr[prev] < arr[i]) {
                    take = 1 + next[i];
                }
                curr[prev + 1] = Math.max(notTake, take);
            }
            next = curr.clone();
        }
        return curr[0];
    }

}

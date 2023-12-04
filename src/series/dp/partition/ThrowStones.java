package series.dp.partition;

import java.util.Arrays;

public class ThrowStones {

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        return stoneGame_util(0, n - 1, stoneValue, dp);
    }

    public int stoneGame_util(int start, int end, int[] stoneValue, int[][] dp) {
        if (start > end) {
            return 0;
        } else if (dp[start][end] != -1) {
            return dp[start][end];
        }
        int rightSum = 0;
        int leftSum = 0;
        int max = 0;
        for (int i = start; i <= end; i++) {
            rightSum += stoneValue[i];
        }
        for (int i = start; i < end; i++) {
            int temp = 0;
            leftSum += stoneValue[i];
            rightSum -= stoneValue[i];
            if (leftSum > rightSum) {
                temp = rightSum + stoneGame_util(i + 1, end, stoneValue, dp);
            } else if (rightSum > leftSum) {
                temp = leftSum + stoneGame_util(start, i, stoneValue, dp);
            } else if (rightSum == leftSum) {
                temp = rightSum + (Math.max(stoneGame_util(start, i, stoneValue, dp), stoneGame_util(i + 1, end, stoneValue, dp)));
            }
            max = Math.max(temp, max);
        }
        return dp[start][end] = max;
    }
}

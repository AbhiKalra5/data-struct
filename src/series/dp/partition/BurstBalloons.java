package series.dp.partition;

import java.util.ArrayList;
import java.util.Arrays;

public class BurstBalloons {

    public int maxCoins(int[] nums) {
        int n = nums.length;
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(nums[i]);
        }
        a.add(0, 1);
        a.add(1);
        int[][] dp = new int[n + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return maxCoins(1, n, a, dp);
    }

    public int maxCoins(int i, int j, ArrayList<Integer> a, int[][] dp) {
        if (i > j) {
            return 0;
        } else if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int max = Integer.MIN_VALUE;
        for (int ind = i; ind <= j; ind++) {
            int res = (a.get(i - 1) * a.get(ind) * a.get(j + 1)) + maxCoins(i, ind - 1, a, dp) + maxCoins(ind + 1, j, a, dp);
            max = Math.max(res, max);
        }
        return dp[i][j] = max;
    }

    int maxCoins_tab(int[] nums) {
        int n = nums.length;
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(nums[i]);
        }
        a.add(0, 1);
        a.add(1);
        int[][] dp = new int[n + 2][n + 2];

        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= n; j++) {
                if (i > j) continue;
                int max = Integer.MIN_VALUE;
                for (int ind = i; ind <= j; ind++) {
                    int res = (a.get(i - 1) * a.get(ind) * a.get(j + 1)) + dp[i][ind - 1] + dp[ind + 1][j];
                    max = Math.max(res, max);
                }
                dp[i][j] = max;
            }
        }
        return dp[1][n];
    }

}

package series.dp;

// TotalNumberOfWaysOfCoinSum
public class CoinsTwo {

    long countWaysToMakeChangeUtil_mem(int[] coins, int ind, int target, long[][] dp) {
        if (ind == 0) {
            if (target % coins[0] == 0) {
                return 1;
            } else {
                return 0;
            }
        } else if (dp[ind][target] != -1) {
            return dp[ind][target];
        }

        long notTake = countWaysToMakeChangeUtil_mem(coins, ind - 1, target, dp);
        long take = 0;
        if (coins[ind] <= target) {
            take = countWaysToMakeChangeUtil_mem(coins, ind, target - coins[ind], dp);
        }
        return dp[ind][target] = take + notTake;
    }

    long countWaysToMakeChangeUtil_tab(int[] coins, int total) {
        int n = coins.length;
        long dp[][] = new long[n][total + 1];

        for (int i = 0; i <= total; i++) {
            dp[0][i] = i % coins[0] == 0 ? 1 : 0;
        }

        for (int ind = 1; ind < n; ind++) {
            for (int target = 0; target <= total; target++) {
                long notTake = dp[ind - 1][target];
                long take = 0;
                if (coins[ind] <= target) {
                    take = dp[ind][target - coins[ind]];
                }
                 dp[ind][target] = take + notTake;
            }
        }
        return dp[n - 1][total];
    }

    long countWaysToMakeChangeUtil_space(int[] coins, int total) {
        int n = coins.length;
        long prev[] = new long[total + 1];

        for (int i = 0; i <= total; i++) {
            prev[i] = i % coins[0] == 0 ? 1 : 0;
        }

        for (int ind = 1; ind < n; ind++) {
            long[] cur = new long[total + 1];
            for (int target = 0; target <= total; target++) {
                long notTake = prev[target];
                long take = 0;
                if (coins[ind] <= target) {
                    take = cur[target - coins[ind]];
                }
                cur[target] = take + notTake;
            }
            prev = cur;
        }
        return prev[total];
    }
}

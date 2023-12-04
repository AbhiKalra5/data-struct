package series.dp;

public class Coins {
    int bigValue = (int) Math.pow(10, 9);

    int minimumElementsUtil_mem(int[] arr, int ind, int target, int[][] dp) {
        if (ind == 0) {
            if (target % arr[0] == 0) {
                return target / arr[0];
            } else {
                return bigValue;
            }
        } else if (dp[ind][target] != -1) {
            return dp[ind][target];
        }

        int notTake = 0 + minimumElementsUtil_mem(arr, ind - 1, target, dp);
        int take = bigValue;
        if (arr[ind] <= target) {
            take = 1 + minimumElementsUtil_mem(arr, ind, target - arr[ind], dp);
        }
        return dp[ind][target] = Math.min(take, notTake);

    }

    int minimumElementsUtil_tab(int[] arr, int target) {
        int n = arr.length;
        int dp[][] = new int[n][target + 1];

        for (int i = 0; i <= target; i++) {
            if (i % arr[0] == 0)
                dp[0][i] = i / arr[0];
            else
                dp[0][i] = bigValue;
        }

        for (int i = 1; i < n; i++) {
            for (int t = 0; t <= target; t++) {
                int notTake = dp[i - 1][t];
                int take = bigValue;
                if (arr[i] <= t) {
                    take = dp[i][t - arr[i]];
                }
                return dp[i][t] = Math.min(take, notTake);
            }
        }
        return dp[n - 1][target];

    }


    int minimumElementsUtil_space(int[] coins, int amount) {
        int bigValue = (int) Math.pow(10, 9);
        int n = coins.length;
        int prev[] = new int[amount + 1];
        int cur[] = new int[amount + 1];
        for (int i = 0; i <= amount; i++) {
            if (i % coins[0] == 0)
                prev[i] = i / coins[0];
            else
                prev[i] = bigValue;
        }

        for (int i = 1; i < n; i++) {
            for (int target = 0; target <= amount; target++) {
                int notTake = 0 + prev[target];
                int take = bigValue;
                if (coins[i] <= target) {
                    take = 1 + cur[target - coins[i]];
                }
                cur[target] = Math.min(take, notTake);
            }
            prev = cur.clone();
        }
        int ans = prev[amount];
        if (ans >= bigValue)
            return -1;
        return ans;

    }
}

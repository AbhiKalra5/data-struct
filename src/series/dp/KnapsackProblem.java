package series.dp;

public class KnapsackProblem {

    public int knapsack_mem(int index, int totalW, int[] wt, int[] val, int[][] dp) {
        if (index == 0) {
            if (wt[0] <= totalW) {
                return val[0];
            }
            return 0;
        }
        if (dp[index][totalW] != -1) {
            return dp[index][totalW];
        }

        int notTake = knapsack_mem(index - 1, totalW, wt, val, dp);
        int take = 0;
        if (wt[index] <= totalW) {
            take = val[index] + knapsack_mem(index - 1, totalW - wt[index], wt, val, dp);
        }
        return dp[index][totalW] = Math.max(take, notTake);
    }

    public int knapsack_tab(int weight, int[] wt, int[] val) {
        int n = wt.length;
        int m = val.length;
        int[][] dp = new int[n][weight + 1];

        for (int i = wt[0]; i <= weight; i++) {
            dp[0][i] = val[0];
        }

        for (int ind = 1; ind < n; ind++) {
            for (int cap = 0; cap <= weight; cap++) {
                int notTake = dp[ind - 1][cap];
                int take = 0;
                if (wt[ind] <= cap) {
                    take = dp[ind - 1][cap - wt[ind]] + val[ind];
                }
                dp[ind][cap] = Math.max(notTake, take);
            }

        }
        return dp[n - 1][weight];
    }

    public int knapsack_space(int W, int[] wt, int[] val, int n) {
        int[] dp = new int[W + 1];
        for (int i = wt[0]; i <= W; i++) {
            dp[i] = val[0];
        }
        for (int ind = 1; ind < n; ind++) {
            for (int cap = W; cap >= 0; cap--) {
                {
                    int notTake = dp[cap];
                    int take = 0;
                    if (wt[ind] <= cap) {
                        take = dp[cap - wt[ind]] + val[ind];
                    }
                    dp[cap] = Math.max(notTake, take);
                }

            }
        }
        return dp[W];
    }
}

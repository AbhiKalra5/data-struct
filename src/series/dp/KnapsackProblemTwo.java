package series.dp;

// pick many times
public class KnapsackProblemTwo {

    public int knapsack_mem(int index, int totalW, int[] wt, int[] val, int[][] dp) {
        if (index == 0) {
            // no need to check min
            return (totalW / wt[0]) * val[0];
        } else if (dp[index][totalW] != -1) {
            return dp[index][totalW];
        }
        int notTake = knapsack_mem(index - 1, totalW, wt, val, dp);
        int take = Integer.MIN_VALUE;
        if (wt[index] <= totalW) {
            take = val[index] + knapsack_mem(index, totalW - wt[index], wt, val, dp);
        }
        return dp[index][totalW] = Math.max(take, notTake);
    }

    public int knapsack_tab(int weight, int[] wt, int[] val) {
        int n = wt.length;
        int[][] dp = new int[n][weight + 1];

        for (int i = 0; i <= weight; i++) {
            dp[0][i] = (i / wt[0]) * val[0];
        }

        for (int ind = 1; ind < n; ind++) {
            for (int cap = 0; cap <= weight; cap++) {
                int notTake = 0 + dp[ind - 1][cap];
                int take = Integer.MIN_VALUE;
                if (wt[ind] <= cap) {
                    take = dp[ind][cap - wt[ind]] + val[ind];
                }
                dp[ind][cap] = Math.max(notTake, take);
            }

        }
        return dp[n - 1][weight];
    }

    public int knapsack_space(int weight, int[] wt, int[] val) {
        int n = wt.length;
        int[] dp = new int[weight + 1];
        for (int i = 0; i <= weight; i++) {
            dp[i] = (i / wt[0]) * val[0];
        }

        for (int ind = 1; ind < n; ind++) {
            for (int cap = 0; cap >= weight; cap--) {
                {
                    int notTake = dp[cap];
                    int take = Integer.MIN_VALUE;
                    if (wt[ind] <= cap) {
                        take = dp[cap - wt[ind]] + val[ind];
                    }
                    dp[cap] = Math.max(notTake, take);
                }

            }
        }
        return dp[weight];
    }
}

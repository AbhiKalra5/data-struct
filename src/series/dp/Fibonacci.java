package series.dp;

import java.util.Arrays;

public class Fibonacci {

    public int fibonacci(int n, int[] dp) {
        if (n <= 1) {
            return n;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        return dp[n] = fibonacci(n - 1, dp) + fibonacci(n - 2, dp);
    }

    public int fibonacci_tab(int n) {
        int dp[] = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int fibonacci_space(int n) {
        int prev2 = 0;
        int prev = 1;

        for (int i = 2; i <= n; i++) {
            int cur_i = prev2 + prev;
            prev2 = prev;
            prev = cur_i;
        }
        return prev;
    }
}

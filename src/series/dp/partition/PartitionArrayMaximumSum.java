package series.dp.partition;

import java.util.Arrays;

public class PartitionArrayMaximumSum {

    public int maxSumAfterPartitioning_mem(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return frontPartition_mem(0, n, k, arr, dp);
    }


    public int frontPartition_mem(int i, int n, int k, int[] arr, int[] dp) {
        if (i == n) {
            return 0;
        } else if (dp[i] != -1) {
            return dp[i];
        }

        int max = Integer.MIN_VALUE;
        int len = 0;
        int sum = 0;
        int maxAns = Integer.MIN_VALUE;
        for (int ind = i; ind < Math.min(n, i + k); ind++) {
            len++;
            max = Math.max(max, arr[ind]);
            sum = (max * len) + frontPartition_mem(ind + 1, n, k, arr, dp);
            maxAns = Math.max(maxAns, sum);
        }
        return dp[i] = maxAns;
    }

    public int maxSumAfterPartitioning_tab(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        dp[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            int max = Integer.MIN_VALUE;
            int len = 0;
            int sum = 0;
            int maxAns = Integer.MIN_VALUE;
            for (int ind = i; ind < Math.min(n, i + k); ind++) {
                len++;
                max = Math.max(max, arr[ind]);

                sum = (max * len) + dp[ind + 1];
                maxAns = Math.max(maxAns, sum);
            }
            dp[i] = maxAns;
        }
        return dp[0];
    }
}

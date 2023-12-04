package series.dp;

import java.util.Arrays;

public class SubSetSum {

    //ind = n-1
    boolean subsetSumUtil_mem(int ind, int target, int[] arr, int[][] dp) {
        if (target == 0) {
            return true;
        } else if (ind == 0) {
            return arr[0] == target;
        } else if (dp[ind][target] != -1) {
            return dp[ind][target] == 0 ? false : true;
        }
        boolean notTaken = subsetSumUtil_mem(ind - 1, target, arr, dp);
        boolean taken = false;
        if (arr[ind] <= target) {
            taken = subsetSumUtil_mem(ind - 1, target - arr[ind], arr, dp);

        }
        dp[ind][target] = notTaken || taken ? 1 : 0;
        return notTaken || taken;
    }

    boolean subsetSumUtil_tab(int n, int k, int[] arr) {
        boolean[][] dp = new boolean[n][k + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        if (arr[0] <= k) {
            dp[0][arr[0]] = true;
        }
        for (int ind = 1; ind < n; ind++) {
            for (int target = 1; target <= k; target++) {
                boolean notTaken = dp[ind - 1][target];
                boolean taken = false;
                if (arr[ind] <= target) {
                    taken = dp[ind - 1][target - arr[ind]];
                }
                dp[ind][target] = notTaken || taken;
            }
        }
        return dp[n - 1][k];
    }

    boolean subsetSumUtil_space(int n, int k, int[] arr) {
        boolean[] dp = new boolean[k + 1];
        dp[0] = true;
        if (arr[0] <= k) {
            dp[arr[0]] = true;
        }
        for (int ind = 1; ind < n; ind++) {
            boolean cur[] = new boolean[k + 1];
            for (int target = 1; target <= k; target++) {
                boolean notTaken = dp[target];
                boolean taken = false;
                if (arr[ind] <= target) {
                    taken = dp[target - arr[ind]];
                }
                cur[target] = notTaken || taken;
            }
            dp = cur;
        }

        return dp[k];
    }

    boolean canDivideSubSequenceInEqualSum(int n, int[] arr) {
        int totSum = 0;
        for (int i = 0; i < n; i++) {
            totSum += arr[i];
        }

        if (totSum % 2 == 1)
            return false;
        else {
            // Calculate the target sum for each subset
            int k = totSum / 2;
            // Create a memoization table
            int dp[][] = new int[n][k + 1];
            for (int row[] : dp)
                Arrays.fill(row, -1);
            // Call the helper function to check if a valid subset exists
            return subsetSumUtil_mem(n - 1, k, arr, dp);
        }
    }


    public int minimumDifference(int[] nums) {
        int n = nums.length;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        boolean[][] dp = new boolean[n][sum + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        if (nums[0] <= sum) {
            dp[0][nums[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int target = 1; target <= sum; target++) {
                boolean pick = false;
                boolean notPick = dp[i - 1][sum];
                if (nums[i] <= target) {
                    pick = dp[i - 1][sum - nums[i]];
                }
                dp[i][target] = pick || notPick;
            }
        }
        int min = Integer.MAX_VALUE;
        for (int s1 = 0; s1 <= sum; s1++) {
            if (dp[n - 1][s1] == true) {
                min = Math.min(Math.abs(sum - 2 * s1), min);
            }
        }
        return min;
    }

}

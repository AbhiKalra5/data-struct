package series.dp;

import java.util.Arrays;

public class SubSetSumTwo {
    // number of total subsets for given target
    // Zeros not included
    // if zeros included then do 2 pow Number of zeros
    int totalSubsetsWithGivenSum_mem(int ind, int target, int[] arr, int[][] dp) {
        if (ind == 0) {
            if (target == 0 && arr[0] == 0) {
                return 2;
            }
            if (target == 0 || target == arr[0]) {
                return 1;
            }
            return 0;
        }
        if (dp[ind][target] != -1) {
            return dp[ind][target];
        }
        int notTaken = totalSubsetsWithGivenSum_mem(ind - 1, target, arr, dp);
        int taken = 0;
        if (arr[ind] <= target) {
            taken = totalSubsetsWithGivenSum_mem(ind - 1, target - arr[ind], arr, dp);

        }
        return dp[ind][target] = (notTaken + taken) % (int) (Math.pow(10, 9) + 7);
    }

    int totalSubsetsWithGivenSum_tab(int k, int[] arr) {
        int n = arr.length;
        int[][] dp = new int[arr.length][k + 1];

        if (arr[0] == 0) {
            dp[0][0] = 2;  // 2 cases -pick and not pick
        } else {
            dp[0][0] = 1;  // 1 case - not pick
        }

        if (arr[0] != 0 && arr[0] <= k) {
            dp[0][arr[0]] = 1;
        }

        for (int ind = 1; ind < n; ind++) {
            for (int target = 0; target <= k; target++) {
                int notTake = dp[ind - 1][target];
                int take = 0;
                if (arr[ind] >= target) {
                    take = dp[ind - 1][target - arr[ind]];
                }

                dp[ind][target] = (take + notTake) % (int) (Math.pow(10, 9) + 7);
            }
        }
        return dp[n - 1][k];
    }

    int totalSubsetsWithGivenSum_space(int k, int[] nums) {
        int n = nums.length;
        int[] dp = new int[k + 1];
        if (nums[0] == 0) {
            dp[0] = 2;  // 2 cases -pick and not pick
        } else {
            dp[0] = 1;  // 1 case - not pick
        }

        if (nums[0] != 0 && nums[0] <= k) {
            dp[nums[0]] = 1;
        }
        for (int ind = 1; ind < n; ind++) {
            int cur[] = new int[k + 1];
            for (int target = 0; target <= k; target++) {
                int notTaken = dp[target];
                int taken = 0;
                if (nums[ind] <= target) {
                    taken = dp[target - nums[ind]];
                }
                cur[target] = (notTaken + taken) % (int) (Math.pow(10, 9) + 7);
            }
            dp = cur;
        }

        return dp[k];
    }

    // S2 = (totSum – D)/2
    int numberOfSubsequenceWithGivenDifference(int d, int[] arr) {
        int n = arr.length;
        int totSum = 0;
        for (int i = 0; i < arr.length; i++) {
            totSum += arr[i];
        }

        //Checking for edge cases
        if (totSum - d < 0)
            return 0;
        else if ((totSum - d) % 2 == 1)
            return 0;

        int s2 = (totSum - d) / 2;
        int dp[][] = new int[n][s2 + 1];
        for (int row[] : dp) {
            Arrays.fill(row, -1);
        }
        return totalSubsetsWithGivenSum_mem(n - 1, s2, arr, dp);
    }

    public int arrangeSignsToGetTotalTarget(int target, int[] arr) {
        return numberOfSubsequenceWithGivenDifference(target, arr);
    }

    static public int minNumberToSum(int index, int totalW, int[] wt, int[][] dp) {
        if (index == 0) {
            if (totalW % wt[0] == 0) {
                return (totalW / wt[0]);
            } else {
                return (int) 1e4;
            }
        } else if (dp[index][totalW] != -1) {
            return dp[index][totalW];
        }

        int notTake = minNumberToSum(index - 1, totalW, wt, dp);
        int take = (int) 1e4;
        if (wt[index] <= totalW) {
            take = 1 + minNumberToSum(index, totalW - wt[index], wt, dp);
        }
        return dp[index][totalW] = Math.min(take, notTake);
    }

}

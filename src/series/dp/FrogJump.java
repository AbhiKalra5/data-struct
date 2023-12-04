package series.dp;

import java.util.Arrays;

public class FrogJump {
    // frog Jump with Minimum effort

    int frogJumpMinimum_mem(int ind, int[] height, int[] dp) {
        if (ind == 0)
            return 0;
        if (dp[ind] != -1)
            return dp[ind];
        int jumpTwo = Integer.MAX_VALUE;
        int jumpOne = frogJumpMinimum_mem(ind - 1, height, dp) + Math.abs(height[ind] - height[ind - 1]);
        if (ind > 1)
            jumpTwo = frogJumpMinimum_mem(ind - 2, height, dp) + Math.abs(height[ind] - height[ind - 2]);

        return dp[ind] = Math.min(jumpOne, jumpTwo);
    }

    public int frogJumpMinimum_tab(int[] nums, int n) {
        int dp[] = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int ind = 1; ind < n; ind++) {
            int jumpTwo = Integer.MAX_VALUE;
            int jumpOne = dp[ind - 1] + Math.abs(nums[ind] - nums[ind - 1]);
            if (ind > 1)
                jumpTwo = dp[ind - 2] + Math.abs(nums[ind] - nums[ind - 2]);

            dp[ind] = Math.min(jumpOne, jumpTwo);
        }
        return dp[n - 1];
    }

    public int frogJumpMinimum_space(int[] height, int n) {
        int prev = 0;
        int prev2 = 0;
        for (int i = 1; i < n; i++) {

            int jumpTwo = Integer.MAX_VALUE;
            int jumpOne = prev + Math.abs(height[i] - height[i - 1]);
            if (i > 1)
                jumpTwo = prev2 + Math.abs(height[i] - height[i - 2]);

            int cur_i = Math.min(jumpOne, jumpTwo);
            prev2 = prev;
            prev = cur_i;

        }
        return prev;
    }

}

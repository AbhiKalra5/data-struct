package series.dp.partition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MinimumCostToCutStick {

    int cost(int n, int c, ArrayList<Integer> cuts) {
        // Modify the cuts array
        cuts.add(n);
        cuts.add(0);
        Collections.sort(cuts);

        int[][] dp = new int[c + 1][c + 1];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return getMinimumCutCost_mem(1, c, cuts, dp);
    }

    // 0    3 4 5 7 11 12   15
    // cover full array for cutting, first is 0 and last is length of stick
    int getMinimumCutCost_mem(int i, int j, ArrayList<Integer> cuts, int[][] dp) {
        if (i > j) {
            return 0;
        } else if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int min = Integer.MAX_VALUE;
        int len = cuts.get(j + 1) - cuts.get(i - 1);
        for (int cutIndex = i; cutIndex <= j; j++) {
            int res = len + getMinimumCutCost_mem(i, cutIndex - 1, cuts, dp) + getMinimumCutCost_mem(cutIndex + 1, j, cuts, dp);
            min = Math.min(res, min);
        }
        return dp[i][j] = min;
    }

    int getMinimumCutCost_tab(int n, int c, ArrayList<Integer> cuts) {
        // Modify the cuts array
        cuts.add(n);
        cuts.add(0);
        Collections.sort(cuts);

        int[][] dp = new int[c + 2][c + 2];

        for (int[] row : dp) {
            Arrays.fill(row, 0);
        }

        for (int i = c; i >= 1; i--) {
            for (int j = 1; j <= c; j++) {
                if (i > j)
                    continue;
                int mini = Integer.MAX_VALUE;

                for (int ind = i; ind <= j; ind++) {
                    int ans = cuts.get(j + 1) - cuts.get(i - 1) + dp[i][ind - 1] + dp[ind + 1][j];
                    mini = Math.min(mini, ans);
                }

                dp[i][j] = mini;
            }
        }
        return dp[1][c];
    }

    public static void main(String[] args) {
        System.out.println(minCost(7, new int[] {1, 3, 4, 5}));
    }

    public static int minCost(int n, int[] cuts) {
        int c = cuts.length;
        int[] newCuts = new int[c + 2];
        Arrays.sort(cuts);
        newCuts[0] = 0;
        newCuts[cuts.length + 1] = n;
        for (int i = 1; i <= c; i++) {
            newCuts[i] = cuts[i - 1];
        }

        int[][] dp = new int[c + 1][c + 1];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return getMinimumCutCost(1, c, newCuts, dp);
    }

    static int getMinimumCutCost(int i, int j, int[] cuts, int[][] dp) {
        if (i > j) {
            return 0;
        } else if (dp[i][j] != -1) {
            return dp[i][j];
        }
        int minValue = Integer.MAX_VALUE;
        int len = cuts[j + 1] - cuts[i - 1];
        for (int index = i; index <= j; index++) {
            int res = len + (getMinimumCutCost(i, index - 1, cuts, dp) + getMinimumCutCost(index + 1, j, cuts, dp));
            minValue = Math.min(minValue, res);
        }
        return dp[i][j] = minValue;
    }
}

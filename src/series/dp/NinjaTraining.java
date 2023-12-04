package series.dp;

import java.util.ArrayList;

public class NinjaTraining {

    public int ninjaTraining_mem(int day, int last, int[][] points, int[][] dp) {
        int n = points.length;
        int m = points[0].length;
        int max = Integer.MIN_VALUE;
        if (day == 0) {
            for (int i = 0; i < m; i++) {
                if (i != last) {
                    max = Math.max(max, points[day][i]);
                }
            }
            return points[day][last] = max;
        }

        for (int task = 0; task < m; task++) {
            if (task != last) {
                int temp = points[day][task] + ninjaTraining_mem(day - 1, task, points, dp);
                max = Math.max(max, temp);
            }

        }
        return points[day][last] = max;

    }

    public int ninjaTraining_tab(int[][] points) {
        int n = points.length;
        int[][] dp = new int[n][4];
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][1], points[0][0]);
        dp[0][3] = Math.max(Math.max(points[0][1], points[0][2]), points[0][0]);

        for (int day = 1; day < n; day++) {
            for (int middle = 0; middle < 4; middle++) {
                dp[day][middle] = Integer.MIN_VALUE;
                for (int task = 0; task < 3; task++) {
                    if (middle != task) {
                        int temp = points[day][task] + dp[day - 1][task];
                        dp[day][middle] = Math.max(dp[day][middle], temp);
                    }
                }
            }
        }
        return dp[n - 1][3];
    }

    public int ninjaTraining_space(int[][] points, int n) {
        int prev[] = new int[4];

        // Initialize the first day's maximum points based on the available choices
        prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);
        prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        // Iterate through each day starting from the second day
        for (int day = 1; day < n; day++) {
            int temp[] = new int[4];
            for (int mid = 0; mid < 4; mid++) {
                temp[mid] = 0;
                for (int task = 0; task < 3; task++) {
                    if (task != mid) {
                        int sumNow = points[day][task] + prev[task];
                        temp[mid] = Math.max(temp[mid], sumNow);
                    }
                }
            }
            prev = temp;
        }
        return prev[3];
    }
}

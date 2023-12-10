package series.dp.lis;

import java.util.Arrays;

public class NumberOfLongestIncreasingSubsequence {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestIncreasingSubsequence(new int[] {1, 5, 4, 3, 2, 6, 7, 10, 8, 9}));
    }

    static int lengthOfLongestIncreasingSubsequence(int nums[]) {
        int n = nums.length;
        int[] res = new int[n];
        int[] count = new int[n];
        Arrays.fill(res, 1);
        Arrays.fill(count, 1);
        int max = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (res[j] + 1 > res[i]) {
                        res[i] = res[j] + 1;
                        count[i] = count[j];
                    } else if (res[j] + 1 == res[i]) {
                        count[i] += count[j];
                    }
                    max = Math.max(res[i], max);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (res[i] == max) {
                ans += count[i];
            }
        }
        return ans;

    }
}

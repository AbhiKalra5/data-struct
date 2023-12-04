package series.dp.lis;

public class LongestPeakSubsequence {
// bitonic
    int longestPeakSubsequence(int arr[], int n) {
        int dp_a[] = longestIncreasingSubsequence(arr, n);
        int dp_b[] = longestIncreasingSubsequence_reverse(arr, n);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(dp_a[i] + dp_b[i] - 1, max);
        }
        return max;
    }

    private int[] longestIncreasingSubsequence(int arr[], int n) {
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = 1;
            for (int prev = 0; prev < i; prev++) {
                if (arr[i] > arr[prev] && res[prev] + 1 > res[i]) {
                    res[i] = res[prev] + 1;
                }
            }
        }
        return res;
    }

    private int[]  longestIncreasingSubsequence_reverse(int arr[], int n) {
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            res[i] = 1;
            for (int prev = n - 1; prev > i; prev--) {
                if (arr[i] > arr[prev] && res[prev] + 1 > res[i]) {
                    res[i] = res[prev] + 1;
                }
            }
        }
        return res;
    }
}

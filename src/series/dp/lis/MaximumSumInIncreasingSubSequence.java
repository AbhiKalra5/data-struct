package series.dp.lis;

public class MaximumSumInIncreasingSubSequence {
    public static void main(String[] args) {
        maximumSumInIncreasingSubSequence(new int[] {5, 101, 2, 3, 100}, 5);
    }

    public static int maximumSumInIncreasingSubSequence(int arr[], int n) {
        int[] res = new int[n];
        int max = arr[0];
        for (int i = 0; i < n; i++) {
            res[i] = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && res[j] + arr[i] > res[i]) {
                    res[i] = res[j] + arr[i];
                }
                max = Math.max(res[i], max);
            }
        }
        return max;
    }
}

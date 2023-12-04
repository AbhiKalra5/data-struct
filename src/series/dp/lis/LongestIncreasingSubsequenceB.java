package series.dp.lis;

import java.util.ArrayList;

public class LongestIncreasingSubsequenceB {
    ArrayList<Integer> longestIncreasingSubsequence_eff(int arr[], int n) {
        int[] res = new int[n];
        int[] trace = new int[n];
        int max = Integer.MIN_VALUE;
        int lastIndex = 0;
        for (int i = 0; i < n; i++) {
            res[i] = 1;
            trace[i] = i;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && res[j] + 1 > res[i]) {
                    res[i] = res[j] + 1;
                    trace[i] = j;
                }
            }
            if (res[i] > max) {
                max = res[i];
                lastIndex = i;
            }
        }

        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(arr[lastIndex]);
        while (trace[lastIndex] != lastIndex) {
            lastIndex = trace[lastIndex];
            temp.add(arr[lastIndex]);
        }
        return temp;
    }
}

package series.dp.lis;

import java.util.ArrayList;
import java.util.Arrays;

public class LongestDivisibleSubset {

    ArrayList<Integer> largestDivisibleSubset_eff(int arr[], int n) {
        int[] res = new int[n];
        int[] trace = new int[n];
        int max = Integer.MIN_VALUE;
        int lastIndex = 0;
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {
            res[i] = 1;
            trace[i] = i;
            for (int prev = 0; prev < i; prev++) {
                if (arr[i] % arr[prev] == 0 && res[prev] + 1 > res[i]) {
                    res[i] = res[prev] + 1;
                    trace[i] = prev;
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

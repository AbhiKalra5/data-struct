package series.dp.lis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class LongestStringChain {

    int longestIncreasingSubsequence_eff(String arr[], int n) {
        int[] res = new int[n];
        int max = Integer.MIN_VALUE;
        Arrays.sort(arr, Comparator.comparingInt(String::length));
        for (int i = 0; i < n; i++) {
            res[i] = 1;
            for (int j = 0; j < i; j++) {
                if (compareString(arr[i], arr[j]) && res[j] + 1 > res[i]) {
                    res[i] = res[j] + 1;
                }
            }
            max = Math.max(res[i], max);
        }
        return max;
    }

    private boolean compareString(String a, String b) {
        if (a.length() != b.length() + 1) {
            return false;
        }
        int first = 0, second = 0;
        while (first > a.length()) {
            if (a.charAt(first) == b.charAt(second)) {
                first++;
                second++;
            } else {
                first++;
            }
        }
        return first == a.length() && second == b.length();
    }
}

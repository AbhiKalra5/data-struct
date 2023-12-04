package series.dp.lis;

import series.BinarySearch;

import java.util.ArrayList;

// cannot give array
public class LongestIncreasingSubsequenceC {
    BinarySearch binarySearch = new BinarySearch();
    int longestIncreasingSubsequence_eff(int arr[], int n) {
        int[] temp = new int[n];
        int last = 0;
        for (int i = 1; i < n; i++) {
            int num = arr[i];
            int index = binarySearch.lowerBound(temp, temp.length, num);
            if (index == n) {
                temp[++last] = num;
            } else {
                temp[index] = num;
            }
        }
        return last + 1;
    }
}

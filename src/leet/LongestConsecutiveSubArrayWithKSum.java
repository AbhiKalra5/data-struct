package leet;

import java.util.HashMap;
import java.util.Map;

// 1. HashMap: put sum till point and location in map, match sum till point or subtract the current sum with target and find in map to get length.
// 2. 2 Pointer: move
public class LongestConsecutiveSubArrayWithKSum {
    int longestConsecutiveSubArrayWithKSum(int[] arr, int k) {
        Map<Integer, Integer> checkMap = new HashMap<>();
        int sum = 0;
        int maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum == k) {
                maxLen = Math.max(maxLen, i + 1);
            }
            int rem = sum - k;
            if (checkMap.containsKey(rem)) {
                int len = i - checkMap.get(rem);
                maxLen = Math.max(maxLen, len);
            } else {
                checkMap.put(sum, i);
            }

        }
        return maxLen;
    }

    int longestConsecutiveSubArrayWithKSum_B(int[] arr, int k) {

        int sum = 0;
        int maxLen = 0;
        int right = 0;
        int left = 0;
        while (right < arr.length) {
            while (left <= right && sum > k) {
                sum = sum - arr[left];
                left++;
            }
            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
            right++;
            if (right < arr.length) {
                sum = sum + arr[right];
            }
        }
        return maxLen;
    }
}

package leet;

import java.util.HashMap;
import java.util.Map;
// 1 Leetcode
// brute force n2
// hash element and index and compute by subtraction value-target should exist in hashmap
// two edge pointer : sort, move from left if greater than target or right if less
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>(len);

        for (int i = 0; i < len; i++) {
            int val = nums[i];
            int gap = target - val;
            if (map.containsKey(gap)) {
                return new int[]{map.get(gap), i};
            }
            map.put(val, i);
        }
        return new int[]{-1, -1};
    }
}

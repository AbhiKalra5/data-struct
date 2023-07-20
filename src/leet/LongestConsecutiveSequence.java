package leet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 128. Longest Consecutive Sequence
//n2
public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        int longest = 0;
        int count = 0;
        int last = Integer.MIN_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            if (temp == last + 1) {
                count++;
                last = temp;
            } else if (temp != last) {
                count = 1;
                last = temp;
            }
            longest = Math.max(longest, count);
        }
        return longest;
    }

    public int longestConsecutiveB(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int longest = 0;
        Set<Integer> checkSet = new HashSet<>();
        for (int num : nums) {
            checkSet.add(num);
        }
        for (int num : nums) {
            if (!checkSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (checkSet.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longest = Math.max(longest, currentStreak);
            }
        }
        return longest;
    }
}

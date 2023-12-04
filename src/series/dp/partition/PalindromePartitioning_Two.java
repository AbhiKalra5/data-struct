package series.dp.partition;

import java.lang.reflect.Array;
import java.util.Arrays;

public class PalindromePartitioning_Two {

    public int minCut_mem(String s) {
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return palindromePartition(0, s, dp) - 1;
    }

    public int palindromePartition(int i, String s, int[] dp) {
        if (i == s.length()) {
            return 0;
        } else if (dp[i] != -1) {
            return dp[i];
        }
        int min = Integer.MAX_VALUE;
        for (int j = i; j < s.length(); j++) {
            if (isPalindrome(s, i, j)) {
                min = Math.min(min, 1 + palindromePartition(j + 1, s, dp));
            }
        }
        return dp[i] = min;
    }

    public int minCut_tab(String s) {
        int[] dp = new int[s.length() + 1];
        for (int i = s.length() - 1; i >= 0; i--) {
            int min = Integer.MAX_VALUE;
            for (int j = i; j < s.length(); j++) {
                if (isPalindrome(s, i, j)) {
                    min = Math.min(min, 1 + dp[j + 1]);
                }
            }
            dp[i] = min;
        }
        return dp[0] - 1;
    }


    boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start++) != s.charAt(end--))
                return false;
        }
        return true;
    }
}

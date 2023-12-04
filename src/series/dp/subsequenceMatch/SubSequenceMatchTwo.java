package series.dp.subsequenceMatch;

public class SubSequenceMatchTwo {
    SubSequenceMatch subSequenceMatch = new SubSequenceMatch();

    public String getLongestMatchingSubSequenceString(String s1, String s2) {
        int[][] dp = subSequenceMatch.longestCommonSubSet_tab(s1, s2);
        int n = s1.length();
        int m = s2.length();
        String res = new String();
        for (int ind1 = n; ind1 > 0; ind1--) {
            for (int ind2 = m; ind2 > 0; ind2--) {
                if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1)) {
                    res = s1.charAt(ind1 - 1) + "" + res;
                    ind1--;
                    ind2--;
                } else {
                    int left = dp[ind1][ind2 - 1];
                    int up = dp[ind1 - 1][ind2];
                    if (left > up) {
                        ind2--;
                    } else {
                        ind1--;
                    }
                }
            }
        }
        return res;
    }

    // reverse and check longest matching subsequence in 2 strings
    public int checkLongestPalindromeSubsequence(String s1) {
        String reverse = new String();
        int i = s1.length() - 1;
        while (i >= 0) {
            reverse = s1.charAt(i) + reverse;
            i--;
        }
        return subSequenceMatch.longestCommonSubSet_space(s1, reverse);
    }

    /* if we keep palindrome subsequence intact , we can add rest characters in reverse position in other half
    abcaa -> A  b  C  a  A : caps is palindrome subsequence, b ,a needs to  be put as a,b
     */

    public int minimumInsertionsToMakeStringPalindrome(String s1) {
        return s1.length() - checkLongestPalindromeSubsequence(s1);
    }

    // return additions + subtraction from s1
    public int minimumInsertionsAndDeletionsToEqualizeString(String s1, String s2) {
        int longestCommonSubSet = subSequenceMatch.longestCommonSubSet_space(s1, s2);
        return (s1.length() - longestCommonSubSet) + (s2.length() - longestCommonSubSet);
    }

    public String shortestCommonSuperSequence(String s1, String s2) {
        int[][] dp = subSequenceMatch.longestCommonSubSet_tab(s1, s2);
        int n = s1.length();
        int m = s2.length();
        String res = new String();
        int i;
        int j = m;
        for (i = n; i > 0; i--) {
            for (j = m; j > 0; j--) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    res = s1.charAt(i) + "" + res;
                    i--;
                    j--;
                } else if (dp[i][j - 1] > dp[i - 1][j]) {
                    res = s1.charAt(i - 1) + "" + res;
                    i--;
                } else {
                    res = s2.charAt(j - 1) + "" + res;
                    j--;
                }
            }
        }
        while (i > 0) {
            res = s1.charAt(i - 1) + res;
            i--;
        }
        while (j > 0) {
            res += s2.charAt(j - 1) + res;
            j--;
        }
        return res;
    }
}

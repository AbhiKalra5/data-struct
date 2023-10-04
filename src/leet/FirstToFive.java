package leet;

import java.util.*;

public class FirstToFive {
    public static void main(String[] args) {

        int[] nums1 = {2,3,5,10};
        int[] nums2 = {6,7,8,11,12};


    }

    private static int substringDetect(String checkString) {
        HashSet<Character> hashSet = new HashSet<>();
        int left = 0, right = 0;
        int max = 0;
        while (right < checkString.length()) {
            if (!hashSet.contains(checkString.charAt(right))) {
                hashSet.add(checkString.charAt(right));
                right++;
            } else {
                hashSet.remove(checkString.charAt(left));
                left++;
            }
            max = Math.max(max, hashSet.size());
        }
        return max;
    }

    private static double merge2SortedMedian(int[] nums1, int[] nums2) {

        int lenght_a = nums1.length, lenght_b = nums2.length;
        int[] newArray = new int[lenght_a + lenght_b];
        int i = 0, j = 0, k = 0;
        while (i < lenght_a && j < lenght_b) {
            if (nums1[i] < nums2[j]) {
                newArray[k] = nums1[i];
                i++;
                k++;
            } else {
                newArray[k] = nums2[j];
                j++;
                k++;
            }
        }
        while (i < lenght_a) {
            newArray[k] = nums1[i];
            i++;
            k++;
        }
        while (j < lenght_b) {
            newArray[k] = nums2[j];
            j++;
            k++;
        }
        return k % 2 != 0 ? (newArray[k / 2]) : ((newArray[(k - 1) / 2] + newArray[k / 2]) / 2.0);
    }

    public static String solvePalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len_a = expandFromMiddle(s, i, i);
            int len_b = expandFromMiddle(s, i, i + 1);
            int len = Math.max(len_a, len_b);
            if (len > end - start) {
                start = i - ((len - 1) / 2);
                end = i + (len / 2);
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandFromMiddle(String s, int left, int right) {
        if (s == null || left > right) {
            return 0;
        }
        while (left >= 0 && right < s.length() && s.charAt(right) == s.charAt(left)) {
            right++;
            left--;
        }
        return right - left - 1;
    }

}

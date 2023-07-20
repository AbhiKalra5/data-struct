package leet;

import java.util.*;

public class FirstToFive {
    public static void main(String[] args) {

        int[] nums1 = {2,3,5,10};
        int[] nums2 = {6,7,8,11,12};
        System.out.println(median(nums1,nums2));


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

    public static int median(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int resultArrayLen = len1 + len2;
        if (len2 < len1) {
           return median(nums2, nums1);
        }

        int low = 0;
        int high = len1;
        while (low <= high) {

            int cutPos1 = (low + high) / 2;
            int cutPos2 = (resultArrayLen + 1) / 2 - cutPos1;

            int left1 = cutPos1 == 0 ? Integer.MIN_VALUE : nums1[cutPos1 - 1];
            int left2 = cutPos2 == 0 ? Integer.MIN_VALUE : nums2[cutPos2 - 1];
            int right1 = cutPos1 == len1 ? Integer.MAX_VALUE : nums1[cutPos1];
            int right2 = cutPos2 == len2 ? Integer.MAX_VALUE : nums2[cutPos2];

            if (left1 <= right2 && left2 <= right1) {
                if (resultArrayLen % 2 == 0) {
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2;
                } else {
                    return (Math.max(left1, left2));
                }

            } else if (left1 > right2) {
                high = cutPos1 - 1;
            } else {
                low = cutPos1 + 1;
            }
        }
        return 0;
    }

}
package leet;

import java.util.Arrays;

//88. Merge Sorted Array
// gap
// compare and swap end of a1 and start of a2, sort arrays.
public class MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len = m + n;
        int gap = (len) / 2 + (len % 2);
        while (gap > 0) {
            int left = 0;
            int right = left + gap;
            while (right < len) {
                if (left < m && right >= m) {
                    // a1 in 1 and a2 in 2 zone
                    swapIfGreater(nums1, left, nums2, right - m);
                } else if (left >= m) {
                    // a1 in 2 zone
                    swapIfGreater(nums2, left - m, nums2, right - m);
                } else if (left < m && right < m) {
                    // a2 in 1 zone
                    swapIfGreater(nums1, left, nums1, right);
                }
                left++;
                right++;

            }
            if (gap == 1) {
                break;
            }
            gap = (gap / 2) + (gap % 2);

        }
    }

    void swapIfGreater(int[] nums1, int i, int[] nums2, int j) {
        if (nums1[i] > nums2[j]) {
            int temp = nums1[i];
            nums1[i] = nums2[j];
            nums2[j] = temp;
        }
    }

    public void mergeB(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = 0;
        while (i >= 0 && j < n) {
            if (nums1[i] > nums2[j]) {
                swap(nums1, i, nums2, j);
                i--;
                j++;
            } else {
                break;
            }
        }
        Arrays.sort(nums1, 0, m);
        Arrays.sort(nums2, 0, n);
        j = 0;
        for (i = m; i < nums1.length; i++, j++) {
            nums1[i] = nums2[j];
        }
    }

    void swap(int[] nums1, int i, int[] nums2, int j) {
        int temp = nums1[i];
        nums1[i] = nums2[j];
        nums2[j] = temp;
    }
}

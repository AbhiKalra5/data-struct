package series;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashMap;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class ArraySeries {

    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int partition = partition(arr, low, high);
            quickSort(arr, low, partition - 1);
            quickSort(arr, partition + 1, high);
        }
    }

    public int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low;
        int j = high;
        while (i < j) {
            while (arr[i] <= pivot && i < high) {
                i++;
            }
            while (arr[j] > pivot && i > low) {
                j--;
            }
            if (i < j) {
                swap(i, j, arr);
            }
        }
        swap(j, low, arr);
        return j;
    }

    public int findKthLargest(int[] nums, int k) {
        return kLargest_quickSort(nums, 0, nums.length - 1, nums.length - k);
    }

    public int kLargest_quickSort(int[] arr, int low, int high, int k) {
        if (low < high) {
            int partition = partition(arr, low, high);
            if (partition == k) {
                return arr[k];
            }
            if (k < partition) {
                return kLargest_quickSort(arr, low, partition - 1, k);
            } else {
                return kLargest_quickSort(arr, partition + 1, high, k);
            }
        } else if (low == high) {
            return arr[low];
        }
        return -1;
    }


    public int secondLargest(int[] arr) {
        int max = arr[0];
        int second = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                second = max;
                max = arr[i];
            } else if (arr[i] < max && arr[i] > second) {
                second = arr[i];
            }
        }
        return second;
    }

    public void removeDuplicates(int arr[]) {
        int first = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[first] != arr[i]) {
                first++;
                arr[first] = arr[i];
            }
        }
    }

    void segregate0and1(int[] arr, int n) {
        int left = 0;
        int right = n - 1;
        while (left < right) {
            while (arr[left] == 0 && left < right) {
                left++;
            }
            while (arr[right] == 1 && left < right) {
                right--;
            }

            if (left < right) {
                arr[left] = 0;
                arr[right] = 1;
                left++;
                right--;
            }
        }
    }

    public void rotateArray(int[] a, int nod, int rotateBy) {
        if (nod > 1) {
            rotateBy = rotateBy % nod;
            if (rotateBy < 0) {
                rotateBy = nod + rotateBy;
            }
            reverse(a, 0, nod - 1 - rotateBy);
            reverse(a, nod - rotateBy, nod - 1);
            reverse(a, 0, nod - 1);

        }
        for (int temp : a) {
            System.out.println(temp);
        }
    }

    public static void reverse(int a[], int i, int j) {
        while (i < j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++;
            j--;
        }
    }

    public void moveZeroToEnd(int[] a, int n) {
        int first = 0;
        while (first < n) {
            if (a[first] == 0) {
                break;
            }
        }
        int second = first + 1;

        while (second < n) {
            if (a[second] != 0) {
                swap(first, second, a);
                first++;
            }
            second++;
        }
    }

    private void swap(int[] nums1, int i, int[] nums2, int j) {
        int temp = nums1[i];
        nums1[i] = nums2[j];
        nums2[j] = temp;
    }

    public int[] unionOfArrays(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;
        int[] res = new int[n + m];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < n && j < m) {
            if (a[i] <= b[j]) {
                if (k == 0 || a[i] != res[k - 1]) {
                    res[k] = a[i];
                    k++;
                }
                i++;
            } else {
                if (k == 0 || b[j] != res[k - 1]) {
                    res[k] = b[j];
                    k++;
                }
                j++;
            }
        }

        while (i < n) {
            if (k == 0 || a[i] != res[k - 1]) {
                res[k] = a[i];
                k++;
            }
            i++;
        }

        while (j < m) {
            if (k == 0 || b[j] != res[k - 1]) {
                res[k] = b[j];
                k++;
            }
            j++;
        }
        return res;
    }

    public int[] intersectionOfArrays(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;
        int[] res = new int[m];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < n && j < m) {
            if (a[i] == b[j]) {
                res[k] = a[i];
                j++;
                i++;
                k++;
            } else if (a[i] < b[j]) {
                i++;
            } else {
                j++;
            }
        }

        return res;
    }

    public int findMissingNumber(int[] a, int n) {
        int sum = (n * (n + 1)) / 2;
        int realSum = 0;
        for (int i = 0; i < n; i++) {
            realSum += a[i];
        }
        return sum - realSum;
    }

    public int findMissingNumberXor(int[] a, int n) {
        int xor = 0;
        int realXor = 0;
        for (int i = 0; i < n; i++) {
            realXor ^= a[i];
            xor ^= a[i + 1];
        }
        xor = xor ^ n;
        return xor ^ realXor;
    }

    public int longestSequenceOfNumber(int[] a, int n, int number) {
        int count = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (a[i] == number) {
                count++;
                max = Math.max(max, count);
            } else {
                count = 0;
            }

        }
        return max;
    }

    public int findNumberAppearingOnce(int[] a, int n) {
        int realXor = 0;
        for (int i = 0; i < n; i++) {
            realXor ^= a[i];
        }
        return realXor;
    }

    //brute
    int longestSubArrayWithKSum_Brute(int[] arr, int k) {
        int maxLength = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[i];
                if (sum == k) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        return maxLength;
    }
    /* For  negative + Positive best approach
    HashMap: put sum till point and location in map, match sum till
     point or subtract the current sum with target and find in map to get length.*/

    static int longestConsecutiveSubArrayWithKSum_Hashing(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (sum == k) {
                maxLen = Math.max(maxLen, i + 1);
            }
            int diff = sum - k;
            if (map.containsKey(diff)) {
                int len = i - map.get(diff);
                maxLen = Math.max(maxLen, len);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return maxLen;
    }

    /*For  only  Positive: best approach
        Pointer: move
     */
    static int longestConsecutiveSubArrayWithKSum_B(int[] arr, int k) {

        int sum = arr[0];
        int maxLen = Integer.MAX_VALUE;
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

    int minLenSubArrayWithKSum(int[] arr, int k) {
        int sum = 0;
        int maxLen = Integer.MAX_VALUE;
        int right = 0;
        int left = 0;
        while (right < arr.length) {
            sum = sum + arr[right];
            while (left <= right && sum >= k) {
                maxLen = Math.min(maxLen, right - left + 1);
                sum = sum - arr[left];
                left++;
            }
            right++;
        }
        return maxLen;
    }

    // similar to Longest subarrays, store sumTillPoint and count
    public int findAllSubArraysWithGivenSum(int arr[], int k) {
        int n = arr.length; // size of the given array.
        Map<Integer, Integer> mpp = new HashMap();
        int preSum = 0, cnt = 0;

        mpp.put(0, 1); // Setting 0 in the map.
        for (int i = 0; i < n; i++) {
            preSum += arr[i];  // add current element to prefix Sum:
            int remove = preSum - k; // Calculate x-k:
            cnt += mpp.getOrDefault(remove, 0); // Add the number of subarrays to be removed:
            mpp.put(preSum, mpp.getOrDefault(preSum, 0) + 1); // Update the count of prefix sum in the map.
        }
        return cnt;
    }

    int longestSubArrWthSumDivByK(int arr[], int n, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            int rem = sum % k;

            if (rem == 0) {
                maxLen = Math.max(maxLen, i + 1);
            } else if (rem < 0) {
                rem = rem + k;
            }
            if (map.containsKey(rem)) {
                maxLen = Math.max(maxLen, i - map.get(rem));
            } else {
                map.put(rem, i);
            }

        }
        return maxLen;
    }

    static long countPairsWithSumDivisibleByK(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        long res = (long) 0.0;
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i] % k;
            if (num != 0) {
                int rem = k - num;
                res += map.getOrDefault(rem, 0);
            } else {
                res += map.getOrDefault(0, 0);
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return res;
    }

    static long countPairsWithMultiplicationDivisibleByK(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        long res = (long) 0.0;
        for (int i = 0; i < arr.length; i++) {
            int gcd1 = gcd(arr[i], k);
            int gcd2 = k / gcd1;
            if (gcd2 == 1) {
                res += i;
            } else {
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    if (entry.getKey() % gcd2 == 0) {
                        res += entry.getValue();
                    }
                }
            }
            map.compute(gcd1, (e, v) -> v == null ? 1 : v + 1);
        }
        return res;

    }

    static public int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }


    // 1 Leetcode
    // brute force n2
    // hash element and index and compute by subtraction value-target should exist in hashmap

    public int[] twoSum_Hash(int[] nums, int target) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>(len);

        for (int i = 0; i < len; i++) {
            int val = nums[i];
            int gap = target - val;
            if (map.containsKey(gap)) {
                return new int[] {map.get(gap), i};
            }
            map.put(val, i);
        }
        return new int[] {-1, -1};
    }

    // two  pointer at edges: sort, move from left if greater than target or right if less
    //best approach: greedy, cannot tell locations because of sort
    public int[] twoSum_Greedy(int[] nums, int target) {
        int right = nums.length;
        int left = 0;
        Arrays.sort(nums);
        while (left <= right) {
            int sum = nums[left] + nums[right];
            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                return new int[] {left, right};
            }
        }
        return new int[] {-1, -1};
    }

    //75. Sort Colors
    // sort with  nlogn
    // modify array based on frequency of 0,1,2
    // DNF
    public void sortThreeNumbers(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            int temp = nums[mid];
            if (temp == 0) {
                swap(low, mid, nums);
                low++;
                mid++;
            } else if (temp == 1) {
                mid++;
            } else if (temp == 2) {
                swap(high, mid, nums);
                high--;
            }
        }

    }

    static private void swap(int i, int j, int[] nums) {
        int c = nums[i];
        nums[i] = nums[j];
        nums[j] = c;
    }

    //169 .Majority Element in an Array
    // count and scan in n2
    // memoization using hashmap
    public int majorityElement(int[] nums) {
        int element = 0;
        int countTillNow = 0;
        for (int i = 0; i < nums.length; i++) {
            if (countTillNow == 0) {
                element = nums[i];
                countTillNow = 1;
            } else if (element != nums[i]) {
                countTillNow--;
            } else {
                countTillNow++;
            }
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (element == nums[i]) {
                count++;
            }
        }
        return count > nums.length / 2 ? element : -1;
    }

    public int maximumSubArraySum(int[] nums) {
        int sum;
        int res = sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(nums[i], sum + nums[i]);
            res = Math.max(sum, res);
        }
        return res;
    }

    public int[] maxSubarraySum(int[] arr, int n) {
        long maxi = Long.MIN_VALUE; // maximum sum
        long sum = 0;

        int start = 0;
        int ansStart = -1, ansEnd = -1;
        for (int i = 0; i < n; i++) {

            if (sum == 0) {
                start = i; // starting index
            }
            sum += arr[i];

            if (sum > maxi) {
                maxi = sum;
                ansStart = start;
                ansEnd = i;
            }

            // If sum < 0: discard the sum calculated
            if (sum < 0) {
                sum = 0;
            }
        }
        return new int[] {ansStart, ansEnd};
    }

    // when equal
    public int[] rearrangePositiveNegative(int[] arr, int n) {
        int[] answer = new int[n];
        int pos = 0;
        int neg = 1;
        for (int i = 0; i < n; i++) {
            if (arr[i] >= 0) {
                answer[pos] = arr[i];
                pos += 2;
            } else {
                answer[neg] = arr[i];
                neg += 2;
            }
        }
        return answer;
    }

    // when Not equal
    public int[] rearrangePositiveNegative_Unequal(int[] arr, int n) {
        int[] answer = new int[n];
        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> neg = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (arr[i] >= 0) {
                pos.add(arr[i]);
            } else {
                neg.add(arr[i]);
            }
        }
        // arrange accordingly
        if (pos.size() < neg.size()) {
            for (int i = 0; i < pos.size(); i++) {
                arr[i * 2] = pos.get(i);
                arr[(i * 2) + 1] = neg.get(i);
            }
            int indexInResult = 2 * pos.size();
            for (int i = pos.size(); i < neg.size(); i++) {
                arr[indexInResult] = neg.get(i);
            }
        } else {
            for (int j = 0; j < neg.size(); j++) {
                arr[j * 2] = pos.get(j);
                arr[(j * 2) + 1] = neg.get(j);
            }
            int indexInResult = 2 * neg.size();
            for (int i = neg.size(); i < pos.size(); i++) {
                arr[indexInResult] = pos.get(i);
            }
        }
        return answer;
    }

    public int buySellStock(int[] a) {
        int min = a[0];
        int profit = 0;
        for (int i = 1; i < a.length; i++) {
            profit = Math.max(profit, a[i] - min);
            min = Math.min(min, a[i]);
        }
        return profit;
    }

    public ArrayList<Integer> leaders(int[] a, int n) {
        ArrayList<Integer> res = new ArrayList<>();
        int max = a[n - 1];
        for (int i = n - 2; i <= 0; i++) {
            if (a[i] > max) {
                max = a[i];
                res.add(a[i]);
            }
        }
        return res;
    }
}



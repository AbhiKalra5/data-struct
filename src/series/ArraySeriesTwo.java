package series;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class ArraySeriesTwo {

    // inversePairs
    int count;

    public int numberOfInversions(int[] a, int n) {
        count = 0;
        // Count the number of pairs:
        mergeSort(a, 0, n - 1);
        return count;
    }

    private void mergeSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    private void merge(int[] arr, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>();
        int i = low;
        int j = mid + 1;

        while (i <= mid && j <= high) {
            if (arr[i] <= arr[j]) {
                temp.add(arr[i]);
                i++;
            } else {
                count += mid - i + 1;
                temp.add(arr[j]);
                j++;
            }
        }
        while (i <= mid) {
            temp.add(arr[i]);
            i++;
        }
        //  if elements on the right half are still left //
        while (j <= high) {
            temp.add(arr[j]);
            j++;
        }

        for (i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
    }

    public int maximumProductSubarray(int[] a, int n) {
        int max = Integer.MIN_VALUE;
        int suffix = 1;
        int prefix = 1;
        for (int i = 0; i < n; i++) {
            suffix = suffix * a[i];
            prefix = prefix * a[n - i - 1];
            max = Math.max(max, Math.max(suffix, prefix));
            if (suffix == 0) {
                suffix = 1;
            }
            if (prefix == 0) {
                prefix = 1;
            }
        }
        return max;
    }

    public int[] nextSmallerElementLeft(int[] array) {
        Stack<Integer> stack = new Stack<>();
        int n = array.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && array[stack.peek()] > array[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peek() + 1;
            stack.push(i);
        }
        return res;
    }

    public int[] nextSmallerElementRight(int[] array) {
        Stack<Integer> stack = new Stack<>();
        int n = array.length;
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && array[stack.peek()] > array[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? n - 1 : stack.peek() - 1;
            stack.push(i);
        }
        return res;
    }

    public static int largestAreaHistogram(int[] arr) {
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || arr[stack.peek()] >= arr[i])) {
                int height = arr[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                res = Math.max(width * height, res);
            }
            stack.push(i);
        }
        return res;
    }

    static public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int ans[] = new int[n - k + 1];
        int counter = 0;
        for (int i = 0; i < n; i++) {
            while (!queue.isEmpty() && queue.peekFirst() <= i - k) {
                queue.poll();
            }
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            if (i >= k - 1) {
                ans[counter++] = nums[queue.peekFirst()];
            }
        }
        return ans;
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer>[] bucket = new List[nums.length + 1];
        int count = 0;
        int[] res = new int[k];

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (bucket[entry.getValue()] == null) {
                bucket[entry.getValue()] = new ArrayList<>();
            }
            bucket[entry.getValue()].add(entry.getKey());
        }
        for (int i = bucket.length - 1; i >= 0 && count < k; i--) {
            if (bucket[i] != null) {
                for (Integer value : bucket[i]) {
                    res[count] = value;
                    count++;
                }
            }
        }
        return res;
    }

    void countFrequency_a(int arr[], int n) {
        int i = 0;
        while (i < n) {
            if (arr[i] > 0) {
                int index = arr[i] - 1;
                if (arr[index] < 0) {
                    arr[index] = arr[index] - 1;
                    arr[i] = 0;
                    i++;
                } else {
                    arr[i] = arr[index];
                    arr[index] = -1;
                }
            } else {
                i++;
            }
        }
    }

    void countFrequency_b(int arr[], int n) {
        for (int j = 0; j < n; j++) {
            arr[j] = arr[j] - 1;
        }

        for (int i = 0; i < n; i++) {
            arr[arr[i] % n] = arr[arr[i] % n] + n;
        }
        for (int i = 0; i < n; i++) {
            System.out.println(i + 1 + "->" + arr[i] / n);
        }
    }

    static void zigZag(int arr[], int n) {
        boolean flag = true;
        for (int i = 0; i <= n - 2; i++) {
            if (flag) {
                if (arr[i] > arr[i + 1]) {
                    swap(i, i + 1, arr);
                }
            } else {
                if (arr[i] < arr[i + 1]) {
                    swap(i, i + 1, arr);
                }
            }
            flag = !flag;
        }
    }

    public static String removeAlAdjacentDuplicates(String s) {
        String res = "";
        while (res.length() != s.length()) {
            res = s;
            s = removeDuplicates(s, s.length());
        }
        return res;
    }

    public static String removeDuplicates(String s, int n) {
        int i = 0;
        String res = "";
        while (i < n) {
            if (i < n - 1 && s.charAt(i) == s.charAt(i + 1)) {
                while (i < n - 1 && s.charAt(i) == s.charAt(i + 1)) {
                    i++;
                }
            } else {
                res = res + s.charAt(i);
            }
            i++;
        }
        return res;
    }

    public static String reverseWords(String s) {
        String res = "";
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                res = addToList(res, s, start, i);
                start = i + 1;
            } else if (i == s.length() - 1) {
                res = addToList(res, s, start, i + 1);
            }
        }
        return res;
    }

    public static String addToList(String res, String s, int start, int i) {
        if (res == "") {
            res = s.substring(start, i);
        } else {
            res = s.substring(start, i) + "." + res;
        }
        return res;
    }

    static public int minimumCharacterInFrontOfString(String s) {
        int len = s.length() - 1;
        int res = 0;
        int i = 0;
        int j = len;
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                res++;
                len = len - 1;
                j = len;
                i = 0;
            }
        }
        return res;
    }

    static int max = 0;
    static String res = "";

    public static String longestPalindromicSubString(String s) {
        for (int i = 0; i < s.length(); i++) {
            checkPalindrome(i, i, s);
            checkPalindrome(i, i + 1, s);
        }
        return res;
    }

    private static void checkPalindrome(int i, int j, String s) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            j++;
            i--;
        }
        if (j - i - 1 > max) {
            max = j - i - 1;
            res = s.substring(i + 1, j);
        }
    }

    static int maxEvenSum(int arr[], int n) {
        int pos_sum = 0;
        for (int i = 0; i < n; ++i) {
            if (arr[i] > 0) {
                pos_sum += arr[i];
            }
        }
        if (pos_sum % 2 == 0) {
            return pos_sum;
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            if (arr[i] % 2 != 0) {
                if (arr[i] > 0)
                    ans = ans > (pos_sum - arr[i]) ? ans : (pos_sum - arr[i]);
                else
                    ans = ans > (pos_sum + arr[i]) ? ans : (pos_sum + arr[i]);
            }
        }
        return ans;
    }

    public int lengthOfLongestNonRepeatingSubString(String s) {
        int left = 0;
        int right = 0;
        int res = 0;
        Set<Character> set = new HashSet<>();
        while (right < s.length()) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                res = Math.max(res, right - left + 1);
                right++;
            } else {
                set.remove(s.charAt(left));
                left++;
            }
        }
        return res;
    }

    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= first) {
                first = nums[i];
            } else if (nums[i] <= second) {
                second = nums[i];
            } else {
                return true;
            }
        }
        return false;
    }

    public List<String> phoneLetterCombinations(String digits) {
        Map<Character, String> digitToLetters = new HashMap<>();
        List<String> resultList = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return resultList;
        }
        digitToLetters.put('2', "abc");
        digitToLetters.put('3', "def");
        digitToLetters.put('4', "ghi");
        digitToLetters.put('5', "jkl");
        digitToLetters.put('6', "mno");
        digitToLetters.put('7', "pqrs");
        digitToLetters.put('8', "tuv");
        digitToLetters.put('9', "wxyz");
        generateCombinations(digits, 0, new StringBuilder(), digitToLetters, resultList);

        return resultList;
    }

    private void generateCombinations(String digits, int currentIndex, StringBuilder currentCombination, Map<Character, String> digitToLetters,
        List<String> resultList) {
        if (currentIndex == digits.length()) {
            resultList.add(currentCombination.toString());
            return;
        }
        char currentDigit = digits.charAt(currentIndex);
        String letterOptions = digitToLetters.get(currentDigit);
        if (letterOptions != null) {
            for (int i = 0; i < letterOptions.length(); i++) {
                char letter = letterOptions.charAt(i);
                currentCombination.append(letter);
                generateCombinations(digits, currentIndex + 1, currentCombination, digitToLetters, resultList);
                currentCombination.deleteCharAt(currentCombination.length() - 1);
            }
        }
    }

    static public int compareVersion(String version1, String version2) {

        String[] v_1 = version1.split("\\.");
        String[] v_2 = version2.split("\\.");
        int i = 0;
        int j = 0;
        while (i < v_1.length || j < v_2.length) {
            int v1 = i < v_1.length ? Integer.parseInt(v_1[i]) : 0;
            int v2 = j < v_2.length ? Integer.parseInt(v_2[j]) : 0;
            if (v1 != v2) {
                return v1 < v2 ? -1 : 1;
            }
            if (i < v_1.length) {
                i++;
            }
            if (j < v_2.length) {
                j++;
            }
        }
        return 0;
    }

    static public int compareVersion_b(String version1, String version2) {

        int v_1 = version1.length();
        int v_2 = version2.length();
        int i = 0;
        int j = 0;
        while (i < v_1 || j < v_2) {
            int val_a = 0;
            int val_b = 0;
            while (i < v_1 && version1.charAt(i) != '.') {
                val_a = val_a * 10 + (version1.charAt(i) - '0');
                i++;
            }

            while (j < v_2 && version2.charAt(j) != '.') {
                val_b = val_b * 10 + (version2.charAt(j) - '0');
                j++;
            }

            if (val_a != val_b) {
                return val_a < val_b ? -1 : 1;
            }
            if (i < v_1) {
                i++;
            }
            if (j < v_2) {
                j++;
            }
        }
        return 0;
    }


    static class BiPair {
        int x;
        int y;

        public BiPair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static public boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, Comparator.comparingInt(a -> a[1]));
        PriorityQueue<BiPair> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.y));
        int count = 0;
        for (int i = 0; i < trips.length; i++) {
            while (!queue.isEmpty() && queue.peek().y <= trips[i][1]) {
                BiPair biPair = queue.remove();
                count = count - biPair.x;
            }
            queue.add(new BiPair(trips[i][0], trips[i][2]));
            count = count + trips[i][0];
            if (count > capacity) {
                return false;
            }
        }
        return true;
    }

    static public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int wordLen = words[0].length();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }

        int range = s.length() - (wordLen * words.length);
        for (int i = 0; i <= range; i++) {
            Map<String, Integer> mapDuplicate = new HashMap<>(map);
            int temp = i;

            if (mapDuplicate.containsKey(s.substring(temp, temp + wordLen))) {

                while (temp <= s.length() - wordLen && mapDuplicate.containsKey(s.substring(temp, temp + wordLen))) {
                    String subString = s.substring(temp, temp + wordLen);
                    mapDuplicate.put(subString, mapDuplicate.get(subString) - 1);
                    mapDuplicate.remove(subString, 0);
                    temp = temp + wordLen;
                }
                if (mapDuplicate.isEmpty()) {
                    res.add(i);
                }
            }
        }
        return res;
    }

    static public int myAtoi(String s) {
        int len = s.length();
        int i = 0;
        boolean positive = true;
        int range = Integer.MAX_VALUE / 10;
        int res = 0;
        while (i < len && s.charAt(i) == ' ') {
            i++;
        }
        if (i < len) {
            if (s.charAt(i) == '-') {
                positive = false;
                i++;
            } else if (s.charAt(i) == '+') {
                i++;
            }
        }
        while (i < len && Character.isDigit(s.charAt(i))) {
            int temp = s.charAt(i) - '0';
            if (res > range || (res == range && temp > 7)) {
                return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = (res * 10) + temp;
            i++;
        }
        return positive ? res : -res;
    }

    // 1 2 3 4 5 6 , 3->  456
    static public int[] maxSubsequenceOfKLength(int[] nums, int k) {
        int[] temp = nums.clone();
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[k];
        int counter = 0;
        Arrays.sort(temp);
        int len = nums.length - 1;
        for (int i = 0; i < k; i++) {
            map.put(temp[len - i], map.getOrDefault(temp[len - i], 0) + 1);
        }
        for (int i = 0; i <= len && counter < k; i++) {
            if (map.containsKey(nums[i]) && map.get(nums[i]) > 0) {
                res[counter++] = nums[i];
                map.compute(nums[i], (key, value) -> value - 1);
            }
        }
        return res;
    }



    public static List<String> vanity(List<String> codes, List<String> numbers) {
        List<String> ans = new ArrayList<>(); //list of all phone numbers that match one or more vanity codes(which we have to return in this function).
        Set<String> set = new HashSet<>(); //set of these phone numbers we have to return.
        ArrayList<String> codeToNum = getVanityStrings(codes);
        //checking numbers one by one if they match any vanity code number.
        for (int i = 0; i < numbers.size(); i++) {
            String number = numbers.get(i);
            for (int j = 0; j < codeToNum.size(); j++) {
                if (number.contains(codeToNum.get(j))) {
                    set.add(number);
                    break;
                }
            }
        }
        ans.addAll(set);//transferring all set data to the arrayList.
        Collections.sort(ans);//sorting the data
        return ans; //returning the arrayList.
    }

    private static ArrayList<String> getVanityStrings(List<String> codes) {
        Map<Character, Integer> map = new HashMap<>();
        int code = 2;
        int k = 0;
        for (int i = 0; i < 26; i++) {
            char c = (char) (i + 'A');
            map.put(c, code);
            if ((k + 1) % 3 == 0)
                code += 1;
            k++;
            if (i == 16 || i == 23) {
                k--;
            }
        }

        ArrayList<String> codeToNum = new ArrayList<>();
        for (int i = 0; i < codes.size(); i++) {
            String temp = "";
            for (int j = 0; j < codes.get(i).length(); j++) {
                char c = codes.get(i).charAt(j);
                if (map.containsKey(c)) {
                    int cod = map.get(c);
                    temp += cod;
                }
            }
            if (temp != "") {
                codeToNum.add(temp);
            }
        }
        return codeToNum;
    }

    static private void swap(int i, int j, int[] nums) {
        int c = nums[i];
        nums[i] = nums[j];
        nums[j] = c;
    }

    static public String longestCommonPrefix(String[] strs) {
        String res = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].equals("") || res.equals("")) {
                return "";
            }
            while (strs[i].indexOf(res) != 0) {
                res = res.substring(0, res.length() - 1);
            }
        }
        return res;
    }

    static public int indexOfFirstOccurrence(String haystack, String needle) {
        int shortLength = needle.length();
        for (int i = 0; i < haystack.length() - shortLength + 1; i++) {
            if (haystack.substring(i, i + shortLength).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    static public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        } else if (n == 2) {
            return "11";
        }
        String word = countAndSay(n - 1);
        String res = "";
        int i = 0;
        int j = 0;
        int count = 0;
        while (j < word.length()) {
            while (j < word.length() && word.charAt(i) == word.charAt(j)) {
                count++;
                j++;
            }
            res += (count + "" + word.charAt(i));
            count = 0;
            i = j;
        }
        return res;
    }


    static public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int defaultValue = -1 * (n + 1);
        for (int i = 0; i < n; i++) {
            if (nums[i] < 1 || nums[i] > n) {
                nums[i] = 0;
            }
        }
        for (int i = 0; i < n; i++) {
            int loc = Math.abs(nums[i]) - 1;
            if (nums[i] != 0 && nums[i] != defaultValue) {
                if (nums[loc] == 0) {
                    nums[loc] = defaultValue;
                } else {
                    nums[loc] = -1 * Math.abs(nums[loc]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (nums[i - 1] >= 0) {
                return i;
            }
        }
        return n + 1;
    }

    static public int longestValidParentheses(String s) {

        int maxLength = 0;
        int opening = 0;
        int closing = 0;

        for (int i = 0; i < s.length(); i++) {
            char parenthesis = s.charAt(i);
            if (parenthesis == ')') {
                closing++;
            } else {
                opening++;
            }
            if (closing > opening) {
                opening = 0;
                closing = 0;
            } else if (closing == opening) {
                maxLength = Math.max(maxLength, 2 * opening);
            }
        }

        opening = 0;
        closing = 0;

        for (int i = s.length() - 1; i <= 0; i--) {
            char parenthesis = s.charAt(i);
            if (parenthesis == ')') {
                closing++;
            } else {
                opening++;
            }
            if (closing < opening) {
                opening = 0;
                closing = 0;
            } else if (closing == opening) {
                maxLength = Math.max(maxLength, 2 * opening);
            }
        }
        return maxLength;
    }

}

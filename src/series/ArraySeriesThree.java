package series;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class ArraySeriesThree {

    static public String minWindow(String s, String t) {
        if (t.equals("")) {
            return "";
        }
        int left = 0;
        int right = 0;
        int need = t.length();
        int have = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        Map<Character, Integer> windowMap = new HashMap<>();
        Map<Character, Integer> needMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char tChar = t.charAt(i);
            needMap.put(tChar, needMap.getOrDefault(tChar, 0) + 1);
            windowMap.put(tChar, 0);
        }

        while (right < s.length()) {
            Character charRight = s.charAt(right);
            if (needMap.containsKey(charRight)) {
                int valueNow = windowMap.get(charRight);
                windowMap.put(charRight, valueNow + 1);
                if (valueNow + 1 <= needMap.get(charRight)) {
                    have++;
                }
            }
            while (need == have) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                    end = right;
                }
                Character charLeft = s.charAt(left);
                if (windowMap.containsKey(charLeft)) {
                    int valueNow = windowMap.get(charLeft);
                    windowMap.put(charLeft, valueNow - 1);
                    if (valueNow - 1 < needMap.get(charLeft)) {
                        have--;
                    }
                }
                left++;
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, end + 1);
    }

    static int minimumOfMaximumSet(int[] space, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        int n = space.length;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (!queue.isEmpty() && queue.peek() == i - k) {
                queue.remove();
            }
            while (!queue.isEmpty() && space[queue.peekLast()] > space[i]) {
                queue.removeLast();
            }
            queue.addLast(i);
            if (i >= k - 1) {
                list.add(space[queue.peek()]);
            }
        }
        int max = Integer.MIN_VALUE;
        for (Integer integer : list) {
            max = Math.max(max, integer);
        }
        return max;
    }

    public int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }
        return stack.size();
    }


    static public String shortestPalindrome(String s) {
        int l = s.length();
        int len = l - 1;
        int left = 0;
        int right = len;
        int count = 0;
        String res = "";
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                count++;
                left = 0;
                len--;
                right = len;
            } else {
                right--;
                left++;
            }
        }
        for (int i = 1; i <= count; i++) {
            res += s.charAt(s.length() - i);
        }
        res += s;
        return res;
    }

    static public int minimizeArrayValue(int[] nums) {
        int i = 1;
        int max = nums[0];
        int sum = nums[0];
        while (i < nums.length) {
            sum += nums[i];
            int diff = (int) Math.ceil(((double) sum / (i + 1)));
            max = Math.max(diff, max);
            i++;
        }
        return max;
    }

    static public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Long> set = new TreeSet<>();
        int index = 0;
        while (index < nums.length) {
            if (index >= indexDiff + 1) {
                set.remove(1L * nums[index - indexDiff - 1]);
            }
            Long floor = set.floor(1L * (nums[index] + valueDiff));
            Long ciel = set.ceiling(1L * (nums[index] - valueDiff));
            if (floor != null && floor >= nums[index] || ciel != null && ciel <= nums[index]) {
                return true;
            }
            set.add(1L * nums[index]);
            index++;
        }
        return false;
    }

    static ArrayList<Integer> sortByFreq(int arr[], int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<>();
        PriorityQueue<Map.Entry<Integer, Integer>> queue =
            new PriorityQueue<>((a, b) -> (a.getValue() == b.getValue()) ? (b.getKey() - a.getKey()) : (b.getValue() - a.getValue()));
        for (Integer value : arr) {
            map.put(value, map.getOrDefault(value, 0) + 1);
        }


        queue.addAll(map.entrySet());
        while (!queue.isEmpty()) {
            Map.Entry<Integer, Integer> entry = queue.remove();
            for (int i = 0; i < entry.getValue(); i++) {
                res.add(entry.getKey());
            }
        }
        return res;
    }

    static public int[] frequencySort(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<>();
        PriorityQueue<Map.Entry<Integer, Integer>> queue =
            new PriorityQueue<>((a, b) -> (b.getValue() == a.getValue()) ? (b.getKey() - a.getKey()) : (a.getValue() - b.getValue()));
        for (Integer value : arr) {
            map.put(value, map.getOrDefault(value, 0) + 1);
        }

        int counter = 0;
        queue.addAll(map.entrySet());
        while (!queue.isEmpty()) {
            Map.Entry<Integer, Integer> entry = queue.remove();
            for (int i = 0; i < entry.getValue(); i++) {
                res.add(entry.getKey());
                arr[counter]=entry.getKey();
                counter++;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        frequencySort(new int[] {-1,1,-6,4,5,-6,1,4,1});
    }
}

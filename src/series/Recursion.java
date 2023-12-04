package series;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Recursion {

    public static void main(String[] args) {
        ArrayList res = new ArrayList();
        ArrayList values = new ArrayList();
        values.add(1);
        values.add(1);
        values.add(2);
        values.add(5);
        //  allPossibleSubsets(0, new ArrayList<>(), values, 3, res);
        // allPossibleUniqueSubsets(0, new int[] {1, 1, 2, 5}, new ArrayList<>(), res);
        System.out.println(generateParenthesis(3));
    }

    static void allPossibleSubsetsSum(int ind, int sum, ArrayList<Integer> arr, int n, ArrayList<Integer> res) {
        if (ind == n) {
            res.add(sum);
            return;
        }
        allPossibleSubsetsSum(ind + 1, sum + arr.get(ind), arr, arr.size(), res);
        allPossibleSubsetsSum(ind + 1, sum, arr, arr.size(), res);

    }

    static void allPossibleSubsets(int ind, ArrayList<Integer> temp, ArrayList<Integer> arr, int n, ArrayList<String> res) {
        if (ind == n) {
            res.add(temp.toString());
            return;
        }
        temp.add(arr.get(ind));
        allPossibleSubsets(ind + 1, temp, arr, arr.size(), res);
        temp.remove(temp.size() - 1);
        allPossibleSubsets(ind + 1, temp, arr, arr.size(), res);

    }

    //sort first
    public static void allPossibleUniqueSubsets(int ind, int[] nums, List<Integer> ds, List<List<Integer>> ansList) {
        ansList.add(new ArrayList<>(ds));
        for (int i = ind; i < nums.length; i++) {
            if (i == ind || nums[i] != nums[i - 1]) {
                ds.add(nums[i]);
                allPossibleUniqueSubsets(i + 1, nums, ds, ansList);
                ds.remove(ds.size() - 1);
            }
        }
    }

    public boolean printOneSubsequenceWithTargetSum(int[] nums, int index, List<Integer> ds, int sum, int k) {
        if (index == nums.length) {
            if (sum == k) {
                System.out.println(ds);
                return true;
            }
            return false;
        }
        ds.add(nums[index]);
        if (printOneSubsequenceWithTargetSum(nums, index + 1, ds, sum + nums[index], k)) {
            return true;
        }
        ds.remove(ds.size() - 1);
        return printOneSubsequenceWithTargetSum(nums, index + 1, ds, sum, k);
    }

    public static int printNumberOfSubsequenceWithSumTarget(int[] nums, int index, int sum, int target) {
        if (index == nums.length) {
            return sum == target ? 1 : 0;
        }
        int l = printNumberOfSubsequenceWithSumTarget(nums, index + 1, sum + nums[index], target);
        int r = printNumberOfSubsequenceWithSumTarget(nums, index + 1, sum, target);
        return l + r;
    }

    private void findCombinationsSumRepeatAnyTimes(int ind, int[] arr, int target, List<List<Integer>> ans, List<Integer> ds) {
        if (ind == arr.length) {
            if (target == 0) {
                ans.add(new ArrayList<>(ds));
            }
            return;
        }
        // can go to infinity if not checked for remaining values
        if (arr[ind] <= target) {
            ds.add(arr[ind]);
            findCombinationsSumRepeatAnyTimes(ind, arr, target - arr[ind], ans, ds);
            ds.remove(ds.size() - 1);
        }
        findCombinationsSumRepeatAnyTimes(ind + 1, arr, target, ans, ds);
    }

    //sort first
    static void findCombinationsSum_NonRepeatingUnique(int ind, int[] arr, int target, List<List<Integer>> ans, List<Integer> ds) {
        if (target == 0) {
            ans.add(new ArrayList<>(ds));
        }

        for (int i = ind; i < arr.length; i++) {
            if ((i == ind || arr[i] != arr[i - 1]) && (arr[ind] <= target)) {
                ds.add(arr[i]);
                findCombinationsSum_NonRepeatingUnique(i + 1, arr, target - arr[i], ans, ds);
                ds.remove(ds.size() - 1);
            }
        }
    }

    static public List<String> wordBreak(String s, List<String> wordDict) {
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, -1);
        List<String> result = new ArrayList<>();
        wordBreak(0, s, "", new HashSet<>(wordDict), result);
        return result;
    }

    static void wordBreak(int i, String s, String current, Set<String> wordDict, List<String> result) {
        if (i == s.length()) {
            result.add(current.trim());
        }

        for (String word : wordDict) {
            if (s.indexOf(word, i) == i) {
                wordBreak(i + word.length(), s, current + word + " ", wordDict, result);
            }
        }
    }

    static public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        rec(n, 0, 0, "", res);
        return res;
    }

    static public void rec(int n, int start, int end, String res, List<String> result) {
        if (start == n && end == n) {
            result.add(res);
        } else if (end > start) {
            return;
        } else if (start > n || end > n) {
            return;
        }

        rec(n, start + 1, end, res + "(", result);

        if (start > end) {
            rec(n, start, end + 1, res + ")", result);
        }
    }

}

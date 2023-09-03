package leet;

import java.util.*;

public class UniqueSubset {

    public static void fun(int[] nums, int index, List<Integer> ds, HashSet<String> res) {
        if (index == nums.length) {
            Collections.sort(ds);
            res.add(ds.toString());
            return;
        }
        ds.add(nums[index]);
        fun(nums, index + 1, ds, res);
        ds.remove(ds.size() - 1);
        fun(nums, index + 1, ds, res);
    }

    public static List<String> subsetsWithDup(int[] nums) {
        List<String> ans = new ArrayList<>();
        HashSet<String> res = new HashSet<>();
        List<Integer> ds = new ArrayList<>();
        fun(nums, 0, ds, res);
        for (String it : res) {
            ans.add(it);
        }
        return ans;
    }


    // approach best

    public static void findSubsets(int ind, int[] nums, List<Integer> ds, List<List<Integer>> ansList) {
        ansList.add(new ArrayList<>(ds));
        for (int i = ind; i < nums.length; i++) {
            if (i == ind || nums[i] != nums[i - 1]) {
                ds.add(nums[i]);
                findSubsets(i + 1, nums, ds, ansList);
                ds.remove(ds.size() - 1);
            }
        }
    }

    public List<List<Integer>> subsetsWithDup_B(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ansList = new ArrayList<>();
        findSubsets(0, nums, new ArrayList<>(), ansList);
        return ansList;
    }

}

package leet;

import java.util.*;

public class Permutations {

    public static void main(String[] args) {
        int[] array = {1, 2, 3};
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ds = new ArrayList<>(array.length);
        boolean[] freq = new boolean[array.length];
        permute(array, ds, res, freq);
        System.out.println(res);
    }


    public static void permute(int[] nums, List<Integer> ds, List<List<Integer>> res, boolean[] freq) {
        if (ds.size() == nums.length) {
            res.add(new ArrayList<>(ds));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!freq[i]) {
                ds.add(nums[i]);
                freq[i] = true;
                permute(nums, ds, res, freq);
                ds.remove(ds.size() - 1);
                freq[i] = false;
            }
        }
    }

    public static void permuteB(int[] nums, int index, List<List<Integer>> res) {
        if (index >= nums.length) {
            List<Integer> ds = new ArrayList<>(nums.length);
            for (int i = 0; i < nums.length; i++) {
                ds.add(nums[i]);
            }
            res.add(ds);
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(i, index, nums);
            permuteB(nums, index + 1, res);
            swap(i, index, nums);
        }
    }

    public static void swap(int i, int j, int[] nums) {
        int c = nums[i];
        nums[i] = nums[j];
        nums[j] = c;
    }

}

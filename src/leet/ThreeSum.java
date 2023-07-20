package leet;

import java.util.*;

// n3 -> store results in sorted hashmap to avoid duplicates
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int first = nums[i];
            HashSet<Integer> set = new HashSet<>();
            for (int j = i + 1; i < nums.length; i++) {
                int second = nums[j];
                int third = -(first + second);
                if (set.contains(third)) {
                    List<Integer> innerResult = new ArrayList<>();
                    innerResult.add(third);
                    innerResult.add(second);
                    innerResult.add(first);
                    innerResult.sort(Comparator.comparingInt(o -> o));
                    result.add(innerResult);
                }
                set.add(second);
            }
        }
        return result.stream().toList();
    }

    public List<List<Integer>> threeSumB(int[] num) {
        Arrays.sort(num);

        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < num.length - 2; i++) {

            if (i == 0 || (i > 0 && num[i] != num[i - 1])) {

                int lo = i + 1;
                int hi = num.length - 1;
                int sum = 0 - num[i];

                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        ArrayList<Integer> temp = new ArrayList<>();
                        temp.add(num[i]);
                        temp.add(num[lo]);
                        temp.add(num[hi]);
                        res.add(temp);

                        while (lo < hi && num[lo] == num[lo + 1]) lo++;
                        while (lo < hi && num[hi] == num[hi - 1]) hi--;

                        lo++;
                        hi--;
                    } else if (num[lo] + num[hi] < sum) {
                        lo++;
                    } else {
                        hi--;
                    }
                }
            }
        }
        return res;
    }

}

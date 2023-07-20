package leet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MajorityElement {
    //169 .Majority Element in an Array
    // count and scan in n2
    // memoization using hashmap
    // Moore's voting algo
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

    //229. Majority Element II
    // brute: n2 , check everyone in list. break if list size is 2
    // hashing: keep updating index with count and add on list if its 3
    public List<Integer> majorityElement_b(int[] nums) {
        int element_a = Integer.MIN_VALUE;
        int element_b = Integer.MIN_VALUE;
        int countTillNow_a = 0;
        int countTillNow_b = 0;
        int len = nums.length;
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            if (countTillNow_a == 0 && temp != element_b) {
                element_a = temp;
                countTillNow_a = 1;
            } else if (countTillNow_b == 0 && temp != element_a) {
                element_b = temp;
                countTillNow_b = 1;
            } else if (element_a == temp) {
                countTillNow_a++;
            } else if (element_b == temp) {
                countTillNow_b++;
            } else {
                countTillNow_a--;
                countTillNow_b--;
            }
        }
        int count_a = 0;
        int count_b = 0;
        for (int i = 0; i < len; i++) {
            if (element_a == nums[i]) {
                count_a++;
            } else if (element_b == nums[i]) {
                count_b++;
            }
        }

        List<Integer> result = new ArrayList<>(2);
        int mini = (len / 3) + 1;
        if (count_a >= mini) {
            result.add(element_a);
        }
        if (count_b >= mini) {
            result.add(element_b);
        }
        result.sort(Comparator.comparingInt(o -> o));
        return result;
    }
}

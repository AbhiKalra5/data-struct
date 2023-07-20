package leet;

// 53. Maximum Subarray
public class MaximumSumSubArray {

    public static void main(String[] args) {
        int[] a = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        maxSubArray(a);
    }

    public static int maxSubArray(int[] nums) {
        int sum;
        int max = sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(nums[i], sum + nums[i]);
            max = Math.max(sum, max);
        }
        return max;
    }

}

package leet;

public class NextPermutation {
    public static void main(String[] args) {
        nextPermutation(new int[]{1, 2, 3});
    }
    // point where a[i]<a[i+1]
    // find next largest to a[i]-> loop backwards find first biggest
    // exchange a[i] with its next greatest
    // reverse the rest so its increasing form

    public static void nextPermutation(int[] nums) {
        int index = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            reverseArray(nums, 0, nums.length - 1);
            return;
        }

        for (int i = nums.length - 1; i >= index; i--) {
            if (nums[i] > nums[index]) {
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
                break;
            }
        }

        reverseArray(nums, index + 1, nums.length - 1);

    }


    static void reverseArray(int[] array, int start, int end) {
        int temp = 0;
        while (start <= end) {
            temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }
}

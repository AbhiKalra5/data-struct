package leet;

//75. Sort Colors
// sort with  nlogn
// modify array based on frequency of 0,1,2
// DNF
public class SortColor {
    public void sortColors(int[] nums) {
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

    public static void swap(int i, int j, int[] nums) {
        int c = nums[i];
        nums[i] = nums[j];
        nums[j] = c;
    }
}

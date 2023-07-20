package leet;

//45
public class JumpGameB {

    public int jump(int[] nums) {
        int l = 0, r = 0;
        int result = 0;

        while (r < nums.length - 1) {
            int largest = 0;
            for (int i = l; i <= r; i++) {
                largest = Math.max(largest, i + nums[i]);
            }
            l = r + 1;
            r = largest;
            result += 1;
        }
        return result;
    }
}

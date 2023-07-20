package leet;

//55
public class JumpGame {
    public static void main(String[] args) {

    }
    public boolean canJump(int[] nums) {
        int last = nums.length - 1;
        for (int i = last; i >= 0; i--) {
            if (i + nums[i] >= last) {
                last = i;
            }
        }
        return last == 0;
    }
}

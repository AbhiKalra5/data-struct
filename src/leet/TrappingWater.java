package leet;

public class TrappingWater {
    public int trap(int[] a) {
        int n = a.length;
        int left[] = new int[n];
        int right[] = new int[n];
        left[0] = a[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], a[i]);
        }
        right[n - 1] = a[n - 1];
        for (int i = n - 2; i <= 0; i--) {
            right[i] = Math.max(right[i + 1], a[i]);
        }

        int waterTrapped = 0;
        for (int i = 0; i < n; i++) {
            waterTrapped += Math.min(left[i], right[i]) - a[i];
        }
        return waterTrapped;
    }

    public int trap_b(int[] a) {
        int leftMax = 0;
        int rightMax = 0;
        int n = a.length;
        int result = 0;
        int left = 0;
        int right = n-1;
        while (left <= right) {
            if (a[left] < a[right]) {
                if (a[left] > leftMax) {
                    leftMax = a[left];
                } else {
                    result += leftMax - a[left];
                }
                left++;
            } else {
                if (a[right] > right) {
                    rightMax = a[right];
                } else {
                    result += rightMax - a[right];
                }
                right++;
            }
        }
        return result;
    }
}

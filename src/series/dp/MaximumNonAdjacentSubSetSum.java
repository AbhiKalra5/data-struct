package series.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximumNonAdjacentSubSetSum {

    public static void main(String[] args) {
        int[] res = new int[5];
        Arrays.fill(res, -1);
        System.out.println(maximumNonAdjacentArray_space(Arrays.asList(2, 7, 9, 3, 1)));
        // System.out.println(maximumNonAdjacentArray_mem(new int[] {2, 7, 9, 3, 1}, 4, res));
    }

    public static int maximumNonAdjacentArray_mem(int[] array, int index, int[] mpp) {
        if (index == -1) {
            return 0;
        } else if (index == 0) {
            return array[0];
        } else if (mpp[index] != -1) {
            return mpp[index];
        }

        int took = array[index] + maximumNonAdjacentArray_mem(array, index - 2, mpp);
        int notTook = 0 + maximumNonAdjacentArray_mem(array, index - 1, mpp);

        return mpp[index] = Math.max(took, notTook);
    }


    public int maximumNonAdjacentArray_tab(int[] array) {
        int n = array.length;
        int[] dp = new int[n];
        dp[0] = array[0];
        dp[1] = Math.max(array[0], array[1]);
        for (int i = 2; i < n; i++) {
            int took = array[i] + dp[i - 2];
            int notTook = 0 + dp[i - 1];
            dp[i] = Math.max(took, notTook);
        }
        return dp[n - 1];
    }

    static long maximumNonAdjacentArray_space(List<Integer> arr) {
        int n = arr.size();
        long prev = arr.get(0);
        long prev2 = Math.max(arr.get(0), 0 + arr.get(1));

        for (int i = 2; i < n; i++) {
            long pick = arr.get(i) + prev;
            long nonPick = 0 + prev2;

            long cur_i = Math.max(pick, nonPick);
            prev = prev2;
            prev2 = cur_i;

        }
        return prev2;
    }

    long robStreet(int n, ArrayList<Integer> arr) {
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();
        if (n == 1)
            return arr.get(0);
        for (int i = 0; i < n; i++) {
            if (i != 0)
                arr1.add(arr.get(i));
            if (i != n - 1)
                arr2.add(arr.get(i));
        }

        long ans1 = maximumNonAdjacentArray_space(arr1);
        long ans2 = maximumNonAdjacentArray_space(arr2);

        return Math.max(ans1, ans2);
    }
}

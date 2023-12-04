package leet;

import java.util.HashMap;
import java.util.Map;

public class NumberOfConsecutiveSubArrayWithKXOR {

    public static int subArraysWithXorK(int[] a, int k) {
        int n = a.length; //size of the given array.
        int xr = 0;
        Map<Integer, Integer> mpp = new HashMap<>();
        mpp.put(xr, 1);
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            xr = xr ^ a[i];
            int x = xr ^ k;
            cnt += mpp.getOrDefault(x, 0);
            mpp.put(xr, mpp.getOrDefault(xr, 0) + 1);
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] a = {4, 2, 2, 6, 4};
        int k = 6;
        int ans = subArraysWithXorK(a, k);
        System.out.println("The number of subarrays with XOR k is: " + ans);
    }


}

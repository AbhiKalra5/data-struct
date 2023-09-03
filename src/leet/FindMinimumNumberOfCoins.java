package leet;

import java.util.ArrayList;

public class FindMinimumNumberOfCoins {

    public void findCoins(int[] coins, int target) {
        int n = coins.length;
        int i = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        while (i < n) {
            int temp = coins[i];
            if (temp <= target) {
                target -= temp;
                ans.add(temp);
            } else {
                i++;
            }
        }
    }
}

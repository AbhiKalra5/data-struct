package leet;

import java.util.ArrayList;

public class FindMinimumNumberOfCoins {

    public void findCoins(int[] coins, int target) {
        int n = coins.length;
        ArrayList < Integer > ans = new ArrayList < > ();
        for (int i = n - 1; i >= 0; i--) {
            while (target>= coins[i]) {
                target -= coins[i];
                ans.add(coins[i]);
            }
        }
    }
}

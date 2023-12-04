package series.dp.stocks;

public class StockA {
    int maximumProfit(int[] arr) {
        // Write your code here.
        int maxProfit = 0;
        int mini = arr[0];

        for (int i = 1; i < arr.length; i++) {
            int curProfit = arr[i] - mini;
            maxProfit = Math.max(maxProfit, curProfit);
            mini = Math.min(mini, arr[i]);
        }
        return maxProfit;
    }
}

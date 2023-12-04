package series.dp.stocks;

// buy sell any times, sell should happen before buying
public class StockB {

    public int buySellStocks_mem(int[] arr, int i, int buy, int[][] dp) {
        if (i == arr.length) {
            return 0;
        } else if (dp[i][buy] != -1) {
            return dp[i][buy];
        }
        int profit;
        if (buy == 0) {
            int take = buySellStocks_mem(arr, i + 1, 1, dp) - arr[i]; // Stocks bought at a price
            int notTake = buySellStocks_mem(arr, i + 1, 0, dp) - 0;  // no Money lost
            profit = Math.max(take, notTake);
        } else {
            int take = buySellStocks_mem(arr, i + 1, 0, dp) + arr[i]; // Stocks sold at a price
            int notTake = buySellStocks_mem(arr, i + 1, 1, dp) + 0;  // no Money gained
            profit = Math.max(take, notTake);
        }
        return dp[i][buy] = profit;
    }

    public int buySellStocks_tab(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][3];

        int profit;
        for (int i = n - 1; i >= 0; i--) {
            for (int buy = 1; buy >= 0; buy--) {
                if (buy == 0) {
                    int take = dp[i + 1][1] - prices[i]; // Stocks bought at a price
                    int notTake = dp[i + 1][0] - 0;         // no Money lost
                    profit = Math.max(take, notTake);
                } else {
                    int take = dp[i + 1][0] + prices[i]; // Stocks sold at a price
                    int notTake = dp[i + 1][1] + 0;  // no Money gained
                    profit = Math.max(take, notTake);
                }

                dp[i][buy] = profit;
            }
        }
        return dp[0][0]; // only on buy level
    }


    public int buySellStocks_space(int[] prices) {
        int n = prices.length;
        int[] prev = new int[2];

        int profit;
        for (int i = n - 1; i >= 0; i--) {
            for (int buy = 1; buy >= 0; buy--) {
                if (buy == 0) {
                    int take = prev[1] - prices[i]; // Stocks bought at a price
                    int notTake =prev[0] - 0;         // no Money lost
                    profit = Math.max(take, notTake);
                } else {
                    int take =prev[0] + prices[i]; // Stocks sold at a price
                    int notTake = prev[1] + 0;  // no Money gained
                    profit = Math.max(take, notTake);
                }

                prev[buy] = profit;
            }
        }
        return prev[0]; // only on buy level
    }



}

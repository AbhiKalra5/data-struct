package series.dp.stocks;

public class StockE {

    public int buySellStocks_mem(int[] arr, int i, int buy, int[][] dp) {
        int transactionCost = 5;
        if (i == arr.length) {
            return 0;
        } else if (dp[i][buy] != -1) {
            return dp[i][buy];
        }
        int profit;
        if (buy == 0) {
            int take = buySellStocks_mem(arr, i + 1, 1, dp) - arr[i]; // Stocks bought at a price
            int notTake = buySellStocks_mem(arr, i + 1, 0, dp);  // no Money lost
            profit = Math.max(take, notTake);
        } else {
            int take = buySellStocks_mem(arr, i + 2, 0, dp) + arr[i] - transactionCost; // Stocks sold at a price
            int notTake = buySellStocks_mem(arr, i + 1, 1, dp) + 0;  // no Money gained
            profit = Math.max(take, notTake);
        }
        return dp[i][buy] = profit;
    }

    int buySellStocks_tab(int[] prices) {
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
                    int take = dp[i + 2][0] + prices[i]; // Stocks sold at a price
                    int notTake = dp[i + 1][1] + 0;  // no Money gained
                    profit = Math.max(take, notTake);
                }

                dp[i][buy] = profit;
            }
        }
        return dp[0][0]; // only on buy level
    }

    public int maxProfit(int[] prices, int fee) {
        int[][] dp = new int[prices.length + 1][3];
        for (int i = 0; i <= prices.length; i++) {
            for (int j = 0; j <= 2; j++) {
                dp[i][j] = -1;
            }
        }
        return buySellStocks(prices, 0, 0, fee, dp);
    }

    public int buySellStocks(int[] arr, int i, int buy, int fee, int[][] dp) {
        if (i == arr.length) {
            return 0;
        } else if (dp[i][buy] != -1) {
            return dp[i][buy];
        }

        if (buy == 0) {
            int take = buySellStocks(arr, i + 1, 1, fee, dp) - arr[i];
            int notTake = buySellStocks(arr, i + 1, 0, fee, dp);
            dp[i][buy] = Math.max(take, notTake);
        } else {
            int take = buySellStocks(arr, i + 1, 0, fee, dp) + arr[i] - fee;
            int notTake = buySellStocks(arr, i + 1, 1, fee, dp);
            dp[i][buy] = Math.max(take, notTake);
        }
        return dp[i][buy];
    }



}

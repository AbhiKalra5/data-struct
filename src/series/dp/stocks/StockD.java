package series.dp.stocks;

public class StockD {


    public int buySellStocks_mem(int[] arr, int i, int transaction, int k, int[][] dp) {
        if (transaction == k * 2 || i == arr.length) { // B S B S
            return 0;
        } else if (dp[i][transaction] != -1) {
            return dp[i][transaction];
        }
        int profit;
        if (transaction % 2 == 0) {
            int buy = buySellStocks_mem(arr, i + 1, transaction + 1, k, dp) - arr[i]; // Stocks bought at a price
            int notBuy = buySellStocks_mem(arr, i + 1, transaction, k, dp) - 0;  // no Money lost
            profit = Math.max(buy, notBuy);
        } else {
            int sell = buySellStocks_mem(arr, i + 1, transaction + 1, k, dp) + arr[i]; // Stocks sold at a price
            int notSell = buySellStocks_mem(arr, i + 1, transaction, k, dp) + 0;  // no Money gained
            profit = Math.max(sell, notSell);
        }
        return dp[i][transaction] = profit;
    }

    public int buySellStocks_tab(int[] prices, int k) {
        int n = prices.length;
        k = 2 * k;
        // Creating a 2d dp array of size [n+1][2][3] initialized to 0
        int[][] dp = new int[n + 1][k + 1];

        for (int i = n - 1; i >= 0; i--) {
            int profit;
            for (int transaction = k - 1; transaction >= 0; transaction--) {
                if (transaction % 2 == 0) {
                    int buy = dp[i + 1][transaction + 1] - prices[i]; // Stocks bought at a price
                    int notBuy = dp[i + 1][transaction] - 0;  // no Money lost
                    profit = Math.max(buy, notBuy);
                } else {
                    int sell = dp[i + 1][transaction + 1] + prices[i]; // Stocks sold at a price
                    int notSell = dp[i + 1][transaction] + 0;  // no Money gained
                    profit = Math.max(sell, notSell);
                }
                dp[i][transaction] = profit;
            }
        }
        return dp[0][0];
    }

    public int buySellStocks_space(int[] prices, int k, int fee) {
        int n = prices.length;
        k = 2 * k;
        // Creating a 2d dp array of size [n+1][2][3] initialized to 0
        int[] ahead = new int[k + 1];
        int[] curr = new int[k + 1];

        for (int i = n - 1; i >= 0; i--) {
            int profit;
            for (int transaction = k - 1; transaction >= 0; transaction--) {
                if (transaction % 2 == 0) {
                    int buy = ahead[transaction + 1] - prices[i]; // Stocks bought at a price
                    int notBuy = ahead[transaction] - 0;  // no Money lost
                    profit = Math.max(buy, notBuy);
                } else {
                    int sell = ahead[transaction + 1] - fee + prices[i]; // Stocks sold at a price
                    int notSell = ahead[transaction] + 0;  // no Money gained
                    profit = Math.max(sell, notSell);
                }
                curr[transaction] = profit;
            }
            ahead = curr.clone();

        }
        return ahead[0];
    }
}

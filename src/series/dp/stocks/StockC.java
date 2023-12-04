package series.dp.stocks;

public class StockC {

    public int buySellStocks_mem(int[] arr, int i, int buy, int count, int[][][] dp) {
        if (count == 0 || i == arr.length) {
            return 0;
        } else if (dp[i][buy][count] != -1) {
            return dp[i][buy][count];
        }
        int profit;
        if (buy == 0) {
            int take = buySellStocks_mem(arr, i + 1, 1, count, dp) - arr[i]; // Stocks bought at a price
            int notTake = buySellStocks_mem(arr, i + 1, 0, count, dp) - 0;  // no Money lost
            profit = Math.max(take, notTake);
        } else {
            int take = buySellStocks_mem(arr, i + 1, 0, count - 1, dp) + arr[i]; // Stocks sold at a price
            int notTake = buySellStocks_mem(arr, i + 1, 1, count, dp) + 0;  // no Money gained
            profit = Math.max(take, notTake);
        }
        return dp[i][buy][count] = profit;
    }

    public int buySellStocks_mem(int[] arr, int k) {
        int n = arr.length;

        // Creating a 3D dp array of size [n+1][2][k+1] initialized to 0
        int[][][] dp = new int[n + 1][2][k + 1];

        // Loop through the dp array, starting from the second last stock (ind=n-1)
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= k; cap++) {
                    if (buy == 0) { // We can buy the stock
                        dp[ind][buy][cap] = Math.max(0 + dp[ind + 1][0][cap], dp[ind + 1][1][cap] - arr[ind]);
                    }
                    if (buy == 1) { // We can sell the stock
                        dp[ind][buy][cap] = Math.max(0 + dp[ind + 1][1][cap], arr[ind] + dp[ind + 1][0][cap - 1]);
                    }
                }
            }
        }
        // The maximum profit with 2 transactions is stored in dp[0][0][2]
        return dp[0][0][k];
    }

    public int buySellStocks_space(int[] arr) {
        int n = arr.length;

        // Creating a 3D dp array of size [n+1][2][3] initialized to 0
        int[][] ahead = new int[2][3];
        int[][] curr = new int[2][3];

        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) {
                    if (buy == 0) { // We can buy the stock
                        curr[buy][cap] = Math.max(0 + ahead[0][cap], ahead[1][cap] - arr[ind]);
                    }
                    if (buy == 1) { // We can sell the stock
                        curr[buy][cap] = Math.max(0 + ahead[1][cap], arr[ind] + ahead[0][cap - 1]);
                    }
                }
            }
        }
        return ahead[0][2];
    }

    public int buySellStocks_mem_eff(int[] arr, int i, int transaction, int[][] dp) {
        if (transaction == 4 || i == arr.length) { // B S B S
            return 0;
        } else if (dp[i][transaction] != -1) {
            return dp[i][transaction];
        }
        int profit;
        if (transaction % 2 == 0) {
            int buy = buySellStocks_mem_eff(arr, i + 1, transaction + 1, dp) - arr[i]; // Stocks bought at a price
            int notBuy = buySellStocks_mem_eff(arr, i + 1, transaction, dp) - 0;  // no Money lost
            profit = Math.max(buy, notBuy);
        } else {
            int sell = buySellStocks_mem_eff(arr, i + 1, transaction + 1, dp) + arr[i]; // Stocks sold at a price
            int notSell = buySellStocks_mem_eff(arr, i + 1, transaction, dp) + 0;  // no Money gained
            profit = Math.max(sell, notSell);
        }
        return dp[i][transaction] = profit;
    }

    public int buySellStocks_tab_eff(int[] arr) {
        int n = arr.length;

        // Creating a 2d dp array of size [n+1][2][3] initialized to 0
        int[][] dp = new int[n + 1][5];

        for (int i = n - 1; i >= 0; i--) {
            int profit;
            for (int transaction = 3; transaction >= 0; transaction--) {
                if (transaction % 2 == 0) {
                    int buy = dp[i + 1][transaction + 1] - arr[i]; // Stocks bought at a price
                    int notBuy = dp[i + 1][transaction]  - 0;  // no Money lost
                    profit = Math.max(buy, notBuy);
                } else {
                    int sell = dp[i + 1][transaction + 1] + arr[i]; // Stocks sold at a price
                    int notSell = dp[i + 1][transaction] + 0;  // no Money gained
                    profit = Math.max(sell, notSell);
                }
                dp[i][transaction] = profit;
            }
        }
        return dp[0][0];
    }
}

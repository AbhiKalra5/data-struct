package leet;

//121
public class MaxProfit {
    public int maxProfit(int[] prices) {
        int profit = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            profit = Math.max(profit, prices[i] - min);
            min = Math.min(prices[i], min);
        }
        return profit;
    }
}

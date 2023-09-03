package leet;

import java.util.Arrays;

public class FractionalKnapsackProblem {
    double fractionalKnapsack(int weight, Item arr[], int n) {
        Arrays.sort(arr, (a, b) -> (b.value / b.weight) - (a.value / a.weight));
        int weightTillNow = 0;
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            Item temp = arr[i];
            if (temp.weight + weightTillNow < weight) {
                weightTillNow += temp.weight;
                res += temp.value;
            } else {
                res += ((temp.value / temp.weight) * (weight - temp.weight));
                break;
            }
        }
        return res;
    }


}

class Item {
    int value, weight;

    Item(int x, int y) {
        this.value = x;
        this.weight = y;
    }
}
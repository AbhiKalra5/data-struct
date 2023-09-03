package leet;

import java.util.ArrayList;
import java.util.Collections;

public class SubSetSum {
    ArrayList<Integer> subsetSums(ArrayList<Integer> arr) {
        ArrayList<Integer> sumSubset = new ArrayList<>();
        subsetSumsHelper(0, 0, arr, arr.size(), sumSubset);
        Collections.sort(sumSubset);
        return sumSubset;
    }

    void subsetSumsHelper(int ind, int sum, ArrayList<Integer> arr, int n, ArrayList<Integer> res) {
        if (ind == n) {
            res.add(sum);
            return;
        }
        subsetSumsHelper(ind + 1, sum + arr.get(ind), arr, arr.size(), res);
        subsetSumsHelper(ind + 1, sum, arr, arr.size(), res);

    }
}

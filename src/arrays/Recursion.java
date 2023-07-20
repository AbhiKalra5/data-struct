package arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Recursion {
    public static void main(String[] args) {
        int[] array = {12, 1, 61, 5, 9, 2};
        findSubsetSum(array, 0, 24, 0, "").forEach(o -> System.out.println(o));
    }

    private static int[] testRec(int[] array, int idx, int data, int resIndex) {
        if (idx == array.length) {
            return new int[resIndex];
        }

        int[] res = testRec(array, idx + 1, data, (data == array[idx] ? resIndex + 1 : resIndex));
        if (data == array[idx]) {
            res[resIndex] = idx;
        }
        return res;
    }

    private static ArrayList<String> charactersPressed(String str, String[] codes) {
        if (str.length() == 0) {
            ArrayList<String> list = new ArrayList<>();
            list.add(" ");
            return list;
        }
        String characters = codes[str.charAt(0) - '0'];

        ArrayList<String> result = charactersPressed(str.substring(1), codes);
        ArrayList<String> endResult = new ArrayList<>();
        for (String value : result) {
            for (int i = 0; i < characters.length(); i++) {
                endResult.add(characters.charAt(i) + value);
            }
        }
        return endResult;
    }

    private static List<String> findSubsetSum(int[] arr, int i, int target, int sum, String set) {
        if (i == arr.length) {
            List<String> newList = new ArrayList<>();
            if (target == sum) {
                newList.add(set);
            }
            return newList;
        }
        List<String> sets = findSubsetSum(arr, i + 1, target, sum + arr[i], (set.isBlank() ? arr[i] + "" : set + " + " + arr[i]));
        sets.addAll(findSubsetSum(arr, i + 1, target, sum, set));
        return sets;
    }

}

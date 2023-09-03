package leet;

import java.util.ArrayList;
import java.util.List;

public class KthPermutation {
    public static void main(String[] args) {
        System.out.println(findKthValue(4, 17));
    }

    // fact
    // k/fac
    // fetch value
    // break if found
    // next pair k = k%fact
    // fact =
    public static String findKthValue(int n, int k) {
        String answer = "";
        int fact = 1;
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            fact = fact * i;
            numbers.add(i);
        }
        numbers.add(n);
        k = k - 1;
        while (true) {
            int temp = k / fact;
            answer = answer + numbers.get(temp);
            numbers.remove(temp);
            if (numbers.size() == 0) {
                break;
            }
            k = k % fact;
            fact = fact / numbers.size();
         }
        return answer;
    }
}

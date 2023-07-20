package stacks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Stream;

public class MergeOverlappingIntervals {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        int n = Integer.parseInt(br.readLine());
        Pair[] pairArray = new Pair[n];

        for (int i = 0; i < n; i++) {
            final Pair pair = new Pair();
            System.out.println("First Value of Pair");
            pair.setStart(Integer.parseInt(br.readLine()));
            System.out.println("Second Value of Pair");
            pair.setEnd(Integer.parseInt(br.readLine()));
            pairArray[i] = pair;
        }
        Arrays.sort(pairArray, (a, b) -> a.getStart() != b.getStart() ? a.getStart() - b.getStart() : a.getEnd() - b.getEnd());
        merge(pairArray, n);
    }

    public static void merge(Pair[] pairs, int n) {
        Stack<Pair> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                stack.push(pairs[i]);
            } else {
                Pair pair = stack.peek();
                if (pair.getEnd() < pairs[i].getStart()) {
                    stack.push(pairs[i]);
                } else {
                    pair.setEnd(Math.max(pairs[i].getEnd(), pair.getEnd()));
                }
            }
        }

        while (stack.size() > 0) {
            Pair pair = stack.pop();
            System.out.println(pair.getStart() + " -" + pair.getEnd());
        }
    }

}

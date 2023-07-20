package stacks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class NextGreaterElementAlternate {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        int n = Integer.parseInt(br.readLine());
        int nge[] = new int[n];
        int a[] = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < n - 1; i++) {
            while (stack.size() > 0 && a[i] > a[stack.peek()]) {
                int pos = stack.pop();
                nge[pos] = a[i];
            }
            stack.push(i);
        }

        while (stack.size() > 0) {
            int pos = stack.pop();
            nge[pos] = -1;
        }

        for (int i = 0; i < nge.length; i++) {
            System.out.println(a[i] + " -> " + nge[i]);
        }
    }
}

package stacks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class SlidingWindowProblem {

    public static void main(String[] args) throws IOException {
        BufferedReader bur = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        int n = Integer.parseInt(bur.readLine());
        int nge[] = new int[n];
        int a[] = new int[n];


        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(bur.readLine());
        }

        int slide = Integer.parseInt(bur.readLine());

        for (int i = n - 1; i >= 0; i++) {
            while (stack.size() > 0 && a[i] > a[stack.peek()]) {
                stack.pop();
            }
            if (stack.empty()) {
                nge[i] = n;
            } else {
                nge[i] = stack.peek();
            }
            stack.push(i);
        }

        for (int i = 0; i <= n - slide; i++) {
            int j = i;
            while (nge[j] < i + slide) {
                j = nge[j];
            }
            System.out.println(a[j]);
        }
    }
}

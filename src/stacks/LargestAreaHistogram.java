package stacks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class LargestAreaHistogram {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = 7;
        int[] a = {6,2,5,4,5,1,6};

        int[] rb = nextLessOnRight(n, a);
        int[] lb = nextLessOnLeft(n, a);
        int maxarea = 0;

        for (int i = 0; i < n; i++) {
            int width = rb[i] - lb[i] - 1;
            int area = width * a[i];
            maxarea = area > maxarea ? area : maxarea;
        }

        System.out.println(maxarea);
    }

    private static int[] nextLessOnRight(int n, int a[]) {
        Stack<Integer> stack = new Stack<>();
        int nge[] = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (stack.size() > 0 && a[i] < a[stack.peek()]) {
                stack.pop();
            }
            if (stack.empty()) {
                nge[i] = n;
            } else {
                nge[i] = stack.peek();
            }
            stack.push(i);
        }
        return nge;
    }

    private static int[] nextLessOnLeft(int n, int a[]) {
        {
            Stack<Integer> stack = new Stack<>();
            int nge[] = new int[n];
            for (int i = 0; i <= n - 1; i++) {
                while (stack.size() > 0 && a[i] < a[stack.peek()]) {
                    stack.pop();
                }
                if (stack.empty()) {
                    nge[i] = -1;
                } else {
                    nge[i] = stack.peek();
                }
                stack.push(i);
            }
            return nge;
        }


    }

}

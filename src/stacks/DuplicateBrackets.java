package stacks;

import java.util.Scanner;
import java.util.Stack;

public class DuplicateBrackets {

    public static void main(String[] args) {
        String term = new Scanner(System.in).nextLine();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < term.length(); i++) {
            char ch = term.charAt(i);

            if (ch == ')') {
                if (stack.peek() == '(') {
                    System.out.println("Duplicate found");
                    break;
                } else {
                    while (stack.peek() != '(') {
                        stack.pop();
                    }
                    stack.pop();
                }
            } else {
                stack.push(ch);
            }
        }
        System.out.println("Duplicate Not found");
    }
}

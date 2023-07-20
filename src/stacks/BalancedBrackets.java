package stacks;

import java.util.Scanner;
import java.util.Stack;

public class BalancedBrackets {

    public static void main(String[] args) {
        String term = new Scanner(System.in).nextLine();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < term.length(); i++) {
            char ch = term.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
                continue;
            }

            if (stack.empty()) {
                System.out.println("Unbalanced");
                break;
            }

            char check;
            switch (ch) {
                case ')':
                    check = stack.pop();
                    if (check != '(')
                        System.out.println("Unbalanced");
                    break;

                case '}':
                    check = stack.pop();
                    if (check != '{')
                        System.out.println("Unbalanced");
                    break;

                case ']':
                    check = stack.pop();
                    if (check != '[')
                        System.out.println("Unbalanced");
                    break;
            }
        }
        if (!stack.empty()) {
            System.out.println("Unbalanced");
        } else {
            System.out.println("Balanced");
        }


    }
}

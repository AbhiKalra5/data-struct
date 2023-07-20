package tree;

import java.util.*;

public class TreeAlternate {
    Node head;
    int size = 0;
    int min = Integer.MIN_VALUE;
    int max = Integer.MAX_VALUE;
    int height = Integer.MIN_VALUE;

    int maxSum = Integer.MIN_VALUE;
    int maxNodeSum = Integer.MIN_VALUE;

    public void constructTree(int[] arr) {
        Stack<Node> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];
            Node newNode = new Node(value);
            if (stack.size() == 0) {
                head = newNode;
                stack.push(newNode);
            } else if (value == -1) {
                stack.pop();
            } else {
                stack.peek().children.add(newNode);
                stack.push(newNode);
            }
        }
    }


    public void display(Node node) {
        String str = node.data + "-> ";
        for (Node child : node.children) {
            str += child.data + " , ";
        }
        System.out.println(str);
        for (Node child : node.children) {
            display(child);
        }

    }

    public void multiSolver(Node node, int depth) {
        size++;
        min = Math.min(node.data, min);
        max = Math.max(node.data, max);
        height = Math.max(depth, height);
        for (Node child : node.children) {
            multiSolver(child, depth + 1);
        }
    }

    public int nodesWithMaxSumSubtree(Node node) {
        int sum = node.data;
        for (Node child : node.children) {
            sum += nodesWithMaxSumSubtree(child);
        }

        if (sum > maxSum) {
            max = sum;
            maxNodeSum = node.data;
        }

        return sum;
    }

}

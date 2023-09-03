package tree;

import java.util.*;

public class Tree {
    Node head;
    Node predecessor;
    Node successor;
    int state = 0;
    int ceil = Integer.MAX_VALUE;
    int floor = Integer.MIN_VALUE;

    int dia = 0;

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

    public void displayTree() {
        display(head);
    }

    private void display(Node node) {
        String str = node.data + "-> ";
        for (Node child : node.children) {
            str += child.data + " , ";
        }
        System.out.println(str);
        for (Node child : node.children) {
            display(child);
        }

    }

    public int size() {
        return fetchSize(head);
    }

    private int fetchSize(Node node) {
        int size = 0;
        for (Node child : node.children) {
            size += fetchSize(child);
        }
        return size + 1;
    }

    public int max() {
        return fetchMax(head);
    }

    private int fetchMax(Node node) {
        int childMax = node.data;
        for (Node child : node.children) {
            childMax = Math.max(childMax, fetchMax(child));
        }
        return childMax;
    }

    public int height(Node node) {
        int height = -1;
        for (Node child : node.children) {
            height = Math.max(height, height(child));
        }
        return height + 1;
    }


    public void displayPre() {
        traversal(head, true);
    }

    public void displayPost() {
        traversal(head, false);
    }

    private void traversal(Node node, Boolean pre) {
        if (pre) {
            System.out.println("Node Pre" + node.data);
        }
        for (Node child : node.children) {
            if (pre) {
                System.out.println("Edge Pre" + node.data + " --> " + child.data);
            }
            traversal(child, pre);
            if (!pre) {
                System.out.println("Edge Post" + node.data + " --> " + child.data);
            }
        }
        if (!pre) {
            System.out.println("Node Post" + node.data);
        }
    }

    public void traversal() {
        Node temp = head;
        Queue<Node> q = new ArrayDeque<>();
        q.add(temp);
        while (q.size() > 0) {
            Node node = q.remove();
            System.out.println(node.data);
            q.addAll(node.children);
        }
    }

    public void traversalLevelWise() {
        Node temp = head;
        Queue<Node> main = new ArrayDeque<>();
        Queue<Node> child = new ArrayDeque<>();
        main.add(temp);
        while (main.size() > 0) {
            Node node = main.remove();
            System.out.print(" " + node.data);
            child.addAll(node.children);
            if (main.size() == 0) {
                System.out.println();
                main = child;
                child = new ArrayDeque<>();
            }
        }
    }

    public void traversalLevelWiseApproachB() {
        Node temp = head;
        Queue<Node> main = new ArrayDeque<>();
        main.add(temp);
        main.add(new Node(-1));
        while (main.size() > 0) {
            Node node = main.remove();
            if (node.data >= 0) {
                System.out.print(" " + node.data);
                main.addAll(node.children);
            } else {
                System.out.println();
                if (main.size() > 0) {
                    main.add(node);
                }
            }
        }
    }

    public void traversalLevelWiseZZ() {
        boolean ltr = true;
        Node temp = head;
        Stack<Node> main = new Stack<>();
        Stack<Node> child = new Stack<>();
        main.add(temp);
        while (main.size() > 0) {
            Node node = main.pop();
            System.out.print(" " + node.data);
            ArrayList<Node> childList = node.children;
            if (ltr) {
                child.addAll(childList);
            } else {
                for (int i = childList.size() - 1; i >= 0; i--) {
                    child.add(childList.get(i));
                }
            }

            if (main.size() == 0) {
                System.out.println();
                main = child;
                child = new Stack<>();
                ltr = !ltr;
            }
        }
    }


    public void mirrorTree(Node node) {
        for (Node n : node.children) {
            mirrorTree(n);
        }
        Collections.reverse(node.children);
    }


    public void removeLeaf(Node node) {
        ArrayList<Node> nodeChildren = node.children;
        for (int i = nodeChildren.size() - 1; i >= 0; i--) {
            Node child = nodeChildren.get(i);
            if (child.children.size() == 0) {
                nodeChildren.remove(i);
            }
        }
        for (Node n : nodeChildren) {
            removeLeaf(n);
        }
    }


    public Node linearEfficient(Node node) {
        if (node.children.size() == 0) {
            return node;
        }
        Node lastTail = linearEfficient(node.children.get(node.children.size() - 1));
        while (node.children.size() > 1) {
            Node last = node.children.remove(node.children.size() - 1);
            Node slast = node.children.get(node.children.size() - 1);
            Node slastTail = linearEfficient(slast);
            slastTail.children.add(last);
        }
        return lastTail;
    }

    private void linear(Node node) {
        for (Node n : node.children) {
            linear(n);
        }
        while (node.children.size() > 1) {
            Node last = node.children.get(node.children.size() - 1);
            Node secondLast = node.children.get(node.children.size() - 1);
            Node tail = getTail(secondLast);
            tail.children.add(last);
        }
    }

    private Node getTail(Node end) {
        while (end.children.size() == 1) {
            end = end.children.get(0);
        }
        return end;
    }

    public boolean findValueRecursive(Node node, int data) {
        if (node.data == data) {
            System.out.println(node.data);
            return true;
        }

        for (Node child : node.children) {
            if (findValueRecursive(child, data)) {
                System.out.println(node.data);
                return true;
            }
        }
        return false;
    }

    public int distanceBetweenNodes(int val_a, int val_b) {
        ArrayList<Node> list_a = findRootPath(head, val_a);
        ArrayList<Node> list_b = findRootPath(head, val_b);
        int a = list_a.size() - 1;
        int b = list_b.size() - 1;
        while (a >= 0 && b >= 0 && list_a.get(a) == list_b.get(b)) {
            a--;
            b--;
        }

        return a + b + 2;
    }

    public int lowestCommonAncestor(int val_a, int val_b) {
        ArrayList<Node> list_a = findRootPath(head, val_a);
        ArrayList<Node> list_b = findRootPath(head, val_b);
        int a = list_a.size() - 1;
        int b = list_b.size() - 1;
        while (a >= 0 && b >= 0 && list_a.get(a) == list_b.get(b)) {
            a--;
            b--;
        }

        return list_a.get(a + 1).data;
    }

    public ArrayList<Node> findRootPath(Node node, int data) {
        if (node.data == data) {
            ArrayList<Node> newlist = new ArrayList<>();
            newlist.add(node);
            return newlist;
        }

        for (Node child : node.children) {
            ArrayList<Node> path = findRootPath(child, data);
            if (!path.isEmpty()) {
                path.add(node);
                return path;
            }
        }
        return new ArrayList<>();
    }

    public boolean testTreeSimilar(Node a, Node b) {
        if (a.children.size() != b.children.size()) {
            return false;
        }
        for (int i = 0; i < a.children.size(); i++) {
            if (!testTreeSimilar(a.children.get(i), b.children.get(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean mirrorImage(Node a, Node b) {
        if (a.children.size() != b.children.size()) {
            return false;
        }
        int i = 0;
        int j = b.children.size() - 1;
        while (i < b.children.size() && j >= 0) {
            if (!mirrorImage(a.children.get(i), b.children.get(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public void predecessorAndSuccessor(Node node, int data) {

        if (state == 0) {
            if (node.data == data) {
                state = 1;
            } else {
                predecessor = node;
            }
        } else if (state == 1) {
            state = 2;
            successor = node;
        }
        for (Node child : node.children) {
            predecessorAndSuccessor(child, data);
        }
    }

    public void ceilingAndFloor(Node node, int data) {
        int nodeData = node.data;
        if (nodeData > data && nodeData < ceil) {
            ceil = data;
        }

        if (nodeData < data && nodeData > floor) {
            floor = data;
        }

        for (Node child : node.children) {
            predecessorAndSuccessor(child, data);
        }
    }

    public void kLargest(Node node, int data) {
        floor = Integer.MIN_VALUE;
        int factor = Integer.MAX_VALUE; // last largest
        for (int i = 0; i < data; i++) {
            floorValue(node, factor);
            factor = floor;
            floor = Integer.MIN_VALUE;
        }
    }

    public void floorValue(Node node, int data) {
        int nodeData = node.data;

        if (nodeData < data && nodeData > floor) {
            floor = data;
        }
        for (Node child : node.children) {
            predecessorAndSuccessor(child, data);
        }
    }

    public int fetchDiameter(Node node) {
        int deepest = -1;
        int secondDeepest = -1;
        for (Node child : node.children) {
            int height = fetchDiameter(child);
            if (deepest < height) {
                secondDeepest = deepest;
                deepest = height;
            } else if (height > secondDeepest) {
                secondDeepest = height;
            }
        }
        dia = Math.max(dia, (deepest + secondDeepest + 2));
        return deepest + 1;
    }

    public void efficientTraversal(Node node) {
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(node, -1));
        StringBuilder pre = new StringBuilder();
        StringBuilder post = new StringBuilder();
        while (stack.size() > 0) {
            Pair pair = stack.peek();
            if (pair.state == -1) {
                pre.append("-").append(pair.node.data);
                pair.state++;
            } else if (pair.state == pair.node.children.size()) {
                post.append("-").append(pair.node.data);
                stack.pop();
            } else {
                Pair newPair = new Pair(pair.node.children.get(pair.state), -1);
                stack.push(newPair);
                pair.state++;
            }
        }
        System.out.println(pre + "\n" + post);
    }
}

package tree;

import java.util.ArrayList;

public class Node {

    int data;
    ArrayList<Node> children;

    public Node(int data) {
        this.data = data;
        children = new ArrayList<>();
    }
}

class Pair{
    Node node;
    int state;

    public Pair(Node node, int state) {
        this.node = node;
        this.state = state;
    }
}

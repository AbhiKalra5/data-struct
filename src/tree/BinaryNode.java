package tree;

public class BinaryNode {

    int data;
    BinaryNode left;
    BinaryNode right;

    public BinaryNode(int data, BinaryNode left, BinaryNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}


class BinaryPair {
    BinaryNode node;
    int state;

    public BinaryPair(BinaryNode node, int state) {
        this.node = node;
        this.state = state;
    }
}

class BinaryMinMaxPair {
    int min;
    int max;
    boolean isBST;
    int size;
    BinaryNode maxNode;

    public BinaryMinMaxPair(int min, int max, boolean isBST, int size, BinaryNode maxNode) {
        this.min = min;
        this.max = max;
        this.isBST = isBST;
        this.size = size;
        this.maxNode = maxNode;
    }

    public BinaryMinMaxPair(int min, int max, boolean isBST) {
        this.min = min;
        this.max = max;
        this.isBST = isBST;
    }
}

class BalancedBinaryPair {
    int height;
    boolean isBalance;

    public BalancedBinaryPair(int height, boolean isBalance) {
        this.height = height;
        this.isBalance = isBalance;
    }
}
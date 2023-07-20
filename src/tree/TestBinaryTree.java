package tree;

public class TestBinaryTree {

    public static void main(String[] args) {


        Integer[] arr =
                {6, 2, 8, 0, 4, 7, 9, null, null, 3, 5};

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.constructTree(arr);
        binaryTree.lowestCommonAncestor(binaryTree.rootNode, 2, 4);
    }
}

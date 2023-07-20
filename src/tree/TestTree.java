package tree;

public class TestTree {

    public static void main(String[] args) {
        int[] arr =
            {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 100, -1, 130, -1, -1,
                -1};
        int[] arr_b =
            {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 100, -1, 130, -1, -1,
                -1};

        Tree tree = new Tree();
        tree.constructTree(arr);
        Tree tree_b = new Tree();
        tree_b.constructTree(arr_b);
        tree.fetchDiameter(tree.head);
        System.out.println(tree.dia);


    }
}

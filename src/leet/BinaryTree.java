package leet;


import java.util.ArrayList;
import java.util.List;

public class BinaryTree {

    public static void main(String[] args) {
        generateTrees(4);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List left = inorderTraversal(root.left);
        left.add(root.val);
        left.addAll(inorderTraversal(root.right));
        return left;
    }


    static List<TreeNode>[][] cache;

    public static List<TreeNode> generateTrees(int n) {
        cache = new List[n + 2][n + 2];
        return helper(1, n);
    }

    public static List<TreeNode> helper(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        if (start > end) {
            result.add(null);
            return result;
        }
        if (cache[start][end] != null) {
            return cache[start][end];
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftList = helper(start, i - 1);
            List<TreeNode> rightList = helper(i + 1, end);
            for (TreeNode left : leftList) {
                for (TreeNode right : rightList) {
                    TreeNode tree = new TreeNode();
                    tree.val = i;
                    tree.left = left;
                    tree.right = right;
                    result.add(tree);
                }
            }
        }
        cache[start][end] = result;
        return result;
    }

    Integer[][] intCache;

    public int helperInt(int start, int end) {
        int result;
        if (start > end) {
            return 1;
        }

        if (intCache[start][end] != null) {
            return intCache[start][end];
        }
        int total = 0;
        for (int i = start; i <= end; i++) {

            total += helperInt(start, i - 1) * helperInt(i + 1, end);
        }
        intCache[start][end] = total;
        return total;
    }

}

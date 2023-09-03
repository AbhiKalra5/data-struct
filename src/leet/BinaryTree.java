package leet;


import tree.BinaryNode;
import tree.Node;
import tree.Tree;

import java.util.*;

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


    // boundry print

    ArrayList<Integer> printBoundary(TreeNode node) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if (isLeaf(node) == false) {
            ans.add(node.val);
        }
        addLeftBoundary(node.left, ans);
        addLeaves(node, ans);
        addRightBoundary(node, ans);
        return ans;
    }

    Boolean isLeaf(TreeNode root) {
        return (root.left == null) && (root.right == null);
    }

    void addLeftBoundary(TreeNode root, ArrayList<Integer> res) {
        while (root != null) {
            if (isLeaf(root)) {
                return;
            } else {
                res.add(root.val);
                root = root.left != null ? root.left : root.right;
            }
        }
    }

    void addLeaves(TreeNode root, ArrayList<Integer> res) {
        if (root.left == null && root.right == null) {
            res.add(root.val);
        }
        if (root.left != null) {
            addLeaves(root.left, res);
        }
        if (root.right != null) {
            addLeaves(root.right, res);
        }
    }

    void addRightBoundary(TreeNode root, ArrayList<Integer> res) {
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        while (root != null) {
            if (isLeaf(root) == false) {
                tmp.add(root.val);
            }
            if (root.right != null) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        for (int i = tmp.size() - 1; i >= 0; --i) {
            res.add(tmp.get(i));
        }
    }

    public static ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<ArrayList<Integer>> wrapList = new ArrayList<>();
        if (root == null) {
            return wrapList;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> subList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                subList.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            wrapList.add(subList);
        }
        return wrapList;
    }

    public static List<List<Integer>> findVertical(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        Queue<Tuple> q = new LinkedList<Tuple>();
        q.offer(new Tuple(root, 0, 0));
        while (!q.isEmpty()) {
            Tuple tuple = q.poll();
            TreeNode node = tuple.node;
            int x = tuple.row;
            int y = tuple.col;

            // no vertical, add first time
            if (!map.containsKey(x)) {
                map.put(x, new TreeMap<>());
            }
            if (!map.get(x).containsKey(y)) {
                map.get(x).put(y, new PriorityQueue<>());
            }
            map.get(x).get(y).offer(node.val);

            if (node.left != null) {
                q.offer(new Tuple(node.left, x - 1, y + 1));
            }
            if (node.right != null) {
                q.offer(new Tuple(node.right, x + 1, y + 1));
            }
        }

        List<List<Integer>> list = new ArrayList<>();
        for (TreeMap<Integer, PriorityQueue<Integer>> ys : map.values()) {
            list.add(new ArrayList<>());
            for (PriorityQueue<Integer> nodes : ys.values()) {
                while (!nodes.isEmpty()) {
                    list.get(list.size() - 1).add(nodes.poll());
                }
            }
        }
        return list;
    }

    public static ArrayList<Integer> topBottomViewTraversal(TreeNode root, boolean isTop) {
        Queue<Pair> queue = new LinkedList<>();
        ArrayList<Integer> wrapList = new ArrayList<>();
        Map<Integer, Integer> map = new TreeMap<>();
        if (root == null) {
            return wrapList;
        }
        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int level = pair.level;
            TreeNode node = pair.node;
            if (!isTop || !map.containsKey(level)) {
                map.put(level, node.val);
            }
            if (node.left != null) {
                queue.add(new Pair(node.left, level - 1));
            }
            if (node.right != null) {
                queue.add(new Pair(node.right, level + 1));
            }

        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            wrapList.add(entry.getValue());
        }
        return wrapList;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        rightView(root, result, 0);
        return result;
    }

    public void rightView(TreeNode curr, List<Integer> result, int currDepth) {
        if (curr != null) {
            if (currDepth == result.size()) {
                result.add(curr.val);
            }
            rightView(curr.right, result, currDepth + 1);
            rightView(curr.left, result, currDepth + 1);

        }
    }

    public boolean symmetry(TreeNode a, TreeNode b) {
        if (a == null || b == null) {
            return a == b;
        }
        return a.val == b.val && symmetry(a.left, b.right) && symmetry(a.right, b.left);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            // this is LCA
            return root;
        }
    }

    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int ans = 0;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));
        while (!q.isEmpty()) {
            int size = q.size();
            int min = q.peek().level;
            int first = 0;
            int last = 0;
            for (int i = 0; i < size; i++) {
                Pair pair = q.poll();
                int nextLevelValue = pair.level - min;
                if (i == 0) {
                    first = pair.level;
                } else if (i == size - 1) {
                    last = pair.level;
                }

                if (pair.node.left != null) {
                    q.offer(new Pair(pair.node.left, nextLevelValue * 2 + 1));
                }
                if (pair.node.right != null) {
                    q.offer(new Pair(pair.node.right, nextLevelValue * 2 + 2));
                }
            }
            ans = Math.max(ans, last - first + 1);
        }
        return ans;
    }

    static void childSumRootOrder(TreeNode root) {
        if (root != null) {
            int childSum = 0;
            TreeNode left = root.left;
            TreeNode right = root.right;
            int rootValue = root.val;
            if (left != null) {
                childSum += left.val;
            }
            if (right != null) {
                childSum += right.val;
            }

            if (childSum < rootValue) {
                if (left != null) {
                    left.val = rootValue;
                }
                if (right != null) {
                    right.val = rootValue;
                }
            }

            childSumRootOrder(left);
            childSumRootOrder(right);
            rootValue = 0;
            left = root.left;
            right = root.right;
            if (left != null) {
                rootValue += left.val;
            }
            if (right != null) {
                rootValue += right.val;
            }
            if (left != null || right != null) {
                root.val = rootValue;
            }

        }
    }
}

package series;



import dtos.Pair;
import dtos.TreeNode;
import dtos.Tuple;
import tree.BinaryNode;

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
            if (isLeaf(root) == false) {
                res.add(root.val);
            }
            root = root.left != null ? root.left : root.right;

        }
    }

    void addLeaves(TreeNode root, ArrayList<Integer> res) {
        if (isLeaf(root)) {
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
            ArrayList<Integer> resultList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                resultList.add(current.val);
                if (root.left != null) {
                    queue.add(root.left);
                }
                if (root.right != null) {
                    queue.add(root.right);
                }
            }
            wrapList.add(resultList);
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

    // number of nodes at the last possible level of complete Binary tree
    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
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

    // k distance nodes

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> parent_track = new HashMap<>();
        markParents(root, parent_track);
        Map<TreeNode, Boolean> visited = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(target);
        visited.put(target, true);
        int level = 0;
        while (!queue.isEmpty() && level <= k) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (current.left != null && visited.get(current.left) == null) {
                    queue.offer(current.left);
                    visited.put(current.left, true);
                }
                if (current.right != null && visited.get(current.right) == null) {
                    queue.offer(current.right);
                    visited.put(current.right, true);
                }
                if (parent_track.get(current) != null && visited.get(parent_track.get(current)) == null) {
                    queue.offer(parent_track.get(current));
                    visited.put(parent_track.get(current), true);
                }
            }
            level++;
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            result.add(current.val);
        }
        return result;

    }

    private void markParents(TreeNode root, Map<TreeNode, TreeNode> parent_track) {
        if (root != null) {
            if (root.left != null) {
                parent_track.put(root.left, root);
                markParents(root.left, parent_track);
            }
            if (root.right != null) {
                parent_track.put(root.right, root);
                markParents(root.right, parent_track);
            }
        }
    }

    // burn the tree

    public int timeToBurnTree(TreeNode root, int start) {
        HashMap<TreeNode, TreeNode> mpp = new HashMap<>();
        TreeNode target = bfsToMapParents(root, mpp, start);
        int maxi = findMaxTime(mpp, target);
        return maxi;
    }

    private TreeNode bfsToMapParents(TreeNode root, HashMap<TreeNode, TreeNode> mpp, int start) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        TreeNode res = new TreeNode(-1);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node.val == start)
                res = node;
            if (node.left != null) {
                mpp.put(node.left, node);
                q.offer(node.left);
            }
            if (node.right != null) {
                mpp.put(node.right, node);
                q.offer(node.right);
            }
        }
        return res;
    }

    private int findMaxTime(HashMap<TreeNode, TreeNode> mpp, TreeNode target) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(target);
        HashMap<TreeNode, Boolean> visitedNodes = new HashMap<>();
        visitedNodes.put(target, true);
        int time = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            boolean burnt = false;
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = q.poll();
                TreeNode node = currentNode.left;
                if (node != null && !visitedNodes.containsKey(node)) {
                    visitedNodes.put(node, true);
                    q.offer(node);
                    burnt = true;
                }
                node = currentNode.right;
                if (node != null && !visitedNodes.containsKey(node)) {
                    visitedNodes.put(node, true);
                    q.offer(node);
                    burnt = true;
                }
                node = mpp.get(currentNode);
                if (node != null && !visitedNodes.containsKey(node)) {
                    visitedNodes.put(node, true);
                    q.offer(node);
                    burnt = true;
                }
            }
            if (burnt) {
                time++;
            }

        }
        return time;
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeightLeft(root.left);
        int right = getHeightRight(root.right);
        return left == right ? ((2 << left) - 1) : 1 + countNodes(root.left) + countNodes(root.right);
    }

    public int getHeightLeft(TreeNode root) {
        int count = 0;
        while (root.left != null) {
            count++;
            root = root.left;
        }
        return count;
    }


    public int getHeightRight(TreeNode root) {
        int count = 0;
        while (root.right != null) {
            count++;
            root = root.right;
        }
        return count;
    }

    // inorder and PreOrder
    TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);
        int rootInOrder = inMap.get(preorder[preStart]);
        int len = rootInOrder - inStart;

        root.left = buildTree(preorder, preStart + 1, preStart + len, inorder, inStart, rootInOrder - 1, inMap);
        root.right = buildTree(preorder, preStart + len + 1, preEnd, inorder, rootInOrder + 1, inEnd, inMap);
        return root;
    }

    static int max;

    static public int maxPathSum(TreeNode root) {
        max = -99999;
        find(root);
        return max;
    }

    static public int find(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(0, find(root.left));
        int right = Math.max(0, find(root.right));
        int sum = left + root.val + right;
        max = Math.max(sum, max);
        return root.val + Math.max(left, right);
    }


    public TreeNode buildTreeFromInPost(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreePostIn(inorder, 0, inorder.length, postorder, 0, postorder.length, map);
    }

    private TreeNode buildTreePostIn(int[] inorder, int is, int ie, int[] postorder, int ps, int pe, HashMap<Integer, Integer> hm) {
        if (is > pe || is > ie) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[pe]);
        int rootLocation = hm.get(root.val);
        int lengthLeft = rootLocation - is;

        root.left = buildTreePostIn(inorder, is, rootLocation - 1, postorder, ps, ps + lengthLeft - 1, hm);
        root.right = buildTreePostIn(inorder, rootLocation + 1, ie, postorder, ps + lengthLeft, pe - 1, hm);
        return root;
    }

    public String serialize(TreeNode node) {
        if (node == null) {
            return "";
        }
        StringBuilder res = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            TreeNode temp = q.poll();
            if (temp != null) {
                res.append(temp.val + ",");
                q.add(temp.left);
                q.add(temp.right);
            } else {
                res.append("$,");
            }
        }
        return res.toString();
    }

    public TreeNode deSerialize(String node) {
        if (node == null || node.isEmpty()) {
            return null;
        }
        String[] values = node.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        int counter = 0;
        while (!q.isEmpty() && counter < values.length) {
            TreeNode temp = q.poll();
            String strRight = values[counter++];
            String strLeft = values[counter++];
            if (strRight != "$") {
                temp.left = null;
            } else {
                TreeNode newNode = new TreeNode(Integer.parseInt(strRight));
                temp.left = newNode;
                q.add(newNode);
            }

            if (strLeft != "$") {
                temp.right = null;
            } else {
                TreeNode newNode = new TreeNode(Integer.parseInt(strLeft));
                temp.right = newNode;
                q.add(newNode);
            }

        }
        return root;

    }

    ArrayList<Integer> inorderTraversal_Morris(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode temp = curr.left;
                while (temp.right != null && temp.right != curr) {
                    curr = curr.right;
                }
                if (temp.right == null) {
                    temp.right = curr;
                    // if pre Order : res.add(curr.val);
                    curr = curr.left;
                } else {
                    temp.right = null;
                    res.add(curr.val);// inorder
                    curr = curr.right;
                }
            }
        }
        return res;
    }

    // flatten

    TreeNode next;

    public void flatten_a(TreeNode node) {
        if (node != null) {
            flatten_a(node.right);
            flatten_a(node.left);

            node.right = next;
            node.left = null;
            next = node;

        }
    }

    public void flatten_Morris(TreeNode node) {
        TreeNode curr = node;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode temp = curr.left;
                while (temp.right != null) {
                    temp = temp.right;
                }
                temp.right = curr.right;
                curr.right = curr.left;
            }
            curr = curr.right;
        }
    }

    // Binary Search
    public TreeNode search(TreeNode node, int key) {
        while (node != null && node.val != key) {
            node = key < node.val ? node.left : node.right;
        }
        return node;
    }

    public int ciel(TreeNode node, int key) {
        int ciel = Integer.MAX_VALUE;
        while (node != null) {
            if (node.val == key) {
                ciel = node.val;
                break;
            } else if (node.val > key) {
                ciel = node.val;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return ciel;
    }

    public int floor(TreeNode root, int key) {
        int floor = Integer.MAX_VALUE;
        while (root != null) {
            if (root.val == key) {
                floor = root.val;
                break;
            } else if (root.val > key) {
                root = root.left;
            } else {
                floor = root.val;
                root = root.right;
            }
        }
        return floor;
    }

    public TreeNode insertIntoBst(TreeNode root, int key) {
        if (root == null) {
            return new TreeNode(key);
        }
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val <= key) {
                if (cur.right != null) {
                    cur = cur.right;
                } else {
                    cur.right = new TreeNode(key);
                    break;
                }
            } else {
                if (cur.left != null) {
                    cur = cur.left;
                } else {
                    cur.left = new TreeNode(key);
                    break;
                }
            }
        }
        return root;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return new TreeNode(key);
        }
        if (root.val == key) {
            return helper(root);
        }
        TreeNode temp = root;
        while (temp != null) {
            if (key < temp.val) {
                if (temp.left != null && temp.left.val == key) {
                    temp.left = helper(temp.left);
                    break;
                } else {
                    temp = temp.left;
                }
            } else {
                if (temp.right != null && temp.right.val == key) {
                    temp.right = helper(temp.right);
                    break;
                } else {
                    temp = temp.right;
                }
            }
        }
        return root;
    }

    public TreeNode helper(TreeNode root) {
        if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        }

        TreeNode largest = fetchLargestNumber(root.left);
        largest.right = root.right;
        return root.left;

    }

    public TreeNode fetchLargestNumber(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    Integer kthLargest_Morris(TreeNode curr, int k) {
        int res = Integer.MIN_VALUE;
        int count = 0;
        TreeNode root = curr;
        while (root != null && count <= k) {
            if (root.left == null) {
                if (++count == k) {
                    res = root.val;
                    break;
                }
                root = root.right;
            } else {
                TreeNode prev = root.left;
                while (prev.right != null && prev.right != root) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    prev.right = root;
                    root = root.left;
                } else {
                    if (++count == k) {
                        res = root.val;
                        break;
                    }
                    prev.right = null;
                    root = root.right;
                }
            }
        }
        return res;
    }

    boolean isBst(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.val > max || root.val < min) {
            return false;
        }
        return isBst(root.left, min, root.val) && isBst(root.right, root.val, max);
    }

    TreeNode lowestCommonAncestorBst(TreeNode node, TreeNode p, TreeNode q) {
        if (node != null) {
            int val = node.val;
            if (val < p.val && val < q.val) {
                return lowestCommonAncestorBst(node.left, p, q);
            } else if (val > p.val && val > q.val) {
                return lowestCommonAncestorBst(node.right, p, q);
            }
        }
        return node;
    }

    // construct from preOrder traversal
    // n2 : 2 loops
    // sort ,create pre order then make
    // 3:
    int counter = 0;

    TreeNode prepareBstPreOrder(int[] pre) {
        return prepareBst(pre, Integer.MAX_VALUE);
    }

    private TreeNode prepareBst(int[] pre, int bound) {
        // array over or range out of bound
        if (counter == pre.length || pre[counter] > bound) {
            return null;
        }
        TreeNode node = new TreeNode(pre[counter++]);
        node.left = prepareBst(pre, node.val);
        node.right = prepareBst(pre, bound);
        return node;
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;

        while (root != null) {
            if (p.val >= root.val) {
                root = root.right;
            } else {
                successor = root;
                root = root.left;

            }
        }
        return successor;
    }



    // 1. Compare inorder traversal with sorted traversal of tree and swap
    class RecoverBst {
        private TreeNode first;
        private TreeNode prev;
        private TreeNode middle;
        private TreeNode last;

        private void inorder(TreeNode root) {
            if (root != null) {
                inorder(root.left);
                if (prev != null && prev.val > root.val) {
                    if (first == null) {
                        first = prev;
                        middle = root;
                    } else {
                        last = root;
                    }
                }
                prev = root;
                inorder(root.right);
            }
        }

        public void recoverTree(TreeNode root) {
            first = middle = last = null;
            prev = new TreeNode(Integer.MIN_VALUE);
            inorder(root);
            if (first != null && last != null) {
                int t = first.val;
                first.val = last.val;
                last.val = t;
            } else if (first != null && middle != null) {
                int t = first.val;
                first.val = middle.val;
                middle.val = t;
            }
        }
    }

    // 1.  O(2n) : should sort and do 2 pointer sum.
    // 2:
    public boolean twoSum(TreeNode node, int target) {
        BstIterator pre = new BstIterator(node, false);
        BstIterator rev = new BstIterator(node, true);
        int i = pre.next();
        int j = rev.next();
        while (i < j) {
            if (i + j == target) {
                return true;
            } else if (i + j < target) {
                i = pre.next();
            } else if (i + j > target) {
                i = rev.next();
            }
        }
        return false;
    }

    public class BstIterator {
        private Stack<TreeNode> stack = new Stack<TreeNode>();

        boolean isReversed;

        public BstIterator(TreeNode root, boolean isReversed) {
            this.isReversed = isReversed;
            pushAll(root);
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public int next() {
            TreeNode node = stack.pop();
            if (isReversed) {
                pushAll(node.left);
            } else {
                pushAll(node.right);
            }

            return node.val;
        }


        private void pushAll(TreeNode node) {
            while (node != null) {
                stack.add(node);
                if (isReversed) {
                    node = node.right;
                } else {
                    node = node.left;
                }

            }
        }
    }
}

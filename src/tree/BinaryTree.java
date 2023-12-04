package tree;

import java.util.*;

public class BinaryTree {

    BinaryNode rootNode;
    int dia = Integer.MIN_VALUE;

    int tilt = 0;
    int maxPath = Integer.MIN_VALUE;

    public void constructTree(Integer[] arr) {
        Stack<BinaryPair> stack = new Stack<>();
        BinaryNode node = new BinaryNode(arr[0], null, null);
        rootNode = node;
        stack.add(new BinaryPair(node, 1));
        int index = 0;
        while (stack.size() != 0) {
            BinaryPair topPair = stack.peek();
            if (topPair.state == 1) {
                topPair.state++;
                index++;
                if (arr.length > index && arr[index] != null) {
                    BinaryNode leftNode = new BinaryNode(arr[index], null, null);
                    topPair.node.left = leftNode;
                    stack.push(new BinaryPair(leftNode, 1));
                }
            } else if (topPair.state == 2) {
                topPair.state++;
                index++;
                if (arr.length > index && arr[index] != null) {
                    BinaryNode rightNode = new BinaryNode(arr[index], null, null);
                    topPair.node.right = rightNode;
                    stack.push(new BinaryPair(rightNode, 1));
                }
            } else {
                stack.pop();
            }
        }
    }

    public void display(BinaryNode node) {
        if (node != null) {
            String nodeString = "";
            if (node.left != null) {
                nodeString += node.left.data;
            }
            nodeString += "<-" + node.data + "->";
            if (node.right != null) {
                nodeString += node.right.data;
            }
            System.out.println(nodeString);
            display(node.left);
            display(node.right);
        }
    }

    public void traversal(BinaryNode node) {
        if (node != null) {
            int data = node.data;
            System.out.println("Pre Order: " + data);
            display(node.left);
            System.out.println("In Order: " + data);
            display(node.right);
            System.out.println("Post Order: " + data);
        }
    }

    public int size(BinaryNode n) {
        int size = 0;
        if (n != null) {
            size++;
            size += size(n.left);
            size += size(n.right);
        }
        return size;
    }

    public int sum(BinaryNode n) {
        int sum = 0;
        if (n != null) {
            sum = n.data;
            sum += sum(n.left);
            sum += sum(n.right);
        }
        return sum;
    }

    public int height(BinaryNode n) {
        int height = 0;
        if (n != null) {
            int heightLeft = height(n.left);
            int heightRight = height(n.right);
            height = Math.max(heightRight, heightLeft) + 1;
        }
        return height;
    }

    public int max(BinaryNode n) {
        int max = Integer.MIN_VALUE;
        if (n != null) {
            max = Math.max(max, max(n.left));
            max = Math.max(max, max(n.right));
            max = Math.max(max, n.data);
        }
        return max;
    }

    public void traversalLevelWiseApproachB(BinaryNode node) {
        Queue<BinaryNode> main = new ArrayDeque<>();
        main.add(node);
        BinaryNode signalNode = new BinaryNode(Integer.MIN_VALUE, null, null);
        main.add(signalNode);
        while (main.size() > 0) {
            BinaryNode peekedNode = main.remove();
            if (peekedNode != signalNode) {
                System.out.print(peekedNode.data + " ");
                if (peekedNode.left != null) {
                    main.add(peekedNode.left);
                }
                if (peekedNode.right != null) {
                    main.add(peekedNode.right);
                }

            } else {
                System.out.println();
                if (main.size() > 0) {
                    main.add(peekedNode);
                }
            }
        }
    }

    public void efficientTraversal(BinaryNode node) {
        Stack<BinaryPair> stack = new Stack<>();
        rootNode = node;
        StringBuilder pre = new StringBuilder();
        StringBuilder in = new StringBuilder();
        StringBuilder post = new StringBuilder();
        stack.add(new BinaryPair(node, 1));
        while (stack.size() > 0) {
            BinaryPair topPair = stack.peek();
            if (topPair.state == 1) {
                topPair.state++;
                pre.append(" , ").append(topPair.node.data);
                if (topPair.node.left != null) {
                    stack.add(new BinaryPair(topPair.node.left, 1));
                }
            } else if (topPair.state == 2) {
                topPair.state++;
                in.append(" , ").append(topPair.node.data);
                if (topPair.node.right != null) {
                    stack.add(new BinaryPair(topPair.node.right, 1));
                }
            } else {
                post.append(" , ").append(topPair.node.data);
                stack.pop();
            }
        }
        System.out.println(pre);
        System.out.println(in);
        System.out.println(post);
    }

    public ArrayList<BinaryNode> findRootPath(BinaryNode node, int data) {
        ArrayList<BinaryNode> list = new ArrayList<>();
        if (node != null) {
            if (node.data == data) {
                list.add(node);
                return list;
            }
            ArrayList<BinaryNode> leftList = findRootPath(node.left, data);
            ArrayList<BinaryNode> rightList = findRootPath(node.right, data);

            if (leftList.size() > 0) {
                leftList.add(node);
                list = leftList;
            } else if (rightList.size() > 0) {
                rightList.add(node);
                list = rightList;
            }
        }
        return list;
    }

    public void printKLevelsDown(BinaryNode node, int level, BinaryNode blocker) {
        if (node != null && level >= 0 && node != blocker) {
            if (level == 0) {
                System.out.println(node.data);
            }
            printKLevelsDown(node.left, level - 1, blocker);
            printKLevelsDown(node.right, level - 1, blocker);
        }
    }

    public void printKLevelsFar(BinaryNode node, int value, int k) {
        ArrayList<BinaryNode> paths = findRootPath(node, value);
        for (int i = 0; i < paths.size(); i++) {
            printKLevelsDown(paths.get(i), k - i, (i == 0 ? null : paths.get(i - 1)));
        }
    }

    public void pathToLeafFromTree(BinaryNode node, String path, int sum, int high, int low) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                sum += node.data;
                if (sum >= low && sum <= high) {
                    System.out.println(path + " :" + sum);
                }
            }
            pathToLeafFromTree(node.left, path + "->" + node.data, sum + node.data, high, low);
            pathToLeafFromTree(node.right, path + "->" + node.data, sum + node.data, high, low);
        }
    }

    public BinaryNode leftClonedTree(BinaryNode binaryNode) {
        if (binaryNode != null) {
            BinaryNode lcr = leftClonedTree(binaryNode.left);
            BinaryNode rcr = leftClonedTree(binaryNode.right);
            binaryNode.left = new BinaryNode(binaryNode.data, lcr, null);
            binaryNode.right = rcr;
        }
        return binaryNode;
    }

    public BinaryNode leftClonedTreeReverse(BinaryNode binaryNode) {
        if (binaryNode != null) {
            BinaryNode lcr = leftClonedTreeReverse(binaryNode.left.left);
            BinaryNode rcr = leftClonedTreeReverse(binaryNode.right);
            binaryNode.left = lcr;
            binaryNode.right = rcr;
        }
        return binaryNode;
    }

    public void printSingleChildNode(BinaryNode binaryNode, BinaryNode parentNode) {
        if (binaryNode != null) {
            if (parentNode != null && (parentNode.left == null || parentNode.right == null)) {
                System.out.println(binaryNode.data);
            }
            printSingleChildNode(binaryNode.left, binaryNode);
            printSingleChildNode(binaryNode.right, binaryNode);
        }
    }

    public BinaryNode removeLeaf(BinaryNode binaryNode) {
        if (binaryNode == null || (binaryNode.left == null && binaryNode.right == null)) {
            return null;
        }
        binaryNode.left = removeLeaf(binaryNode.left);
        binaryNode.right = removeLeaf(binaryNode.right);
        return binaryNode;
    }


    public BinaryNode lowestCommonAncestor(BinaryNode root, int p, int q) {
        if (root != null) {
            BinaryNode left = root.left;
            BinaryNode right = root.right;


            ArrayList<BinaryNode> first = findRootPath(root, p);
            ArrayList<BinaryNode> second = findRootPath(root, q);
            int a = first.size() - 1;
            int b = second.size() - 1;
            while (a >= 0 && b >= 0 && first.get(a) == second.get(b)) {
                a--;
                b--;
            }

            return first.get(a + 1);
        }
        return null;
    }

    public int calculateTiltAndSum(BinaryNode binaryNode) {
        if (binaryNode == null) {
            return 0;
        }

        int sumLeft = calculateTiltAndSum(binaryNode.left);
        int sumRight = calculateTiltAndSum(binaryNode.right);

        tilt += Math.abs(sumLeft - sumRight);
        return sumLeft + sumRight + binaryNode.data;
    }

    public BinaryMinMaxPair isBinaryTree(BinaryNode node) {
        if (node == null) {
            BinaryMinMaxPair pair = new BinaryMinMaxPair(Integer.MAX_VALUE, Integer.MIN_VALUE, true, 0, null);
            return pair;
        }

        BinaryMinMaxPair pairLeft = isBinaryTree(node.left);
        BinaryMinMaxPair pairRight = isBinaryTree(node.right);
        boolean isCurrentBST = pairLeft.isBST && pairLeft.isBST && pairLeft.max <= node.data && pairRight.min >= node.data;

        BinaryMinMaxPair pair = new BinaryMinMaxPair(Math.min(node.data, Math.min(pairLeft.min, pairRight.min)),
            Math.max(node.data, Math.max(pairLeft.max, pairRight.max)),
            isCurrentBST);

        if (isCurrentBST) {
            pair.size = pairLeft.size + pairRight.size + 1;
            pair.maxNode = node;
        } else if (pairLeft.isBST) {
            pair.size = pairLeft.size;
            pair.maxNode = pairLeft.maxNode;
        } else if (pairRight.isBST) {
            pair.size = pairRight.size;
            pair.maxNode = pairRight.maxNode;
        }
        return pair;
    }


    public BalancedBinaryPair isBalanced(BinaryNode n) {
        BalancedBinaryPair newPair = new BalancedBinaryPair(0, true);
        if (n != null) {
            BalancedBinaryPair leftPair = isBalanced(n.left);
            BalancedBinaryPair rightPair = isBalanced(n.right);
            newPair.height = Math.max(leftPair.height, rightPair.height) + 1;
            newPair.isBalance = Math.abs(leftPair.height - rightPair.height) <= 1 && leftPair.isBalance && rightPair.isBalance;
        }
        return newPair;
    }

    public int diameter(BinaryNode root) {
        if (root == null) {
            return 0;
        }
        int heightLeft = diameter(root.left);
        int heightRight = diameter(root.right);
        dia = Math.max(dia, (heightLeft + heightRight));
        return 1 + Math.max(heightRight, heightLeft);
    }


    public int maxPath(BinaryNode n) {
        int maximumThisLevel = 0;
        if (n != null) {
            int left = maxPath(n.left);
            int right = maxPath(n.right);
            maximumThisLevel = n.data + Math.max(left, right);
            maxPath = Math.max(maxPath, (n.data + left + right));
        }
        return maximumThisLevel;
    }

}

package leet;

import dtos.TreeNode;

public class BinaryTreeTilt {
    int tilt;
    public int findTilt(TreeNode root) {
        tilt = 0;
        calculateTiltAndSum(root);
        return tilt;
    }
    public int calculateTiltAndSum(TreeNode binaryNode) {
        if (binaryNode == null) {
            return 0;
        }

        int sumLeft = calculateTiltAndSum(binaryNode.left);
        int sumRight = calculateTiltAndSum(binaryNode.right);

        tilt += Math.abs(sumLeft - sumRight);
        return sumLeft + sumRight + binaryNode.val;
    }
}

package org;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.value = value;
        }

        public String toString() {
            return "[value: " + value + " { left: " + left + ", right: " + right + " }]";
        }
    }

    /*
        Given a sorted (increasing order) array with unique integer elements, write an algorithm
        to create a binary search tree with minimal height.
     */
    TreeNode createMinimalBST(int[] arr) {
        return createMinimalBST(arr, 0, arr.length - 1);
    }

    TreeNode createMinimalBST(int[] arr, int start, int end) {
        if (end < start) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode result = new TreeNode(arr[mid]);
        result.left = createMinimalBST(arr, start, mid - 1);
        result.right = createMinimalBST(arr, mid + 1, end);
        return result;
    }

    /*
        Given a binary tree, design an algorithm which creates a linked list of all the nodes
        at each depth (e.g., if you have a tree with depth D, you'll have D linked lists).
     */
    List<LinkedList<TreeNode>> createLevelLinkedListDF(TreeNode root) {
        List<LinkedList<TreeNode>> lists = new ArrayList<>();
        createLevelLinkedListDF(root, lists, 0);
        return lists;
    }

    // depth-first search
    void createLevelLinkedListDF(TreeNode root, List<LinkedList<TreeNode>> lists, int level) {
        if (root == null) return; // base case

        LinkedList<TreeNode> list;
        if (lists.size() == level) {
            // levels are always traversed in order, so we can add the level at the end
            list = new LinkedList<>();
            lists.add(list);
        } else {
            list = lists.get(level);
        }
        list.add(root);
        createLevelLinkedListDF(root.left, lists, level + 1);
        createLevelLinkedListDF(root.right, lists, level + 1);
    }

    // breadth-first search
    List<LinkedList<TreeNode>> createLevelLinkedListBF(TreeNode root) {
        List<LinkedList<TreeNode>> result = new ArrayList<>();
        // visit the root
        LinkedList<TreeNode> current = new LinkedList<>();
        if (root != null) {
            current.add(root);
        }
        // visit other nodes
        while (current.size() > 0) {
            result.add(current); // add previous level
            LinkedList<TreeNode> parents = current; // go to next level
            current = new LinkedList<>();
            for (TreeNode parent : parents) {
                // visit the children
                if (parent.left != null) {
                    current.add(parent.left);
                }
                if (parent.right != null) {
                    current.add(parent.right);
                }
            }
        }
        return result;
    }

    /*
        Implement a function to check if a binary tree is balanced. For the purposes of this question,
        a balanced tree is defined to be a tree such that the heights of the two subtrees of any node
        neve differ by more than one.
     */
    int getHeight(TreeNode root) {
        if (root == null) return -1; // base case
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    // O(n log n)
    boolean isBalanced(TreeNode root) {
        if (root == null) return true; // base case

        int heightDiff = getHeight(root.left) - getHeight(root.right);
        if (Math.abs(heightDiff) > 1) {
            return false;
        } else {
            // recurse
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }

    //---- optimized approach
    int checkHeight(TreeNode root) {
        if (root == null) return -1;

        int leftHeight = checkHeight(root.left);
        if (leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;

        int rightHeight = checkHeight(root.right);
        if (rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;

        int heightDiff = leftHeight - rightHeight;
        if (Math.abs(heightDiff) > 1) {
            return Integer.MIN_VALUE;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    // O(n)
    boolean isBalancedOptimized(TreeNode root) {
        return checkHeight(root) != Integer.MIN_VALUE;
    }

    /*
        Implement a function to check if a binary tree is a binary search tree.
     */
    // binary search tree should satisfy the condition left.data <= current.data < right.data for each node
    // O(n)
    boolean checkBST(TreeNode root) {
        return checkBST(root, null, null);
    }

    private boolean checkBST(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;

        // all left nodes must be less than or equal to the current node,
        // which must be less than all right nodes
        if ((min != null && root.value <= min)
            || (max != null && root.value > max)) {
            return false;
        }

        if (!checkBST(root.left, min, root.value)
            || !checkBST(root.right, root.value, max)) {
            return false;
        }

        return true;
    }


    public static void main(String[] args) {
	    // write your code here
        int[] arr = new int[] {1,3,4,7,9,11,14,23,25,34,48};
        Main main = new Main();
        TreeNode root = main.createMinimalBST(arr);
        List<LinkedList<TreeNode>> lists1 = main.createLevelLinkedListDF(root);
        List<LinkedList<TreeNode>> lists2 = main.createLevelLinkedListBF(root);
        boolean isBalanced = main.isBalanced(root);
        isBalanced = main.isBalancedOptimized(root);
        boolean isBST = main.checkBST(root);
        System.out.println("DONE");
    }
}

package bst;

import com.sun.tools.javac.util.ArrayUtils;

import java.io.*;
import java.util.*;

public class Solution {
    private static final int DEFAULT_DISTANCE = 0;
    private final Scanner scanner;

    class Node implements Comparable<Node> {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }

        @Override
        public int compareTo(Node o) {
            return 0;
        }
    }

    public Solution(final Scanner scanner) {
        this.scanner = scanner;
    }

    public Node constructTree(int n) {
        // initialize tree root
        Stack<Node> tree = new Stack<>();
        Node root = new Node(scanner.nextInt());
        tree.add(root);

        // reading from the STDIN and creating BST
        for (int i = 1; i < n; i++) {
            Node node = new Node(scanner.nextInt());

            Node tmp = null;
            while (!tree.isEmpty() && node.data > tree.peek().data) {
                tmp = tree.pop();
            }

            if (tmp != null) {
                tmp.right = node;
            } else {
                tree.peek().left = node;
            }

            tree.push(node);
        }

        return root;
    }

    private int getDistance(final Node root, int data) {
        int dist = DEFAULT_DISTANCE;

        Node tmp = root;
        do {
            if (tmp.data < data) {
                tmp = tmp.right;
            } else if (tmp.data > data) {
                tmp = tmp.left;
            } else {
                return dist;
            }
            dist++;
        } while (tmp != null);

        return DEFAULT_DISTANCE;
    }

    // distance from 'a' to 'b' can be calculated using formula
    // Dist('a', 'b') = Dist(root, 'a') + Dist(root, 'b') - 2*Dist(root, lca)
    // where 'lca' is a lowerst common ancestor of 'a' and 'b'
    public void printDistance(final Node root, int a, int b) {
        if (root == null) {
            System.out.println("ROOT is null");
        } else {
            int distToA = getDistance(root, a);
            if (DEFAULT_DISTANCE == distToA) {
                System.out.println("Node 'A' is not found");
                return;
            }

            int distToB = getDistance(root, b);
            if (DEFAULT_DISTANCE == distToB) {
                System.out.println("Node 'B' is not found");
                return;
            }

            int lca = 0;
            System.out.println(distToA + distToB - 2*lca);
        }
    }

    private static List<Integer> getItems() {
        System.out.println("getItems");
        return Arrays.asList(3, 4, 5, 6);
    }

    public static void main(String args[] ) throws Exception {


        for (int item : getItems()) {
            System.out.println("we are in loop: " + item);
        }
        return;
/*
        int i1 = 5;
        int i2 = 2;
        int i3 = i1 / i2;

        Scanner scanner = new Scanner(System.in);

        // read 'a' and 'b'
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        // read number of elements
        int n = scanner.nextInt();

        Solution sol = new Solution(scanner);
        Node root = sol.constructTree(n);
        sol.printDistance(root, a, b);
*/
    }
}

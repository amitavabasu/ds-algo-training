package training.tree;

import java.util.LinkedList;
import java.util.Queue;

public class DepthAndWidthOfABinaryTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int value) {
            this.value = value;
        }
    }

    public static Node buildTree() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);
        root.left.left.right = new Node(7);
        root.left.left.right.left = new Node(8);
        return root;
    }

    public static int depth(Node root, int depth) {
        if (root == null) {
            return depth;
        }
        depth++;
        return Math.max(depth(root.left, depth), depth(root.right, depth));
    }

    public static int width(Node root) {
        if (root == null) return 0;
        int width = 1;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            if (width < queue.size()) width = queue.size();//<-- this may not be correct
            Node node = queue.remove();
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return width;
    }

    public static void main(String[] args) {
        Node root =  buildTree();
        System.out.println("Tree depth==>" + depth(root, 0));
        System.out.println("Tree width==>" + width(root));
    }
}

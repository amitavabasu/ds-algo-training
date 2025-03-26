package practice;

public class DepthOfABinaryTree {
    public static class Node{
        int value;
        Node left;
        Node right;
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
        if (root == null) return depth;
        return Math.max(depth(root.left, depth+1), depth(root.right, depth+1));
    }
    public static void main(String[] args) {
        Node root =  buildTree();
        System.out.println("Tree depth==>" + depth(root, 0));
    }
}

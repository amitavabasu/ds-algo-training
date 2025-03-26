package training.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AllDFSTreeTraversal {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int value) {
            this.value = value;
        }
    }

    public static Node buildTree() {
        Node root = new Node(9);
        root.left = new Node(4);
        root.right = new Node(20);
        root.left.left = new Node(1);
        root.left.right = new Node(6);
        root.right.left = new Node(15);
        root.right.right = new Node(170);
        return root;
    }

    public static List<Integer> inOrder(Node root, List<Integer> result) {
        if (root == null) return Collections.emptyList();
        if (root.left != null) inOrder(root.left, result);
        result.add(root.value);
        if (root.right != null) inOrder(root.right, result);
        return result;
    }

    public static List<Integer> preOrder(Node root, List<Integer> result) {
        if (root == null) return Collections.emptyList();
        result.add(root.value);
        if (root.left != null) preOrder(root.left, result);
        if (root.right != null) preOrder(root.right, result);
        return result;
    }

    public static List<Integer> postOrder(Node root, List<Integer> result) {
        if (root == null) return Collections.emptyList();
        if (root.left != null) postOrder(root.left, result);
        if (root.right != null) postOrder(root.right, result);
        result.add(root.value);
        return result;
    }




    public static void main(String[] args) {
        Node root = buildTree();
        System.out.println("Post Order: " + Arrays.toString(postOrder(root, new ArrayList()).toArray()));
        System.out.println("Pre Order: " + Arrays.toString(preOrder(root, new ArrayList()).toArray()));
        System.out.println("In Order: " + Arrays.toString(inOrder(root, new ArrayList<>()).toArray()));
    }


}

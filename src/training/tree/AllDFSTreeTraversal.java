package training.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    public static Node buildTree(Integer[] levelOrderData, int index) {
            if (index >= levelOrderData.length) return null;
            Node node = new Node(levelOrderData[index]);
            node.left = buildTree(levelOrderData, index*2+1);
            node.right = buildTree(levelOrderData, index*2+2);
            return node;
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

    public static List<Integer> levelOrder(Node root) {
        if (root == null) return Collections.emptyList();
        List<Integer> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            result.add(node.value);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return result;
    }






    public static void main(String[] args) {
        Node root = buildTree();
        System.out.println("Post Order: " + Arrays.toString(postOrder(root, new ArrayList()).toArray()));
        System.out.println("Pre Order: " + Arrays.toString(preOrder(root, new ArrayList()).toArray()));
        System.out.println("In Order: " + Arrays.toString(inOrder(root, new ArrayList<>()).toArray()));
        System.out.println("Level Order: " + Arrays.toString(levelOrder(root).toArray()));

        System.out.println("-------------");

        Integer[] treeDataLevelOrder = {9, 4,20, 1,6,15,170, };
        Node rootNode = buildTree(treeDataLevelOrder, 0);
        System.out.println("Post Order: " + Arrays.toString(postOrder(rootNode, new ArrayList()).toArray()));
        System.out.println("Pre Order: " + Arrays.toString(preOrder(rootNode, new ArrayList()).toArray()));
        System.out.println("In Order: " + Arrays.toString(inOrder(rootNode, new ArrayList<>()).toArray()));
        System.out.println("Level Order: " + Arrays.toString(levelOrder(root).toArray()));
    }


}

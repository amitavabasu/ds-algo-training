package practice.repeat0;

import java.util.LinkedList;
import java.util.Queue;

public class ValidateBST {

    public static class Node {
        int value;
        Node left;
        Node right;
        public Node (int value) {
            this.value = value;
        }
    }

    public static Node buildBST(int[] values) {
        if (values == null || values.length == 0) return null;
        Node root = new Node(values[0]);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        for (int i = 1; i < values.length; i++) {
            Node node = queue.remove();
            node.left = new Node(values[i]);
            queue.offer(node.left);
            if (i < values.length-1) {
                i++;
                node.right = new Node(values[i]);
                queue.offer(node.right);
            }
        }
        return root;
    }

    public static void levelOrder(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int size = queue.size();
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.print(node.value + " ");
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
            size--;
            if (size == 0) {
                System.out.println("");
                size = queue.size();
            }
        }
    }

    public static boolean isValidBST(Node root, int min, int max) {
        if (root == null) return true;
        if (root.value < min || root.value > max) return false;
        if (root.left != null){
            if(!isValidBST(root.left, min, root.value)) return false;
        }
        if (root.right != null){
            if(!isValidBST(root.right, root.value, max)) return false;
        }
        return true;
    }



    public static void main(String[] args) {
        int[][] valuesArray = {
                {20,18,22,17,19,21,25},//Valid
                {20,18,22,17,23,21,25},//Invalid
                {25,18,22,17,23,21,20}//Invalid
        };
        for (int[] values : valuesArray) {
            Node root = buildBST(values);
            levelOrder(root);
            System.out.println("--------------------------------" + isValidBST(root,Integer.MIN_VALUE, Integer.MAX_VALUE));
        }
    }
}

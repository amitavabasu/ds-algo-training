package practice.repeat3;

import java.util.Objects;

public class ValidateBST {

    public static class Node {
        int value;
        Node left;
        Node right;
        public Node(int val) { this.value = val;}
    }

    public static Node buildTree(int[] arr, int l, int r) {
        if (l > r) return null;
        int mid = (l+r) / 2;
        Node node = new Node(arr[mid]);
        node.left = buildTree(arr, l, mid-1);
        node.right = buildTree(arr, mid+1, r);
        return node;
    }

    public static boolean isValidBST(Node node, int min, int max) {
        Objects.requireNonNull(node, "Node cannot be null");
        if (node.value <= min || node.value >= max) {
            return false;
        }
        if (node.left != null && !isValidBST(node.left, min, node.value)) {
            return false;
        }
        if (node.right != null && !isValidBST(node.right, node.value, max)){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] arrays = {
                {1,2,3,4,5,6,7,8,9,10},//True
                {1,8,3,4,5,6,7,2,9,10},//False
                {1,3,2,4,5,6,7,8,9,10}//False
        };

        for (int[] arr : arrays) {
            Node tree = buildTree(arr, 0, arr.length-1);
            System.out.println(isValidBST(tree, Integer.MIN_VALUE, Integer.MAX_VALUE));
        }
    }
}

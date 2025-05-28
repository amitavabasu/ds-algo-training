package training.tree;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FindAllNodesInBetweenTwoValues {

    /*
    Given a fully balanced binary search tree (created from a sorted list)
    find all the nodes/elements which falls in between two given values (inclusive)
     */

    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node (int val) {
            this.val = val;
        }
    }

    public static Node buildBSTFromSortedArray(int[] array, int l, int r) {
        if (l > r) return null;
        int mid = (l+r) / 2;
        Node node = new Node(array[mid]);
        node.left = buildBSTFromSortedArray(array, l, mid-1);
        node.right = buildBSTFromSortedArray(array, mid+1, r);
        return node;
    }

    public static void printTree(Node node) {
        if (node == null) return;
        if (node.right != null) printTree(node.right);
        System.out.print(node.val + " ");
        if (node.left != null) printTree(node.left);
    }

    public static void findBetween(Node node, int start, int end) {
        if (node == null) return;
        if (start < node.val && end < node.val) findBetween(node.left, start, end);
        if (start > node.val && end > node.val) findBetween(node.right, start, end);
        if (node.val >= start && node.val <= end) {
            System.out.print(node.val + " ");
            if (start < node.val) findBetween(node.left, start, end);
            if (end > node.val) findBetween(node.right, start, end);
        }
    }

    public static void main(String[] args) {
        int[] array = {0,2,4,6,8,10,12,14,16,18,20, 11};
        List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
        Collections.sort(list);
        Node root = buildBSTFromSortedArray(list.stream().mapToInt(Integer::intValue).toArray(), 0, array.length-1);
        printTree(root);
        System.out.println();
        findBetween(root, 10, 17);
        System.out.println();
    }

}

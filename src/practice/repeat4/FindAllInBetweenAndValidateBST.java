package practice.repeat4;

import practice.ValidateBST;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FindAllInBetweenAndValidateBST {

    public static class Node {
        int val;
        Node l;
        Node r;
        public Node(int val) { this.val = val;}
    }



    public static Node buildTreeFromIOData(Integer[] data, int l, int r) {
        if ( l <= r) {
            int m = (l + r) / 2;
            if (data[m] == null) return null;
            Node node = new Node(data[m]);
            node.l = buildTreeFromIOData(data, l, m-1);
            node.r = buildTreeFromIOData(data, m+1, r);
            return node;
        } else {
            return null;
        }
    }

    public static void printInOrder(Node root) {
        if (root == null) return;
        if (root.l != null) printInOrder(root.l);
        System.out.print(root.val + " ");
        if (root.r != null) printInOrder(root.r);
    }

    public static void findInBetweenExclusive(Node node, int l, int r) {
        if (node == null || l > r) return;
        if (node.val > l && node.val < r) System.out.print(node.val+ " ");
        if (node.l != null && node.val >= l) findInBetweenExclusive(node.l, l, r);
        if (node.r != null && node.val <= r) findInBetweenExclusive(node.r, l, r);
    }

    public static void findInBetweenInclusive(Node node, int l, int r) {
        if (node == null || l > r) return;
        if (node.val >= l && node.val <= r) System.out.print(node.val+ " ");
        if (node.l != null && node.val >= l) findInBetweenExclusive(node.l, l, r);
        if (node.r != null && node.val <= r) findInBetweenExclusive(node.r, l, r);
    }

    public static boolean isBST(Node node, int min, int max) {
        if (node == null) return true;
        if ( node.val < min || node.val > max) return false;
        boolean l = isBST(node.l, min, node.val);
        boolean r = isBST(node.r, node.val, max);
        return l && r;
    }

    public static Node buildTreeFromLOData(int[] data, int i) {
        if (data == null || data.length == 0 || i >= data.length) return null;
        Node node = new Node(data[i]);
        node.l = buildTreeFromLOData(data, 2*i+1);
        node.r = buildTreeFromLOData(data, 2*i+2);
        return node;
    }

    public static void levelOrder(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int size = queue.size();
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.print(node.val + " ");
            if (node.l != null) queue.offer(node.l);
            if (node.r != null) queue.offer(node.r);
            size--;
            if (size == 0) {
                System.out.println("");
                size = queue.size();
            }
        }
    }



    public static void main(String[] args) {
        Integer[] array = {0,2,4,6,8,10,11,12,14,16,18,20};
        Node root = buildTreeFromIOData(array, 0, array.length-1);
        printInOrder(root);
        System.out.println();
        findInBetweenExclusive(root, 10, 17);
        System.out.println();
        findInBetweenInclusive(root, 10, 17);
        System.out.println();
        System.out.println("isBST==>" + isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));

        int[][] valuesArray = {
                {20,18,22,17,19,21,25},//Valid
                {20,18,22,17,23,21,25},//Invalid
                {25,18,22,17,23,21,20}//Invalid
        };
        for (int[] data : valuesArray) {
            Node treeNode = buildTreeFromLOData(data, 0);
            levelOrder(treeNode);
            System.out.println("--------------------------------");
            System.out.println(Arrays.toString(data)+ " isBST==>" + isBST(treeNode, Integer.MIN_VALUE, Integer.MAX_VALUE));
        }

    }
}

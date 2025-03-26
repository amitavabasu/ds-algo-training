package practice.repeat3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class RightViewOfBinaryTree {

    public static class Node {
        int value;
        Node left;
        Node right;
        public Node(int val) {this.value = val;}
    }


    public static Node buildBinaryTree(int[] arr, int l, int r) {
        if (l > r) return null;
        int mid = (l + r) / 2;
        Node node = new Node(arr[mid]);
        node.left = buildBinaryTree(arr, l, mid-1);
        node.right = buildBinaryTree(arr, mid+1, r);
        return node;
    }

    public static List<Integer> rightViewBFS(Node root) {
        if (root == null) return Collections.emptyList();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> rightView = new ArrayList<>();
        int queueSize = queue.size();
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            queueSize--;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
            if (queueSize == 0) {
                rightView.add(node.value);
                queueSize = queue.size();
            }
        }
        return rightView;
    }

    public static List<Integer> rightViewDFS(Node node) {
        int level = 0;
        Map<Integer, Integer> map = new HashMap<>();
        if (node == null) return Collections.emptyList();
        rightViewDSFRec(node, level, map);
        return new ArrayList<>(map.values());
    }

    public static void rightViewDSFRec(Node node, int level, Map<Integer, Integer> map) {
        if (node == null) return;
        if (map.getOrDefault(level, null) == null) map.put(level, node.value);
        level++;
        rightViewDSFRec(node.right, level, map);
        rightViewDSFRec(node.left, level, map);
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        Node root = buildBinaryTree(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(rightViewBFS(root).toArray()));
        System.out.println(Arrays.toString(rightViewDFS(root).toArray()));
    }
}

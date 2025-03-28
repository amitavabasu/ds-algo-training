package practice.repeat3;

import com.sun.source.tree.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class TopViewOfBinaryTree {
    public static Node buildTreeFixed() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);
        root.left.left.right = new Node(7);
        root.left.left.right.left = new Node(8);
        root.left.left.right.left.left = new Node(9);
        root.right.right.left = new Node(10);
        return root;
    }// 9, 4, 2, 1, 3, 6

    public static Node buildTreeFromArrays(Integer[][] arrays) {
        if (arrays == null || arrays.length == 0 || arrays[0].length == 0) return null;
        Queue<Node> queue = new LinkedList<>(List.of(new Node(arrays[0][0])));
        Node root = queue.peek();
        for (int i = 1; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j += 2) {
                Node node = queue.remove();
                if (arrays[i][j] != null) queue.offer(node.left = new Node(arrays[i][j]));
                if (arrays[i][j + 1] != null) queue.offer(node.right = new Node(arrays[i][j + 1]));
            }
        }
        return root;
    }

    public static Node buildTreeFromArrays2(Integer[][] arrays) {
        if (arrays == null || arrays.length == 0 || arrays[0].length == 0) return null;
        Queue<Node> queue = new LinkedList<>(List.of(new Node(arrays[0][0])));
        Node root = queue.peek();
        for (int i = 1; i < arrays.length; i++)
            for (int j = 0; j < arrays[i].length; j += 2) {
                Node node = queue.remove();
                if (arrays[i][j] != null) queue.offer(node.left = new Node(arrays[i][j]));
                if (arrays[i][j + 1] != null) queue.offer(node.right = new Node(arrays[i][j + 1]));
            }
        return root;
    }

    public static Map<Integer, Integer> getTopView(Node root) {
        Map<Integer, Integer> result = new TreeMap<>();
        getTopViewRec(root, 0, result);
        return result;
    }

    public static void getTopViewRec(Node node, int index, Map<Integer, Integer> result) {
        if (node == null) return;
        if (!result.containsKey(index)) result.put(index, node.val);
        if (node.left != null) getTopViewRec(node.left, index - 1, result);
        if (node.right != null) getTopViewRec(node.right, index + 1, result);
    }

    public static void main(String[] args) {
        Integer[][] treeAsArrays = {
                {1},
                {2, 3},
                {4, 5, null, 6},
                {null, 7, null, null, 10, null},
                {8, null, null, null},
                {9, null}
        };

        Node root = buildTreeFixed();
        Map<Integer, Integer> topView = getTopView(root);
        System.out.println(topView);

        root = buildTreeFromArrays(treeAsArrays);
        topView = getTopView(root);
        System.out.println(topView);

        root = buildTreeFromArrays2(treeAsArrays);
        topView = getTopView(root);
        System.out.println(topView);

    }

    public static class Node {
        Integer val;
        Node left;
        Node right;

        public Node(Integer val) {
            this.val = val;
        }
    }
}

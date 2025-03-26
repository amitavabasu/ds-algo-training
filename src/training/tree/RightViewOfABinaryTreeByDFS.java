package training.tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RightViewOfABinaryTreeByDFS {

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
    } //<-- Right View ==> [1, 3, 6, 7, 8]

    public static void rightView(Node root, int level, Map<Integer, Integer> rightViewMap) { //ROOT --> RIGHT --> LEFT
        if (root == null) {
            return;
        }
        if (!rightViewMap.containsKey(level)) rightViewMap.put(level, root.value);
        level++;
        rightView(root.right, level, rightViewMap);
        rightView(root.left, level, rightViewMap);
    }

    public static void main(String[] args) {
        Node root =  buildTree();
        Map<Integer, Integer> rightViewMap = new HashMap<>();
        rightView(root, 0, rightViewMap);
        System.out.println("Right View ==> " + Arrays.toString(rightViewMap.values().toArray()));
    }
}

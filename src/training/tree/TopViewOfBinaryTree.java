package training.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TopViewOfBinaryTree {

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
        root.left.left.right.left.left = new Node(9);
        root.right.right.left = new Node(10);
        return root;
    }// 9, 4, 2, 1, 3, 6

    public static Collection<Node> generateTopViewDFS(Node root) {
        if (root == null) return Collections.emptyList();
        Map<Integer, Node> map = new HashMap<>();
        recursiveDFS(root, map, 0);
        Set<Integer> keySet = map.keySet();
        ArrayList<Integer> keys = new ArrayList<Integer>(keySet);
        Collections.sort(keys);
        List<Node> result = new ArrayList<>(keys.size());
        for (Integer key : keys) {
            result.add(map.get(key));
        }
        return result;
    }

    public static void recursiveDFS(Node root, Map<Integer, Node> map, int index) {
        if (root == null) return;
        if (!map.containsKey(index)) map.put(index, root);
        recursiveDFS(root.left, map, index - 1);
        recursiveDFS(root.right, map, index + 1);
    }

    public static void main(String[] args) {
        Node root =  buildTree();
//        List<Node> result = generateTopBFS(root);
//        for (Node node : result) {
//            System.out.print(node.value + " ");
//        }
//        System.out.println("");

        Collection<Node> rView = generateTopViewDFS(root);
        for (Node node : rView) {
            System.out.print(node.value + " ");
        }
        System.out.println("");


    }
}

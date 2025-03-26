package practice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class RightViewOfBinaryTree {

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
        return root;
    }

    public static List<Node> generateRightViewBFS(Node root) {
        if (root == null) return Collections.emptyList();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int qSize = queue.size();
        List<Node> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
            qSize--;
            if(qSize == 0){
                result.add(current);
                qSize = queue.size();
            }
        }
        return result;
    }

    public static Collection<Node> generateRightViewDFS(Node root) {
        if (root == null) return Collections.emptyList();
        Map<Integer, Node> rViewMap = new HashMap<>();
        recursiveDFS(root, rViewMap, 0);
        return rViewMap.values();
    }

    public static void recursiveDFS(Node root, Map<Integer, Node> rViewMap, int level) {
        if (root == null) return;
        if (!rViewMap.containsKey(level)) rViewMap.put(level, root);
        level++;
        recursiveDFS(root.right, rViewMap, level);
        recursiveDFS(root.left, rViewMap, level);
    }

    public static void main(String[] args) {
        Node root =  buildTree();
        List<Node> result = generateRightViewBFS(root);
        for (Node node : result) {
            System.out.print(node.value + " ");
        }
        System.out.println("");

        Collection<Node> rView = generateRightViewDFS(root);
        for (Node node : rView) {
            System.out.print(node.value + " ");
        }
        System.out.println("");


    }
}

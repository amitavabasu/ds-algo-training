package training.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTraversalOfABinaryTree {

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
    }

    public static Queue<Node> queue = new LinkedList();
    public static ArrayList<Integer[]> levelOrder(Node root) {
        if(root == null){
            return new ArrayList<>();
        }
        ArrayList<Integer[]> result = new ArrayList<>();
        queue.offer(root);
        int numberOfNodesInLevel = queue.size();
        Integer[] nodeValues = new Integer[numberOfNodesInLevel];
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            nodeValues[nodeValues.length - numberOfNodesInLevel] = currentNode.value;
            numberOfNodesInLevel--;
            if(currentNode.left != null) { queue.offer(currentNode.left);}
            if(currentNode.right != null) {queue.offer(currentNode.right);}
            if(numberOfNodesInLevel == 0) {
                //Finished one level
                result.add(nodeValues);
                numberOfNodesInLevel = queue.size();
                nodeValues = new Integer[numberOfNodesInLevel];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Node root =  buildTree();
        ArrayList<Integer[]> result = levelOrder(root);
        System.out.println("Breadth First Search ==>");
        for (Integer[] levelNodes : result) {
            System.out.println(Arrays.toString(levelNodes));
        }
    }
}

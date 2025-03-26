package training.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RightViewOfABinaryTreeByBFS {

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
    public static ArrayList<Integer> rightView(Node root) {
        if(root == null){
            return new ArrayList<>();
        }
        queue.offer(root);
        int numberOfNodesInLevel = queue.size();
        ArrayList<Integer> rightViewNodes = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            numberOfNodesInLevel--;
            if(currentNode.left != null) { queue.offer(currentNode.left);}
            if(currentNode.right != null) {queue.offer(currentNode.right);}
            if(numberOfNodesInLevel == 0) {
                //Finished one level
                numberOfNodesInLevel = queue.size();
                rightViewNodes.add(currentNode.value);
            }
        }
        return rightViewNodes;
    }

    public static void main(String[] args) {
        Node root =  buildTree();
        ArrayList<Integer> result = rightView(root);
        System.out.println("Right View ==> " + Arrays.toString(result.toArray()));
    }
}

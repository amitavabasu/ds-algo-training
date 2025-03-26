package training.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ValidateBST {

    public static class Node {
        int value;
        Node left;
        Node right;
        public Node (int value) {
            this.value = value;
        }
    }

    public static Node buildBST(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        Node root = new Node(values[0]);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < values.length) {
            int val = values[i];
            Node node = queue.poll();
            node.left = new Node(val);
            queue.offer(node.left);
            i++;
            if (i < values.length) {
                val = values[i];
                node.right = new Node(val);
                queue.offer(node.right);
                i++;
            }
        }
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

    public static boolean isValidBST(Node root, int min, int max) {
        if (min >= root.value || root.value >= max) {
            return false;
        }
        if (root.left != null) {
            if (!isValidBST(root.left, min, root.value)) {
                return false;
            }
        }
        if (root.right != null) {
            if (!isValidBST(root.right, root.value, max)) {
                return false;
            }
        }
        return true;
    }



    public static void main(String[] args) {
        int[][] valuesArray = {
                {20,18,22,17,19,21,25},//Valid
                {20,18,22,17,23,21,25},//Invalid
                {25,18,22,17,23,21,20}//Invalid
        };
        for (int[] values : valuesArray) {
            Node root = buildBST(values);
            ArrayList<Integer[]> result = levelOrder(root);
            for (Integer[] levelNodes : result) {
                System.out.println(Arrays.toString(levelNodes));
            }
            System.out.println("--------------------------------" + isValidBST(root,Integer.MIN_VALUE, Integer.MAX_VALUE));
        }
    }





}

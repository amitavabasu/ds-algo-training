package training.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CountNumberOfNodesInACompleteBinaryTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
    }

    public static Node buildCompleteBinaryTree(int levels, int leafNodeCount) {
        Node root = new Node();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int parentsSize = queue.size();
        for (int i = 1; i < levels-1; i++) {
            while (parentsSize > 0) {
                Node parent = queue.poll();
                parent.left = new Node();
                parent.right = new Node();
                queue.offer(parent.left);
                queue.offer(parent.right);
                parentsSize--;
            }
                parentsSize = queue.size();
        }
        while (leafNodeCount > 0) {
            Node parent = queue.poll();
            parent.left = new Node();
            leafNodeCount--;
            if(leafNodeCount > 0) {
                parent.right = new Node();
                leafNodeCount--;
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

    public static int findCompleteBinaryTreeDepth(Node root) {
        if(root == null) {
            return 0;
        }
        int depth = 1;
        Node node = root.left;
        while (node != null) {
            depth++;
            node = node.left;
        }
        return depth;
    }

    public static int countCompleteTreeNodesInOLogN(Node root) {
        int completeTreeDepth = findCompleteBinaryTreeDepth(root);
        int numberOfNodesInTreeExceptLastLevel = (int)Math.pow(2,(completeTreeDepth-1)) -1;
        System.out.println("numberOfNodesInTreeExceptLastLevel ==> " + numberOfNodesInTreeExceptLastLevel);
        int numberOfNodesAtLastLevel = 1;
        int maxNumberOfNodesAtLastLevel = (int)Math.pow(2, (completeTreeDepth-1));
        int lIndex = 0, rIndex = maxNumberOfNodesAtLastLevel-1;
        while (lIndex <= rIndex) {
            int mid = (int) Math.ceil((double) (lIndex + rIndex) / 2);
            boolean exists = findIfExistsInLastLevel(root, mid, 0, (maxNumberOfNodesAtLastLevel-1));
            if (exists) {
                numberOfNodesAtLastLevel = (mid + 1);
                lIndex = mid + 1;
            } else {
                rIndex = mid - 1;
            }
        }


        return numberOfNodesInTreeExceptLastLevel + numberOfNodesAtLastLevel;
    }

    public static boolean findIfExistsInLastLevel(Node root, int indexToFind, int start, int end) {
        Node node = root;
        while (start <= end) {
            int mid = (int) Math.ceil((double) (start + end) / 2);
            if (indexToFind < mid) {
                node = node.left;
                end = mid - 1;
            } else {
                node = node.right;
                start = mid + 1;
            }
        }
          return ( node != null);
    }

    public static void main(String[] args) {
        Node root =  buildCompleteBinaryTree(4, 5);
        ArrayList<Integer[]> result = levelOrder(root);
        System.out.println("Tree ==>");
        for (Integer[] levelNodes : result) {
            System.out.println(Arrays.toString(levelNodes));
        }
        System.out.println("Depth = " + findCompleteBinaryTreeDepth(root));
        System.out.println("countCompleteTreeNodesInOLogN ==> " + countCompleteTreeNodesInOLogN(root));
    }

}

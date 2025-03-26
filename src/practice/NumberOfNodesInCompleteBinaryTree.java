package practice;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfNodesInCompleteBinaryTree {
    public static class Node {
        char val = 'O';
        Node left;
        Node right;
    }

    public static Node buildCompleteTree(int depth, int leafNodeCount) {
        Node root = new Node();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int size = queue.size();
        for (int i = 1; i < depth - 1; i++) {
            while (size > 0) {
                Node node = queue.remove();
                node.left = new Node();
                node.right = new Node();
                queue.offer(node.left);
                queue.offer(node.right);
                size--;
            }
            size = queue.size();
        }
        while (!queue.isEmpty() && leafNodeCount > 0) {
            Node node = queue.remove();
            node.left = new Node();
            leafNodeCount--;
            if (leafNodeCount > 0) {
                node.right = new Node();
                leafNodeCount--;
            }
        }
        return root;
    }

    public static void display(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int size = queue.size();
        while(!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.print(node.val + " ");
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
            size--;
            if(size == 0) {
                System.out.println("");
                size = queue.size();
            }
        }
    }

    public static int findHeightOfCompleteBinaryTree(Node root) {
        int height = 0;
        if (root == null) return height;
        height++;
        while (root.left != null) {
            root = root.left;
            height++;
        }
        return height;
    }

    public static boolean isExists(Node root, int v, int h, int m) {
        int l = 0;
        int r = m;
        for (int i = 1; i < h; i++) {
            int vBar = (int)Math.ceil(((double)l+(double)r)/2);
            if( v >= vBar) {
                root = root.right;
                l = vBar;
            } else {
                root = root.left;
                r = vBar-1;
            }
        }
        return root != null;
    }
    public static int countNodes(Node root) {
        int count = 0;
        if(root == null) return 0;
        int height = findHeightOfCompleteBinaryTree(root);
        int n = height-1;
        int noOfNodesExceptLeafNodes = (int)Math.pow(2,n)-1;
        int l = 0;
        int maxIndex = (int)Math.pow(2,n)-1;
        int r = maxIndex;
        int m = 1;
        while (l <= r) {
            m = (int)Math.ceil(((double)l+(double)r)/2);
            if (isExists(root, m, height, maxIndex)) {
                l = m+1;
            } else {
                r = m;
            }
        }
        count = noOfNodesExceptLeafNodes + (m+1);
        return count;
    }

    public static void main(String[] args) {
        Node root = buildCompleteTree(4, 5);
        display(root);
        System.out.println("height ==> " + findHeightOfCompleteBinaryTree(root));
        System.out.println("Node count ==> " + countNodes(root));
    }

}

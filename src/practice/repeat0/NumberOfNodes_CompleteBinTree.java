package practice.repeat0;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfNodes_CompleteBinTree {

    public static class Node {
        char val = 'O';
        Node left;
        Node right;
    }

    public static Node buildCompleteTree(int depth, int lastLevelNodes) {
        if (depth == 0) return null;
        Node root = new Node();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int size = queue.size();
        for (int i = 2; i < depth; i++) {
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
        int i = 0;
        while (i < lastLevelNodes) {
            if (!queue.isEmpty()) {
                Node node = queue.remove();
                node.left = new Node();
                i++;
                if ( i < lastLevelNodes) {
                    node.right = new Node();
                    i++;
                }
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

    public static boolean isExists(int d, Node root, int l, int r, int indexToCheck) {
        for( int i = 1; i < d; i++) {
            int m = (int)Math.ceil(((double)l + (double)r) / 2);
            if (indexToCheck >= m) {
                root = root.right;
                l = m;
            } else {
                root = root.left;
                r = m-1;
            }
        }
        return root != null;
    }

    public static int countNodes(int depth, Node root) {
        int numberOfNodeExceptLastLevel = (int)Math.pow(2, (depth-1)) - 1;
        int l = 0;
        int maxIndex = (int)Math.pow(2, (depth-1)) -1;
        int r = maxIndex;
        int lastFoundIndex = -1;
        while (l <= r) {
            int m = (int)Math.ceil(((double)l + (double)r)/2);
            if (isExists(depth, root, 0, maxIndex, m)) {
                l = m+1;
                lastFoundIndex = m;
            } else {
                r = m-1;
            }
        }
        return numberOfNodeExceptLastLevel + lastFoundIndex + 1;
    }



    public static void main(String[] args) {
        Node root = buildCompleteTree(4, 1);
        display(root);
        System.out.println("Node count ==> " + countNodes(4, root));
    }
}

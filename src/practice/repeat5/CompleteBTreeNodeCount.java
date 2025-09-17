package practice.repeat5;

import java.util.LinkedList;
import java.util.Queue;

public class CompleteBTreeNodeCount {
    public static class Node {
        int val;
        Node left;
        Node right;
    }

    public static Node buildTree(int levels, int nodeCountLastLevel) {
        if (levels == 0) return null;
        Node root = new Node();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int levelSize = queue.size();
        for (int i = 1; i < levels-1; i++) {
            while (!queue.isEmpty()) {
                Node node = queue.remove();
                levelSize--;
                node.left = new Node();
                queue.add(node.left);
                node.right = new Node();
                queue.add(node.right);
                if (levelSize == 0) {
                    levelSize = queue.size();
                    break;
                }
            }
        }
        while (nodeCountLastLevel > 0 && !queue.isEmpty()) {
            Node node = queue.remove();
            node.left = new Node();
            nodeCountLastLevel--;
            if (nodeCountLastLevel > 0) {
                node.right = new Node();
                nodeCountLastLevel--;
            }
        }
        return root;
    }

    public static void levelOrder(Node root) {
        if (root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int lSize = queue.size();
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.print("*");
            lSize--;
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
            if (lSize == 0) {
                System.out.println();
                lSize = queue.size();
            }
        }
        System.out.println();
    }

    public static int height(Node root) {
        if (root == null) return 0;
        int depth = 1;
        Node node = root;
        while (node.left != null) {
            node = node.left;
            depth++;
        }
        System.out.println(depth);
        return depth;
    }

    public static int numberOfNodes(Node root) {
        int height = height(root);
        int topNodeCount = (int)Math.pow( 2, (height-1))-1;
        System.out.println(topNodeCount);
        int lastLevelCount = 0;
        int min = 0;
        int maxIndex = (int)Math.pow(2, height-1)-1;
        int max = maxIndex;
        while ( min <= max) {
            int mid = (int)Math.ceil((double)(min+max)/2);
            if (nodeExists(root, mid, 0, maxIndex)) {
                lastLevelCount = mid+1;
                min = mid + 1;
            } else {
                max = mid-1;
            }
        }
        return topNodeCount + lastLevelCount;
    }

    public static boolean nodeExists(Node root, int target, int min, int max) { // 4, 0, 7
        Node node = root;
        while (min <= max) {
            int mid = (int)Math.ceil((double)(min+max)/2); // 4, 0, 7, 4; 4, 5, 7, 6; 4, 5, 5, 5; 4, 5, 4, 5.
            if (target  < mid ) {
                node = node.left;//2, 3
                max = mid-1;//4, 5, 5; 4, 3, 5
            } else {
                node = node.right; //1
                min = mid+1;// 4, 5, 7;
            }
        }
        return (node != null) ;
    }




    public static void main(String[] args) {
        Node root = buildTree(4, 5);
        levelOrder(root);
        numberOfNodes(root);

    }




}

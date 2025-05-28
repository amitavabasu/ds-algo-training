package practice.repeat4;

import java.util.LinkedList;
import java.util.Queue;

public class DepthAndWidth {

    public static class Node {
        int val;
        Node l;
        Node r;
        public Node(int val) { this.val = val; }
    }

    public static Node buildTreeFromLOData(Integer[] data, int i) {
        if (i < data.length && data[i] !=null) {
            Node node = new Node(data[i]);
            node.l = buildTreeFromLOData(data, 2*i+1);
            node.r = buildTreeFromLOData(data, 2*i+2);
            return node;
        } else {
            return null;
        }
    }

    public static int height(Node node, int h) {
        if (node == null) return h;
        h++;
        return Math.max(height(node.l, h), height(node.r, h));
    }

    public static int width(Node root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int count = queue.size();
        int width = count;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            count--;
            if (node.l != null) queue.offer(node.l);
            if (node.r != null) queue.offer(node.r);
            if (count == 0) {
                if (width < queue.size()) width = queue.size();
                count = queue.size();
            }
        }
        return width;
    }


    public static void main(String[] args) {
        Integer[] treeData = {9,3,11,1,6,7,8,15,10,19,null,20,16,20,25,50,2};
        Node root = buildTreeFromLOData(treeData, 0);
        System.out.println("Height==>" + height(root, 0));
        System.out.println("Width==>" + width(root));
    }
}

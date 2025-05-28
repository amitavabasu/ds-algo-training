package practice.repeat4;

public class FindAllInBetweenAndValidateBST {

    public static class Node {
        int val;
        Node l;
        Node r;
        public Node(int val) { this.val = val;}
    }



    public static Node buildTreeFromIOData(Integer[] data, int l, int r) {
        if ( l < r) {
            int m = (l + r) / 2;
            if (data[m] == null) return null;
            Node node = new Node(data[m]);
            node.l = buildTreeFromIOData(data, l, m-1);
            node.r = buildTreeFromIOData(data, m+1, r);
            return node;
        } else {
            return null;
        }
    }

    public static void printInOrder(Node root) {
        if (root == null) return;
        if (root.l != null) printInOrder(root.l);
        System.out.println(root.val);
        if (root.r != null) printInOrder(root.r);
    }


    public static void main(String[] args) {
        Integer[] array = {0,2,4,6,8,10,12,14,16,18,20, 11};
        Node root = buildTreeFromIOData(array, 0, array.length-1);
        printInOrder(root);
    }
}

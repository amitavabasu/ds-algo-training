package practice.repeat5;


import java.util.*;

public class AllTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int value) {
            this.value = value;
        }
    }

    public static Node buildTree() {
        Node root = new Node(9);
        root.left = new Node(4);
        root.right = new Node(20);
        root.left.left = new Node(1);
        root.left.right = new Node(6);
        root.right.left = new Node(15);
        root.right.right = new Node(170);
        return root;
    }

    public static Node buildTree(Integer[] array, int index) {
        if (index >= array.length) return null;
        Node node = new Node(array[index]);
        node.left = buildTree(array, index*2+1);
        node.right = buildTree(array, index*2+2);
        return node;
    }

    //PreOrder: Root - Left - right
    //InOrder: Left - Root - Right
    //PostOrder: Left - Right - Root

    public static List<Integer> preOrder(Node node, List<Integer> result) {
        if (node == null)  return Collections.emptyList();
        result.add(node.value);
        if (node.left != null) preOrder(node.left, result);
        if (node.right != null) preOrder(node.right, result);
        return result;
    }

    public static List<Integer> inOrder(Node node, List<Integer> result) {
        if (node == null) return Collections.emptyList();
        if (node.left != null) inOrder(node.left, result);
        result.add(node.value);
        if (node.right != null) inOrder(node.right, result);
        return result;
    }

    public static List<Integer> postOrder(Node node, List<Integer> result) {
        if (node == null) return Collections.emptyList();
        if (node.left != null) postOrder(node.left, result);
        if (node.right != null) postOrder(node.right, result);
        result.add(node.value);
        return result;
    }

    public static List<Integer> levelOrder(Node node) {
        if (node == null) return Collections.emptyList();
        List<Integer> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()) {
            Node current = queue.remove();
            result.add(current.value);
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        return result;
    }



    public static void main(String[] args) {
        Node root = buildTree();
        System.out.println("Pre Order: " + Arrays.toString(preOrder(root, new ArrayList()).toArray()));
        System.out.println("In Order: " + Arrays.toString(inOrder(root, new ArrayList<>()).toArray()));
        System.out.println("Post Order: " + Arrays.toString(postOrder(root, new ArrayList()).toArray()));
        System.out.println("Level Order: " + Arrays.toString(levelOrder(root).toArray()));

        System.out.println("-------------");

        Integer[] treeDataLevelOrder = {9, 4,20, 1,6,15,170 };
        Node rootNode = buildTree(treeDataLevelOrder, 0);
        System.out.println("Pre Order: " + Arrays.toString(preOrder(rootNode, new ArrayList()).toArray()));
        System.out.println("In Order: " + Arrays.toString(inOrder(rootNode, new ArrayList<>()).toArray()));
        System.out.println("Post Order: " + Arrays.toString(postOrder(rootNode, new ArrayList()).toArray()));
        System.out.println("Level Order: " + Arrays.toString(levelOrder(root).toArray()));
    }


}

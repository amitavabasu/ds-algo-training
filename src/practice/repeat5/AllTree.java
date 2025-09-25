package practice.repeat5;


import java.util.*;
import java.util.stream.Collectors;

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
        root.left.right.right = new Node(8);
        return root;
    }

    //PreOrder: Root - Left - right
    //InOrder: Left - Root - Right
    //PostOrder: Left - Right - Root

    public static List<Integer> preOrder(Node node, List<Integer> result) {
        if (node == null) return Collections.emptyList();
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
        while (!queue.isEmpty()) {
            Node current = queue.remove();
            result.add(current.value);
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        return result;
    }

    public static List<Integer> topView(Node root) {
        Map<Integer, Integer[]> map = new HashMap<>();
        top(root, map, 0, 0);
        return map.values().stream().map( a -> a[1]).collect(Collectors.toList());
    }

    //pre - order (node - left - right)
    public static void top(Node node, Map<Integer, Integer[]> map, int index, int level) {
        if (!map.containsKey(index)) {
            map.put(index, new Integer[]{level, node.value});
        } else {
            Integer[] entry = map.get(index);
            int l = entry[0];
            if (level < l) {
                map.put(index, new Integer[]{level, node.value});
            }
        }
        if (node.left != null) top(node.left, map, index - 1, level+1);
        if (node.right != null) top(node.right, map, index + 1, level+1);
    }

    public static List<Integer> bottomView(Node root) {
        Map<Integer, Integer[]> map = new HashMap<>();
        bottom(root, map, 0, 0);
        return map.values().stream().map( a -> a[1]).collect(Collectors.toList());
    }

    //post-order (left - right - node)
    public static void bottom(Node node, Map<Integer, Integer[]> map, int index, int level) {
        if (!map.containsKey(index)) {
            map.put(index, new Integer[]{level, node.value});
        } else {
            Integer[] entry = map.get(index);
            int l = entry[0];
            if (level > l) {
                map.put(index, new Integer[]{level, node.value});
            }
        }
        if (node.right != null) bottom(node.right, map, index + 1, level+1);
        if (node.left != null) bottom(node.left, map, index - 1, level+1);
    }


    public static List<Integer> leftView(Node root) {
        Map<Integer, Integer> map = new HashMap<>();
        left(root, map, 0);
        return new ArrayList<>(map.values());
    }


    // pre-order (left first) (node - left - right)
    public static void left(Node node, Map<Integer, Integer> map, int level) {
        if (node != null) {
            if (map.getOrDefault(level, null) == null) {
                map.put(level, node.value);
            }
            left(node.left, map, level+1);
            left(node.right, map, level+1);
        }
    }

    public static List<Integer> rightView(Node root) {
        Map<Integer, Integer> map = new HashMap<>();
        right(root, map, 0);
        return new ArrayList<>(map.values());
    }

    // pre-order (right first) (node - right - left)
    public static void right(Node node, Map<Integer, Integer> map, int level) {
        if (node != null) {
            if (map.getOrDefault(level, null) == null) {
                map.put(level, node.value);
            }
            right(node.right, map, level+1);
            right(node.left, map, level+1);
        }
    }

    //post order (left - right - node
    public static void transpose(Node node) {
        if (node.left != null) transpose(node.left);
        if (node.right != null) transpose(node.right);
        Node t = node.left;
        node.left = node.right;
        node.right = t;
    }




    public static void main(String[] args) {
        Node root = buildTree();
//        System.out.println("Pre Order: " + Arrays.toString(preOrder(root, new ArrayList()).toArray()));
        System.out.println("In Order: " + Arrays.toString(inOrder(root, new ArrayList<>()).toArray()));
//        System.out.println("Post Order: " + Arrays.toString(postOrder(root, new ArrayList()).toArray()));
//        System.out.println("Level Order: " + Arrays.toString(levelOrder(root).toArray()));
//
//        System.out.println("Top: " + Arrays.toString(topView(root).toArray()));
//        System.out.println("Left: " + Arrays.toString(leftView(root).toArray()));
//        System.out.println("Right: " + Arrays.toString(rightView(root).toArray()));
//        System.out.println("Bottom: " + Arrays.toString(bottomView(root).toArray()));
        transpose(root);
        System.out.println("In Order: " + Arrays.toString(inOrder(root, new ArrayList<>()).toArray()));

    }


}

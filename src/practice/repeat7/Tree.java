package practice.repeat7;

import practice.repeat5.AllTree;

import java.util.*;
import java.util.stream.Collectors;

public class Tree {

    public static class Node {
        int value;
        Node left;
        Node right;
        public Node(int val) {this.value = val;}
    }

    public static Node buildBalancedBinaryTree(int[] data, int l, int r) {
        if (data == null || data.length == 0) {
            return null;
        }
        if (l <= r) {
            int m = (l+r)/2;
            Node node = new Node(data[m]);
            node.left = buildBalancedBinaryTree(data, l, m-1);
            node.right = buildBalancedBinaryTree(data, m+1, r);
            return node;
        }
        return null;
    }

    public static void printLevelOrder(Node root) {
        if (root != null) {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            int qSize = queue.size();
            while (!queue.isEmpty()) {
                Node node = queue.remove();
                System.out.print (node.value + " ");
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
                qSize--;
                if (qSize == 0) {
                    System.out.println();
                    qSize = queue.size();
                }
            }
        }
    }


    public static void printInOrder(Node root) {
        if (root != null) {
            if (root.left!= null) printInOrder(root.left);
            System.out.print(root.value+" ");
            if (root.right!= null) printInOrder(root.right);
        }
    }

    public static void printInOrderReverse(Node root) {
        if (root != null) {
            if (root.right!= null) printInOrderReverse(root.right);
            System.out.print(root.value+" ");
            if (root.left!= null) printInOrderReverse(root.left);
        }
    }

    //Order irrelevant
    public static Node clone(Node node) {
        if (node == null) return null;
        Node newNode = new Node(node.value);
        newNode.left = clone(node.left);
        newNode.right = clone(node.right);
        return newNode;
    }

    //post order (L - R - N)
    public static Node transposeClone(Node node) {
        if (node == null) return null;
        Node newNode = new Node(node.value);
        newNode.left = transposeClone(node.right);
        newNode.right = transposeClone(node.left);
        return newNode;
    }

    public static List<Integer> topView(Node root) {
        Map<Integer, Integer[]> result = new HashMap<>();
        if (root != null) {
            topViewRec(root, result, 0, 0);
        }
        return result.values().stream().map(a -> a[1]).collect(Collectors.toList());
    }

    //pre-order (node - left - right
    public static void topViewRec(Node node, Map<Integer, Integer[]> map, int index, int level) {
        if (!map.containsKey(index)) {
            map.put(index, new Integer[]{level, node.value});
        } else {
            Integer[] item = map.get(index);
            int previousLevel = item[0];
            if (level < previousLevel) {
                map.put(index, new Integer[]{level, node.value});
            }
        }
        if (node.left != null) topViewRec(node.left, map, index-1, level+1);
        if (node.right != null) topViewRec(node.right, map, index+1, level+1);
    }

    public static List<Integer> leftView(Node root) {
        List<Node> result = new LinkedList<>();
        if (root!= null) {
            leftViewRec(root, result, 0);
        }
        return result.stream().map(a -> a.value).toList();
    }
    public static int maxLevel = -1;
    //pre-order left first (node - left - right)
    public static void leftViewRec(Node node, List<Node> list, int level) {
        if (maxLevel < level) {
            maxLevel = level;
            list.add(node);
        }
        if (node.left != null) leftViewRec(node.left, list, level+1);
        if (node.right != null) leftViewRec(node.right, list, level+1);
    }

    public static List<Integer> rightView(Node root) {
        Map<Integer, Node> map = new HashMap<>();
        if (root != null) {
            rightViewRec(root, map, 0);
        }
        return map.values().stream().map(a -> a.value).collect(Collectors.toList());
    }

    //pre-order right first
    public static void rightViewRec(Node node, Map<Integer, Node> map, int level) {
        if (!map.containsKey(level)) map.put(level, node);
        if (node.right != null) rightViewRec(node.right, map, level+1);
        if (node.left != null) rightViewRec(node.left, map, level+1);
    }




    public static void main(String[] args) {
        int[] data = {0,1,2,3,4,5,6,7,8,9};
        Node root = buildBalancedBinaryTree(data, 0, data.length-1);
        printInOrder(root);
        System.out.println();
//        printInOrderReverse(root);
//        System.out.println();
        Node clone = clone(root);
        printInOrder(clone);
        System.out.println();
        Node transposedClone = transposeClone(root);//***********
        printInOrder(transposedClone);
        System.out.println();
        printLevelOrder(clone);
        System.out.println(topView(root));
        System.out.println(leftView(root));
        System.out.println(rightView(root));
    }

}

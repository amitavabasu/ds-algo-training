package practice.repeat4;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class LeftRightTopViewOfATree {

    public static class Node {
        int value;
        Node left;
        Node right;
        public Node(int val) { this.value = val;}
    }

    public static Node buildTree(Integer[] data, int i) {
        if (data == null || data.length == 0 || i >= data.length || data[i] == null) return null;
        Node node = new Node(data[i]);
        node.left = buildTree(data, 2*i+1);
        node.right = buildTree(data, 2*i+2);
        return node;
    }

    public static List<Integer> topView(Node root) {
        Map<Integer, Integer[]> top = new TreeMap<>();
        topViewRec(root, top, 0, 0);
        return top.values().stream().map(a -> a[1]).collect(Collectors.toList());
    }

    public static void topViewRec(Node node, Map<Integer, Integer[]> top, int index, int level) {
        if (node == null) return;
        if (!top.containsKey(index)) {
            top.put(index, new Integer[]{level,node.value});
        } else {
            Integer[] entry = top.get(index);
            int l = entry[0];
            if (level < l) {
                top.put(index, new Integer[]{level,node.value});
            }
        }
        if (node.left != null) topViewRec(node.left, top, index-1, level+1);
        if (node.right != null) topViewRec(node.right, top, index+1, level+1);
    }

    public static List<Integer> leftView(Node node) {
        Map<Integer, Integer> lv = new TreeMap<>();
        leftViewRec(node, lv, 0);
        return lv.values().stream().toList();
    }

    public static void leftViewRec(Node node, Map<Integer, Integer> lv, int level) {
        if (node == null) return;
        if (!lv.containsKey(level)) lv.put(level, node.value);
        level++;
        leftViewRec(node.left, lv, level);
        leftViewRec(node.right, lv, level);
    }

    public static List<Integer> rightView(Node node) {
        Map<Integer, Integer> lv = new TreeMap<>();
        rightViewRec(node, lv, 0);
        return lv.values().stream().toList();
    }

    public static void rightViewRec(Node node, Map<Integer, Integer> lv, int level) {
        if (node == null) return;
        if (!lv.containsKey(level)) lv.put(level, node.value);
        level++;
        rightViewRec(node.right, lv, level);
        rightViewRec(node.left, lv, level);
    }


    public static void main(String[] args){
        Integer[] data = {8,3,20,2,7,null,28,0,1,5,6};
        //TopView = 0,2,3,8,20,28
        //LeftView = 0,2,3,8
        //RightView = 8.20,28,6
        Node root = buildTree(data, 0);
        System.out.println("TopView==>" + Arrays.toString(topView(root).toArray()));
        System.out.println("LeftView==>" + Arrays.toString(leftView(root).toArray()));
        System.out.println("RightView==>" + Arrays.toString(rightView(root).toArray()));
    }



}

package training.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
# BST Merge Into Array

A binary search tree (BST) is a binary tree where, for _every_ node:

- All the values on its **left** subtree are _less than or equal_ to the node's value.
- All the values on its **right** subtree are _greater than or equal_ to the node's value.

Given the roots of two binary search trees, return an array containing all elements from both trees in sorted order. The trees may contain duplicate values.

Example:
root1 =
              5
            /    \
           2      9
            \    / \
             4  9   11
root2 =
              3
            /    \
           2      7
          /      / \
         1      6   8

Output: [1, 2, 2, 3, 4, 5, 6, 7, 8, 9, 9, 11]

Example 2:
root1 =
              2
            /    \
           2      2

root2 =
              2
            /    \
           2      2

Output: [2, 2, 2, 2, 2, 2]

Constraints:

- The number of nodes of each tree is at most `10^5`
- The height of each tree is at most `500`
- The value at each node is between `0` and `10^9`
 */
public class BSTMergeIntoAnArray {
    /*
        Binary Search Tree Traversal + Sorted merge.
     */

    public static class TNode {
        TNode left;
        TNode right;
        int value;
        public TNode(int val) {
            this.value = val;
        }
    }

    public static TNode buildTree1(){
        TNode root = new TNode(5);
        root.left = new TNode(2);
        root.right = new TNode(9);
        root.left.right = new TNode(4);
        root.right.left = new TNode(9);
        root.right.right = new TNode(11);
        return root;
    }

    public static TNode buildTree2(){
        TNode root = new TNode(3);
        root.left = new TNode(2);
        root.right = new TNode(7);
        root.left.left = new TNode(1);
        root.right.left = new TNode(6);
        root.right.right = new TNode(8);
        return root;
    }

    public static void inOrder(TNode node, List<Integer> result) {
        if (node == null) return;
        if (node.left != null) inOrder(node.left, result);
        result.add(node.value);
        if (node.right != null) inOrder(node.right, result);
    }

    public static int[] merge(List<Integer> list1, List<Integer> list2) {
        if (list1 == null && list2 == null) return new int[]{};
        int len1 = list1.size(), len2 = list2.size();
        int[] result = new int[len1+len2];
        int i = 0, j = 0, k = 0;
        while ( i < len1 && j < len2) {
            if (list1.get(i) < list2.get(j)) {
                result[k++] = list1.get(i++);
            } else {
                result[k++] = list2.get(j++);
            }
        }
        while ( i < len1) {
            result[k++] = list1.get(i++);
        }
        while ( j < len2) {
            result[k++] = list2.get(j++);
        }
        return result;
    }


    public static int[] mergeBST(TNode tree1, TNode tree2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        if (tree1 != null) {
            inOrder(tree1, list1);
        }
        if (tree2 != null) {
            inOrder(tree2, list2);
        }
        return merge(list1, list2);
    }


    public static void main(String[] args) {
        TNode tree1 = buildTree1();
        TNode tree2 = buildTree2();
        System.out.println(Arrays.toString(mergeBST(tree1, tree2)));
    }


}

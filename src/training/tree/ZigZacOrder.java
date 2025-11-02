package training.tree;

/*
Hello! Your interview question is below. Write code in this pad just like you would normally – your AI Interviewer will be able to see it.

# Zig-Zag Order

Given a binary tree, return the values of all its nodes in _zig-zag order_. This is similar to a level-order traversal but alternating the direction of the nodes at each level. Nodes at even depth are ordered left to right, and nodes at odd depth are ordered right to left.

Example:

Input:
    1
   / \
  2   3
 / \   \
4   5   6

Output: [1, 3, 2, 4, 5, 6]

https://iio-beyond-ctci-images.s3.us-east-1.amazonaws.com/trees_fig19.png

Constraints:

- The number of nodes is at most `10^5`
- The values at each node are between `0` and `10^9`
 */

import java.util.*;

public class ZigZacOrder {
    public static class TNode {
        int value;
        TNode left;
        TNode right;
        public TNode(int val) {
            this.value = val;
        }
    }

    public static TNode buildTree() {
        TNode root = new TNode(1);
        root.left = new TNode(2);
        root.right = new TNode(3);
        root.left.left = new TNode(4);
        root.left.right = new TNode(5);
        root.right.right = new TNode(6);
        return root;
    }

    public static List<Integer> parseZigZag(TNode root) {
        if (root == null) return Collections.emptyList();
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<TNode> queue = new LinkedList<>();
        queue.add(root);
        int qSize = queue.size();
        int level = 0;
        while (!queue.isEmpty()) {
            TNode node = queue.remove();
            List<Integer> list = map.getOrDefault(level, new ArrayList<>());
            list.add(node.value);
            map.put (level, list);
            qSize--;
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
            if (qSize == 0) {
                qSize = queue.size();
                level++;
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            if ( i % 2 == 0) {
                result.addAll(map.get(i));
            } else {
                List<Integer> list = map.get(i);
                Collections.reverse(list);
                result.addAll(list);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TNode tree = buildTree();
        System.out.println("Result ==> " + parseZigZag(tree));
    }


}

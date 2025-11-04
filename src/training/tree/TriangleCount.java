package training.tree;
/*
    # Triangle Count

    Given the root of a binary tree, return the number of _triangles_.
    A triangle is a set of three distinct nodes, `a`, `b`, and `c`, where:

    1. `a` is the _lowest common ancestor_ of `b` and `c`.
    2. `b` and `c` have the same depth.
    3. the path from `a` to `b` only consists of left children (the nodes in the path can have right children).
    4. the path from `a` to `c` only consists of right children (the nodes in the path can have left children).

    Example 1:
             0
         /       \
        1         2
         \       / \
          3     4   5
         / \   /     \
        6   7 8       9

    Output: 4.
    The triangles are: (0, 1, 2), (3, 6, 7), (2, 4, 5), (2, 8, 9).

    https://iio-beyond-ctci-images.s3.us-east-1.amazonaws.com/trees_fig12.png

    Example 2:
          0
       /      \
      1        4
     /  \       \
    2    3       5
    Output: 3.
    The triangles are: (0, 1, 4), (1, 2, 3), (0, 2, 5).

    Constraints:

    - The number of nodes is at most `10^5`
    - The height of the tree is at most `500`
    - The value at each node doesn't matter.
 */
public class TriangleCount {
    public static class TNode {
        TNode left;
        TNode right;
        int val;
        public TNode(int v) {
            this.val = v;
        }
    }

    public static TNode buildTree0() {
        TNode root = new TNode(0);
        root.left = new TNode(1);
        root.right = new TNode(2);
        return root;
    }

    public static TNode buildTree1() {
    TNode root = new TNode(0);
    root.left = new TNode(1);
    root.right = new TNode(2);
    root.left.right = new TNode(3);
    root.left.right.left = new TNode(6);
    root.left.right.right = new TNode(7);
    root.right.left = new TNode(4);
    root.right.right = new TNode(5);
    root.right.left.left = new TNode(8);
    root.right.right.right = new TNode(9);
    return root;
}

    public static TNode buildTree2() {
        TNode root = new TNode(0);
        root.left = new TNode(1);
        root.right = new TNode(4);
        root.left.left = new TNode(2);
        root.left.right = new TNode(3);
        root.right.right = new TNode(5);
        return root;
    }
    public static int count = 0;
    public static int[]  countTriangleRec(TNode node) {
        int[] lrCounts = {0, 0};
        if (node.left != null) {
            int[] llrCounts = countTriangleRec(node.left);
            lrCounts[0] = llrCounts[0] + 1;
        }
        if (node.right != null) {
            int[] lrrCounts = countTriangleRec(node.right);
            lrCounts[1] = lrrCounts[1] + 1;
        }
        System.out.println(node.val);
        count = count + Math.min(lrCounts[0], lrCounts[1]);
        return lrCounts;
    }

    public static int countTriangle(TNode root) {
        if (root == null || root.left == null || root.right == null) return count;
        countTriangleRec(root);
        return count;
    }

    public static void main(String[] args) {
        TNode tree0 = buildTree0();
        System.out.println("Result ==> " + countTriangle(tree0));
        count = 0;
        TNode tree1 = buildTree1();
        System.out.println("Result ==> " + countTriangle(tree1));
        count = 0;
        TNode tree2 = buildTree2();
        System.out.println("Result ==> " + countTriangle(tree2));
    }
}

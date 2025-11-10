package practice.repeat8;

public class TriangleCountInATree {
    public static class TNode {
        TNode left;
        TNode right;
        int val;
        public TNode(int v){ this.val = v;}
    }
/*
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
 */
    public static TNode buildTree1() {
        TNode root = new TNode(0);
        root.left = new TNode(1);
        root.right = new TNode(2);
        root.left.right = new TNode(3);
        root.left.right.left = new TNode(6);
        root.left.right.right = new TNode(7);
        root.right.left = new TNode(4);
        root.right.left.left = new TNode(8);
        root.right.right = new TNode(5);
        root.right.right.right = new TNode(9);
        return root;
    }

/*
    Example 2:
          0
       /      \
      1        4
     /  \       \
    2    3       5
    Output: 3.
    The triangles are: (0, 1, 4), (1, 2, 3), (0, 2, 5).
*/
    public static Integer count = 0;
    public static Integer countTriangle(TNode node) {
        if (node == null) return 0;
        int[] lr = countTriangleRec(node);
        return count;
    }

    public static int[] countTriangleRec(TNode node) {
        int[] countLR = new int[]{0, 0};
        if (node.left != null) {
            int[] countL = countTriangleRec(node.left);
            countLR[0] = countL[0] + 1;
        }
        if (node.right != null) {
            int[] countR = countTriangleRec(node.right);
            countLR[1] = countR[1] + 1;

        }
        count = count + Math.min(countLR[0], countLR[1]);
        return countLR;
    }



    public static void main(String[] args) {
        TNode tree = buildTree1();
        System.out.println("Result ==> " + countTriangle(tree));
    }


}

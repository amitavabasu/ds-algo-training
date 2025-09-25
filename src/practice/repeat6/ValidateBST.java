package practice.repeat6;

public class ValidateBST {

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int val) {
            this.value = val;
        }
    }

    public static Node buildTree(int[] arr, int l, int r) {
        Node node = null;
        if (arr != null && arr.length != 0 && l <= r) {
            int mid = (l + r) / 2;
            node = new Node(arr[mid]);
            node.left = buildTree(arr, l, mid - 1);
            node.right = buildTree(arr, mid + 1, r);
            return node;
        }
        return node;
    }

    public static void inOrder(Node node) {
        if (node != null) {
            if (node.right != null) inOrder(node.right);
            System.out.print(node.value + " ");
            if (node.left != null) inOrder(node.left);
        }
    }

    public static boolean validateBST(Node node, int maxL, int maxR) {
        if (node == null) return true;
        if (node.value < maxL || node.value > maxR) return false;
        boolean isLeftBST = validateBST(node.left, maxL, node.value);
        boolean isRightBST = validateBST(node.right, node.value, maxR);
        return (isLeftBST && isRightBST);
    }

    public static void main(String[] args) {
        int[][] arrays = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},//True
                {1, 8, 3, 4, 5, 6, 7, 2, 9, 10},//False
                {1, 3, 2, 4, 5, 6, 7, 8, 9, 10}//False
        };

        for (int[] arr : arrays) {

            Node tree = buildTree(arr, 0, arr.length - 1);
            inOrder(tree);
            System.out.println();
            System.out.println(validateBST(tree, Integer.MIN_VALUE, Integer.MAX_VALUE));
            System.out.println("-------");
        }
    }
}


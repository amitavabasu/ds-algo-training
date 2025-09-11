package practice.repeat5;

public class ValidateBST {
    public static class Node {
        int value;
        Node left;
        Node right;
        public Node(int val) { this.value = val;}
    }

    public static Node buildTree(int[] array, int l, int r) {
        if (l > r) return null;
        int mid = (l + r) / 2;
        Node node = new Node(array[mid]);
        node.left = buildTree(array, l, mid-1);
        node.right = buildTree(array, mid+1, r);
        return node;
    }


    public static boolean validateBST(Node node, int min, int max) {
        if (node == null) return true;
        if ( node.value < min || node.value > max) return false;
        boolean isLeftBST = validateBST(node.left, min, node.value);
        boolean isRightBST = validateBST(node.right, node.value, max);
        return (isLeftBST && isRightBST);
    }




   public static void main(String[] args) {
//        int[] array = {1,2,3,4,5,6,7,8,9,10};
//        Node root = buildTree(array, 0, array.length-1);
//        System.out.println(validateBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
       int[][] arrays = {
               {1,2,3,4,5,6,7,8,9,10},//True
               {1,8,3,4,5,6,7,2,9,10},//False
               {1,3,2,4,5,6,7,8,9,10}//False
       };

       for (int[] arr : arrays) {
           Node tree = buildTree(arr, 0, arr.length-1);
           System.out.println(validateBST(tree, Integer.MIN_VALUE, Integer.MAX_VALUE));
       }

   }


}

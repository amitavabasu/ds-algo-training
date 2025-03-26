package training.linkedlist;

public class SLLReversalAndReverseInBetween {
    public static class Node {
        int value;
        Node next;
    }

    public static Node buildSLL(int n) {
        Node start = null;
        Node prev = null;
        for (int i = 0; i < n; i++) {
            Node node = new Node();
            node.value = i+1;
            if (start == null) {
                 start = node;
                 prev = start;
            }
            prev.next = node;
            prev = node;
        }
        return start;
    }

    public static void display(Node start) {
        Node node = start;
        while (node!=null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println("");
    }

    public static Node reverse(Node start) {
        Node node = start;
        Node prev = null;
        while(node != null) {
            Node next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }

    public static Node reverseInBetween(Node root, int start, int end) {
        Node node = root;
        Node prev = null;
        while(node != null && node.value != start) {
            prev = node;
            node = node.next;
        }// node --> start & prev --> start-1
        Node first = prev;
        Node last = node;

        prev = node;
        node = node.next;
        while(node != null && node.value != end) {
            Node next = node.next;
            node.next = prev;//<-- pointing back
            prev = node;
            node = next;
        }
        Node next = node.next;
        node.next = prev;
        first.next = node;
        last.next = next;
        return root;
    }


    public static void main(String[] args) {
        Node start = buildSLL(5);
        display(start);
        start = reverse(start);
        display(start);
        start = buildSLL(5);
        display(start);
        start = reverseInBetween(start, 2, 4);
        display(start);
        start = buildSLL(5);
        display(start);
        start = reverseInBetween(start, 1, 5);
        display(start);
        start = buildSLL(5);
        display(start);
        start = reverseInBetween(start, 3, 3);
        display(start);
    }


}

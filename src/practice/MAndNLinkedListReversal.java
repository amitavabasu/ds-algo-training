package practice;

public class MAndNLinkedListReversal {

    public static class Node {
        int val;
        Node next;
        public Node(int val) {
            this.val = val;
        }
    }

    public static Node buildSLL(int n) {
        Node head = new Node(1);
        Node node = head;
        for (int i = 2; i <= n; i++) {
            node.next = new Node(i);
            node = node.next;
        }
        return head;
    }

    public static void display(Node head) {
        Node node = head;
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println("");
    }

    public static Node reverseInBetween(Node head, int m, int n) {
        Node node = head;
        Node prev = null;
        while (node.val != m) {
            prev = node;
            node = node.next;
        }
        //prev = m-1, node = m
        Node tail = node;
        Node nextToNode = node.next;
        while (node != null && node.val != n ) {
            Node temp = node;
            node = nextToNode;
            nextToNode = node.next;
            node.next = temp;

        }
        //node = n, nextToNode = n+1
        if (prev != null) prev.next = node; else head = node;
        if (tail != null) tail.next = nextToNode;
        return head;
    }


    public static void main(String[] args) {
        Node start = buildSLL(7);
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
        start = reverseInBetween(start, 3, 4);
        display(start);
    }
}

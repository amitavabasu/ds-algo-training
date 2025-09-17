package practice.repeat5;

public class LLDLL {

    public static class Node {
        int value;
        Node next;
        public Node(int val) { this.value = val;}
    }

    public static Node build(int[] data) {
        if (data == null || data.length == 0) return null;
        Node head = new Node(data[0]);
        Node node = head;
        for (int i = 1; i < data.length; i++) {
            node.next = new Node(data[i]);
            node = node.next;
        }
        return head;
    }

    public static void print(Node head) {
        if (head == null) return;
        Node node = head;
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static Node reverse(Node head) {
        if (head == null) return head;
        Node prev = null;
        Node current = head;
        Node next = current.next;
        while (next != null) {
            current.next = prev;
            prev = current;
            current = next;
            next = next.next;
        }
        current.next = prev;
        head = current;
        return head;
    }

    public static Node reverse(Node head, int m, int n) {
        if (head == null) return head;
        Node prev = null;
        Node current = head;
        Node next = current.next;
        for (int i = 1; i < m; i++) {
            prev = current;
            current = next;
            next = next.next;
        }
        Node tail = prev;
        Node start = current;
        prev = current;
        current = next;
        next = next.next;
        for (int i = m; i < n; i++) {
            current.next = prev;
            prev = current;
            current = next;
            next = next.next;
        }
        tail.next = prev;
        start.next = current;
        return head;
    }





    public static void main(String[] args) {
        int[] values = {1,2,3,4,5,6,7,8};
        Node head = build(values);
        print(head);
//        head = reverse(head);
//        print(head);
//        head = build(values);
        head = reverse(head, 3,5);
        print(head);
    }



}

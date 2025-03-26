package training.linkedlist;

public class ReversingLinkedList {
    public static class Node {
        int value;
        Node next;
    }

    public static void printLL(Node head) {
        while (head != null) {
            System.out.print(head.value + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static Node buildLL(int[] values) {
        if (values == null || values.length == 0) return null;
        Node head = null;
        Node prev = null;
        for( int value : values) {
            if (head == null) {
                head = new Node();
                head.value = value;
                prev = head;
            } else {
                Node node = new Node();
                node.value = value;
                prev.next = node;
                prev = node;
            }
        }
        return head;
    }

    public static Node reverseLL(Node head) {
        Node current = head;
        Node prev = null;
        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        int[] values = {1,2,3,4,5};
        Node head = buildLL(values);
        printLL(head);
        Node reversed = reverseLL(head);
        printLL(reversed);
    }


}

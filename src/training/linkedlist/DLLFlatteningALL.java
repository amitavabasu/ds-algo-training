package training.linkedlist;

public class DLLFlatteningALL {

    public static class Node {
        int value;
        Node next;
        Node previous;
        Node child;
        public Node(int value) {
            this.value = value;
        }
        public Node(int value, Node previous) {
            this.value = value;
            this.previous = previous;
        }
        public Node(int value, Node previous, Node next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }

    }
    /*
    1 --> 2 --> 3 --> 4 --> 5
    |
    6--> 7 -- 8
         |
         9 --> 10 --> 11
    1, 6, 7, 9, 10, 11, 8, 2, 3, 4, 5
    */
    public static Node flatten(Node start) {
        if (start == null) return null;
        Node node = start;
        while (node != null) {
            Node next = node.next;
            if (node.child != null) {
                Node child = node.child;
                node.next = node.child;
                node.child.previous = node;
                node.child = null;
                node = child;
                while (node.next != null) {
                    node = node.next;
                }
                node.next = next;
                next.previous = node;
                node = child;
            } else {
                node = next;
            }
        }
        return start;
    }

    public static void main(String[] args) {
        Node start = new Node(1, null);
        start.next = new Node(2, start);
        start.next.next = new Node(3, start.next);
        start.next.next.next = new Node(4, start.next.next);
        start.next.next.next.next = new Node(5, start.next.next.next, null);
        start.child = new Node(6, null);
        start.child.next = new Node(7, start.child);
        start.child.next.next = new Node(8, start.child.next, null);
        start.child.next.child = new Node(9, null);
        start.child.next.child.next = new Node(10, start.child.next.child);
        start.child.next.child.next.next = new Node(11,start.child.next.child.next, null);

        start = flatten(start);

        while (start != null) {
            System.out.print(start.value + " ");
            start = start.next;
        }
        System.out.println("");
    }
}

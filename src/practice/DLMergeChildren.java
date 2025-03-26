package practice;

public class DLMergeChildren {
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

    public static void display(Node head) {
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println("");
    }

        /*
    1 --> 2 --> 3 --> 4 --> 5
    |
    6--> 7 -- 8
         |
         9 --> 10 --> 11
    1, 6, 7, 9, 10, 11, 8, 2, 3, 4, 5
    */


    public static void mergeChildrenRecursion(Node prev, Node head, Node next) {
        if (head == null) return;
        if (prev != null) prev.next = head;
        head.previous = prev;
        Node node = head;
        Node cPrev = null;
        Node cNext = null;
        while (node != null) {
            if (node.child != null) {
                mergeChildrenRecursion(node, node.child, node.next == null ? next : node.next);
                node.child = null;
            }
            cPrev = node;
            cNext = node.next;
            node = cNext;
        }
        cPrev.next = next;
        if (next != null) next.previous = cPrev;
    }

    public static void mergeChildrenNoRecursionNode(Node head) {
        if (head == null) return;
        Node prev;
        Node node = head;
        Node next;
        while (node != null) {
            next = node.next;
            prev = node;
            if (node.child != null) {
                node = node.child;
                if (prev != null) prev.next = node;
                node.previous = prev;
                Node cNode = node;
                while (cNode.next != null) {
                    cNode = cNode.next;
                }
                cNode.next = next;
                if (next != null) next.previous = cNode;
            } else {
                node = node.next;
            }
        }
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

        mergeChildrenRecursion(null, start, null);
        display(start);

        start = new Node(1, null);
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


        mergeChildrenNoRecursionNode(start);
        display(start);

    }

}

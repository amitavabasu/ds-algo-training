package training.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class CycleDetectionInLinkedList {
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
    public static void printCycleLL(Node head) {
        Set<Node> knownNodes = new HashSet<>();
        Node current = head;
        Node prev = null;
        boolean complete = false;
        while (!complete) {
            if (knownNodes.contains(current)) {
                // Found the tail and it is pointing to some other known nodes
                // current is the fork node and prev is the tail
                System.out.print(" ->| " + current.value);
                complete = true;
            } else {
                knownNodes.add(current);
                System.out.print(current.value + " -> ");
            }
            prev = current;
            current = current.next;
        }


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

    public static Node makeACycle(Node head, int distanceFromHead) {
        Node current = head;
        Node forkNode = null;
        Node tail = null;
        int count = 0;
        while (current != null) {
            count++;
            if (count == distanceFromHead) {
                forkNode = current;
            }
            if (current.next == null) {
                tail = current;
            }
            current = current.next;
        }
        if (forkNode != null && tail.next == null) {
            tail.next = forkNode;
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

    public static int findCycleAndForkNode(Node head) {
        if (head == null) return -1;
        if (head.next == null) return -1;
        Node t = head;
        Node r = head;
        Node m = null;
        while (true) {
            if (r == null || r.next == null) return -1;
            if (t == r && t != head && r != head) {
                m = t;
                break;
            }
            t = t.next;
            r = r.next.next;
        }
        System.out.println("MeetingPoint: " + m.value);
        Node p1 = head;
        Node p2 = m;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1.value;
    }

    public static void main(String[] args) {
        int[] values = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
        Node head = buildLL(values);
        printLL(head);
//        Node reversed = reverseLL(head);
//        printLL(reversed);
        Node cycle = makeACycle(head, 11);
        printCycleLL(cycle);
        System.out.println("ForkNode: " + findCycleAndForkNode(cycle));
        head = buildLL(values);
        System.out.println("ForkNode: " + findCycleAndForkNode(head));
    }


}

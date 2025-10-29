package training.linkedlist;

/*
    Hello! Your interview question is below. Write code in this pad just like you would normally – your AI Interviewer will be able to see it.

    # Singly Linked List Design

    Implement a `SinglyLinkedList` class with the following methods:

    push_front(v):
        Adds a node with value v at the beginning of the list.

    pop_front():
        Removes the node at the beginning of the list and returns its value.
        If the list is empty, returns None.

    push_back(v):
        Adds a node with value v at the end of the list.

    pop_back():
        Removes the node at the end of the list and returns its value.
        If the list is empty, returns None.

    size():
        Returns the number of nodes in the list.

    contains(v):
        Return the first node with value v, if any, or null otherwise.

    Here are some examples:

    Example 1:
    list = SinglyLinkedList()
    list.push_front(1)  # List is now: 1
    list.push_front(2)  # List is now: 2->1
    list.push_back(3)   # List is now: 2->1->3
    list.contains(2)    # Returns node with value 2
    list.contains(4)    # Returns None (value not found)
    list.size()         # Returns 3
    list.pop_front()    # Returns 2, list is now: 1->3
    list.pop_back()     # Returns 3, list is now: 1

    Example 2:
    list = SinglyLinkedList()
    list.pop_front()    # Returns None (empty list)
    list.pop_back()     # Returns None (empty list)
    list.size()         # Returns 0

    This diagram shows the typical structure of a singly linked list:

    https://iio-beyond-ctci-images.s3.us-east-1.amazonaws.com/singly-linked-list-design-1.png

    Constraints:

    - You have to create the `Node` class with a `val` field and a `next` pointer. If your language is typed, you can either make the type of `val` be generic or integer.
    - The `push_front()`, `pop_front()`, and `size()` methods should take `O(1)` time if the elements are integers.
    - The list can contain up to `10^5` nodes.

 */
public class LinkedList {
    public static class Node{
        Integer val;
        Node next;
        public Node(Integer val) {
            this.val = val;
        }
    }

    private Node head = null, tail = null;
    private int size = 0;
    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void push_front(Integer v) {
        Node node = new Node(v);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;

    }

    public Integer pop_front() {
        if (head == null) {
            return null;
        } else {
            Integer retVal = head.val;
            head = head.next;
            size--;
            if (size == 0) tail = null;
            return retVal;
        }
    }

    public void push_back(Integer v) {
        Node node = new Node(v);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public Integer pop_back() {
        Integer retVal = null;
        if (tail == null) {
            return retVal;
        } else {
            if (head == tail) {
                retVal = head.val;
                head = null;
                tail = null;
            } else {
                Node prev = head;
                while(prev.next != tail) {
                    prev = prev.next;
                }
                retVal = tail.val;
                tail = prev;
                tail.next = null;
            }
            size--;
        }
        return retVal;
    }

    public int size() {
        return size;
    }

    public Integer contains(Integer v) {
        if (head == null) return null;
        Node node = head;
        while (node != null) {
            if (node.val.equals(v)) {
                return v;
            } else {
                node = node.next;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        LinkedList list =  new LinkedList();
        list.push_front(1);//  # List is now: 1
        list.push_front(2);//  # List is now: 2->1
        list.push_back(3);//   # List is now: 2->1->3
        System.out.println(list.contains(2));//    # Returns node with value 2
        System.out.println(list.contains(4));//    # Returns None (value not found)
        System.out.println(list.size());//         # Returns 3
        System.out.println(list.pop_front()); //   # Returns 2, list is now: 1->3
        System.out.println(list.pop_back());//     # Returns 3, list is now: 1

        //Example 2:
        list = new LinkedList();
        System.out.println(list.pop_front());//    # Returns None (empty list)
        System.out.println(list.pop_back());//     # Returns None (empty list)
        System.out.println(list.size());//         # Returns 0
    }




}

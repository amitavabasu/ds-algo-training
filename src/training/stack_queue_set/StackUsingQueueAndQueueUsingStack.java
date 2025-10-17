package training.stack_queue_set;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackUsingQueueAndQueueUsingStack {
    public static class MyStack {
        private Queue<Integer> queue1 = new LinkedList<>();
        private Queue<Integer> queue2 = new LinkedList<>();

        public void push(Integer x) {
            queue1.offer(x);
            while (!queue2.isEmpty()) {
                queue1.offer(queue2.poll());
            }
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
        }
        public Integer pop() {
            if (queue2.isEmpty()) {
                return null;
            } else {
                return queue2.poll();
            }
        }
        public Integer peek() {
            if (queue2.isEmpty()) {
                return null;
            } else {
                return queue2.peek();
            }
        }

        public boolean isEmpty(){
            return queue1.isEmpty() && queue2.isEmpty();
        }
    }

    public static class MyQueue {
        private Stack<Integer> stack1 = new Stack<>();
        private Stack<Integer> stack2 = new Stack<>();

        public void add(Integer x) {
            stack1.push(x);
        }

        public Integer remove() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            if(stack2.isEmpty()){
                return null;
            } else {
                return stack2.pop();
            }
        }

        public Integer top() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            if(stack2.isEmpty()){
                return null;
            } else {
                return stack2.peek();
            }
        }

        public boolean isEmpty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        for (int i = 1; i <= 10; i++) {
            myStack.push(i);
        }
        System.out.println("-->" + myStack.peek());
        for (int i = 11; i <= 20; i++) {
            myStack.push(i);
        }
        while (!myStack.isEmpty()) {
            System.out.println(myStack.pop());
        }

        MyQueue myQueue = new MyQueue();
        for (int i = 1; i <= 10; i++) {
            myQueue.add(i);
        }
        System.out.println("-->" + myQueue.top());
        for (int i = 11; i <= 20; i++) {
            myQueue.add(i);
        }
        while (!myQueue.isEmpty()) {
            System.out.println(myQueue.remove());
        }


    }
}

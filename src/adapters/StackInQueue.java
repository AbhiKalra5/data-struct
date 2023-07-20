package adapters;

import queue.DynamicQueue;

public class StackInQueue {

    DynamicQueue mainQueue, helperQueue;

    public StackInQueue() {
        this.mainQueue = new DynamicQueue(10);
        this.helperQueue = new DynamicQueue(10);
    }

    int size() {
        return mainQueue.getSize();
    }

    int peek() {
        if (size() == 0) {
            System.out.println("UnderFlow");
            return -1;
        } else {
            return mainQueue.peek();
        }
    }

    void push(int num) {
        while (mainQueue.getSize() > 0) {
            helperQueue.add(mainQueue.remove());
        }
        mainQueue.add(num);
        while (helperQueue.getSize() > 0) {
            mainQueue.add(helperQueue.remove());
        }
    }

    int pop() {
        if (size() == 0) {
            System.out.println("UnderFlow");
            return -1;
        } else {
            return mainQueue.remove();
        }
    }
}

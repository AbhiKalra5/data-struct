package adapters;

import queue.DynamicQueue;

public class StackInQueueEfficient {

    DynamicQueue mainQueue, helperQueue;

    public StackInQueueEfficient() {
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
            while (mainQueue.getSize() > 1) {
                helperQueue.add(mainQueue.remove());
            }
            int val = mainQueue.remove();
            helperQueue.add(val);
            while (helperQueue.getSize() > 0) {
                mainQueue.add(helperQueue.remove());
            }
            return val;
        }
    }

    void push(int num) {
        mainQueue.add(num);
    }

    int pop() {
        if (size() == 0) {
            System.out.println("UnderFlow");
            return -1;
        } else {
            while (mainQueue.getSize() > 1) {
                helperQueue.add(mainQueue.remove());
            }
            int val = mainQueue.remove();
            while (helperQueue.getSize() > 1) {
                mainQueue.add(helperQueue.remove());
            }
            return val;
        }
    }
}



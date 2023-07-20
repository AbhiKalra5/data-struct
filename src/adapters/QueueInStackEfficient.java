package adapters;

import stacks.DynamicStack;

public class QueueInStackEfficient { DynamicStack mainStack, helpStack;

    public QueueInStackEfficient(int num) {
        this.mainStack = new DynamicStack(num);
        this.helpStack = new DynamicStack(num);
    }

    int size() {
        return mainStack.size();
    }

    public int peek() {
        if (mainStack.size() == 0) {
            System.out.println("UnderFlow");
            return -1;
        } else {
            while (mainStack.size() > 1) {
                helpStack.push(mainStack.pop());
            }
            int val = mainStack.pop();
            helpStack.push(val);
            while (helpStack.size() > 0) {
                mainStack.push(helpStack.pop());
            }
            return val;
        }
    }

    public void add(int num) {
        mainStack.push(num);
    }

    public int remove() {
        if (mainStack.size() == 0) {
            System.out.println("UnderFlow");
            return -1;
        } else {
            while (mainStack.size() > 1) {
                helpStack.push(mainStack.pop());
            }
            int val = mainStack.pop();
            while (helpStack.size() > 0) {
                mainStack.push(helpStack.pop());
            }
            return val;
        }
    }
}

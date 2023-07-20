package adapters;

import stacks.DynamicStack;

public class QueueInStack {
    DynamicStack mainStack, helpStack;

    public QueueInStack(int num) {
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
            return mainStack.peek();
        }
    }

    public void add(int num) {
        while (mainStack.size() > 0) {
            helpStack.push(mainStack.pop());
        }
        mainStack.push(num);
        while (helpStack.size() > 0) {
            mainStack.push(helpStack.pop());
        }
    }

    public int remove() {
        if (mainStack.size() == 0) {
            System.out.println("UnderFlow");
            return -1;
        } else {
            return mainStack.pop();
        }
    }
}

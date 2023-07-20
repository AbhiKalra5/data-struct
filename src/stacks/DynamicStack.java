package stacks;

import java.io.IOException;


public class DynamicStack {
    int top;
    int[] data;
    int min;

    public DynamicStack(int size) {
        this.top = -1;
        this.data = new int[size];
    }

    public int size() {
        return top + 1;
    }

    public int peek() {
        if (top == -1) {
            System.out.println("UnderFlow");
            return -1;
        } else {
            if (data[top] < min) {
                return min;
            }
            return data[top];
        }
    }

    public void push(int num) {
        int size = data.length;
        if (top == size - 1) {
            int newData[] = new int[size * 2];
            for (int i = 0; i <= size; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
        top++;

        if (top == -1) {
            min = num;
            data[top] = num;
        } else if (num < min) {
            data[top] = num + num - min;
            min = num;
        } else {
            data[top] = num;
        }
    }

    public int pop() {
        if (top == -1) {
            System.out.println("UnderFlow");
            return -1;
        } else {
            int topValue = data[top];
            top--;
            if (topValue < min) {
                int original = min;
                min = min + min - topValue;
                return original;
            }
            return topValue;
        }
    }

    int min() {
        return min;
    }
}


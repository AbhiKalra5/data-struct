package stacks;

public class TwoStackInArray {

    int top_a;
    int top_b;
    int[] data;

    public TwoStackInArray(int size) {
        this.top_a = -1;
        this.top_b = size;
        this.data = new int[size];
    }

    public int sizeA() {
        return top_a + 1;
    }

    public int sizeB() {
        return data.length - top_b;
    }

    public void pushA(int num) {
        if (top_a == top_b - 1) {
            System.out.println("Over Flow");
        }
        top_a++;
        data[top_a] = num;
    }

    public void pushB(int num) {
        if (top_a == top_b - 1) {
            System.out.println("Over Flow");
        }
        top_b--;
        data[top_b] = num;
    }

    public int peekA() {
        if (sizeA() == 0) {
            System.out.println("Under Flow");
            return -1;
        }
        return data[top_a];
    }

    public int peekB() {
        if (sizeB() == 0) {
            System.out.println("Under Flow");
            return -1;
        }
        return data[top_b];
    }

    public int popA() {
        if (sizeA() == 0) {
            System.out.println("Under Flow");
            return -1;
        }
        int val = data[top_a];
        top_a--;
        return val;
    }

    public int popB() {
        if (sizeB() == 0) {
            System.out.println("Under Flow");
            return -1;
        }
        int val = data[top_b];
        top_b++;
        return val;
    }


}

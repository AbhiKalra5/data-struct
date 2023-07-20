package queue;

public class DynamicQueue {

    int front, rear, size;
    int[] data;


    public DynamicQueue(int size) {
        front = 0;
        rear = 0;
        size = 0;
        this.data = new int[size];
    }


    public int peek() {
        if (size == 0) {
            System.out.println("UnderFlow");
            return -1;
        } else {
            return data[front];
        }
    }

    public void add(int num) {
        if (size == data.length) {
            int newQueue[] = new int[data.length * 2];
            for (int i = 0; i < size; i++) {
                int idx = (i + front) % data.length;
                newQueue[i] = data[idx];
            }
            data = newQueue;
            front = 0;
        }
        int rear = (front + size) % data.length;
        data[rear] = num;
        size++;

    }

    public int remove() {
        if (size == 0) {
            System.out.println("UnderFlow");
            return -1;
        } else {
            int value = data[front];
            front = (front + 1) % data.length;
            size--;
            return value;
        }
    }

    void display() {
        if (size == 0) {
            System.out.println("UnderFlow");
        } else {
            for (int i = 0; i < size; i++) {
                int idx = (i + front) % data.length;
                System.out.println(data[idx]);
            }
        }
    }

    public int getSize() {
        return size;
    }
}


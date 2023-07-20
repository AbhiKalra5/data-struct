package LinkedList;

public class StackInLinkedList {
    LinkedList list;

    public StackInLinkedList() {
        list = new LinkedList();
    }

    int size() {
        return list.size;
    }

    int peek() {
        if (list.size == 0) {
            System.out.println("Empty Stack");
            return -1;
        }
        return list.getFirst();
    }

    void push(int num) {
        list.addFirst(num);
    }

    int pop() {
        if (list.size == 0) {
            System.out.println("Empty Stack");
            return -1;
        }
        return list.removeFromFirst();
    }

}

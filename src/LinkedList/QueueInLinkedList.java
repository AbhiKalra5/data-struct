package LinkedList;

public class QueueInLinkedList {

    LinkedList list;

    public QueueInLinkedList() {
        list = new LinkedList();
    }

    int size() {
        return list.size;
    }

    int peek() {
        if (list.size == 0) {
            System.out.println("Empty Queue");
            return -1;
        }
        return list.getFirst();
    }

    void add(int num) {
        list.addLast(num);
    }

    int remove() {
        if (list.size == 0) {
            System.out.println("Empty Queue");
            return -1;
        }
        return list.removeFromFirst();
    }
}

package LinkedList;

public class KReverse {


    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);
        linkedList.addLast(6);
        linkedList.addLast(7);
        linkedList.addLast(8);
        linkedList.addLast(9);
        linkedList.addLast(10);
        linkedList.addLast(11);
        linkedList = kReverse(linkedList, 3);
        linkedList.display();
    }

    private static LinkedList kReverse(LinkedList source, int k) {
        LinkedList prev = null;

        while (source.size > 0) {
            LinkedList current = new LinkedList();
            if (source.size >= k) {
                for (int i = 0; i < k; i++) {
                    int value = source.removeFromFirst();
                    current.addFirst(value);
                }
            } else {
                int size = source.size;
                for (int i = 0; i < size; i++) {
                    int value = source.removeFromFirst();
                    current.addLast(value);
                }
            }

            if (prev == null) {
                prev = current;
            } else {
                prev.tail.next = current.head;
                prev.tail = current.tail;
                prev.size = prev.size + current.size;
            }
        }
        return prev;
    }
}

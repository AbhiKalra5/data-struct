package LinkedList;

public class FoldList {
    static Node left;
    static Node tail;
    static int size;

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);
        linkedList.addLast(6);
        linkedList.addLast(7);
        linkedList.addLast(8);
        size = linkedList.size;
        left = linkedList.head;
        tail = linkedList.tail;
        foldList(linkedList.head, 0);
        linkedList.display();

    }

    private static void foldList(Node right, int floor) {
        if (right == null) {
            return;
        }
        foldList(right.next, floor + 1);
        if (floor > size / 2) {
            Node temp = left.next;
            left.next = right;
            right.next = temp;
            left = temp;
        } else if (floor == size / 2) {
            right.next = null;
            tail = right;
        }

    }
}

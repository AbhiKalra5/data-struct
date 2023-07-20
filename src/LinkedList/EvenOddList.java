package LinkedList;

public class EvenOddList {

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
        linkedList = sortEvenOddList(linkedList);
        linkedList.display();
    }

    private static LinkedList sortEvenOddList(LinkedList source) {
        LinkedList evenList = new LinkedList();
        LinkedList oddList = new LinkedList();
        while (source.size > 0) {
            int value = source.removeFromFirst();
            if (value % 2 == 0) {
                evenList.addLast(value);
            } else {
                oddList.addLast(value);
            }
        }

        if (oddList.size > 0 && evenList.size > 0) {
            oddList.tail.next = evenList.head;
            oddList.size = oddList.size + evenList.size;
            return oddList;
        } else if (oddList.size > 0) {
            return oddList;
        } else {
            return evenList;
        }

    }
}

package LinkedList;

public class RemoveSortedDuplicates {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.addLast(2);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(3);
        linkedList.addLast(3);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);
        linkedList.addLast(5);
        linkedList.addLast(5);
        linkedList.addLast(7);
        linkedList = removeSortedDuplicates(linkedList);
        linkedList.display();
    }

    private static LinkedList removeSortedDuplicates(LinkedList source) {
        LinkedList sortedLinkedList = new LinkedList();
        while (source.size > 0) {
            int value = source.getFirst();
            source.removeFromFirst();
            if (sortedLinkedList.size == 0 || sortedLinkedList.getLast() != value) {
                sortedLinkedList.addLast(value);
            }
        }
        return sortedLinkedList;
    }
}

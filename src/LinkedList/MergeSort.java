package LinkedList;

public class MergeSort {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.addLast(2);
        linkedList.addLast(7);
        linkedList.addLast(1);
        linkedList.addLast(6);
        linkedList.addLast(5);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(8);
        linkedList.addLast(9);
        LinkedList sortedList = mergeSort(linkedList.head, linkedList.tail);
    }

    private static LinkedList mergeSort(Node head, Node tail) {
        if (head == tail) {
            LinkedList br = new LinkedList();
            br.addLast(head.data);
            return br;
        }
        Node center = findMiddleNode(head, tail);
        LinkedList fsh = mergeSort(head, center);
        LinkedList ssh = mergeSort(center.next, tail);
        MergeTwoSortedList mergeTwoSortedList = new MergeTwoSortedList(fsh, ssh);

        return mergeTwoSortedList.merge();
    }

    private static Node findMiddleNode(Node head, Node tail) {
        Node f = head;
        Node s = head;
        while (f != tail && f.next != tail) {
            f = f.next.next;
            s = s.next;
        }
        return s;
    }
}

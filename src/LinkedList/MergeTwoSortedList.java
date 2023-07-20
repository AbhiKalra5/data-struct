package LinkedList;

public class MergeTwoSortedList {

    LinkedList list_a, list_b, merge;

    MergeTwoSortedList() {
        list_a = new LinkedList();
        list_b = new LinkedList();
        merge = new LinkedList();
    }

    public MergeTwoSortedList(LinkedList list_a, LinkedList list_b) {
        super();
        this.list_a = list_a;
        this.list_b = list_b;
        merge = new LinkedList();
    }

    public LinkedList merge() {
        Node head_a = list_a.head;
        Node head_b = list_b.head;

        while (head_a != null && head_b != null) {
            if (head_a.data < head_b.data) {
                merge.addLast(head_a.data);
                head_a = head_a.next;
            } else {
                merge.addLast(head_b.data);
                head_b = head_b.next;
            }
        }
        while (head_a != null) {
            merge.addLast(head_a.data);
            head_a = head_a.next;
        }
        while (head_b != null) {
            merge.addLast(head_b.data);
            head_b = head_b.next;
        }
        return merge;
    }
}

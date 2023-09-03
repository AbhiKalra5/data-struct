package leet;

public class FlattenLinkedList {

    public ListNode flatten(ListNode head) {
        if (head.next == null || head.next == null) {
            return head;
        }
        ListNode node = flatten(head.next);
        return mergeTwoLists(head, node);
    }

    public ListNode mergeTwoLists(ListNode first, ListNode second) {
        ListNode temp = new ListNode(0);
        ListNode res = temp;
        while (first != null && second != null) {
            if (first.val < second.val) {
                temp.next = first;
                first = first.bottom;
                temp = temp.next;
            } else {
                temp.next = second;
                second = second.bottom;
                temp = temp.next;
            }

            if (first != null) {
                temp.bottom = first;
            } else {
                temp.bottom = second;
            }
        }
        return res.bottom;
    }
}

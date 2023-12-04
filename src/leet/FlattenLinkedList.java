package leet;

public class FlattenLinkedList {

    public ListNode flatten(ListNode head) {
        if (head == null || head.next == null) {
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
                temp.bottom = first;
                first = first.bottom;
                temp = temp.bottom;
            } else {
                temp.bottom = second;
                second = second.bottom;
                temp = temp.bottom;
            }
        }
        if (first != null) {
            temp.bottom = first;
        } else {
            temp.bottom = second;
        }

        return res.bottom;
    }
}

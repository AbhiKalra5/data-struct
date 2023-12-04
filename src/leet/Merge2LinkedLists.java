package leet;

public class
Merge2LinkedLists {

    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val > l2.val) {
            ListNode temp = l1;
            l1 = l2;
            l2 = temp;
        }
        ListNode res = l1;
        while (l1 != null && l2 != null) {
            ListNode temp = l1;
            while (l1 != null && l1.val <= l2.val) {
                temp = l1;
                l1 = l1.next;
            }
            temp.next = l2;

            ListNode swap = l1;
            l1 = l2;
            l2 = swap;
        }
        return res;
    }
}

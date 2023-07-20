package leet;

import LinkedList.Node;

//leetcode 25
public class ReverseKPairs {

    public static void main(String[] args) {
        ListNode b = new ListNode(7);
        ListNode a = new ListNode(6, b);

        ListNode g = new ListNode(5, a);
        ListNode f = new ListNode(4, g);
        ListNode e = new ListNode(3, f);

        ListNode j = new ListNode(2, e);
        ListNode i = new ListNode(1, j);

        reverseKGroup(i, 3);
    }

    static int lengthOfLinkedList(ListNode head) {
        int length = 0;
        while(head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) return head;

        int length = lengthOfLinkedList(head);

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode pre = dummyHead;
        ListNode cur;
        ListNode nex;

        while (length >= k) {
            cur = pre.next;
            nex = cur.next;
            for (int i = 1; i < k; i++) {
                cur.next = nex.next;
                nex.next = pre.next;
                pre.next = nex;
                nex = cur.next;
            }
            pre = cur;
            length -= k;
        }
        return dummyHead.next;
    }

}

package leet;

import LinkedList.LinkedList;
import LinkedList.Node;

public class PallindromeList {

    public static void main(String[] args) {
        // ListNode b = new ListNode(1);
        ListNode a = new ListNode(1);
        ListNode g = new ListNode(2, a);
        ListNode f = new ListNode(3, g);
        ListNode b = new ListNode(5, f);
        ListNode e = new ListNode(3, b);
        ListNode j = new ListNode(2, e);
        ListNode i = new ListNode(1, j);

        checkPalindromeList(i);
    }


    public static boolean checkPalindromeList(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = reverseList(slow.next);
        slow = slow.next;
        while (slow != null) {
            if (head.val != slow.val)
                return false;
            head = head.next;
            slow = slow.next;
        }
        return true;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}

package leet;

import LinkedList.Node;

//leetcode 25
public class ReverseKPairs {

    public static void main(String[] args) {

        ListNode a = new ListNode(6, null);

        ListNode g = new ListNode(5, a);
        ListNode f = new ListNode(4, g);
        ListNode e = new ListNode(3, f);

        ListNode j = new ListNode(2, e);
        ListNode i = new ListNode(1, j);

        reverseKGroup(i, 2);
    }

    static int lengthOfLinkedList(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }

    public static ListNode reverseKGroup_a(ListNode head, int k) {
        if (head == null || head.next == null)
            return head;

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

    static public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) {
            return head;
        }
        ListNode temp = head;
        ListNode prevInGroup = temp;
        ListNode nextInGroup;
        int counter = 0;
        int length = lengthOfLinkedList(head);
        while (temp != null) {
            ListNode innerTemp = temp;
            if (counter + k <= length) {
                int i = 1;
                while (innerTemp != null && i < k) {
                    innerTemp = innerTemp.next;
                    i++;
                }
                counter = counter + k;
            } else {
                break;
            }
            nextInGroup = innerTemp.next;
            innerTemp.next = null;
            ListNode reversed = reverseList(temp);
            if (prevInGroup == head) {
                ListNode tempNode = head;
                head = prevInGroup;
                prevInGroup = head;
                head = reversed;
            } else {
                prevInGroup.next = reversed;
                prevInGroup = temp;
            }

            temp.next = nextInGroup;
            temp = nextInGroup;
        }
        return head;
    }

    static public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }



}

package leet;

// 22 and 23
public class KSortedListMerge {

    public static void main(String[] args) {
        ListNode c = new ListNode(5);
        ListNode b = new ListNode(4, c);
        ListNode a = new ListNode(1, b);

        ListNode g = new ListNode(4);
        ListNode f = new ListNode(3, g);
        ListNode e = new ListNode(1, f);

        ListNode j = new ListNode(6);
        ListNode i = new ListNode(2, j);

        ListNode node = mergeKListsB(new ListNode[]{i, e, a});
        while (node != null) {
            System.out.print(node.val);
            node = node.next;
        }
    }

    public static ListNode mergeKListsB(ListNode[] lists) {
        return mergeKListsRecursion(lists, 0, lists.length - 1);
    }

    public static ListNode mergeKListsRecursion(ListNode[] lists, int a, int b) {
        if (b < a) {
            return null;
        } else if (a == b) {
            return lists[a];
        }

        int mid = (a + b) / 2;
        ListNode first = mergeKListsRecursion(lists, a, mid);
        ListNode second = mergeKListsRecursion(lists, mid + 1, b);

        return merge(first, second);

    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        int counter = 1;
        ListNode result = lists[0];
        while (counter < lists.length) {
            result = merge(result, lists[counter]);
            counter += 1;
        }
        return result;
    }

    public static ListNode merge(ListNode head_a, ListNode head_b) {
        ListNode start = new ListNode();
        ListNode result = start;
        while (head_a != null && head_b != null) {
            if (head_a.val <= head_b.val) {
                result.next = head_a;
                result = head_a;
                head_a = head_a.next;
            } else {
                result.next = head_b;
                result = head_b;
                head_b = head_b.next;
            }

        }
        while (head_a != null) {
            result.next = head_a;
            result = head_a;
            head_a = head_a.next;
        }
        while (head_b != null) {
            result.next = head_b;
            result = head_b;
            head_b = head_b.next;
        }
        return start.next;
    }

}

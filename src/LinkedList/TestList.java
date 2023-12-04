package LinkedList;

public class TestList {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.addLast(3);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);
        linkedList.addLast(6);

        Node a = new Node(1);
        Node b = new Node(2);

        a.next = b;

        detectCycle(a);

    }


    public static Node detectCycle(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node fast = head;
        Node slow = head;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }

        if (slow == null || fast == null) {
            return null;
        }

        fast = head;
        while (slow != null && fast != null && fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast != slow ? null : slow;
    }
}

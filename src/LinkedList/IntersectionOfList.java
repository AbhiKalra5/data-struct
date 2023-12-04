package LinkedList;

public class IntersectionOfList {

    public static void main(String[] args) {
        LinkedList a = new LinkedList();
        LinkedList b = new LinkedList();
        b.addLast(6);
        b.addLast(5);
        b.addLast(4);
        b.addLast(3);
        a.addLast(8);
        a.addLast(7);
        a.addLast(6);
        a.addLast(5);
        a.addLast(4);
        a.tail.next = b.tail;
        b.addLast(2);
        b.addLast(1);
        a.size = a.size + 3;
        findIntersection(a.head, a.size, b.head, b.size);

    }

    private static void findIntersection(Node a, int size_a, Node b, int size_b) {
        Node central = size_a >= size_b ? checkIntersection(a, size_a - size_b, b) : checkIntersection(b, size_b - size_a, a);
        System.out.println(central != null ? central.data : "No intersection");
    }

    private static Node checkIntersection(Node larger, int difference, Node smaller) {
        if (difference != 0) {
            for (int i = 1; i <= difference; i++) {
                larger = larger.next;
            }
        }
        while (larger != smaller) {
            larger = larger.next;
            smaller = smaller.next;
        }
        return larger;
    }

    public Node intersectionPresent(Node head1, Node head2) {
        Node d1 = head1;
        Node d2 = head2;

        while (d1 != d2) {
            d1 = d1 == null ? head2 : d1.next;
            d2 = d2 == null ? head1 : d2.next;
        }
        return d1;
    }
}

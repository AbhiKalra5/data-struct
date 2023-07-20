package LinkedList;

public class AddTwoLists {
    static LinkedList list;

    public static void main(String[] args) {
        LinkedList a = new LinkedList();
        LinkedList b = new LinkedList();
        a.addLast(9);
        a.addLast(9);
        a.addLast(2);
        a.addLast(2);
        a.addLast(2);
        b.addLast(2);
        b.addLast(2);
        b.addLast(2);
        b.addLast(2);
        list = new LinkedList();
        int oc = addTwoLists(a.head, a.size, b.head, b.size);
        if (oc > 0) {
            list.addFirst(oc);
        }
        list.display();

    }

    private static int addTwoLists(Node a, int pc_a, Node b, int pc_b) {
        if (pc_a == 0 && pc_b == 0) {
            return 0;
        }

        int val = 0;
        if (pc_a > pc_b) {
            val = addTwoLists(a.next, pc_a - 1, b, pc_b);
            val = val + a.data;
        } else if (pc_b > pc_a) {
            val = addTwoLists(a, pc_a, b.next, pc_b - 1);
            val = val + b.data;
        } else {
            val += addTwoLists(a.next, pc_a - 1, b.next, pc_b - 1);
            val = a.data + b.data + val;
        }


        list.addFirst(val % 10);
        return val / 10;

    }
}

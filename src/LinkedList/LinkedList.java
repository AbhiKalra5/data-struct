package LinkedList;

public class LinkedList {

    Node head;
    Node tail;
    int size;
    Node tempRecursion;

    LinkedList() {
        size = 0;
        head = null;
        tail = null;
    }

    public void addLast(int num) {
        Node tempNode = new Node(num);
        if (size == 0) {
            head = tail = tempNode;
        } else {
            tail.next = tempNode;
            tail = tempNode;
        }
        size++;
    }

    public void addFirst(int num) {
        Node tempNode = new Node(num);
        if (size == 0) {
            head = tail = tempNode;
        } else {
            tempNode.next = head;
            head = tempNode;
        }
        size++;
    }

    public void addAtIndex(int num, int idx) {

        if (size == 0) {
            addFirst(num);
        } else if (idx < 0 || idx >= size) {
            System.out.println("Invalid");
        } else if (size == idx + 1) {
            addLast(num);
        } else {
            Node tempNode = new Node(num);
            Node tempHead = head;
            for (int i = 0; i < idx - 1; i++) {
                tempHead = tempHead.next;
            }
            tempNode.next = tempHead.next;
            tempHead.next = tempNode;
        }
    }

    public int removeFromFirst() {
        if (size == 0) {
            System.out.println("Empty List");
            return -1;
        } else if (head == tail) {
            int value = head.data;
            head = tail = null;
            size--;
            return value;
        } else {
            int value = head.data;
            head = head.next;
            size--;
            return value;
        }
    }

    public void removeFromLast() {
        if (size == 0) {
            System.out.println("Empty Queue");
        } else if (head == tail) {
            head = tail = null;
            size--;
        } else {
            Node tempHead = head;
            while (tempHead.next != tail) {
                tempHead = tempHead.next;
            }
            tail = tempHead;
            tempHead.next = null;
            size--;
        }

    }

    public void deleteValueAtIndex(int idx) {
        if (idx < 0 || idx >= size) {
            System.out.println("Invalid");
        } else if (idx == 0) {
            removeFromFirst();
        } else if (idx == size - 1) {
            removeFromLast();
        } else {
            Node temp = head;
            for (int i = 0; i < idx - 1; i++) {
                temp = temp.next;
            }
            System.out.println(temp.next.data);
            temp.next = temp.next.next;
            size--;
        }
    }

    public void getValueAtIndex(int idx) {
        if (size == 0) {
            System.out.println("Empty Queue");
        } else if (idx < 0 || idx >= size) {
            System.out.println("Invalid");
        } else {
            Node temp = head;
            for (int i = 0; i < idx; i++) {
                temp = temp.next;
            }
            System.out.println(temp.data);
        }
    }


    public void reverseValueOfList() {
        int l = 0;
        int r = size - 1;
        while (l < r) {
            Node nl = getNodeAt(l);
            Node nr = getNodeAt(r);
            int temp = nl.data;
            nl.data = nr.data;
            nr.data = temp;
            l++;
            r--;
        }
    }

    public void reverseValueOfListRecursive() {
        tempRecursion = head;
        reverseValueRecursive(head, 0);
    }

    private void reverseValueRecursive(Node node, int floor) {
        if (node == null) {
            return;
        }
        reverseValueRecursive(node.next, floor + 1);
        if (floor >= size / 2) {
            int value = node.data;
            node.data = tempRecursion.data;
            tempRecursion.data = value;
            tempRecursion = tempRecursion.next;
        }
    }

    public void reverseList() {
        Node prev = null, current = head;
        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
    }

    public void reverseListRecursive() {
        reverseRecursive(head);
        head.next = null;
        Node temp = head;
        head = tail;
        tail = temp;
        display();
    }

    private void reverseRecursive(Node node) {
        if (node == null) {
            return;
        }
        reverseRecursive(node.next);
        if (node != tail) {
            node.next.next = node;
        }
    }

    public int getFirst() {
        if (size == 0) {
            System.out.println("Empty Queue");
            return -1;
        } else {
            return head.data;
        }
    }

    public int getLast() {
        if (size == 0) {
            System.out.println("Empty Queue");
            return -1;
        } else {
            return tail.data;
        }
    }

    public int getKValueFromLast(int idx) {
        Node slow = head, fast = head;
        for (int i = 0; i < idx; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow.data;
    }

    public int getMiddleValue() {
        Node slow = head, fast = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow.data;
    }

    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public void displayReverseRecursion() {
        displayReverse(head);
    }

    private void displayReverse(Node node) {
        if (node == null) {
            return;
        }
        displayReverse(node.next);
        System.out.println(node.data + " ");
    }

    private Node getNodeAt(int idx) {
        Node temp = head;
        for (int i = 0; i < idx; i++) {
            temp = temp.next;
        }
        return temp;
    }

}

package LinkedList;

public class PalindromeList {

    static Node left;

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);
        linkedList.addLast(5);
        linkedList.addLast(4);
        linkedList.addLast(3);
        linkedList.addLast(2);

        left = linkedList.head;
        System.out.println(traverseAndCheckPalindrome(linkedList.head));

    }

    private static Boolean traverseAndCheckPalindrome(Node right) {
        if (right == null) {
            return true;
        }
        Boolean isPalindromeTillNow = traverseAndCheckPalindrome(right.next);
        if (!isPalindromeTillNow || left.data != right.data) {
            return false;
        } else {
            left = left.next;
            return true;
        }
    }
}


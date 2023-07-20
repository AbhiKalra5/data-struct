package leet;

import LinkedList.LinkedList;

import java.util.HashMap;
import java.util.Map;

public class SixToTen {

    public static void main(String[] args) {

        ListNode node_b = new ListNode(1);
        ListNode node_c = new ListNode(2, node_b);
        ListNode node_d = new ListNode(3, node_c);
        ListNode node_e = new ListNode(3, node_d);
        ListNode node_f = new ListNode(2, node_e);
        ListNode node_g = new ListNode(1, node_f);


        System.out.println(patternMatcher("aaaaaaaaaaaaaaaaaaab", "a*a*a*a*a*a*a*a*a*a*"));
    }

    private static String zigZag(String task, int row) {
        int len = task.length();
        if (len == 1) {
            return task;
        }

        int counter = 0;
        StringBuilder[] stringBuilder = new StringBuilder[row];
        for (int i = 0; i < row; i++) {
            stringBuilder[i] = new StringBuilder();
        }

        while (counter < len) {
            for (int i = 0; i < row && counter < len; i++) {
                stringBuilder[i].append(task.charAt(counter++));
            }

            for (int i = row - 2; i > 0 && counter < len; i--) {
                stringBuilder[i].append(task.charAt(counter++));
            }
        }

        for (int i = 1; i < row; i++) {
            stringBuilder[0].append(stringBuilder[i].toString());
        }
        return stringBuilder[0].toString();
    }

    public static int reverse(int x) {
        int temp = x;
        int nod = 0;
        int lower = (int) Math.pow(-2, 31);
        int high = (int) (Math.pow(2, 31) - 1);
        if (lower >= x || x >= high) {
            return 0;
        }
        while (temp != 0) {
            nod++;
            temp = temp / 10;
        }
        temp = 0;
        while (x != 0) {
            int temp_a = x % 10;
            temp += temp_a * Math.pow(10, --nod);
            x = x / 10;
        }
        if (lower >= temp || temp >= high) {
            return 0;
        } else {
            return temp;
        }
    }


    public static int myAtoi(String s) {
        boolean neg = false;
        int pos = 0;
        int result = 0;
        int max = Integer.MAX_VALUE / 10;

        while (pos < s.length() && s.charAt(pos) == ' ') {
            pos++;
        }

        if (pos < s.length()) {
            if (s.charAt(pos) == '-') {
                neg = true;
                pos++;
            } else if (s.charAt(pos) == '+') {
                pos++;
            }
        }

        while (pos < s.length() && Character.isDigit(s.charAt(pos))) {
            int temp = s.charAt(pos++) - '0';
            if (result > max || (result == max && temp > 7)) {
                return (neg) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }

            result = result * 10 + temp;
        }

        if (result == 0) {
            return 0;
        } else if (neg) {
            return -result;
        } else {
            return result;
        }
    }

    public static boolean isPalindrome(int x) {
        if (x >= 0 && x <= 9) {
            return true;
        } else if (x < 0 || x >= Integer.MAX_VALUE || x % 10 == 0) {
            return false;
        }
        int rev = 0;
        while (x > rev) {

            rev = rev * 10 + x % 10;
            x = x / 10;

        }
        return (x == rev || x == rev / 10);
    }

    public static boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        slow = reverse(slow.next);
        while (slow != null) {
            if (slow.val != fast.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static boolean patternMatcher(String main, String pattern) {
        Boolean[][] array = new Boolean[main.length() + 1][pattern.length() + 1];
        return matchString(main, 0, pattern, 0, array);
    }

    public static boolean matchString(String main, int i, String pattern, int j, Boolean[][] array) {
        int stringLength = main.length();
        int patternLength = pattern.length();

        if (array[i][j] != null) {
            return array[i][j];
        } else if (i >= stringLength && j >= patternLength) {
            return true;
        } else if (j >= patternLength) {
            return false;
        }

        boolean match = i < stringLength && (main.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '.');

        if ((j + 1) < patternLength && pattern.charAt(j + 1) == '*') {
            array[i][j] = matchString(main, i, pattern, j + 2, array) || (match && matchString(main, i + 1, pattern, j, array));
            return array[i][j];
        }

        if (match) {
            array[i][j] = matchString(main, i + 1, pattern, j + 1, array);
            return array[i][j];
        }
        array[i][j] = false;
        return array[i][j];
    }


}
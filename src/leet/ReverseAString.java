package leet;

public class ReverseAString {

    public static void main(String[] args) {
        System.out.println(result("this is an amazing program"));
    }

    static private String result(String s) {
        int left = 0;
        int right = s.length() - 1;

        String temp = "";
        String ans = "";

        while (left <= right) {
            char ch = s.charAt(left);
            if (ch != ' ') {
                temp += ch;
            } else if (ch == ' ') {
                if (!ans.equals("")) {
                    ans = temp + " " + ans;
                } else {
                    ans = temp;
                }
                temp = "";
            }
            left++;
        }
        
        if (!temp.equals("")) {
            if (!ans.equals("")) {
                ans = temp + " " + ans;
            } else {
                ans = temp;
            }
        }

        return ans;
    }
}

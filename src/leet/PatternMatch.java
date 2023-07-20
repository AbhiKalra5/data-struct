package leet;

public class PatternMatch {
    public static void main(String[] args) {

        System.out.println(isMatch("aa", "a*"));
    }

    public static boolean isMatch(String s, String p) {
        Boolean[][] array = new Boolean[s.length() + 1][p.length() + 1];
        return  matchString(s, 0, p, 0, array);
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
        } else if (i >= stringLength && j < patternLength ) {
            for (int k = j; k < patternLength ; k++) {
                if (pattern.charAt(k) != '*')
                    return false;
            }
            return true;
        }

        if (i < stringLength && (main.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '?')) {
            array[i][j] = matchString(main, i + 1, pattern, j + 1, array);
            return array[i][j];
        } else if (i < stringLength && pattern.charAt(j) == '*') {
            array[i][j] = matchString(main, i, pattern, j + 1, array) || matchString(main, i + 1, pattern, j, array);
            return array[i][j];
        }


        array[i][j] = false;
        return array[i][j];
    }
}

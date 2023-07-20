package leet;

public class Decoding {


    public static void main(String[] args) {
        String s = "121";
        Integer[] cache = new Integer[s.length() + 1];
        cache[s.length()] = 1;
        System.out.println(numDecoding(s, 0, cache));
        System.out.println(cache);

    }

    public static int numDecoding(String s, int i, Integer[] cache) {
        int length = s.length();
        if ((cache[i] != null)) {
            return cache[i];
        } else if (s.charAt(i) - '0' == 0) {
            return 0;
        }

        int charCurrent = s.charAt(i) - '0';
        int result = numDecoding(s, i + 1, cache);
        if (i + 1 < length && (charCurrent == 1 || (charCurrent == 2 && s.charAt(i + 1) - '0' >= 0 && s.charAt(i + 1) - '0' <= 6))) {
            result += numDecoding(s, i + 2, cache);
        }
        cache[i] = result;
        return result;
    }
}

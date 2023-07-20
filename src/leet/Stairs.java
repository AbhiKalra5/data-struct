package leet;

public class Stairs {
    public static void main(String[] args) {

        int one = 1, two = 1, range = 3;
        for (int i = 2; i <= range; i++) {
            int temp = two;
            two = one + two;
            one = temp;
        }
        System.out.println(two);
    }

}

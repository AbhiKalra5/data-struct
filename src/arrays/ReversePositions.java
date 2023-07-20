package arrays;

public class ReversePositions {
    public static void main(String[] args) {
        int num = 21453;

        int position = 1;
        int newNum = 0;
        while (num != 0) {
            int temp = num % 10;
            num = num / 10;
            newNum = newNum + (position * (int) Math.pow(10, (temp-1)));
            position++;
        }

        System.out.println(newNum);
    }
}

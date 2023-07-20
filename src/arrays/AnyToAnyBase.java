package arrays;

import java.util.Scanner;

public class AnyToAnyBase {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int num = scn.nextInt();
        int base_from = scn.nextInt();
        int base_to = scn.nextInt();
        int result = getFromDecimal(getInDecimal(num, base_from), base_to);
        System.out.println(result);
    }


    public static int getInDecimal(int num, int base) {
        int value = 0;
        int counter = 1;
        while (num > 0) {
            int dig = num % 10;
            num = num / 10;
            value = value + dig * counter;
            counter = counter * base;
        }
        return value;
    }

    public static int getFromDecimal(int num, int base) {
        int value = 0;
        int counter = 1;
        while (num > 0) {
            int dig = num % base;
            num = num / base;
            value = value + dig * counter;
            counter = counter * 10;
        }
        return value;
    }
}

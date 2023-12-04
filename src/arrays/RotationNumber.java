package arrays;

import java.util.Scanner;

public class RotationNumber {

    public static void main(String[] args) {

        int num = 12345;
        int rotateBy = 3;
        int nod = 0;
        int temp = num;
        while (temp != 0) {
            nod++;
            temp = temp / 10;
        }

        if (nod > 1) {
            rotateBy = rotateBy % nod;
            if (rotateBy < 0) {
                rotateBy = nod + rotateBy;
            }
            int div = (int) Math.pow(10, rotateBy);
            int side = num % div;
            int main = num / div;
            num = (side * (int) Math.pow(10, (nod - rotateBy))) + main;
        }
        System.out.println(num);
    }
}

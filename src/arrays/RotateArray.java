package arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RotateArray {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int a[] = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }

        int rotateBy = Integer.parseInt(br.readLine());
        int nod = a.length;
        if (nod > 1) {
            rotateBy = rotateBy % nod;
            if (rotateBy < 0) {
                rotateBy = nod + rotateBy;
            }
            reverse(a, 0, nod - 1 - rotateBy);
            reverse(a, nod - rotateBy, nod - 1);
            reverse(a, 0, nod - 1);

        }
        for (int temp : a) {
            System.out.println(temp);
        }
    }

    public static void reverse(int a[], int i, int j) {
        while (i < j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++;
            j--;
        }
    }
}

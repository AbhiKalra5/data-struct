package arrays;

public class PrimeRange {
    public static void main(String[] args) {
        int low = 5;
        int high = 1000;

        for (int i = low; i <= high; i++) {
            int count = 0;

            for (int div = 2; div * div <= i; div++) {
                if (i % div == 0) {
                    count++;
                    break;
                }
            }

            if (count == 0) {
                System.out.println("Prime Number" + i);
            } else {
                System.out.println("Not Prime Number" + i);
            }
        }

    }
}

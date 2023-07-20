package arrays;

public class FirstIndexOfValue {

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 5, 6, 9, 9, 9, 10};
        int value = 9;
        int low = 0;
        int high = array.length - 1;
        int potential = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int middleValue = array[mid];
            if (value < middleValue) {
                high = mid - 1;
            } else if (value > middleValue) {
                low = mid + 1;
            } else {
                potential = mid;
                high = mid - 1;
            }
        }
        System.out.println(potential+1);
    }
}
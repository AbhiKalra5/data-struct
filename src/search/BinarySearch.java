package search;

public class BinarySearch {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        int data = 12;
        int right = array.length - 1;
        int left = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            int valueMid = array[mid];
            if (valueMid == data) {
                System.out.println("value found.");
                break;
            } else if (valueMid < data) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println("value Not found.");
    }
}

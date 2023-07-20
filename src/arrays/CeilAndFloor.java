package arrays;

public class CeilAndFloor {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        float value = 7.7f;
        int low = 0;
        int high = array.length - 1;
        int ceil = Integer.MAX_VALUE, floor = Integer.MIN_VALUE;
        while (low <= high) {
            int mid = (low + high) / 2;
            int middleValue = array[mid];
            if (value < middleValue) {
                high = mid - 1;
                ceil = middleValue;
            } else if (value > middleValue) {
                low = mid + 1;
                floor = middleValue;
            } else {
                ceil = floor = middleValue;
                break;
            }
        }
        System.out.println(floor + " : " + ceil);
    }
}

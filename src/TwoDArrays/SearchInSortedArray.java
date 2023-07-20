package TwoDArrays;

public class SearchInSortedArray {

    public static void main(String[] args) {
        int[][] array = {{1, 2, 3, 4, 5, 6}, {7, 8, 9, 10, 11, 12}, {13, 14, 15, 16, 17, 18},
                {19, 20, 21, 22, 23, 24}, {25, 26, 27, 28, 29, 30}, {31, 32, 33, 34, 35, 36}};
        int find = 10;
        int i = 0, j = array[0].length - 1;

        while (i < array.length && j >= 0) {
            int temp = array[i][j];
            if (temp == find) {
                System.out.println(i + " " + j);
                break;
            } else if (temp < find) {
                 i++;
            } else {
                j--;
            }
        }

    }
}

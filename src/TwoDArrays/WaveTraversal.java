package TwoDArrays;

public class WaveTraversal {

    public static void main(String[] args) {
        int[][] array = {{1, 2, 3, 4}, {5, 6, 7, 8,}, {9, 10, 11, 12}};
        int counter = 0;
        int min_row = 0;
        int min_collumn = 0;
        int max_row = 2;
        int max_collumn = 3;
        int total = max_row * max_collumn;

        while (counter < total) {
            for (int i = min_row; i <= max_row && counter < total; i++) {
                System.out.println(array[i][min_collumn]);
                counter++;
            }
            min_collumn++;

            for (int i = min_collumn; i <= max_collumn && counter < total; i++) {
                System.out.println(array[max_row][i]);
                counter++;
            }
            max_row--;

            for (int i = max_row; i >= min_row && counter < total; i++) {
                System.out.println(array[i][max_collumn]);
                counter++;
            }
            max_collumn--;


            for (int i = max_collumn; i >= min_collumn && counter < total; i--) {
                System.out.println(array[min_row][i]);
                counter++;
            }
            min_row++;
        }
    }
}

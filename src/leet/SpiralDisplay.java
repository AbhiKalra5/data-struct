package leet;

import java.util.ArrayList;
import java.util.List;

public class SpiralDisplay {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        spiralOrder(matrix);
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        int counter = 0;
        int minR = 0;
        int minC = 0;
        int maxR = matrix.length - 1;
        int maxC = matrix[0].length - 1;

        int totalElements = (maxR + 1) * (maxC + 1);
        List<Integer> result = new ArrayList<>(totalElements);

        while (counter < totalElements) {
            for (int i = minC; i <= maxC && counter < totalElements; i++) {
                result.add(matrix[minR][i]);
                counter++;
            }
            minR++;

            for (int i = minR; i <= maxR && counter < totalElements; i++) {
                result.add(matrix[i][maxC]);
                counter++;
            }
            maxC--;

            for (int i = maxC; i >= minC && counter < totalElements; i--) {
                result.add(matrix[maxR][i]);
                counter++;
            }
            maxR--;

            for (int i = maxR; i >= minR && counter < totalElements; i--) {
                result.add(matrix[i][minC]);
                counter++;
            }
            minC++;
        }
        return result;
    }
}

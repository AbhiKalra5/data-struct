package leet;

public class SearchSortedArray {
    public static void main(String[] args) {
        int[][] array = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int find = 3;
        searchMatrix(array, find);
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int colLength = matrix[0].length;
        int low = 0;
        int high = (matrix.length * colLength) - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int element = matrix[mid / colLength][mid % colLength];
            if (target == element) {
                return true;
            } else if (target < element) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

    public boolean searchMatrix_B(int[][] matrix, int target) {
        int i = 0, j = matrix[0].length - 1;

        while (i < matrix.length && j >= 0) {
            int temp = matrix[i][j];
            if (temp == target) {
                return true;
            } else if (temp < target) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }
}

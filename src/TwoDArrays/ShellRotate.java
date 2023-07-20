package TwoDArrays;

public class ShellRotate {

    public static void main(String[] args) {
        int[][] array = {{1, 2, 3, 4, 5, 6}, {7, 8, 9, 10, 11, 12}, {13, 14, 15, 16, 17, 18}, {19, 20, 21, 22, 23, 24}, {25, 26, 27, 28, 29, 30}, {31, 32, 33, 34, 35, 36}};
        printDiagonal(array);
    }

    private static void printDiagonal(int[][] arr) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0, k = i; j < 6 - i; j++, k++) {
                    System.out.print(arr[j][k] + "\t");
            }
            System.out.println();
        }
    }


    private static void shellRotate(int[][] arr, int shellNum, int rotateBy) {
        int[] a = flattenShell(arr, shellNum);
        rotateArray(a, rotateBy);
        assembleShell(arr, shellNum, a);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private static int[] flattenShell(int[][] arr, int shellNum) {
        int minr = shellNum - 1;
        int minc = shellNum - 1;
        int maxr = arr.length - shellNum;
        int maxc = arr.length - shellNum;
        int size = 2 * (maxc + maxr - minc - minr);
        int result[] = new int[size];
        int counter = 0;
        while (counter < size) {
            for (int i = minr; i <= maxr && counter < size; i++) {
                result[counter] = arr[i][minc];
                counter++;
            }
            for (int i = minc + 1; i <= maxc && counter < size; i++) {
                result[counter] = arr[maxr][i];
                counter++;
            }
            for (int i = maxr - 1; i >= minr && counter < size; i--) {
                result[counter] = arr[i][maxc];
                counter++;
            }
            for (int i = maxc - 1; i >= minc + 1 && counter < size; i--) {
                result[counter] = arr[minr][i];
                counter++;
            }
        }
        return result;
    }

    private static void assembleShell(int[][] arr, int shellNum, int[] result) {
        int minr = shellNum - 1;
        int minc = shellNum - 1;
        int maxr = arr.length - shellNum;
        int maxc = arr.length - shellNum;
        int size = result.length;
        int counter = 0;

        while (counter < size) {
            for (int i = minr; i <= maxr && counter < size; i++) {
                arr[i][minc] = result[counter];
                counter++;
            }
            for (int i = minc + 1; i <= maxc && counter < size; i++) {
                arr[maxr][i] = result[counter];
                counter++;
            }
            for (int i = maxr - 1; i >= minr && counter < size; i--) {
                arr[i][maxc] = result[counter];
                counter++;
            }
            for (int i = maxc - 1; i >= minc + 1 && counter < size; i--) {
                arr[minr][i] = result[counter];
                counter++;
            }
        }
    }

    private static void rotateArray(int[] arr, int rotateBy) {
        rotateBy = rotateBy % arr.length;
        if (rotateBy < 0) {
            rotateBy = arr.length + 1;
        }

        reverse(arr, 0, arr.length - 1 - rotateBy);
        reverse(arr, arr.length - rotateBy, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
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

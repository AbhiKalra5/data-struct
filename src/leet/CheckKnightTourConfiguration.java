package leet;

// 2596. Check Knight Tour Configuration
public class CheckKnightTourConfiguration {
    public static void main(String[] args) {
        int[][] arr = {{8, 3, 6}, {5, 0, 1}, {2, 7, 4}};
        System.out.println(checkValidGrid(arr));
    }

    public static boolean checkValidGrid(int[][] grid) {
        int n = grid.length;
        int[][] offsets = {{0, 0}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
        int x = 0;
        int y = 0;
        int counter = 0;
        while (counter <= n * n - 1) {
            boolean found = false;
            for (int[] offset : offsets) {
                int newX = x + offset[0];
                int newY = y + offset[1];
                if (newX >= 0 && newX < n && newY >= 0 && newY < n && grid[newX][newY] == counter) {
                    counter++;
                    x = newX;
                    y = newY;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }
}

package leet;

//200
public class NumberOfIslands {
    public static void main(String[] args) {
        char[][] arr = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};

        numIslands(arr);
    }

    public static int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    searchGrid(grid, i, j);
                }
            }
        }
        return count;
    }

    public static void searchGrid(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        searchGrid(grid, i + 1, j);
        searchGrid(grid, i, j + 1);
        searchGrid(grid, i - 1, j);
        searchGrid(grid, i, j - 1);
    }
}

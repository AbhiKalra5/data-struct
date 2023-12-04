package series.graph;

import series.graph.dataStructures.BiPair;
import series.graph.dataStructures.ThreePair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class GridProblems {
    public int rottenTomatoes(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        boolean[][] dp = new boolean[n][m];
        int freshCount = 0;
        int rottenCount = 0;
        Queue<ThreePair> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 2) {
                    dp[i][j] = true;
                    queue.add(new ThreePair(i, j, 0));
                } else if (arr[i][j] == 1) {
                    freshCount++;
                }
            }
        }
        int[] rows = new int[] {-1, 0, 1, 0};
        int[] columns = new int[] {0, 1, 0, -1};
        int maxTime = 0;
        while (!queue.isEmpty()) {
            ThreePair threePair = queue.remove();
            int i = threePair.x;
            int j = threePair.y;
            int t = threePair.z;
            maxTime = Math.max(maxTime, t);
            for (int ind = 0; ind < 4; ind++) {
                int row = i + rows[ind];
                int col = j + columns[ind];
                if (row >= 0 && row < n && col >= 0 && col < m && !dp[row][col] && arr[row][col] == 1) {
                    queue.add(new ThreePair(row, col, t + 1));
                    dp[row][col] = true;
                    rottenCount++;
                }
            }
        }
        return rottenCount != freshCount ? -1 : maxTime;
    }



    public int[][] nearestCellDistance(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean vis[][] = new boolean[n][m];
        int dist[][] = new int[n][m];
        int[] rows = new int[] {-1, 0, 1, 0};
        int[] columns = new int[] {0, 1, 0, -1};
        Queue<ThreePair> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new ThreePair(i, j, 0));
                    vis[i][j] = true;
                }
            }
        }
        while (!queue.isEmpty()) {
            ThreePair threePair = queue.remove();
            int i = threePair.x;
            int j = threePair.y;
            int distance = threePair.z;
            dist[i][j] = distance;
            for (int ind = 0; ind < 4; ind++) {
                int row = i + rows[ind];
                int col = j + columns[ind];
                if (row >= 0 && row < n && col >= 0 && col < m && !vis[row][col] && grid[row][col] == 0) {
                    queue.add(new ThreePair(row, col, distance + 1));
                    vis[row][col] = true;
                }
            }
        }
        return dist;
    }

    public void markSurroundedRegions(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean vis[][] = new boolean[n][m];
        checkBorders(n, m, grid, vis, 0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!vis[i][j] && grid[i][j] != 1) {
                    grid[i][j] = 1;
                }
            }
        }
    }

    void checkBorders(int n, int m, int[][] grid, boolean vis[][], int valueToCheck) {
        int[] rows = new int[] {-1, 0, 1, 0};
        int[] columns = new int[] {0, 1, 0, -1};
        for (int i = 0; i < m; i++) {
            if (grid[0][i] == valueToCheck && !vis[0][i]) {
                traverseAndMark_dfs(0, i, grid, n, m, vis, rows, columns, valueToCheck);
            }
            if (grid[n - 1][i] == 0 && !vis[n - 1][i]) {
                traverseAndMark_dfs(n - 1, i, grid, n, m, vis, rows, columns, valueToCheck);
            }
        }
        for (int i = 1; i < n - 1; i++) {
            if (grid[i][0] == valueToCheck && !vis[i][0]) {
                traverseAndMark_dfs(0, i, grid, n, m, vis, rows, columns, valueToCheck);
            }
            if (grid[i][m - 1] == 0 && !vis[i][m - 1]) {
                traverseAndMark_dfs(n - 1, i, grid, n, m, vis, rows, columns, valueToCheck);
            }
        }
    }

    void traverseAndMark_dfs(int i, int j, int[][] grid, int n, int m, boolean[][] vis, int[] rows, int[] columns, int val) {
        vis[i][j] = true;
        grid[i][j] = val == 1 ? 0 : 1;
        for (int ind = 0; ind < 4; ind++) {
            int row = i + rows[ind];
            int col = j + columns[ind];
            if (row >= 0 && row < n && col >= 0 && col < m && !vis[row][col] && grid[row][col] == val) {
                traverseAndMark_dfs(row, col, grid, n, m, vis, rows, columns, val);
            }
        }
    }

    public int checkIsolatedRegions(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean vis[][] = new boolean[n][m];
        checkBorders(n, m, grid, vis, 1);
        int count = 0;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (!vis[i][j] && grid[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }


    public int numberOfSimilarIslands(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean vis[][] = new boolean[n][m];
        Set<ArrayList<String>> res = new HashSet<>();
        int[] rows = new int[] {-1, 0, 1, 0};
        int[] columns = new int[] {0, 1, 0, -1};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!vis[i][j] && grid[i][j] == 1) {
                    res.add(bfsOfGraph_islands(grid, n, m, vis, rows, columns, i, j));
                }
            }
        }
        return res.size();
    }

    public ArrayList<String> bfsOfGraph_islands(int[][] grid, int n, int m, boolean[][] visited, int[] rows, int[] columns, int parentFirst, int parentSecond) {
        ArrayList<String> res = new ArrayList<>();
        Queue<BiPair> queue = new ArrayDeque<>();
        res.add(convertToString(0, 0));
        queue.add(new BiPair(parentFirst, parentSecond));
        while (!queue.isEmpty()) {
            BiPair pair = queue.remove();
            int currRow = pair.x;
            int currColl = pair.y;
            for (int ind = 0; ind < 4; ind++) {
                int row = currRow + rows[ind];
                int col = currColl + columns[ind];
                if (row >= 0 && row < n && col >= 0 && col < m && !visited[row][col] && grid[row][col] == 1) {
                    queue.add(new BiPair(row, col));
                    visited[row][col] = true;
                    res.add(convertToString(parentFirst - row, parentSecond - col));
                }
            }
        }
        return res;
    }

    private static String convertToString(int row, int coll) {
        return row + " " + coll;
    }

}

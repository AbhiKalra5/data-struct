package series.graph.disjointSet;

import java.util.HashSet;
import java.util.Set;

public class MaximumSizeArea {
    DisjointSet disjointSet;

    public int MaxConnection(int grid[][]) {
        int n = grid.length;
        int m = grid[0].length;
        disjointSet = new DisjointSet(n * m);
        int[] rows = new int[] {-1, 0, 1, 0};
        int[] columns = new int[] {0, 1, 0, -1};
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int sourcePoint = (i * m) + j;
                if (grid[i][j] == 1) {
                    for (int ind = 0; ind < 4; ind++) {
                        int row = i + rows[ind];
                        int col = j + columns[ind];
                        int targetPoint = (row * m) + col;
                        if (checkValidCord(row, col, n, m, sourcePoint, targetPoint) && grid[row][col] == 1) {
                            disjointSet.unionBySize(sourcePoint, targetPoint);
                        }
                    }
                }
                max = Math.max(max, disjointSet.size.get(disjointSet.findUPar(sourcePoint)));
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    int sourcePoint = (i * m) + j;
                    Set<Integer> set = new HashSet<>();
                    for (int ind = 0; ind < 4; ind++) {
                        int row = i + rows[ind];
                        int col = j + columns[ind];
                        int targetPoint = (row * m) + col;
                        if (checkValidCord(row, col, n, m, sourcePoint, targetPoint) && grid[row][col] == 1) {
                            set.add(disjointSet.findUPar(targetPoint));
                        }
                    }
                    int sum = 1;
                    for (Integer integer : set) {
                        sum += disjointSet.size.get(integer);
                    }
                    max = Math.max(sum, max);
                }
            }
        }
        return max;
    }

    private boolean checkValidCord(int row, int col, int n, int m, int sourcePoint, int targetPoint) {
        return row >= 0 && col >= 0 && row < n && col < m && disjointSet.findUPar(sourcePoint) != disjointSet.findUPar(targetPoint);
    }

}

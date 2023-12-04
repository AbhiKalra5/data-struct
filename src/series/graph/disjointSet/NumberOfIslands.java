package series.graph.disjointSet;

import java.util.ArrayList;
import java.util.List;

public class NumberOfIslands {
    public List<Integer> numOfIslands(int n, int m, int[][] operators) {
        List<Integer> res = new ArrayList<>();
        DisjointSet ds = new DisjointSet(n * m);
        boolean[][] visited = new boolean[n][m];
        int[] rows = new int[] {-1, 0, 1, 0};
        int[] columns = new int[] {0, 1, 0, -1};
        int count = 0;

        for (int a = 0; a < operators.length; a++) {
            int x = operators[a][0];
            int y = operators[a][1];
            if (!visited[x][y]) {
                int sourcePoint = (x * m) + y;
                count++;
                visited[x][y] = true;
                for (int ind = 0; ind < 4; ind++) {
                    int row = x + rows[ind];
                    int col = y + columns[ind];
                    int targetPoint = (row * m) + col;
                    if (row >= 0 && col >= 0 && row < n && col < m && visited[row][col] && ds.findUPar(sourcePoint) != ds.findUPar(targetPoint)) {
                        count--;
                        ds.unionBySize(sourcePoint, targetPoint);
                    }
                }
            }

            res.add(count);
        }
        return res;
    }
}

package series.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Traversals {

    public void bfsOfGraph(int v, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[v];
        Queue<Integer> queue = new ArrayDeque<>();
        bfsOfGraph_util(v, adj, visited, 0);

    }

    public ArrayList<Integer> dfsOfGraph(int v, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> res = new ArrayList<>();
        boolean vis[] = new boolean[v + 1];
        ArrayList<Integer> ls = new ArrayList<>();
        traverse_dfs(0, ls, adj, vis);
        return ls;

    }

    public void traverse_dfs(Integer nodeLocation, ArrayList<Integer> res, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        visited[nodeLocation] = true;
        res.add(nodeLocation);
        for (Integer value : adj.get(nodeLocation)) {
            if (!visited[value]) {
                traverse_dfs(value, res, adj, visited);
            }
        }
    }

    public int numberOfProvince(int v, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(v);
        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < adj.size(); i++) {
            for (int j = 0; j < adj.get(0).size(); j++) {
                if (i != j && adj.get(i).get(j) == 1) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        boolean[] visited = new boolean[v];
        int count = 0;
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                count++;
                bfsOfGraph_util(v, adj, visited, i);
            }
        }
        return count;
    }

    public void bfsOfGraph_util(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited, int i) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(i);
        visited[i] = true;
        while (!queue.isEmpty()) {
            int location = queue.poll();
            for (Integer currentNeighbour : adj.get(location)) {
                if (!visited[currentNeighbour]) {
                    visited[currentNeighbour] = true;
                    queue.add(currentNeighbour);
                }
            }
        }
    }

    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        int[] rows = new int[] {-1, 0, 1, 0};
        int[] columns = new int[] {0, 1, 0, -1};
        int[] max = new int[1];
        max[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfsOfGraph_graph(i, j, matrix, dp, rows, columns, max);
            }
        }
        return max[0];
    }

    public int dfsOfGraph_graph(int i, int j, int[][] graph, int[][] dp, int[] rows, int[] col, int[] maximum) {
        int max = Integer.MIN_VALUE;
        for (int ind = 0; ind < 4; ind++) {
            int x = i + rows[ind];
            int y = j + col[ind];
            if (x < 0 || y < 0 || x >= graph.length || y >= graph[0].length) {
                continue;
            }
            if (graph[x][y] > graph[i][j]) {
                if (dp[x][y] != 0) {
                    max = Math.max(max, dp[x][y] + 1);
                } else {
                    max = Math.max(max, dfsOfGraph_graph(x, y, graph, dp, rows, col, maximum) + 1);
                }
            } else {
                max = Math.max(max, 1);
            }
        }
        maximum[0] = Math.max(max, maximum[0]);
        return dp[i][j] = max;
    }
}

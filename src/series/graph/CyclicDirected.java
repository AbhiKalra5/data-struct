package series.graph;

import java.util.ArrayList;
import java.util.List;

public class CyclicDirected {
    TopologicalSort topologicalSort = new TopologicalSort();

    public boolean isCycle_directed_bfs(int n, ArrayList<ArrayList<Integer>> adj) {
        List<Integer> res = topologicalSort.topologicalSortAlgorithm_kahn_bfs(n, adj);
        return res.size() != n;
    }

    public boolean  isCycle_directed_dfs(int n, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[n];
        boolean[] pathVisited = new boolean[n];
        for (int i = 0; i < n; i++)
            if (!visited[i]) {
                if (checkDirectedCycle_dfs(i, adj, visited, pathVisited, null)) {
                    return true;
                }
            }
        return false;
    }

    public boolean checkDirectedCycle_dfs(int i, ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] pathVisited, boolean[] safeStates) {
        visited[i] = true;
        pathVisited[i] = true;
        for (Integer adjacent : adj.get(i)) {
            if (!visited[adjacent]) {
                if (checkDirectedCycle_dfs(adjacent, adj, visited, pathVisited, safeStates)) {
                    return true;
                }
            } else if (pathVisited[adjacent]) {
                return true;
            }
        }
        if (safeStates != null) {
            safeStates[i] = true;
        }
        pathVisited[i] = false;
        return false;
    }

    public boolean isTaskSchedulingPossible(int n, int p, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < p; i++) {
            adj.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }
        return !isCycle_directed_dfs(n, adj);
    }
}

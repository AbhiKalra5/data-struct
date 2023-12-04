package series.graph;

import series.graph.dataStructures.BiPair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class CyclicUnDirected {

    public boolean isCycle_bfs(int n, ArrayList<ArrayList<Integer>> adj) {
        boolean vis[] = new boolean[n];
        for (int i = 0; i < n; i++)
            if (vis[i] == false) {
                if (checkForCycle_bfs(adj, vis, i)) {
                    return true;
                }
            }

        return false;
    }

    public boolean checkForCycle_bfs(ArrayList<ArrayList<Integer>> adj, boolean visited[], int cur) {
        Queue<BiPair> queue = new ArrayDeque<>();

        visited[cur] = true;
        queue.add(new BiPair(0, -1));
        while (!queue.isEmpty()) {
            BiPair pair = queue.remove();
            for (Integer curr : adj.get(pair.x)) {
                if (!visited[curr]) {
                    visited[curr] = true;
                    queue.add(new BiPair(curr, pair.x));
                } else if (curr != pair.y) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCycle_dfs(int n, ArrayList<ArrayList<Integer>> adj) {
        boolean vis[] = new boolean[n + 1];
        for (int i = 1; i <= n; i++)
            if (vis[i] == false) {
                if (checkForCycle_dfs(adj, vis, new BiPair(i, 0))) {
                    return true;
                }
            }

        return false;
    }

    public boolean checkForCycle_dfs(ArrayList<ArrayList<Integer>> adj, boolean visited[], BiPair pair) {
        int curr = pair.x;
        int prev = pair.y;
        visited[curr] = true;
        for (Integer next : adj.get(pair.x)) {
            if (!visited[next]) {
                visited[next] = true;
                if (checkForCycle_dfs(adj, visited, new BiPair(next, pair.x))) {
                    return true;
                }
            } else if (next != prev) {
                return true;
            }
        }
        return false;
    }
}

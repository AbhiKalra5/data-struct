package series.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class SafeStates {
    CyclicDirected cyclicDirected = new CyclicDirected();

    public List<Integer> safeStates_dfs(int n, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[n];
        boolean[] safeStates = new boolean[n];
        List<Integer> res = new ArrayList<>();
        boolean pathVisited[] = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                cyclicDirected.checkDirectedCycle_dfs(i, adj, visited, pathVisited, safeStates);
            }
        }
        for (int i = 0; i < n; i++) {
            if (safeStates[i]) {
                res.add(i);
            }
        }
        return res;
    }

    public static List<Integer> safeStates_Kahn_bfs(int n, List<List<Integer>> adj) {
        ArrayList<ArrayList<Integer>> rev = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            rev.add(new ArrayList<>());
        }
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (Integer integer : adj.get(i)) {
                rev.get(integer).add(i);
                inDegree[i]++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int node = queue.remove();
            res.add(node);
            for (Integer integer : rev.get(node)) {
                inDegree[integer]--;
                if (inDegree[integer] == 0) {
                    queue.add(integer);
                }
            }
        }
        Collections.reverse(res);
        return res;
    }
}

package series.graph;

import series.graph.dataStructures.BiPair;

import java.util.ArrayList;
import java.util.Stack;

public class ShortestPathDirected {
    public int[] shortestPath_dfs(int n, int m, int[][] edges) {
        ArrayList<ArrayList<BiPair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            adj.get(edges[i][0]).add(new BiPair(edges[i][1], edges[i][2]));
        }

        int[] temp = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (temp[i] == 0) {
                constructTopologicalStack_dfs(i, adj, temp, stack);
            }
        }
        temp = new int[n];
        for (int i = 0; i < n; i++) {
            temp[i] = (int) (1e9);
        }
        temp[stack.peek()] = 0;
        while (!stack.isEmpty()) {
            Integer node = stack.pop();
            for (BiPair nodePair : adj.get(node)) {
                temp[nodePair.x] = Math.min(temp[node] + nodePair.y, temp[nodePair.x]);
            }
        }
        for (int i = 0; i < n; i++) {
            if (temp[i] == (int) (1e9)) {
                temp[i] = -1;
            }
        }
        return temp;

    }

    private void constructTopologicalStack_dfs(int i, ArrayList<ArrayList<BiPair>> adj, int[] visited, Stack<Integer> stack) {
        visited[i] = 1;
        for (BiPair pair : adj.get(i)) {
            if (visited[pair.x] == 0) {
                constructTopologicalStack_dfs(pair.x, adj, visited, stack);
            }
        }
        stack.add(i);
    }
}

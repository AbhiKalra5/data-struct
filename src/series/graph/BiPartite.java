package series.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class BiPartite {
    public boolean checkForBipartiteGraph(ArrayList<ArrayList<Integer>> adj, int n) {
        int color[] = new int[n];
        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                if (!checkForBipartiteGraph_util(adj, i, color)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkForBipartiteGraph_util(ArrayList<ArrayList<Integer>> adj, int i, int color[]) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(i);
        color[i] = 1;
        while (!queue.isEmpty()) {
            int curr = queue.remove();
            for (Integer adjacent : adj.get(curr)) {
                if (color[adjacent] == 0) {
                    color[adjacent] = (color[curr] == 1) ? 2 : 1;
                    queue.add(adjacent);
                } else if (color[adjacent] == color[curr]) {
                    return false;
                }
            }
        }
        return true;
    }
}

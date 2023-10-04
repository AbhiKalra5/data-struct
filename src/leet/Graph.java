package leet;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Graph {

    public ArrayList<Integer> bfsOfGraph(int v,
        ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[v];
        Queue<Integer> queue = new ArrayDeque<>();
        visited[0] = true;
        queue.add(0);
        while (!queue.isEmpty()) {
            int location = queue.poll();
            for (Integer currentNeighbour : adj.get(location)) {
                if (!visited[currentNeighbour]) {
                    visited[currentNeighbour] = true;
                    queue.add(currentNeighbour);
                }
            }
        }
        return res;
    }

    public ArrayList<Integer> dfsOfGraph(int v, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> res = new ArrayList<>();

        boolean vis[] = new boolean[v + 1];
        vis[0] = true;
        ArrayList<Integer> ls = new ArrayList<>();
        traverse(0, ls, adj, vis);
        return ls;

    }

    public void traverse(Integer nodeLocation, ArrayList<Integer> res, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {

        visited[nodeLocation] = true;
        res.add(nodeLocation);
        for (Integer value : adj.get(nodeLocation)) {
            if (!visited[value]) {
                traverse(value, res, adj, visited);
            }
        }
    }
}

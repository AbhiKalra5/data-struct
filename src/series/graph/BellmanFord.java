package series.graph;

import java.util.ArrayList;

public class BellmanFord {

    int[] bellman_ford(int v, ArrayList<ArrayList<Integer>> edges, int s) {
        // Write your code here
        int dist[] = new int[v];
        for (int i = 0; i < v; i++) {
            dist[i] = (int) 1e8;
        }
        dist[s] = 0;

        for (int i = 0; i < v - 1; i++) {
            for (ArrayList<Integer> list : edges) {
                int source = list.get(0);
                int target = list.get(1);
                int weight = list.get(2);
                if (dist[source] != 1e8 && dist[source] + weight < dist[target]) {
                    dist[target] = dist[source] + weight;
                }
            }
        }
        //nth cycle to check negative
        for (ArrayList<Integer> list : edges) {
            int source = list.get(0);
            int target = list.get(1);
            int weight = list.get(2);
            if (dist[source] != 1e8 && dist[source] + weight < dist[target]) {
                return new int[] {-1};
            }
        }

        return dist;
    }

}

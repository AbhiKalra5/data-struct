package series.graph.disjointSet;


import series.graph.dataStructures.BiPair;
import series.graph.dataStructures.ThreePair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PrimMST {

    // Bidirectional
    int spanningTree(int v, int e, int edges[][]) {

        // Code Here.
        ArrayList<ArrayList<ArrayList<Integer>>> adj = createAdjList(v, e, edges);
        boolean[] visited = new boolean[v];
        int sum = 0;
        ArrayList<BiPair> mst = new ArrayList<>();
        // weight , node , parent
        Queue<ThreePair> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.x));
        priorityQueue.add(new ThreePair(0, 0, -1));
        while (!priorityQueue.isEmpty()) {
            ThreePair threePair = priorityQueue.remove();
            if (visited[threePair.y]) {
                continue;
            }
            if (threePair.z != -1) {
                sum += threePair.x;
                mst.add(new BiPair(threePair.z, threePair.y));
            }
            visited[threePair.y] = true;
            for (ArrayList<Integer> list : adj.get(threePair.y)) {
                if (!visited[list.get(0)]) {
                    priorityQueue.add(new ThreePair(list.get(1), list.get(0), threePair.y));
                }
            }
        }
        return sum;
    }

    /*
    {
        {
            {1, 2},
            {2, 1}
        },
        {
            {0, 2},
            {2, 1}
        },
        {
            {0, 1},
            {1, 1},
            {3, 2},
            {4, 2}
        },
        {
            {2, 2},
            {4, 1}
        },
        {
            {3, 1},
            {2, 2}
        }
    }
   */
    protected ArrayList<ArrayList<ArrayList<Integer>>> createAdjList(int v, int e, int edges[][]) {
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            int s = edges[i][0];
            int t = edges[i][1];
            int w = edges[i][2];

            ArrayList<Integer> tmp1 = new ArrayList<>();
            ArrayList<Integer> tmp2 = new ArrayList<>();
            tmp1.add(t);
            tmp1.add(w);

            tmp2.add(s);
            tmp2.add(w);

            adj.get(s).add(tmp1);
            adj.get(t).add(tmp2);
        }
        return adj;
    }

}

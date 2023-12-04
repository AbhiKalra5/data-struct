package series.graph.disjointSet;


import series.graph.dataStructures.Edge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KruskalMST {
    DisjointSet disjointSet;

    int spanningTree(int v, int e, int edges[][]) {
        disjointSet = new DisjointSet(v);
        List<Edge> edgesList = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            Edge edge = new Edge(edges[i][0], edges[i][1], edges[i][2]);
            edgesList.add(edge);
        }
        Collections.sort(edgesList, (Comparator.comparingInt(o -> o.weight)));
        int mstWt = 0;
        for (int i = 0; i < edgesList.size(); i++) {
            int wt = edgesList.get(i).weight;
            int src = edgesList.get(i).src;
            int dest = edgesList.get(i).dest;

            if (disjointSet.findUPar(src) != disjointSet.findUPar(dest)) {
                mstWt += wt;
                disjointSet.unionBySize(src, dest);
            }
        }

        return mstWt;
    }
}

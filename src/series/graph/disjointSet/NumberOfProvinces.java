package series.graph.disjointSet;

import java.util.ArrayList;
import java.util.List;

public class NumberOfProvinces {

    // adjacency matrix it is
    int numProvinces(ArrayList<ArrayList<Integer>> adj, int v) {
        DisjointSet disjointSet = new DisjointSet(v);
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (adj.get(i).get(j) == 1) {
                    disjointSet.unionBySize(i, j);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < v; i++) {
            if (disjointSet.findUPar(i) == i) {
                count += 1;
            }
        }
        return count;
    }

}

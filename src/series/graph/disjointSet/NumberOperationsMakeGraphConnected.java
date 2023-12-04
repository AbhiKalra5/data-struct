package series.graph.disjointSet;

public class NumberOperationsMakeGraphConnected {
    DisjointSet disjointSet;

    public int solve(int n, int[][] edge) {
        disjointSet = new DisjointSet(n);
        int count = 0;
        int m = edge.length;
        for (int i = 0; i < m; i++) {
            int e = edge[i][0];
            int v = edge[i][1];
            if (disjointSet.findUPar(e) == disjointSet.findUPar(v)) {
                count++;
            } else {
                disjointSet.unionBySize(e, v);
            }
        }
        int nc = -1;
        for (int i = 0; i < n; i++) {
            if (disjointSet.findUPar(i) == i) {
                nc++;
            }
        }

        return count >= nc ? count : -1;
    }
}

package series.graph.disjointSet;

import java.util.ArrayList;
import java.util.List;

public class DisjointSet {
    List<Integer> rank = new ArrayList<>();
    List<Integer> parent = new ArrayList<>();
    List<Integer> size = new ArrayList<>();

    public DisjointSet(int n) {
        for (int i = 0; i <= n; i++) {
            rank.add(0);
            parent.add(i);
            size.add(1);
        }
    }

    public int findUPar(int node) {
        if (node == parent.get(node)) {
            return node;
        }
        int up = findUPar(parent.get(node));
        parent.set(node, up);
        return parent.get(node);
    }

    public void unionByRank(int u, int v) {
        int up = findUPar(u);
        int vp = findUPar(v);
        if (up == vp) {
            return;
        }
        int rankU = rank.get(up);
        int rankV = rank.get(vp);

        if (rankU > rankV) {
            parent.set(vp, up);
        } else if (rankU < rankV) {
            parent.set(up, vp);
        } else {
            parent.set(up, vp);
            rank.set(vp, rankV + 1);
        }
    }

    public void unionBySize(int u, int v) {
        int up = findUPar(u);
        int vp = findUPar(v);
        if (up == vp) {
            return;
        }
        int sizeU = size.get(up);
        int sizeV = size.get(vp);

        if (sizeU >= sizeV) {
            parent.set(vp, up);
            size.set(up, sizeU + sizeV);
        } else {
            parent.set(up, vp);
            size.set(vp, sizeU + sizeV);
        }
    }
}

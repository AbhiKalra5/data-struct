package series.graph.disjointSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountMerge {
    DisjointSet disjointSet;

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        DisjointSet disjointSet = new DisjointSet(accounts.size());
        List<List<String>> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                if (!map.containsKey(account.get(j))) {
                    map.put(account.get(j), i);
                } else {
                    disjointSet.unionBySize(map.get(account.get(j)), i);
                }
            }
        }

        for (int i = 0; i < map.size(); i++) {
            res.add(new ArrayList<String>());
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int parent = disjointSet.findUPar(entry.getValue());
            if (res.get(parent).size() == 0) {
                res.get(parent).add(accounts.get(parent).get(0));
            }
            res.get(parent).add(entry.getKey());
        }
        for (int i = 0; i < res.size(); i++) {
            List<String> result = res.get(i);
            Collections.sort(result);
            if (result.size() == 0) {
                res.remove(i);
                i--;
            }
        }
        return res;
    }
}

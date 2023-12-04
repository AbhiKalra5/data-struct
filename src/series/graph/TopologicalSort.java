package series.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TopologicalSort {

    public List<Integer> topologicalSortAlgorithm_kahn_bfs(int n, ArrayList<ArrayList<Integer>> adj) {
        int[] inDegree = new int[n];
        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < adj.size(); i++) {
            for (Integer integer : adj.get(i)) {
                inDegree[integer]++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int node = queue.remove();
            res.add(node);
            for (Integer integer : adj.get(node)) {
                inDegree[integer]--;
                if (inDegree[integer] == 0) {
                    queue.add(integer);
                }
            }
        }
        return res;
    }

    public static List<Integer> topologicalSortAlgorithm_dfs(int n, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                constructTopologicalStack_dfs(i, adj, visited, stack);
            }
        }
        while (!stack.empty()) {
            res.add(stack.pop());
        }
        return res;
    }

    private static void constructTopologicalStack_dfs(int i, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
        visited[i] = true;
        for (Integer adjacent : adj.get(i)) {
            if (!visited[adjacent]) {
                constructTopologicalStack_dfs(adjacent, adj, visited, stack);
            }
        }
        stack.push(i);
    }

    public static String findOrder(String[] dict, int n, int k) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            String s1 = dict[i];
            String s2 = dict[i + 1];
            int len = Math.min(s1.length(), s2.length());
            for (int ptr = 0; ptr < len; ptr++) {
                if (s1.charAt(ptr) != s2.charAt(ptr)) {
                    adj.get(s1.charAt(ptr) - 'a').add(s2.charAt(ptr) - 'a');
                    break;
                }
            }
        }

        List<Integer> res = topologicalSortAlgorithm_dfs(k, adj);
        String ans = "";
        for (Integer integer : res) {
            ans = ans + (char) (integer + (int) 'a');
        }
        return ans;
    }

    public static void main(String[] args) {
        findOrder(new String[] {"baa", "abcd", "abca", "cab", "cad"}, 5, 4);
    }
}

package series.graph;


import series.graph.dataStructures.BiPair;
import series.graph.dataStructures.ThreePair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class DijkstraAlgorithm {

    // use set for reduced operations
    int[] shortestPath_dijkstra(int v, ArrayList<ArrayList<ArrayList<Integer>>> adj, int s) {
        PriorityQueue<BiPair> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.x));
        int[] dist = new int[v];
        for (int i = 0; i < v; i++) {
            dist[i] = (int) 1e9;
        }
        dist[s] = 0;
        priorityQueue.add(new BiPair(0, s));
        while (!priorityQueue.isEmpty()) {
            BiPair pair = priorityQueue.remove();
            int distance = pair.x;
            int nodeValue = pair.y;
            for (ArrayList<Integer> inner : adj.get(nodeValue)) {
                int edgeWeight = inner.get(1);
                int adjNode = inner.get(0);
                if (distance + edgeWeight < dist[adjNode]) {
                    dist[adjNode] = distance + edgeWeight;
                    priorityQueue.add(new BiPair(dist[adjNode], adjNode));
                }
            }
        }
        return dist;
    }

    // extra array for tracking
    static public List<Integer> trackShortestPath(int n, int m, int edges[][]) {

        int[] dist = new int[n + 1];
        int[] trace = new int[n + 1];
        List<Integer> res = new ArrayList<>();
        PriorityQueue<BiPair> priorityQueue = new PriorityQueue<>((a, b) -> a.x - b.x);

        ArrayList<ArrayList<BiPair>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            adj.get(edges[i][0]).add(new BiPair(edges[i][1], edges[i][2]));
            adj.get(edges[i][1]).add(new BiPair(edges[i][0], edges[i][2]));
        }

        for (int i = 0; i <= n; i++) {
            dist[i] = (int) 1e9;
            trace[i] = i;
        }
        dist[1] = 0;
        priorityQueue.add(new BiPair(0, 1));
        while (!priorityQueue.isEmpty()) {
            BiPair pair = priorityQueue.remove();
            int distance = pair.x;
            int nodeValue = pair.y;
            for (BiPair adjPair : adj.get(nodeValue)) {
                int adjNode = adjPair.x;
                int edgeWeight = adjPair.y;
                if (distance + edgeWeight < dist[adjNode]) {
                    dist[adjNode] = distance + edgeWeight;
                    priorityQueue.add(new BiPair(dist[adjNode], adjNode));
                    trace[adjNode] = nodeValue;
                }
            }
        }
        if (dist[n] == 1e9) {
            res.add(-1);
            return res;

        }

        res.add(n);
        while (trace[n] != n) {
            res.add(trace[n]);
            n = trace[n];
        }
        Collections.reverse(res);
        return res;
    }

    // distance, x ,y coordinates
    // constant increase by 1, use set, normal queue.
    // stop as soon as you get the answer as it is linearly increasing, first one is king
    public int shortestPathInGrid(int[][] grid, int[] source, int[] destination) {
        if (source[0] == destination[0] && source[1] == destination[1]) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        int[][] distance = new int[n][m];
        int[] rows = new int[] {-1, 0, 1, 0};
        int[] columns = new int[] {0, 1, 0, -1};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                distance[i][j] = (int) 1e9;
            }
        }
        Queue<ThreePair> queue = new LinkedList<>();
        queue.add(new ThreePair(source[0], source[1], 0));
        while (!queue.isEmpty()) {
            ThreePair pair = queue.remove();
            int x = pair.x;
            int y = pair.y;
            int currentDistance = pair.z;
            for (int ind = 0; ind < 4; ind++) {
                int row = x + rows[ind];
                int col = y + columns[ind];
                if (row >= 0 && row < n && col >= 0 && col < m && grid[row][col] != 0 && distance[row][col] > currentDistance + 1) {
                    if (row == destination[0] && col == destination[1]) {
                        return currentDistance + 1;
                    }
                    distance[row][col] = currentDistance + 1;
                    queue.add(new ThreePair(row, col, currentDistance + 1));
                }
            }
        }
        return -1;
    }

    // take max of distance difference forward, Sort on Difference
    int minimumEffortToReachEnd(int heights[][]) {
        int n = heights.length;
        int m = heights[0].length;
        int[][] distance = new int[n][m];
        int[] rows = new int[] {-1, 0, 1, 0};
        int[] columns = new int[] {0, 1, 0, -1};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                distance[i][j] = (int) 1e9;
            }
        }
        PriorityQueue<ThreePair> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.z));
        queue.add(new ThreePair(0, 0, 0));
        while (!queue.isEmpty()) {
            ThreePair pair = queue.remove();
            int x = pair.x;
            int y = pair.y;
            int currentDistance = pair.z;
            if (x == n - 1 && y == m - 1) {
                return currentDistance;
            }
            for (int ind = 0; ind < 4; ind++) {
                int row = x + rows[ind];
                int col = y + columns[ind];

                if (row >= 0 && row < n && col >= 0 && col < m) {
                    int differenceNow = Math.max(Math.abs(heights[x][y] - heights[row][col]), currentDistance);
                    if (distance[row][col] > differenceNow) {
                        distance[row][col] = differenceNow;
                        queue.add(new ThreePair(row, col, differenceNow));
                    }
                }
            }
        }
        //return distance[n-1][m-1] == (int) (1e9) ? -1 : distance[n - 1][m-1];
        return -1;
    }

    // stops are priority
    public int cheapestFlightWithStops(int n, int flights[][], int src, int dst, int k) {
        int[] dist = new int[n];
        Queue<ThreePair> queue = new ArrayDeque<>();
        ArrayList<ArrayList<BiPair>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < flights.length; i++) {
            adj.get(flights[i][0]).add(new BiPair(flights[i][1], flights[i][2]));
        }

        for (int i = 0; i < n; i++) {
            dist[i] = (int) (1e9);
        }
        dist[src] = 0;
        queue.add(new ThreePair(0, src, 0));
        while (!queue.isEmpty()) {
            ThreePair threePair = queue.remove();
            int stops = threePair.x;
            int node = threePair.y;
            int cost = threePair.z;
            if (stops > k) {
                continue;
            }
            for (BiPair integer : adj.get(node)) {
                int adjacentNode = integer.x;
                int edgeW = integer.y;
                if (cost + edgeW < dist[adjacentNode] && stops <= k) {
                    dist[adjacentNode] = cost + edgeW;
                    queue.add(new ThreePair(stops + 1, adjacentNode, cost + edgeW));
                }
            }
        }
        return dist[dst] == (int) (1e9) ? -1 : dist[dst];
    }

    public int minimumMultiplications(int[] arr, int start, int end) {
        if (start == end) {
            return 0;
        }
        int mod = 100000;
        int[] dist = new int[mod];
        for (int i = 0; i < mod; i++) {
            dist[i] = (int) 1e9;
        }
        dist[start] = 0;
        Queue<BiPair> q = new LinkedList<>();
        q.add(new BiPair(0, start));

        while (!q.isEmpty()) {
            BiPair pair = q.remove();
            int level = pair.x;
            int number = pair.y;
            for (int i = 0; i < arr.length; i++) {
                int num = (arr[i] * number) % mod;
                if (dist[num] > level + 1) {
                    if (num == end) {
                        return level + 1;
                    }
                    q.add(new BiPair(level + 1, num));
                    dist[num] = level + 1;
                }
            }
        }
        return -1;
    }

    public int countShortestPathsWays(int n, List<List<Integer>> roads) {
        int mod = (int) (1e9 + 7);
        ArrayList<ArrayList<BiPair>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        int m = roads.size();
        for (int i = 0; i < m; i++) {
            adj.get(roads.get(i).get(0)).add(new BiPair(roads.get(i).get(1), (roads.get(i).get(2))%mod));
            adj.get(roads.get(i).get(1)).add(new BiPair(roads.get(i).get(0), (roads.get(i).get(2))%mod));

        }

        PriorityQueue<BiPair> priorityQueue = new PriorityQueue<>((a, b) -> a.x - b.x);
        int[] dist = new int[n];
        int[] ways = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = (int) 1e9;
            ways[i] = 0;
        }
        dist[0] = 0;
        ways[0] = 1;
        priorityQueue.add(new BiPair(0, 0)); // distance , node
        while (!priorityQueue.isEmpty()) {
            BiPair pair = priorityQueue.remove();
            int distancePair = pair.x;
            int nodeValuePair = pair.y;
            for (BiPair inner : adj.get(nodeValuePair)) {
                int adjNode = inner.x;
                int edW = inner.y;
                int newDistance = edW + distancePair;
                if (newDistance < dist[adjNode]) {
                    dist[adjNode] = newDistance;
                    ways[adjNode] = ways[nodeValuePair];
                    priorityQueue.add(new BiPair(newDistance, adjNode));
                } else if (newDistance == dist[adjNode]) {
                    ways[adjNode] = (ways[adjNode] + ways[nodeValuePair]) % mod;
                }
            }
        }
        return ways[n - 1] % mod;
    }
}






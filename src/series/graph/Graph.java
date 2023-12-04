package series.graph;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;


public class Graph {

    public static void main(String[] args) {
        int[][] arr2 =
            {{0, 2, 6}, {0, 3, 7}, {0, 4, 9}, {0, 6, 8}, {0, 7, 6}, {1, 2, 6}, {1, 3, 7}, {1, 5, 10,}, {1, 6, 1}, {1, 7, 4}, {2, 3, 3}, {2, 6, 10,}, {2, 8, 8},
                {2, 9, 10,}, {3, 5, 3}, {3, 6, 10,}, {3, 7, 5}, {5, 6, 9}, {5, 7, 7}, {6, 7, 7}, {6, 8, 8}, {6, 9, 8}, {7, 9, 1}, {8, 9, 6}};
        int[][] grid = {{1, 1, 1, 1}, {1, 1, 0, 1}, {1, 1, 1, 1}, {1, 1, 0, 0}, {1, 0, 0, 1}};
        int[][] arr1 = {{0, 1, 2}, {0, 4, 8}, {1, 2, 3}, {1, 4, 2}, {2, 3, 1}, {3, 4, 1}};
        ArrayList<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(7).add(7);
        adj.get(0).add(1);
        adj.get(0).add(2);
        adj.get(1).add(2);
        adj.get(1).add(3);
        adj.get(3).add(0);
        adj.get(4).add(5);
        adj.get(2).add(5);


        int n = 7;
        List<List<String>> roads = new ArrayList<>() {
            {
                add(new ArrayList<String>(Arrays.asList("mark", "mark2@gmail.com")));
                add(new ArrayList<String>(Arrays.asList("alice", "alice2@mail.com", "alice9@google.in", "alice6gfg.org")));
                add(new ArrayList<String>(Arrays.asList("fern",
                    "fern9gfg.org",
                    "fern3@mail.com",
                    "fern3@mail.com",
                    "fern7gfg.org",
                    "fern2gfg.org",
                    "fern8@mail.com")));
                add(new ArrayList<String>(Arrays.asList("kevin",
                    "kevin0gfg.org",
                    "kevin3@mail.com",
                    "kevin2@gmail.com",
                    "kevin8@gmail.com",
                    "kevin8@mail.com")));
                add(new ArrayList<String>(Arrays.asList("kevin",
                    "kevin1@gmail.com",
                    "kevin6@google.in",
                    "kevin6@google.in",
                    "kevin2@mail.com",
                    "kevin7@google.in",
                    "kevin5@gmail.com",
                    "kevin9gfg.org")));
                add(new ArrayList<String>(Arrays.asList("bob", "bob3@gmail.com", "bob4@mail.com")));

            }
        };

        int[][] arr = {{5, 3}, {3, 1}, {1, 2}, {2, 4}, {4, 0}};
        ArrayList<ArrayList<Integer>> arrays = new ArrayList<>() {
            {
                add(new ArrayList<Integer>(Arrays.asList()));
                add(new ArrayList<Integer>(Arrays.asList(2)));
                add(new ArrayList<Integer>(Arrays.asList(4)));
                add(new ArrayList<Integer>(Arrays.asList(1)));
                add(new ArrayList<Integer>(Arrays.asList(0)));
                add(new ArrayList<Integer>(Arrays.asList(3)));

            }
        };
        int[][] graph = new int[][] {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};

        Traversals traversals = new Traversals();
        traversals.longestIncreasingPath(graph);

    }



}

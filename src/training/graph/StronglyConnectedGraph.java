package training.graph;

import java.util.*;
import java.util.stream.Collectors;

public class StronglyConnectedGraph {
    /*

        # Strongly Connected Graph

        Given the adjacency list of a non-empty **directed** graph, `graph`, return whether it is _strongly connected_.

        A directed graph is strongly connected if every node can reach every other node (in a directed graph, it is possible that `node1` can reach `node2` but not the other way around).

        Below is an image of strongly connected, weakly connected, and disconnected directed graphs. A directed graph is weakly connected if it would be connected if edges didn't have directions.

        https://iio-beyond-ctci-images.s3.us-east-1.amazonaws.com/strongly-connected-graph-1.png

        Example 1:
        graph = [
          [1, 3],    # Node 0
          [2],       # Node 1
          [0],       # Node 2
          [2]        # Node 3
        ]

        Output: True
        Left graph in the image above

        Example 2:
        graph = [
          [1, 2, 3], # Node 0
          [2],       # Node 1
          [],        # Node 2
          [2]        # Node 3
        ]

        Output: False
        Middle graph in the image above
        Node 2 cannot reach node 0, among others

        Example 3:
        graph = [
          [1],       # Node 0
          [0],       # Node 1
          [3],       # Node 2
          [2]        # Node 3
        ]

        Output: False
        Right graph in the image above
        Node 0 cannot reach node 3, among others

        Constraints:

        - `1 ≤ graph.length ≤ 1000`
        - `graph[i].length < 1000`
        - `0 ≤ graph[i][j] < graph.length`
        - The adjacency list is properly formatted, with no parallel edges or self-loops
        - The graph is directed

     */

    public static boolean isStronglyConnected(List<Integer>[] adjList) {
        if (adjList == null || adjList.length <= 1) return true;
        int n = adjList.length;
        boolean[] seen = new boolean[n];
        dfsFrom0(adjList, seen);
        for (boolean b : seen) {
            if (!b) return false;
        }
        Arrays.fill(seen,false);
        List<Integer>[] tAdjList = transpose(n, adjList);
        dfsFrom0(tAdjList, seen);
        for (boolean b : seen) {
            if (!b) return false;
        }
        return true;
    }

    public static void dfsFrom0(List<Integer>[] adjList, boolean[] seen) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            Integer node = queue.remove();
            seen[node] = true;
            if (adjList[node] != null && !adjList[node].isEmpty()) {
                for (Integer neighbour : adjList[node]) {
                    if (!seen[neighbour]) {
                        queue.add(neighbour);
                    }
                }
            }
        }
    }

    public static List<Integer>[] transpose(int n, List<Integer>[] adjList) {
        List<Integer>[] tAdjList = new ArrayList[n];
        Arrays.fill(tAdjList, new ArrayList<Integer>());
        for (int i = 0; i < n; i++){
            List<Integer> neighbourList = adjList[i];
            if (neighbourList != null && !neighbourList.isEmpty()) {
                for (Integer neighbour : neighbourList) {
                    tAdjList[neighbour].add(i);
                }
            }
        }
        return tAdjList;
    }




    public static void main(String[] args) {

        List<Integer>[][] adjLists = new List[][] {
            {
                Arrays.asList(new Integer[]{1, 3}),
                Arrays.asList(new Integer[]{2}),
                Arrays.asList(new Integer[]{0}),
                Arrays.asList(new Integer[]{2})
            },
            {
                Arrays.asList(new Integer[]{1, 2, 3}),
                Arrays.asList(new Integer[]{2}),
                Arrays.asList(new Integer[]{}),
                Arrays.asList(new Integer[]{2})
            },
            {
                Arrays.asList(new Integer[]{1}),
                Arrays.asList(new Integer[]{0}),
                Arrays.asList(new Integer[]{3}),
                Arrays.asList(new Integer[]{2})
            }
    };
        for (List<Integer>[] adjList : adjLists) {
            System.out.println("Result ==> " + isStronglyConnected(adjList));
        }
    }
}

package training.graph;

import java.util.*;

/*
    # MST Reconstruction

    Given a weighted, undirected graph, find a minimum spanning tree (MST) and return an array with the edges in it. If the MST is not unique, return any of them. If the graph is not connected, return an empty list.

    - A spanning tree is a subset of edges that connects (i.e., "spans") every node and has no cycles.
    - The minimum spanning tree is the spanning tree with minimum edge weight sum.

    The graph is given as an edge list. We are given `V`, the number of nodes, and `edges`, an edge list where each entry is a triplet `{u, v, w}`, where `u` and `v` are the endpoints of an edge, and `w` is the weight. Nodes are identified by integers from `0` to `V-1`. Weights can be positive, zero, or negative.

    Example 1: V = 9, edges = {{0, 1, 3}, {1, 8, 9}, {8, 7, 5}, {7, 4, 13}, {4, 3, 4}, {3, 0, 5}, {1, 5, 8}, {5, 4, 2}, {4, 2, 3}, {2, 1, -1}, {2, 5, 10}, {5, 6, 11}, {6, 8, 0}, {6, 7, -2}}
    Output: {{0, 1, 3}, {1, 8, 9}, {4, 3, 4}, {5, 4, 2}, {4, 2, 3}, {2, 1, -1}, {6, 8, 0}, {6, 7, -2}}.

    Example 2: V = 3, edges = {{0, 1, 1}}
    Output: {}. The graph is not connected.

    Example 3: V = 3, edges = {{0, 1, 1}, {1, 2, 1}, {2, 0, 1}}
    Output: {{0, 1, 1}, {1, 2, 1}}. The solution is not unique.

    Here is the graph from Example 1:

    https://iio-beyond-ctci-images.s3.us-east-1.amazonaws.com/mst-reconstruction-1.png

    Constraints:

    - `0 <= V <= 1000` (number of vertices, can be empty graph)
    - `0 <= edges.length <= V*(V-1)/2` (from no edges to a complete graph)
    - `0 <= u, v < V` for each edge `{u, v, w}`
    - `-10^6 <= w <= 10^6` for each edge `{u, v, w}`
 */
public class MinimumSpanningTreeFromAGraph {

    public static class DSU {
        private int[] parent = null;
        private int[] size = null;
        private int componentCount;
        public DSU(int n) {
            this.parent = new int[n];
            this.size = new int[n];
            this.componentCount = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            if ( parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rX = find(x);
            int rY = find(y);
            if (rX == rY) return false;
            int large = 0;
            int small = 0;
            if (size[rX] < size[rY]) {
                large = rY;
                small = rX;
            } else {
                large = rX;
                small = rY;
            }
            parent[small] = large;
            size[large] = size[small] + size[large];
            componentCount--;
            return true;
        }
    }

    public static List<int[]> findMinimumSpanningTree(int V, int[][] edges) {
        List<int[]> result = new ArrayList<>();
        if (V == 0 || edges == null || edges.length == 0) return result;
        Arrays.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });
        DSU dsu = new DSU(V);
        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            if (dsu.union(node1, node2)) {
                result.add(edge);
            }
            if (result.size() == V-1) break;
        }
        if (result.size() != (V-1)) result.clear();
        return result;
    }
    public static void main(String[] args) {
        int[][][] graphs = {
                {{0, 1, 3}, {1, 8, 9}, {8, 7, 5}, {7, 4, 13}, {4, 3, 4}, {3, 0, 5}, {1, 5, 8}, {5, 4, 2}, {4, 2, 3}, {2, 1, -1}, {2, 5, 10}, {5, 6, 11}, {6, 8, 0}, {6, 7, -2}},
                {{0, 1, 1}},
                {{0, 1, 1}, {1, 2, 1}, {2, 0, 1}}
        };

        int[] V = {9, 3, 3};

        for (int i = 0; i < graphs.length; i++) {
            int[][] graph = graphs[i];
            List<int[]> minST = findMinimumSpanningTree(V[i], graph);
            System.out.println("RESULT");
            for (int[] edge : minST) {
                System.out.print(Arrays.toString(edge) + " ");
            }
            System.out.println();
            System.out.println("-----------------------------");
        }
    }




    
}

package training.stack_queue_set;

/*
Hello! Your interview question is below. Write code in this pad just like you would normally – your AI Interviewer will be able to see it.

# Largest Connected Component Over Time

Given a graph as the number `V` of nodes and a list of edges where each edge is a triplet `[u, v, time]`, where `u` and `v` are nodes between `0` and `V - 1` (inclusive) and `time` is a positive integer representing the time when the edge was added to the graph. We are also given a sorted list `times` with positive integers.

Return an array with the same length as `times` with the size of the largest connected component at each time in `times`.

Example:
n: 4
edges: [[0, 1, 60], [0, 3, 180], [2, 3, 120]]
times: [30, 120, 210]
Output: [1, 2, 4]

Constraints:

- `1 <= V <= 1000` (number of vertices)
- `0 <= edges.length <= V*(V-1)/2` (from no edges to a complete graph)
- `0 <= u, v < V` for each edge `[u, v, time]`
- `1 <= time <= 10^9` for each edge `[u, v, time]`
- `1 <= times.length <= 10^6`
- `1 <= times[i] <= 10^9` for each query time
- `times` array is sorted in non-decreasing order

 */


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class LargetConnectedComponentOverTime_GraphAndSet {

    /*
    Need to build a Disjoined Set Union or Find-Union data structure.
    This specific data structure needed to implement an algorithm for this problem
     */
    public static class DSU {
        private int[] parent;
        private int[] size;
        private int setCount;
        private int maxComponentSize;

        public DSU(int size) {
            this.parent = new int[size];
            this.size = new int[size];
            this.setCount = size;
            this.maxComponentSize = 1;
            for (int i = 0; i < size; i++) {
                this.parent[i] = i;
                this.size[i] = 1;
            }
        }

        public int find(int x) {
            if (this.parent[x] != x) { //if x is not its own parent
                // find its parent's parent.
                // Also update its parent to it's parent's parent
                //Until a root is found, which means tee parent is same at itself
              parent[x] = find(parent[x]);
            }
            //At this point element x has its own index as parent
            // return the root's index of element x;
            return parent[x];
        }

        public boolean union(int x, int y) {
            int xRoot = find(x); //Find root parent of x
            int yRoot = find(y); //Find root parent of y
            if (xRoot == yRoot) return false; //if both elements root parent is same both belong to same set return false as no union is possible.
            if (size[xRoot] >= size[yRoot]) { //If size of x's root is grater than y's root
                parent[yRoot] = parent[xRoot]; //then make y's root as a child of x's root. Making x as the parent of y
                size[xRoot] = size[xRoot] + size[yRoot]; //Increment the size of the x's root to contain size of the y's root
                if (maxComponentSize < size[xRoot]) {
                    maxComponentSize = size[xRoot]; //adjust maxComponent/structure size
                }
            } else { //if otherwise
                parent[xRoot] = parent[yRoot]; //then make x's root as a child of y's root. Making y as the parent of x
                size[yRoot] = size[yRoot] + size[xRoot]; //Increment the size of the y's root to contain size of the x's root
                if (maxComponentSize < size[yRoot]) {
                    maxComponentSize = size[yRoot]; //adjust maxComponent/structure size
                }
            }
            setCount--; //Decrement set count
            return true;
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y); //if root of both x & y are same return true as they belong to the same set and connected
        }

        public int getSizeOf(int x) {
            int xRoot = find(x); //find the root of x
            return size[xRoot]; //return the size of x's root
        }

        public int getSetCount() {
            return setCount;
        }

        public int getMaxComponentSize() {
            return maxComponentSize;
        }
    }

    public static int[] findLargetConnectedComponents(int n, int[][] edges, int[] times) {
        if (edges == null || edges.length == 0 || n <=0 || times == null || times.length == 0) return new int[]{};
        int[] result = new int[times.length];
        Arrays.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });
        for (int[] a : edges) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println(Arrays.toString(times));
        DSU dsu = new DSU(n);
        int i = 0;
        int j = 0;
        while (i < times.length) {
            int time = times[i];
            while (j < edges.length && time >= edges[j][2]) {
                int[] edge = edges[j];
                if (!dsu.connected(edge[0], edge[1])) {
                    dsu.union(edge[0], edge[1]);
                }
                j++;
            }
            result[i] = dsu.getMaxComponentSize();
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {
            {0, 1, 60},
            {0, 3, 180},
            {2, 3, 120}
        };
        int[] times = {30, 120, 210};
        System.out.println("Output==> " + Arrays.toString(findLargetConnectedComponents(n, edges, times)));
    }
}

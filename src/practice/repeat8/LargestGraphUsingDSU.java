package practice.repeat8;

import java.util.Arrays;
import java.util.Comparator;

public class LargestGraphUsingDSU {

    public static class DSU {
        private int[] parent;
        private int[] size;
        private int setCount = 0;
        private int maxComponentSize = 0;

        public DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
            setCount = n;
            maxComponentSize = 1;
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx == rooty) return false;
            int larger, smaller;
            if (size[rootx] >= size[rooty]) {
                larger = rootx;
                smaller = rooty;
            } else {
                smaller = rootx;
                larger = rooty;
            }
            parent[smaller] = parent[larger];
            size[larger] = size[larger] + size[smaller];
            setCount--;
            if (size[larger] > maxComponentSize) {
                maxComponentSize = size[larger];
            }
            return true;
        }

        public int getSetCount() {
            return setCount;
        }

        public int getMaxComponentSize() {
            return maxComponentSize;
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }

        public int getSize(int x) {
            int rootx = find(x);
            return size[rootx];
        }
    }

    public static int[] findLargetConnectedComponents(int n, int[][] edges, int[] times) {
        int[] result = new int[times.length];
        if (n == 0 || edges == null || edges.length == 0 || edges[0].length == 0) return new int[]{};
        Arrays.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2], o2[2]);
            }
        });
        int i = 0;
        int j = 0;
        DSU dsu = new DSU(n);
        while (i < times.length) {
            int time = times[i];
            while (j < edges.length && time >= edges[j][2]) {
                if (!dsu.connected(edges[j][0], edges[j][1])) {
                    dsu.union(edges[j][0], edges[j][1]);
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

package training.stack_queue_set;

class DSU {
    private final int[] parent;   // parent[i] stores the parent of i; if parent[i] == i, i is a root
    private final int[] size;     // size[i] (valid at roots) stores the size of the tree rooted at i
    private int components;       // current number of disjoint sets (components)

    public DSU(int n) {
        parent = new int[n];      // allocate parent array for n elements
        size = new int[n];        // allocate size array for n elements
        components = n;           // initially, every element is its own set → n components
        for (int i = 0; i < n; i++) { // initialize each element
            parent[i] = i;        // each node starts as a root (parent to itself)
            size[i] = 1;          // each set has size 1 initially
        }
    }

    public int find(int x) {
        // path compression: recursively find root and flatten the tree by linking x directly to the root
        if (parent[x] != x) {     // if x is not a root
            parent[x] = find(parent[x]); // compress path: set parent of x to the root
        }
        return parent[x];         // return the root representative of x's set
    }

    public boolean union(int a, int b) {
        // find roots (representatives) of a and b
        int ra = find(a);
        int rb = find(b);
        if (ra == rb) return false;     // already in the same set; no merge happened

        // union by size: attach smaller tree under larger tree's root to keep trees shallow
        if (size[ra] < size[rb]) {      // ensure ra is the root of the larger set
            int t = ra;                 // swap ra and rb
            ra = rb;
            rb = t;
        }
        parent[rb] = ra;                // make ra the parent of rb (merge rb's tree under ra)
        size[ra] += size[rb];           // update size of the new root to include rb's subtree
        components--;                   // one merge reduces the number of components by 1
        return true;                    // merge succeeded
    }

    public boolean connected(int a, int b) {
        return find(a) == find(b);      // two elements are connected if they share the same root
    }

    public int sizeOf(int x) {
        return size[find(x)];           // size of the component containing x (size is stored at the root)
    }

    public int componentCount() {
        return components;              // how many disjoint sets currently exist
    }
    public static void main(String[] args) {
        DSU dsu = new DSU(5);        // {0}{1}{2}{3}{4}
        dsu.union(0, 1);             // {0,1}{2}{3}{4}
        dsu.union(3, 4);             // {0,1}{2}{3,4}
        boolean same = dsu.connected(1, 0); // true
        int sz = dsu.sizeOf(0);      // 2
        int comps = dsu.componentCount(); // 3
    }


}
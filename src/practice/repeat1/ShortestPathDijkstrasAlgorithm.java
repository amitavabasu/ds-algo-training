package practice.repeat1;

import training.heap.HeapUsingArrayList;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class ShortestPathDijkstrasAlgorithm {

    public static ArrayList<List<int[]>> buildGraph(int n, int[][] travelDistances) {
        ArrayList<List<int[]>> adjacencyList = new ArrayList<>(Collections.nCopies(n, null));
        if (n <= 1 || travelDistances == null || travelDistances.length == 0) return adjacencyList;
        for (int[] travelDistance : travelDistances) {
            int source = travelDistance[0]-1;
            int dest = travelDistance[1]-1;
            int distance = travelDistance[2];
            List<int[]> edges = adjacencyList.get(source);
            if (edges == null) {
                edges = new LinkedList<>();
                adjacencyList.set(source, edges);
            }
            edges.add(new int[]{dest, distance});
        }
        return adjacencyList;
    }

    public static int[] findShortestPath(int n, ArrayList<List<int[]>> adjacencyList, int k) {
        int[] result = new int[n];
        if (adjacencyList == null || adjacencyList.size() == 0) return result;
        Arrays.fill(result, Integer.MAX_VALUE);
        result[k - 1] = 0;
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[1] < o2[1]) ? 1 : 0;
            }
        });
        heap.offer(new int[]{k-1, result[k-1]});
        while (!heap.isEmpty()) {
            int[] lowestDistVertex = heap.remove();
            int vertex = lowestDistVertex[0];
            int currentDistance = lowestDistVertex[1];
            List<int[]> edges = adjacencyList.get(vertex);
            if (edges != null) {
                for (int[] edge : edges) {
                    int dest = edge[0];
                    int distance = edge[1];
                    int combinedDistance = currentDistance + distance;
                    if (combinedDistance < result[dest]){
                        result[dest] = combinedDistance;
                        heap.offer(new int[]{dest, combinedDistance});
                    }
                }
            }
        }
        //heap is empty
        return result;
    }


    /*
    Dijkstra's Algorithm output for given graph
    1 ==> 0
    2 ==> 6
    3 ==> 14
    4 ==> 2
    5 ==> 7
     */

    public static void main(String[] args) {
        int n = 5; //number of nodes
        int k = 1; //starting node
        int[][] travelDistances = {//first --> source node; second --> destination node; third --> travel time / weight
                {1, 2, 9},
                {1, 4, 2},
                {2, 5, 1},
                {4, 2, 4},
                {4, 5, 6},
                {3, 2, 3},
                {5, 3, 7},//Comment this to see unreachable node
                {3, 1, 5},
        };

        ArrayList<List<int[]>> adjList = buildGraph(n, travelDistances);
        System.out.println("Dijkstra's Algorithm");
        int[] result = findShortestPath(n, adjList, k);
        for (int i = 0; i < result.length; i++) {
            System.out.println((i + 1) + " ==> " + result[i]);
        }
    }
}

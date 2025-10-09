package training.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstrasGreedyAlgorithmGRAPH {

    public static ArrayList<List<int[]>> buildGraph(int n, int[][] timeToTravel) {
        ArrayList<List<int[]>> adjList = new ArrayList<>(Collections.nCopies(n, null));
        for (int i = 0; i < timeToTravel.length; i++) {
            int[] tot = timeToTravel[i];
            int source = tot[0]-1;
            int dest = tot[1]-1;
            int weight = tot[2];
            List<int[]> sourceVertexList = adjList.get(source);
            if (sourceVertexList == null) {
                sourceVertexList = new LinkedList<>();
                adjList.set(source, sourceVertexList);
            }
            sourceVertexList.add(new int[]{dest, weight});
        }
        return adjList;
    }

    public static int[] findShortestTimeDijkstra(ArrayList<List<int[]>> adjList, int n, int k) {
        int[] result = new int[n];
        Arrays.fill(result, Integer.MAX_VALUE);
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[1] < o2[1]) ? 1 : 0;
            }
        });
        result[k-1] = 0;//<-- don't forget.
        heap.offer(new int[]{k - 1, result[k-1]});
        while (!heap.isEmpty()) {
            int[] lowest = heap.remove();
            int sourceVertex = lowest[0];
            List<int[]> connections = adjList.get(sourceVertex);
            if (connections != null) {
                for (int[] connection : connections) {
                    int destVertex = connection[0];
                    int weight = connection[1];
                    if (result[destVertex] > result[sourceVertex] + weight) {
                        result[destVertex] = result[sourceVertex] + weight;
                        heap.offer(new int[]{destVertex, result[destVertex]});
                    }
                }
            }
        }
        return result;
    }

    public static int[] findShortestTimeBellmanFord(ArrayList<List<int[]>> adjList, int n, int k) {
        int[] result = new int[n];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[k-1] = 0;
        for (int i = 0; i < n-1; i++) {
            boolean lessWeightFound = false;
            for (int sourceVertex = 0; sourceVertex < adjList.size(); sourceVertex++) {
                List<int[]> edges = adjList.get(sourceVertex);
                if (edges != null) {// Check to avoid NPE
                    for (int[] edge : edges) {
                        int dstVertex = edge[0];
                        int dstWeight = edge[1];
                        int sourceVertexWeight = result[sourceVertex];
                        if (sourceVertexWeight != Integer.MAX_VALUE) {
                            int newDestWeight = sourceVertexWeight + dstWeight;
                            if (result[dstVertex] > newDestWeight) {
                                result[dstVertex] = newDestWeight;
                                lessWeightFound = true;
                            }
                        }
                    }
                }
            }
            System.out.println("Less weighted path found ==>" + lessWeightFound);
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 5; // number of nodes
        int k = 1; // starting node
        int[][] timeToTravel  = {
                {1, 2, 9}, // source --> destination, weight/distance; source and destination are items not index;
                //sourceIndex = source-1 & destinationIndex = destination-1;
                {1, 4, 2},
                {2, 5, 1},
                {4, 2, 4},
                {4, 5, 6},
                {3, 2, 3},
                 {5, 3, 7},//Comment this to see unreachable node
                {3, 1, 5},
        };

        ArrayList<List<int[]>> adjList = buildGraph(n, timeToTravel);
//        System.out.println("Dijkstra's Algorithm, old");
//        int[] result = findShortestTimeDijkstra(adjList, n, k);
//        for (int i = 0; i < result.length; i++) {
//            System.out.println((i+1) + " ==> " + result[i]);
//        }

        System.out.println("Bellman-Ford Algorithm");
        int[] result = findShortestTimeBellmanFord(adjList, n, k);
        for (int i = 0; i < result.length; i++) {
            System.out.println((i+1) + " ==> " + result[i]);
        }

        timeToTravel  = new int[][]{
                {1, 2, 9}, // source --> destination, weight/distance; source and destination are items not index;
                //sourceIndex = source-1 & destinationIndex = destination-1;
                {1, 4,  2},
                {2, 5, -3},
                {4, 2, -4},
                {4, 5,  6},
                {3, 2,  3},
                {5, 3,  7},//Comment this to see unreachable node
                {3, 1,  5},//Make the weight -5 to check the effect of negative cycle.
        };

        adjList = buildGraph(n, timeToTravel);
//        System.out.println("Dijkstra's Algorithm, old");
//        result = findShortestTimeDijkstra(adjList, n, k);
//        for (int i = 0; i < result.length; i++) {
//            System.out.println((i+1) + " ==> " + result[i]);
//        }

        System.out.println("Bellman-Ford Algorithm");
        result = findShortestTimeBellmanFord(adjList, n, k);
        for (int i = 0; i < result.length; i++) {
            System.out.println((i+1) + " ==> " + result[i]);
        }

        System.out.println();
    }
}

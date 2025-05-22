package practice.repeat3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BellmanFordShortestPath {

    public static List<List<Integer[]>> buildGraph(int[][] distances, int n) {
        if (distances == null || distances.length == 0 || distances[0].length == 0) return Collections.emptyList();
        List<List<Integer[]>> graph = new ArrayList<>(Collections.nCopies(n, null));
        for (int[] connection : distances) {
            int sourceNodeIndex = connection[0]-1;
            int destNodeIndex = connection[1]-1;
            int distance = connection[2];
            List<Integer[]> sourceConnections = graph.get(sourceNodeIndex);
            if (sourceConnections == null) { sourceConnections = new ArrayList<>(2); graph.set(sourceNodeIndex, sourceConnections);}
            sourceConnections.add(new Integer[]{destNodeIndex, distance});
        }
        return graph;
    }

    public static int[] findShortestDistancesByBellmanFordMethod(List<List<Integer[]>> adjList, int n, int k) {
        int[] shortestDistances = new int[n];
        Arrays.fill(shortestDistances, Integer.MAX_VALUE);
        shortestDistances[k] = 0;
        for (int i = 0; i < (n-1); i++) {
            for (int sourceIndex = 0; sourceIndex < n; sourceIndex++) {
                List<Integer[]> connections = adjList.get(sourceIndex);
                if (connections != null) {
                    for (Integer[] connection : connections) {
                        int destIndex = connection[0];
                        int distance = connection[1];
                        if (shortestDistances[sourceIndex] != Integer.MAX_VALUE) {
                            if ((shortestDistances[sourceIndex] + distance) < shortestDistances[destIndex]) {
                                shortestDistances[destIndex] = shortestDistances[sourceIndex] + distance;
                            }
                        }
                    }
                }
            }
        }
        return shortestDistances;
    }



    public static void main(String[] args) {
        int[][] distanceToTravel  = {
                {1, 2, 9},
                {1, 4, 2},
                {2, 5, 1},
                {4, 2, 4},
                {4, 5, 6},
                {3, 2, 3},
                {5, 3, 7},//Comment this to see unreachable node
                {3, 1, 5},
        };


        int[][] timeToTravel  = new int[][]{
                {1, 2,  9},
                {1, 4,  2},
                {2, 5, -3},
                {4, 2, -4},
                {4, 5,  6},
                {3, 2,  3},
                {5, 3,  7},//Comment this to see unreachable node
                {3, 1,  5},//Make the weight -5 to check the effect of negative cycle.
        };

        int n = 5;
        int k = 1;

        List<List<Integer[]>> adjacencyList = buildGraph(distanceToTravel, n);
        int[] shortestDistances = findShortestDistancesByBellmanFordMethod(adjacencyList, n, k-1);
        System.out.println(Arrays.toString(shortestDistances));

        adjacencyList = buildGraph(timeToTravel, n);
        shortestDistances = findShortestDistancesByBellmanFordMethod(adjacencyList, n, k-1);
        System.out.println(Arrays.toString(shortestDistances));
    }
}

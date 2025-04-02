package practice.repeat3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraShortestPath {

    public static List<List<Integer[]>> buildGraph(int[][] distances, int n) {
        if (distances == null || distances.length == 0 || distances[0].length == 0) return Collections.emptyList();
        List<List<Integer[]>> graph = new ArrayList<>(Collections.nCopies(5, null));
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

    public static int[] findShortestDistancesByDijkstraMethod(List<List<Integer[]>> adjList, int n, int k) {
        int[] shortestDistances = new int[n];
        Arrays.fill(shortestDistances, Integer.MAX_VALUE);
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[1] < o2[1]) ? 1 : 0;
            }
        });
        shortestDistances[k] = 0;
        queue.offer(new int[]{k, shortestDistances[k]});
        while (!queue.isEmpty()) {
            int[] conn = queue.remove();
            int nodeIndex = conn[0];
            int currentDistance = conn[1];
            List<Integer[]> connections = adjList.get(nodeIndex);
            if (connections != null) {
                for (Integer[] connection : connections) {
                    int destIndex = connection[0];
                    int distance = connection[1];
                    if ((currentDistance + distance) < shortestDistances[destIndex]) {
                        shortestDistances[destIndex] = currentDistance + distance;
                        queue.offer(new int[]{destIndex, shortestDistances[destIndex]});
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
        int n = 5;
        int k = 1;
        List<List<Integer[]>> adjacencyList = buildGraph(distanceToTravel, n);
        int[] shortestDistances = findShortestDistancesByDijkstraMethod(adjacencyList, n, k-1);
        System.out.println(Arrays.toString(shortestDistances));
    }
}

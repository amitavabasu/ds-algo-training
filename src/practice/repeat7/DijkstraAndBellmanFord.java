package practice.repeat7;

import java.util.*;

public class DijkstraAndBellmanFord {

    public static List<List<Integer[]>> buildGraph(int n, int[][] travelTimes) {
        if (travelTimes == null || travelTimes.length == 0) return Collections.emptyList();
        List<List<Integer[]>> adjList = new ArrayList<>(Collections.nCopies(n, null));
        for (int[] element : travelTimes) {
            int source = element[0]-1;
            int destination = element[1]-1;
            int travelTime = element[2];
            List<Integer[]> destinations = adjList.get(source);
            if (destinations == null) destinations = new ArrayList<>();
            destinations.add(new Integer[]{destination, travelTime});
            adjList.set(source, destinations);
        }
        return adjList;
    }

    public static int[] findShortestTimeDijkstra(List<List<Integer[]>> adjList, int n, int k) {
        int[] shortestTime = new int[n];
        Arrays.fill(shortestTime, Integer.MAX_VALUE);
        shortestTime[k-1] = 0;
        PriorityQueue<Integer[]> heap = new PriorityQueue<>(
                new Comparator<Integer[]>() {
                    @Override
                    public int compare(Integer[] o1, Integer[] o2) {
                        return o1[1] < o2[1] ? 1 : 0;
                    }
                }
        );
        heap.offer(new Integer[]{k-1, shortestTime[k-1]});
        while (!heap.isEmpty()) {
            Integer[] currentNode = heap.remove();
            int source = currentNode[0];
            List<Integer[]> destinations = adjList.get(source);
            if (destinations != null) {
                for (Integer[] destinationNode : destinations) {
                    int timeToDest = destinationNode[1];
                    if (shortestTime[destinationNode[0]] > shortestTime[source] + timeToDest) {
                        shortestTime[destinationNode[0]] = shortestTime[source] + timeToDest;
                        heap.offer(new Integer[]{destinationNode[0], shortestTime[destinationNode[0]]});
                    }
                }
            }
        }
        return shortestTime;
    }

    public static int[] findShortestTimeBellmanFord(List<List<Integer[]>> adjList, int n, int k) {
        int[] shortestTimes = new int[n];
        Arrays.fill(shortestTimes, Integer.MAX_VALUE);
        shortestTimes[k-1] = 0;
        for (int i = 0; i < n-1; i++) {
            boolean shortestTimeFound = false;
            for (int source = 0; source < adjList.size(); source++) {
                List<Integer[]> destNodes = adjList.get(source);
                if (destNodes != null) {
                    for(Integer[] destNode : destNodes) {
                        int destination = destNode[0];
                        int timeToDest = destNode[1];
                        if (shortestTimes[source] != Integer.MAX_VALUE) {
                            if (shortestTimes[destination] > shortestTimes[source] + timeToDest) {
                                shortestTimeFound = true;
                                shortestTimes[destination] = shortestTimes[source] + timeToDest;
                            }
                        }
                    }
                }
            }
            System.out.println("Less weighted path found ==>" + shortestTimeFound);
        }
        return shortestTimes;
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

        List<List<Integer[]>> adjList = buildGraph(n, timeToTravel);

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
                {1, 2,  9}, // source --> destination, weight/distance
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

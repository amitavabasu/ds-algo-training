package practice.repeat5;

import java.util.*;

public class DijkstraAlgo {

    public static ArrayList<List<int[]>> buildGraph(int n, int[][] timeToTravel) {
        ArrayList<List<int[]>> adjList = new ArrayList<>(Collections.nCopies(n, null));
        for (int i = 0; i < timeToTravel.length; i++) {
                int[] data  = timeToTravel[i];
            int source = data[0]-1;
            int dest = data[1]-1;
            int time = data[2];
            List<int[]> connections = adjList.get(source);
            if (connections == null) {
                connections = new LinkedList<>();
                adjList.set(source, connections);
            }
            connections.add(new int[]{dest, time});
        }
        return adjList;
    }

    //Do not work for negative weight, but cycle works if path weight all positive
    public static int[] findShortestTimeDijkstra(ArrayList<List<int[]>> adjList, int n, int start) {
        int[] result = new int[n];
        Arrays.fill(result, Integer.MAX_VALUE);
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] < o2[1] ? 1 : 0;
            }
        });
        result[start-1] = 0;
        heap.offer(new int[]{start-1, result[start-1]});
        while (!heap.isEmpty()) {
            int[] item = heap.remove();
            int currentSource = item[0];
            List<int[]> destinations = adjList.get(currentSource);
            if (destinations != null) {
                for (int[] destination : destinations) {
                    int destIndex = destination[0];
                    int destTime = destination[1];
                    if (result[destIndex] > result[currentSource] + destTime) {
                        result[destIndex] = result[currentSource] + destTime;
                        heap.offer(new int[]{destIndex, result[destIndex]});
                    }
                }
            }
        }
        return result;
    }



    public static void main(String[] args) {
        int n = 5;
        int k = 1;
        int[][] timeToTravel  = {
                {1, 2, 9},
                {1, 4, 2},
                {2, 5, 1},
                {4, 2, 4},
                {4, 5, 6},
                {3, 2, 3},
               // {5, 3, 7},//Comment this to see unreachable node
                {3, 1, 5},
        };

        ArrayList<List<int[]>> adjList = buildGraph(n, timeToTravel);
        System.out.println("Dijkstra's Algorithm, new");
        int[] result = findShortestTimeDijkstra(adjList, n, k);
        for (int i = 0; i < result.length; i++) {
            System.out.println((i+1) + " ==> " + result[i]);
        }

//        System.out.println("Bellman-Ford Algorithm");
//        result = findShortestTimeBellmanFord(adjList, n, k);
//        for (int i = 0; i < result.length; i++) {
//            System.out.println((i+1) + " ==> " + result[i]);
//        }

        timeToTravel  = new int[][]{
                {1, 2,  9},
                {1, 4,  2},
                {2, 5, -3},
                {4, 2, -4},
                {4, 5,  6},
                {3, 2,  3},
                {5, 3,  7},//Comment this to see unreachable node
                {3, 1,  5},//Make the weight -5 to check the effect of negative cycle.
        };

        adjList = buildGraph(n, timeToTravel);
        System.out.println("Dijkstra's Algorithm, new");
        result = findShortestTimeDijkstra(adjList, n, k);
        for (int i = 0; i < result.length; i++) {
            System.out.println((i+1) + " ==> " + result[i]);
        }

//        System.out.println("Bellman-Ford Algorithm");
//        result = findShortestTimeBellmanFord(adjList, n, k);
//        for (int i = 0; i < result.length; i++) {
//            System.out.println((i+1) + " ==> " + result[i]);
//        }

        System.out.println();
    }
}

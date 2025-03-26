package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class ShortestPath_GRAPH {


    public static ArrayList<List<int[]>> buildGraph(int n, int[][] paths) {
        ArrayList<List<int[]>> adjList = new ArrayList<>(Collections.nCopies(n, null));
        if (paths == null || paths.length == 0 || paths[0].length == 0) return adjList;
        for (int[] path : paths) {
            int sVi = path[0];
            int dVi = path[1];
            int weight = path[2];
            List<int[]> sV = adjList.get(sVi);
            if (sV == null) {
                sV = new LinkedList<>();
                adjList.set(sVi, sV);
            }
            sV.add(new int[] {dVi, weight});
        }
        return adjList;
    }

    public static int[] findShortestPathByDijkstrasAlgo(ArrayList<List<int[]>> adjList, int k) {
        int[] result = new int[adjList.size()];
        Arrays.fill(result, Integer.MAX_VALUE);

        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] < o2[1]) return 1; else return 0;
            }
        });

        result[k] = 0;
        heap.offer(new int[] {k, result[k]});
        while (!heap.isEmpty()) {
            int[] minValNode = heap.remove();
            int currentVi = minValNode[0];
            List<int[]> connections = adjList.get(currentVi);
            if( connections != null) {
                for (int[] connection : connections) {
                    int destVi = connection[0];
                    int destVw = connection[1];
                    if (result[destVi] > result[currentVi] + destVw) {
                        result[destVi] = result[currentVi] + destVw;
                        heap.offer(new int[] {destVi, result[destVi]});
                    }
                }
            }
        }
        return result;
    }

    public static int[] findShortestPathByBellmanFordAlgo(ArrayList<List<int[]>> adjList, int k) {
        int n = adjList.size();
        int[] result = new int[n];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[k] = 0;
        for (int iteration = 1; iteration <= n-1; iteration++) {
            boolean isModified = false;
            for (int i = 0; i < adjList.size(); i++) {
                List<int[]> connectionList = adjList.get(i);
                if (connectionList != null) {
                    for (int[] connection : connectionList) {
                        int dVi = connection[0];
                        int dVw = connection[1];
                        int sVw = result[i];
                        if (sVw != Integer.MAX_VALUE) {
                            if (result[dVi] > sVw + dVw) {
                                result[dVi] = sVw + dVw;
                                isModified = true;
                            }
                        }
                    }
                }
            }
            System.out.println(iteration + " Is modified: " + isModified);
        }
        return result;
    }


    public static void main(String[] args) {
        int[][] paths = {
                {0, 1, 4},
                {0, 2, 2},
                {1, 3, 1},
                {2, 3, 6},
                {2, 4, 5},
                {3, 4, 3},
                {3, 5, 4},
                {4, 5, 10},
                {5, 1, 5}
        };
        int n = 6;
        int k = 0;

        ArrayList<List<int[]>> adjList = buildGraph(n, paths);
        int[] shortestPath = findShortestPathByDijkstrasAlgo(adjList, k);
        for (int i = 0; i < shortestPath.length; i++) {
            System.out.println(i + " ==> " + shortestPath[i]);
        }
        shortestPath = findShortestPathByBellmanFordAlgo(adjList, k);
        System.out.println("======================================================");
        for (int i = 0; i < shortestPath.length; i++) {
            System.out.println(i + " ==> " + shortestPath[i]);
        }
    }
}

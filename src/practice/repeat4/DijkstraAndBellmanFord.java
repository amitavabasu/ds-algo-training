package practice.repeat4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraAndBellmanFord {

    public static List<List<Integer[]>> buildAdjList(int[][] data, int n) {
        List<List<Integer[]>> adjList = new ArrayList<>(Collections.nCopies(n, null));
        if (data == null || data.length == 0 || data[0].length == 0) return adjList;
        for (int[] row : data) {
            int src = row[0];
            int dst = row[1];
            int weight = row[2];
            List<Integer[]> connections = adjList.get(src);
            if (connections == null) {
                connections = new LinkedList<>();
                adjList.set(src, connections);
            }
            connections.add(new Integer[]{dst, weight});
        }
        return adjList;
    }


    public static int[] findShortest(int k, List<List<Integer[]>> adjList) {
        int n = adjList.size();
        int[] result = new int[n];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[k] = 0;
        PriorityQueue<Integer[]> pq = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[1].compareTo(o2[1]);
            }
        });
        pq.offer(new Integer[]{k, result[k]});
        while (!pq.isEmpty()) {
            int lowestWeightIdx = pq.remove()[0];
            List<Integer[]> connections = adjList.get(lowestWeightIdx);
            if (connections != null) {
                for(Integer[] connection : connections) {
                    int dstIdx = connection[0];
                    int dstWeight = connection[1];
                    int newDstWeight = result[lowestWeightIdx] + dstWeight;
                    if (newDstWeight < result[dstIdx]) {
                        result[dstIdx] = newDstWeight;
                        pq.offer(new Integer[]{dstIdx, result[dstIdx]});
                    }
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int n = 5;
        int k = 0;
        int[][] timeToTravel  = {
                {0, 1, 9},
                {0, 3, 2},
                {1, 4, 1},
                {3, 1, 4},
                {3, 4, 6},
                {2, 1, 3},
                //{4, 2, 7},//Comment this to see unreachable node
                {2, 0, 5},
        };

        List<List<Integer[]>> adjList = buildAdjList(timeToTravel, n);
        System.out.println("ShortestPath ==> " + Arrays.toString(Arrays.stream(findShortest(k, adjList)).toArray()));



    }
}

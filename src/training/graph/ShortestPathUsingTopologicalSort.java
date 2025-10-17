package training.graph;

import java.util.*;
import java.util.stream.Collectors;

public class ShortestPathUsingTopologicalSort {

    public static void findShortestPath(List<List<Integer[]>> adjList, int n, int start, int end,
                                                 List<Integer> results, List<Integer> distances) {
        if (adjList == null || adjList.isEmpty()) return;
        int[] inDegree = new int[n];
        for (int i = 0; i < adjList.size(); i++) {
            List<Integer[]> edges = adjList.get(i);
            if (edges != null && !edges.isEmpty()) {
                for (Integer[] edge : edges) {
                    inDegree[edge[0]]++;
                }
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }
        List<Integer> topo = new ArrayList<>();
        while (!queue.isEmpty()) {
            int nodeIndex = queue.remove();
            topo.add(nodeIndex);
            List<Integer[]> edges = adjList.get(nodeIndex);
            if (edges != null && !edges.isEmpty()) {
                for (Integer[] edge : edges) {
                    inDegree[edge[0]]--;
                    if (inDegree[edge[0]] == 0) queue.add(edge[0]);
                }
            }
        }
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        distances.set(start, 0);
        for (Integer s : topo) {
            if (distances.get(s) == Integer.MAX_VALUE) continue;
            List<Integer[]> edges = adjList.get(s);
            if (edges != null && !edges.isEmpty()) {
                for (Integer[] edge : edges) {
                    if (distances.get(s) + edge[1] < distances.get(edge[0])) {
                        distances.set(edge[0], (distances.get(s) + edge[1]));
                        parent[edge[0]] = s;
                    }
                }
            }
        }
        //Generate result
        for (int i = end; i != -1; i = parent[i]) results.add(i);
        Collections.reverse(results);
    }




    public static void main(String[] args) {
        int[][][] adjArray = {
                {{1, 10}},//0
                {},//1
                {{1, 10}},//2
                {{4, 12}},//3
                {{1, 11}, {2, 21}, {5, 14}},//4
                {{2, -30}}//5
        };
        int n = 6;
        int start = 4;
        int goal = 1;

        List<List<Integer[]>> adjList = Arrays.stream(adjArray)
                // Stream<int[][]>
                .map(twoDArray -> Arrays.stream(twoDArray)
                        // Stream<int[]>
                        .map(oneDArray -> Arrays.stream(oneDArray)
                                // IntStream
                                .boxed() // Stream<Integer>
                                .toArray(Integer[]::new)) // Integer[]
                        .collect(Collectors.toList())) // List<Integer[]>
                .collect(Collectors.toList()); // List<List<Integer[]>>

        adjList.forEach(list -> {
            list.forEach(array -> System.out.print(Arrays.toString(array) + " "));
            System.out.println();
        });

        List<Integer> result = new ArrayList<>();
        List<Integer> distances = new ArrayList<>(Collections.nCopies(n, Integer.MAX_VALUE));

        findShortestPath(adjList, n, start, goal, result, distances);
        System.out.println(result);
        System.out.println(distances);
    }

}

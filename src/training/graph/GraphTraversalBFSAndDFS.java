package training.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphTraversalBFSAndDFS {

    public static int[][] adjacencyList = {
            /* 0-->*/{1, 3},
            /* 1-->*/{0},
            /* 2-->*/{3,8},
            /* 3-->*/{0,2,4,5},
            /* 4-->*/{3,6},
            /* 5-->*/{3},
            /* 6-->*/{4,7},
            /* 7-->*/{6},
            /* 8-->*/{2}
    };

    public static List<Integer> traversalBFS(int[][] graph) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        List<Integer> values = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];
        while (!queue.isEmpty()) {
            int value = queue.poll();
            values.add(value);
            visited[value] =  true;
            for (int connection : graph[value]) {
                if (!visited[connection]) {
                    queue.offer(connection);
                }
            }
        }
        return values;
    }

    public static void traversalDFS(int vertex, int[][] graph, List<Integer> values, boolean[] visited) {
        values.add(vertex);
        visited[vertex] = true;
        for (int connection : graph[vertex]) {
            if(!visited[connection]) {
                traversalDFS(connection, graph, values, visited);
            }
        }
    }



    public static void main(String[] args) {
        System.out.println("BFS Traversal values ==>" + Arrays.toString(traversalBFS(adjacencyList).toArray()));
        int startingVertex = 0;
        boolean[] visited = new boolean[adjacencyList.length];
        List<Integer> values = new LinkedList<>();
        traversalDFS(startingVertex, adjacencyList, values, visited);
        System.out.println("DFS Traversal values ==>" + Arrays.toString(values.toArray()));
    }
}

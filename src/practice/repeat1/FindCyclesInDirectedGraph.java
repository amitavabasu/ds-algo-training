package practice.repeat1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class FindCyclesInDirectedGraph {

    public static void buildGraph(int[][] edges, ArrayList<List<Integer>> adjacencyList, int[] inDegree) {
        if (edges == null || edges.length == 0) return;
        for (int i = 0; i < edges.length; i++) {
            int source = edges[i][0];
            int destination = edges[i][1];
            List<Integer> destinationList = adjacencyList.get(source);
            if(destinationList == null) {
                destinationList = new LinkedList<>();
                adjacencyList.set(source, destinationList);
            }
            destinationList.add(destination);
            inDegree[destination]++;
        }
    }

    public static boolean detectCycle(ArrayList<List<Integer>> adjacencyList, int[] inDegree) {
        if (adjacencyList == null || adjacencyList.size() == 0 || inDegree == null || inDegree.length == 0) return false;
        int processedVertexCount = 0;
        Stack<Integer> zeroInDegreeStack = new Stack<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                zeroInDegreeStack.push(i);
            }
        }
        while (!zeroInDegreeStack.isEmpty()) {
            int source = zeroInDegreeStack.pop();
            List<Integer> destinationList = adjacencyList.get(source);
            if (destinationList != null) {
                for (Integer destination : destinationList) {
                    inDegree[destination]--;
                    if (inDegree[destination] == 0) {
                        zeroInDegreeStack.push(destination);
                    }
                }
            }
            processedVertexCount++;
        }
        return processedVertexCount != inDegree.length;
    }



    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {
                {0, 1},
                {1, 2},
                {2, 5}, //exchange to test negative case
                {3, 0},
                {3, 4},
                {5, 3},
                {5, 4}
        };

        ArrayList<List<Integer>> adjList = new ArrayList<>(Collections.nCopies(n,null));
        int[] inDegree = new int[n];

        buildGraph(edges, adjList, inDegree);

        System.out.println("Cycle Exists ==> " + (detectCycle(adjList, inDegree) ? "Yes" : "No"));
    }

}

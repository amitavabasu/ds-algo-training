package practice.repeat3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {

    public static List<List<Integer>> buildGraph(int n, int[][] prerequisites, int[] inDegree) {
        List<List<Integer>> adjList = new ArrayList<>(Collections.nCopies(n, null));

        for (int[] item : prerequisites) {
            int currentCourse = item[0];
            int preCourse = item[1];
            List<Integer> existingPreCourse = adjList.get(preCourse);
            if (existingPreCourse == null) {
                existingPreCourse = new ArrayList<>();
                adjList.set(preCourse, existingPreCourse);
            }
            existingPreCourse.add(currentCourse);
            inDegree[currentCourse]++;
        }
        return adjList;
    }

    public static boolean findCompletable(List<List<Integer>> adjList, int[] inDegree) {
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) stack.push(i);
        }
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            count++;
            List<Integer> edges = adjList.get(vertex);
            if (edges != null) {
                for (int postCourse : edges) {
                    inDegree[postCourse]--;
                    if (inDegree[postCourse] == 0) {
                        stack.push(postCourse);
                    }
                }
            }
        }
        return count == inDegree.length;
    }



    public static void main(String[] args) {
        int n = 6;
        int[][] prerequisites = {
                {1,0},
                {2,1},
                {5,2}, //exchange to test negative case
                {0,3},
                {4,3},
                {3,5},
                {4,5}
        };

        int[] inDegree = new int[n];
        Arrays.fill(inDegree, 0);

        List<List<Integer>> adjList = buildGraph(n, prerequisites, inDegree);
        System.out.println(adjList);
        System.out.println(Arrays.toString(inDegree));
        System.out.println("Can complete course ==> " + (findCompletable(adjList, inDegree) ? "Yes" : "No"));
    }
}

package practice.repeat7;

import java.util.*;

public class TopologicalSortGRAPH {

    public static void buildGraph(int[][] preReq, int n, List<List<Integer>> adjList, int[] indegree) {
        if (preReq == null) return;
        for (int[] element : preReq) {
            int course = element[0];
            int preRCourse = element[1];
            List<Integer> courseList = adjList.get(preRCourse);
            if (courseList == null) courseList = new ArrayList<>();
            courseList.add(course);
            adjList.set(preRCourse, courseList);
            indegree[course]++;
        }
    }

    public static boolean findCompletable(List<List<Integer>> adjList, int[] inDegree) {
        if (adjList == null || adjList.isEmpty()) return true;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                stack.push(i);
            }
        }
        int cCount = 0;
        while (!stack.isEmpty()) {
            int cIndex = stack.pop();
            cCount++;
            List<Integer> courseList = adjList.get(cIndex);
            if (courseList != null) {
                for (int course : courseList) {
                    inDegree[course] --;
                    if (inDegree[course] == 0) {
                        stack.push(course);
                    }
                }
            }
        }
        return cCount == inDegree.length;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] prerequisites = {
                {1,0}, //1 <-- 0
                {2,1}, //2 <-- 1
                {5,2}, //2 <-- 5 (exchange to test negative case)
                {0,3}, //0 <-- 3
                {4,3}, //4 <-- 3
                {3,5}, //3 <-- 5
                {4,5}  //4 <-- 5
        };

        ArrayList<List<Integer>> adjList = new ArrayList<>(Collections.nCopies(n,null));
        int[] inDegree = new int[n];

        buildGraph(prerequisites, n, adjList, inDegree);

        System.out.println(adjList);
        System.out.println(Arrays.toString(inDegree));

        System.out.println("Can complete course ==> " + (findCompletable(adjList, inDegree) ? "Yes" : "No"));
        System.out.println();
    }
}

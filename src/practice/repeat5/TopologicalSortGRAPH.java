package practice.repeat5;

import java.util.*;

public class TopologicalSortGRAPH {



    public static void main(String[] args) {
        int n = 6;
        int[][] prerequisites = {
                {1,0},
                {2,1},
                {2,5}, //exchange to test negative case
                {0,3},
                {4,3},
                {3,5},
                {4,5}
        };

        ArrayList<LinkedList<Integer>> adjList = new ArrayList<>(Collections.nCopies(n,null));
        int[] inDegree = new int[n];
        Arrays.fill(inDegree, 0);

        buildGraph(prerequisites, adjList, inDegree);

        System.out.println(adjList);
        System.out.println(Arrays.toString(inDegree));

        System.out.println("Can complete course ==> " + (findCompletable(adjList, inDegree) ? "Yes" : "No"));
        System.out.println("");
    }

    public static void buildGraph(int[][] preq, ArrayList<LinkedList<Integer>> adjList, int[] inDegree) {
        for (int[] pre : preq) {
            int cource = pre[0];
            int prevc = pre[1];
            LinkedList<Integer> courses = adjList.get(prevc);
            if (courses == null) {
                courses = new LinkedList<>();
                adjList.set(prevc, courses);
            }
            courses.add(cource);
            inDegree[cource]++;
        }
    }

    public static boolean findCompletable(ArrayList<LinkedList<Integer>> adjList, int[] inDegree) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                stack.push(i);
            }
        }
        int count = 0;
        while (!stack.isEmpty()) {
            int index = stack.pop();
            count++;
            LinkedList<Integer> courses = adjList.get(index);
            if (courses != null) {
                for (int course : courses) {
                    inDegree[course]--;
                    if (inDegree[course] == 0) {
                        stack.push(course);
                    }
                }
            }
        }
        return (count == inDegree.length);
    }


}

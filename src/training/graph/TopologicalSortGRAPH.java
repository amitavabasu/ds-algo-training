package training.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TopologicalSortGRAPH {

    public static void buildGraph(int[][] prerequisites, ArrayList<List<Integer>> adjList, int[] inDegree) {
        for (int i = 0; i < prerequisites.length; i++) { //Iterate over prerequisites array
            int[] pre = prerequisites[i]; //For each prerequisite course
            int course = pre[0]; // get the course
            int pCourse = pre[1]; //get the prerequisite course
            List<Integer> followUpCourses = adjList.get(pCourse); //From the adjacency list pickup the node representing pre-course
            if ( followUpCourses == null) { //Check that pre course's available post course list is not null
                followUpCourses = new LinkedList<>(); //If null create new list of post courses
                adjList.set(pCourse, followUpCourses);//Set the list as this pre-course's post course list
            }
            followUpCourses.add(course);// add the course as post course list
            inDegree[course]++;//increment the in-degree count of this course, as this course has a directed edge from it's pre-course
        }
    }

    public static boolean findCompletable(ArrayList<List<Integer>> adjList, int[] inDegree) {
        int vertexCount = 0; //this variable is needed to compare that all nodes are processed
        Stack<Integer> stack = new Stack<>();//this stack is needed to hold all the nodes who's in-degree becomes 0
        for (int i = 0; i < inDegree.length; i++) { //iterate over in-degree array
            if (inDegree[i] == 0) { //if it's in-degree value is 0
                stack.push(i);//push that node into the stack
            }
        }
        while (!stack.isEmpty()) { //repeat until the stack is empty
            int currentVertex = stack.pop();//pop the node who's in-degree is 0
            vertexCount ++; // increment the processed vertex count as this vertex is getting processed now
            List<Integer> followUpCourses = adjList.get(currentVertex);//find the list of post-course nodes
            if (followUpCourses != null) { //check that post-courses node list is not null, because a course can have no post-courses
                for (int followUpCourse : followUpCourses) { //iterate over all the post-courses this course can have
                    inDegree[followUpCourse]--; //decrease the in-degree count of each post-courses
                    if (inDegree[followUpCourse] == 0) {//if post-courses in-degree count becomes 0
                        stack.push(followUpCourse);//push that course into stack to process
                    }
                }
            }
        }
        return vertexCount == inDegree.length; //If the processed vertex count is same as number of vertexes then there is no cycle otherwise there exist a cycle in this graph.
    }


    public static void main(String[] args) {
        int n = 6;
        int[][] prerequisites = {
                {1,0}, //1 <-- 0
                {2,1}, //2 <-- 1
                {2,5}, //2 <-- 5 (exchange to test negative case)
                {0,3}, //0 <-- 3
                {4,3}, //4 <-- 3
                {3,5}, //5 <-- 5
                {4,5}  //4 <-- 5
        };

        ArrayList<List<Integer>> adjList = new ArrayList<>(Collections.nCopies(n,null));
        int[] inDegree = new int[n];

        buildGraph(prerequisites, adjList, inDegree);

        System.out.println(adjList);
        System.out.println(Arrays.toString(inDegree));

        System.out.println("Can complete course ==> " + (findCompletable(adjList, inDegree) ? "Yes" : "No"));
        System.out.println();
    }

}

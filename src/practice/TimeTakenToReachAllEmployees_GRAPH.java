package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TimeTakenToReachAllEmployees_GRAPH {


    public static ArrayList<List<Integer>> buildTheGraph(int n, int[] managers) {
        ArrayList<List<Integer>> adjList = new ArrayList<>(Collections.nCopies(n, null));
        for (int i = 0; i < n; i++) {
            int employee = i;
            int manager = managers[i];
            if (manager > 0) {
                List<Integer> subordinates = adjList.get(manager);
                if (subordinates == null) {
                    subordinates = new LinkedList<>();
                    adjList.set(manager, subordinates);
                }
                subordinates.add(employee);
            }
        }
        return adjList;
    }

    public static int findTimeNeededToInformAll(int head, ArrayList<List<Integer>> adjList, int[] times) {
        int maxTime = 0;
        List<Integer> subordinates = adjList.get(head);
        if (subordinates != null) {
            for (int subordinate : subordinates) {
                int time = findTimeNeededToInformAll(subordinate, adjList, times);
                maxTime = Math.max(maxTime, time);
            }
        }
        return maxTime + times[head];
    }






    public static void main(String[] args) {
        int numberOfEmployees = 8; //[0-7]
        int headOfCompany = 4;
        int[] managers = {2, 2, 4, 6, -1, 4, 4, 5}; //-1 means have no manager
        int[] informTime = {0, 0, 4, 0, 7, 3, 6, 0};//0's are employees have no subordinates
        ArrayList<List<Integer>> adjacencyList = buildTheGraph(numberOfEmployees, managers);
        int time = findTimeNeededToInformAll(headOfCompany, adjacencyList, informTime);
        System.out.println("Time to inform all employees => " + time);
    }

}

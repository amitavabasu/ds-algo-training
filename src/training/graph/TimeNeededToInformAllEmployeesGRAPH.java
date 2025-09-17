package training.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TimeNeededToInformAllEmployeesGRAPH {
    static int numberOfEmployees = 8; //[0-7]
    static int headOfCompany = 4;//Index not position
    static int[] managers = {2, 2, 4, 6, -1, 4, 4, 5}; //-1 means have no manager
    static int[] informTime = {0, 0, 4, 0, 7, 3, 6, 0};//0's are employees have no subordinates

    public static ArrayList<List<Integer>> buildTheGraph() {
        ArrayList<List<Integer>> adjacencyList = new ArrayList<>(Collections.nCopies(numberOfEmployees, null));
        for (int i = 0; i < managers.length; i++) {
            int manager = managers[i];
            if (manager >= 0) {
                List<Integer> subordinates = adjacencyList.get(manager);
                if (subordinates == null) {
                    subordinates = new LinkedList<>();
                    adjacencyList.set(manager, subordinates);
                }
                subordinates.add(i);
            }
        }
        return adjacencyList;
    }

    public static int findTimeNeededToInformAll(int vertex, ArrayList<List<Integer>> graph, int[] informTime) {
        //We don't need visited array because it is an n-ary tree.
        int maxTime = 0;
        List<Integer> connections = graph.get(vertex);
        if (connections != null) {
            for (Integer connection : connections) {
                int time = findTimeNeededToInformAll(connection, graph, informTime);
                if (maxTime < time) {
                    maxTime = time;
                }
            }
        }
        return maxTime + informTime[vertex];
    }





    public static void main(String[] args) {
        ArrayList<List<Integer>> adjacencyList = buildTheGraph();
        int time = findTimeNeededToInformAll(headOfCompany, adjacencyList, informTime);
        System.out.println("Time to inform all employees => " + time);
    }
}

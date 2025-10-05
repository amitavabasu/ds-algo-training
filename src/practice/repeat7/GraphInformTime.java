package practice.repeat7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphInformTime {
    static int numberOfEmployees = 8; //[0-7]
    static int headOfCompany = 4;//Index not position
    static int[] managers = {2, 2, 4, 6, -1, 4, 4, 5}; //-1 means have no manager
    static int[] informTime = {0, 0, 4, 0, 7, 3, 6, 0};//0's are employees have no subordinates

    public static List<List<Integer>> buildAdjList(int n, int headIndex, int[] managers) {
        if (n == 0 || managers == null || managers.length == 0 || headIndex < 0 || headIndex > managers.length) return Collections.emptyList();
        List<List<Integer>> adjList = new ArrayList<>(Collections.nCopies(n, null));
        for (int i = 0; i < managers.length; i++) {
            int manager = managers[i];
            if (manager >= 0) {
                List<Integer> subordinates = adjList.get(manager);
                if (subordinates == null) {
                    subordinates = new ArrayList<>();
                    adjList.set(manager, subordinates);
                }
                subordinates.add(i);
            }
        }
        return adjList;
    }

    public static int findTimeNeededToInformAll(int head, List<List<Integer>> adjList, int[] informTimes) {
        if (adjList == null || informTime == null || adjList.size() == 0 || informTime.length == 0 || adjList.size() != informTime.length) return 0;
        int maxTime = 0;
        List<Integer> subordinates = adjList.get(head);
        if (subordinates != null) {
            for (Integer subordinate : subordinates) {
                int informTime = findTimeNeededToInformAll(subordinate, adjList, informTimes);
                if (maxTime < informTime) maxTime = informTime;
            }
        }
        return maxTime + informTimes[head];
    }

    public static void main(String[] args) {
        List<List<Integer>> adjList = buildAdjList(numberOfEmployees, headOfCompany, managers);
        int time = findTimeNeededToInformAll(headOfCompany, adjList, informTime);
        System.out.println("Time to inform all employees => " + time);
    }

}

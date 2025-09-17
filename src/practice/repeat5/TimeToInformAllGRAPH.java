package practice.repeat5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class TimeToInformAllGRAPH {

    public static ArrayList<LinkedList<Integer>> buildGraph(int[] managers, int count, int head) {
        ArrayList<LinkedList<Integer>> adjList = new ArrayList<>(Collections.nCopies(count, null));
        for (int employeeIndex = 0; employeeIndex < count; employeeIndex++) {
            int managerIndex = managers[employeeIndex];
            if (managerIndex >= 0) {
                LinkedList<Integer> subordinates = adjList.get(managerIndex);
                if (subordinates == null) {
                    subordinates = new LinkedList<>();
                    adjList.set(managerIndex, subordinates);
                }
                subordinates.add(employeeIndex);
            }
        }
        return adjList;
    }

    public static int maxTime(ArrayList<LinkedList<Integer>> adjList, int[] time, int head) {
        int maxTime = 0;
        LinkedList<Integer> subs = adjList.get(head);
        if (subs != null) {
            for (Integer sub : subs) {
                int timeToInform = maxTime(adjList, time, sub);
                maxTime = Math.max(maxTime, timeToInform);
            }
        }
        return maxTime + time[head];
    }


    public static void main(String[] args) {
        int numberOfEmployees = 8; //[0-7]
        int headOfCompany = 4;//Index not position
        int[] managers = {2, 2, 4, 6, -1, 4, 4, 5}; //-1 means have no manager
        int[] informTime = {0, 0, 4, 0, 7, 3, 6, 0};//0's are employees have no subordinates

        ArrayList<LinkedList<Integer>> adjList = buildGraph(managers, numberOfEmployees, headOfCompany);

        System.out.println(maxTime(adjList, informTime, headOfCompany));

    }
}

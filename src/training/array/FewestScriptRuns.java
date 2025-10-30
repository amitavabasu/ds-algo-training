package training.array;

/*
    # Fewest Script Runs

    There are `n` meetings scheduled, each with a start time and an end time. We have a script that, when run, captures some information about all ongoing meetings. Given an array, `meetings`, where each element is a tuple `[l, r]` with `l < r`, what's the minimum number of times we need to run the script to capture information from all meetings?

    If the script runs at the same time that a meeting starts or ends, it captures the information for that meeting.

    Example 1:
    meetings = [[2, 3], [1, 4], [2, 3], [3, 6], [8, 10]]

    Output: 2
    We can run the script at t = 3 and t = 9.

    Constraints:

    - `0 <= n <= 10^5`
    - `0 <= meetings[i][0] < meetings[i][1] <= 10^9`
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class FewestScriptRuns {

    public static int minScriptRun(int[][] meetings) {
        if (meetings == null || meetings.length == 0) return 0;
        Arrays.sort(meetings, new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        Stack<int[]> stack = new Stack<>();
        for (int[] meeting : meetings) {
            if (stack.isEmpty()) {
                stack.push(meeting);
            } else {
                int[] maxMin = stack.pop();
                if (meeting[0] >= maxMin[0] && meeting[0] <= maxMin[1]) {
                    //it should be in the same group
                    int max = Math.max(meeting[0], maxMin[0]);
                    int min = Math.min(meeting[1], maxMin[1]);
                    stack.push(new int[]{max, min});
                } else {
                    stack.push(maxMin);
                    stack.push(meeting);
                }
            }
        }
        return stack.size();
    }

    public static int minScriptRun2(int[][] meetings) {
        if (meetings == null || meetings.length == 0) return 0;
        Arrays.sort(meetings, new Comparator<int[]> () {
           @Override
           public int compare(int[] o1, int[] o2) {
               return Integer.compare(o1[1], o2[1]);
           }
        });
        int count = 0;
        int runTime = -1;
        for (int[] meeting : meetings) {
            if (meeting[0] > runTime) {
                count++;
                runTime = meeting[1];
                System.out.println("Runtime: " + meeting[1]);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] meetings = {
            {2, 3}, {1, 4}, {2, 3}, {3, 6}, {8, 10}
        };

        System.out.println("Result ==>" + minScriptRun(meetings));

        System.out.println("Result2 ==>" + minScriptRun2(meetings));

    }
}

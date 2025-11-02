package training.array;
/*
    Hello! Your interview question is below. Write code in this pad just like you would normally – your AI Interviewer will be able to see it.

    # Nested Array Sum

    A _nested array_ is an array where each element is either:

    1. An integer, or
    2. A nested array (note that this is a recursive definition).

    The _sum_ of a nested array is defined recursively as the sum of all its elements.
    Given a nested array, `arr`, return its sum.

    Example 1: arr = [1, [2, 3], [4, [5]], 6]
    Output: 21

    Example 2: arr = [[[[1]], 2]]
    Output: 3

    Example 3: arr = []
    Output: 0

    Example 4: arr = [[], [1, 2], [], [3]]
    Output: 6

    Example 5: arr = [-1, [-2, 3], [4, [-5]], 6]
    Output: 5

    Constraints:

    - The array can be nested to any depth
    - Each integer in the array is between -10^9 and 10^9
    - The total number of integers in the array (counting nested ones) is at most 10^5
 */
public class NestedArraySum {

    public static int getSum(Object[] data) {
        if (data == null || data.length == 0) return 0;
        int sum = 0;
        for (Object obj : data) {
            if (obj.getClass().isAssignableFrom(Integer.class)) {
                sum = sum+ (Integer)obj;
            } else {
                sum = sum + getSum((Object[]) obj);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Object[][] data ={
                {1, new Object[]{2,3}, new Object[]{4, new Object[]{5}}, 6},
                {new Object[]{new Object[]{new Object[]{1}}, 2}},
                {},
                {-1, new Object[]{-2, 3}, new Object[]{4, new Object[]{-5}}, 6}
        };
        for (Object[] o : data) {
            System.out.println("Result ==> " + getSum(o));
        }
    }

}

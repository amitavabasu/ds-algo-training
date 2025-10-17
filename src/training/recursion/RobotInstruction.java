package training.recursion;

import java.util.ArrayList;

public class RobotInstruction {
    /*
Hello! Your interview question is below. Write code in this pad just like you would normally – your AI Interviewer will be able to see it.

# Robot Instructions

We are given a string, `seq`, with a sequence of instructions for a robot.
The string consists of characters `'L'`, `'R'`, and `'2'`. The letters `'L'` and `'R'` instruct the robot to move left or right.

The character `'2'` (which never appears at the end of the string) means "perform all the instructions after this `'2'` twice, but skip the instruction immediately following the `'2'` during the second repetition." Output a string with the final list of left and right moves that the robot should do.

Example 1: seq = "LL"
Output: "LL"

Example 2: seq = "2LR"
Output: "LRR". The '2' indicates that we need to do "LR" first and then "R".

Example 3: seq = "2L"
Output: "L". The '2' indicates that we need to do "L" first and then "" (the empty string).

Example 4: seq = "22LR"
Output: "LRRLR". The first '2' indicates that we need to do "2LR" first and then "LR".

Example 5: seq = "LL2R2L"
Output: "LLRLL"

Constraints:

- The length of `seq` is at most `10^4`
- `seq` consists only of the characters `'L'`, `'R'`, and `'2'`
- `'2'` never appears at the end of `seq`
     */

    public static String robotInstruction(String expression, int index) {
        if (expression == null || expression.isEmpty() || index >= expression.length()) return "";
        if (expression.charAt(index) == '2') {
            String part1 = robotInstruction(expression, index + 1);
            String part2 = robotInstruction(expression, index + 2);
            return part1 + part2;
        } else if (expression.charAt(index) == 'L' || expression.charAt(index) == 'R') {
            return expression.charAt(index) + robotInstruction(expression, index + 1);
        } else {
            throw new RuntimeException("Invalid Input in expression instruction");
        }
    }

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("2LR");
        strings.add("2L");
        strings.add("22LR");
        strings.add("LL2R2L");
        for (String string : strings) {
            System.out.println(string + " ==> " + robotInstruction(string, 0));
        }
    }
}

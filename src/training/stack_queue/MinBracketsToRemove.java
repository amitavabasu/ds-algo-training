package training.stack_queue;

import java.util.Stack;

public class MinBracketsToRemove {

    public static String removeMinBrackets(String inputString) {
        if (inputString == null || "".equals(inputString)) {
            return inputString;
        }
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder(inputString);
        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) == '(') {
                stack.push(i);
            } else if(inputString.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    sb.setCharAt(i, '~');
                } else {
                    stack.pop();
                }
            }
        }
        while (!stack.isEmpty()) {
            int removeIndex = stack.pop();
            sb.setCharAt(removeIndex, '~');
        }
        return sb.toString().replaceAll("~", "");
    }

    public static void main(String[] args) {
        String[] inputStrings = {
                "a(bc(d)", //<-- abc(d)
                "(ab(c)d", //<-- ab(c)d || (abc)d
                "))((" //<-- ""
        };
        for (String inputString : inputStrings) {
            System.out.println(inputString + " ==> " + removeMinBrackets(inputString));
        }
    }

}

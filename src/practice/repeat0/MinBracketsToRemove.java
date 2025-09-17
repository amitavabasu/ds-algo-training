package practice.repeat0;

import java.util.Stack;

public class MinBracketsToRemove {

    public static String removeMinBrackets(String s) {
        if (s == null || s.length() == 0) return s;
        Stack<Integer> stack = new Stack<>();
        StringBuffer sb = new StringBuffer(s);
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (sb.charAt(i) == ')') {
                    if (stack.empty()) {
                        sb.setCharAt(i, '~');
                    } else {
                        stack.pop();
                    }
                } else {
                    //do nothing
                }
            }
        }
        while (!stack.empty()) {
            int index = stack.pop();
            sb.setCharAt(index, '~');
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

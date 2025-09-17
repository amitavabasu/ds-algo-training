package practice.repeat0;

import java.util.Stack;

public class BackSpaceStringComparison {

    public static String removeBackSpaces(String s) {
        if (s == null || s.length() == 0) return "";
        int hashCount = 0;
        String output = "";
        int i = s.length()-1;
        while(i >= 0) {
            if (s.charAt(i) == '#') {
                hashCount++;
                i--;
            } else {
                if (hashCount > 0) {
                    hashCount--;
                    i--;
                } else {
                    output = s.charAt(i) + output;
                    i--;
                }
            }
        }
        return output;
    }

    public static String removeBackSpacesUsingStack(String s) {
        if (s == null || s.length() == 0) return "";
        String output = "";
        int i = 0;
        Stack<Character> stack = new Stack<>();
        while(i < s.length()) {
            if (s.charAt(i) == '#') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(s.charAt(i));
            }
            i++;
        }
        while (!stack.isEmpty()) {
            output = stack.pop() + output;
        }
        return output;
    }
    public static boolean getMatches(String S, String T) {
        String sBar = removeBackSpaces(S);
        String tBar = removeBackSpaces(T);
        return sBar.equals(tBar);
    }
    public static boolean getMatchesUsingStack(String S, String T) {
        String sBar = removeBackSpacesUsingStack(S);
        String tBar = removeBackSpacesUsingStack(T);
        return sBar.equals(tBar);
    }

    public static void main(String[] args) {

        String S = "abc#d"; //<-- abd
        String T = "abzz##d"; //<-- abd


        System.out.println("Matches==> " + getMatches(S, T));
        System.out.println("Matches==> " + getMatchesUsingStack(S, T));
    }
}

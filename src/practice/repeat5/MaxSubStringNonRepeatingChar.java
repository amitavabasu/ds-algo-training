package practice.repeat5;

import java.util.HashMap;
import java.util.Map;

public class MaxSubStringNonRepeatingChar {

    public static int getLongestSubstring(String input) {
        if (input == null || input.isEmpty()) return 0;
        int maxLength = 1;
        Map<Character, Integer> map = new HashMap<>();
        int l = 0;
        int r = 0;
        while (r < input.length()) {
            Integer existingCharIndex = map.getOrDefault(input.charAt(r), null);
            if (existingCharIndex == null) {
                map.put(input.charAt(r), r);
            } else if (existingCharIndex < l) {
                map.put(input.charAt(r), r);
            } else {
                l = r+1;
            }
            r++;
            maxLength = Math.max(maxLength, (r - l));
        }
        return maxLength+1;
    }

    public static void main(String[] args) {
        String[] inputStrings = {
                "abcbdaac", "abcbdacefffffghijklmn", "abcbdaac", "abcbdaac", "abcbdaac"
        };
        for (int i = 0; i < inputStrings.length; i++) {
            String inputString = inputStrings[i];
            System.out.println("Iteration: " + i +" String: '" +inputString+ "' Length of longest substring without repeating characters == > " + getLongestSubstring(inputString));
        }
    }
}

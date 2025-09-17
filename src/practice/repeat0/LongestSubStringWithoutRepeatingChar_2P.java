package practice.repeat0;

import java.util.HashMap;
import java.util.Map;

public class LongestSubStringWithoutRepeatingChar_2P {

    public static int getLongestSubstring(String inputString) {
        if (inputString == null || inputString.length() == 0) return 0;
        int longestSubStringLength = 0;
        Map<Character, Integer> map = new HashMap<>();
        int lp = 0;
        int rp = lp;
        while (lp < inputString.length() && rp < inputString.length()) {
            char c = inputString.charAt(rp);
            Integer prevIndex = map.get(c);
            if (prevIndex == null || prevIndex < lp) {
                longestSubStringLength = Math.max(longestSubStringLength, (rp-lp+1));
                map.put(c, rp);
                rp++;
            } else {
                lp = prevIndex+1;
            }
        }
        return longestSubStringLength;
    }




    public static void main(String[] args) {
        String[] inputStrings = {
                "bcbdaac", "abcbdacefffffghijklmn", "abcbdaac", "abcbdaac", "abcbdaac", "ccccb"
        };
        for (int i = 0; i < inputStrings.length; i++) {
            String inputString = inputStrings[i];
            System.out.println("Iteration: " + i +" String: '" +inputString+ "' Length of longest substring without repeating characters == > " + getLongestSubstring(inputString));
        }
    }
}

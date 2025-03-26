package training.map;

import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharacters {

    public static int getLongetSubstring(String inputString) {
        int length = 0;
        HashMap<Character, Integer> storage = new HashMap<>();
        int pl = 0, pr = pl;
        while (pr < inputString.length() && pl < inputString.length()) {
            Integer prevIndex = storage.get(inputString.charAt(pr));
            if( prevIndex == null || prevIndex < pl) {
                storage.put(inputString.charAt(pr), pr);
                length = Math.max(length, (pr - pl + 1));
                pr++;
            } else {
                pl = prevIndex + 1;
            }
        }
        return length;
    }


    public static void main(String[] args) {
        String[] inputStrings = {
                "abcbdaac", "abcbdacefffffghijklmn", "abcbdaac", "abcbdaac", "abcbdaac"
        };
        for (int i = 0; i < inputStrings.length; i++) {
            String inputString = inputStrings[i];
            System.out.println("Iteration: " + i +" String: '" +inputString+ "' Length of longest substring without repeating characters == > " + getLongetSubstring(inputString));
        }
    }
}

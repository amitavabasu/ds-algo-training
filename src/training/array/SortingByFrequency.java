package training.array;

import java.util.*;

public class SortingByFrequency {
/*
    Hello! Your interview question is below. Write code in this pad just like you would normally – your AI Interviewer will be able to see it.

    # Sorting By Frequency

    Given a string, `word`, consisting of lowercase letters only, return a sorted array with all the letters in `word` sorted from most frequent to least frequent. If two frequencies are the same, break the tie alphabetically.

    Example 1: word = "supercalifragilisticexpialidocious"
    Output: ['i', 'a', 'c', 'l', 's', 'e', 'o', 'p', 'r', 'u', 'd', 'f', 'g', 't', 'x']

    Example 2: word = "aabbbcccc"
    Output: ['c', 'b', 'a']. 'c' appears 4 times, 'b' appears 3 times, and 'a' appears 2 times.

    Example 3: word = "abc"
    Output: ['a', 'b', 'c']. All letters appear once, so they are sorted alphabetically.

    Constraints:

    - The length of `word` is at most `10^5`
    - `word` contains only lowercase letters
*/
    public static char[] sortByFrequency(String word) {
        if (word == null || word.isEmpty()) return new char[]{};
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : word.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c,1);
            } else {
                map.put(c, (map.get(c)+1));
            }
        }
        Set<Map.Entry<Character, Integer>> mapSet = map.entrySet();
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(mapSet);
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>(){
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                if (o1.getValue().equals(o2.getValue())) {
                    return Character.compare(o1.getKey(), o2.getKey());
                } else {
                    return Integer.compare(o2.getValue(), o1.getValue());
                }
            }
        });
        char[] result = new char[list.size()];
        int i = 0;
        for (Map.Entry<Character, Integer> e : list) {
            result[i++] = e.getKey();
        }
        return result;
    }


    public static void main(String[] args) {
        String[] words = {
          "supercalifragilisticexpialidocious",
          "aabbbcccc",
          "abc"
        };

        for (String word : words) {
            System.out.println("String ==> " + word + "SortedByFrequency ==> " + Arrays.toString(sortByFrequency(word)));
        }
    }
}

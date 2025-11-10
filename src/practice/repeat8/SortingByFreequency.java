package practice.repeat8;

import java.util.*;

public class SortingByFreequency {

    public static char[] sortByFrequency(String word) {
        if (word == null || word.isEmpty()) return new char[]{};
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : word.toCharArray()) {
            if (map.get(c) == null) {
                map.put(c, 1);
            } else {
                map.put(c, map.get(c)+1);
            }
        }
        Set<Map.Entry<Character, Integer>> entrySet = map.entrySet();
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(entrySet);
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>(){
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                if (o1.getValue().equals(o2.getValue())) {
                    return Integer.compare(o1.getKey(), o2.getKey());
                } else {
                    return Integer.compare(o2.getValue(), o1.getValue());
                }
            }
        });
        char[] result = new char[list.size()];
        int i = 0;
        for (Map.Entry<Character, Integer> entry : list) {
            result[i++] = entry.getKey();
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

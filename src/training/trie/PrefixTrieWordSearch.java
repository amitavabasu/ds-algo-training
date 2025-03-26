package training.trie;

import java.util.HashMap;
import java.util.Map;

public class PrefixTrieWordSearch {
    public class TrieNode {
        char value;
        boolean end;
        Map<Character, TrieNode> map = new HashMap<>();

        public TrieNode(char c) {
            this.value = c;
        }
    }

    TrieNode top = new TrieNode(' ');
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        TrieNode current = top;
        for (char c : word.toCharArray()) {
            TrieNode next = current.map.get(c);
            if (next == null) {
                next = new TrieNode(c);
                current.map.put(c, next);
            }
            current = next;
        }
        current.end = true;
    }

    public boolean search(String word) {
        if (word == null || word.length() == 0) return false;
        TrieNode current = top;
        for (char c : word.toCharArray()) {
            TrieNode next = current.map.get(c);
            if (next == null) {
                return false;
            }
            current = next;
        }
        return (current.end);
    }

        public boolean startsWith(String word) {
            if (word == null || word.length() == 0) return false;
            TrieNode current = top;
            for (char c : word.toCharArray()) {
                TrieNode next = current.map.get(c);
                if (next == null) {
                    return false;
                }
                current = next;
            }
            return true;
    }


    public static void main(String[] args) {
        PrefixTrieWordSearch trie = new PrefixTrieWordSearch();
        trie.insert("apple");
        System.out.println("Search result ==> " + trie.search("dog"));
        trie.insert("dog");
        System.out.println("Search result ==> " + trie.search("dog"));
        System.out.println("StartsWith result ==> " + trie.startsWith("app"));
        System.out.println("Search result ==> " + trie.search("app"));
        trie.insert("app");
        System.out.println("Search result ==> " + trie.search("app"));
    }
}

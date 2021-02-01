import java.util.*;

public class MyTrieSet implements TrieSet61B{


    private class Node {
        char val;
        Boolean isKey;
        Map<Character, Node> next;

        public Node() {
            isKey = false;
        }
    }

    private Node root;

    public MyTrieSet() {
        root = new Node();
        root.isKey = false;
        root.next = new HashMap<>();
    }
    /** Clears all items out of Trie */
    public void clear() {
        root = new Node();
        root.isKey = false;
        root.next = new HashMap<>();
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    public boolean contains(String key) {
        if(key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        Node current = root;
        for(int i = 0; i < key.length(); i += 1) {
            if(current.next.containsKey(key.charAt(i))) {
                current = current.next.get(key.charAt(i));
            } else {
                return false;
            }
        }
        if(!current.isKey) {
            return false;
        }
        return true;
    }

    /** Inserts string KEY into Trie */
    public void add(String key) {
        Node current = root;
        for(int i = 0; i < key.length(); i += 1) {
            if(current.next == null) {
                Node n = new Node();
                n.val = key.charAt(i);
                if(i == key.length() - 1) {
                    n.isKey = true;
                }
                current.next = new HashMap<>();
                current.next.put(key.charAt(i), n);
                current = n; continue;
            }
            if(current.next.containsKey(key.charAt(i))) {
                current = current.next.get(key.charAt(i));
                if(i == key.length() - 1) {
                    current.isKey = true;
                }
            } else {
                Node n = new Node();
                n.val = key.charAt(i);
                if(i == key.length() - 1) {
                    n.isKey = true;
                }
                current.next.put(n.val, n);
                current = n;
            }
        }
    }

    /** Returns a list of all words that start with PREFIX */
    public List<String> keysWithPrefix(String prefix) {
        List<String> words = new ArrayList<>();
        Node current = root;
        for(int i = 0; i < prefix.length(); i += 1) {
            if(current.next == null) return null;
            if(current.next.containsKey(prefix.charAt(i))) {
                current = current.next.get(prefix.charAt(i));
            } else {
                return null;
            }
        }
        words = prefixHelper(words, prefix, current);

        return words;
    }

    private List<String> prefixHelper(List<String> words, String p, Node n) {
        if(n.next == null) return null;
        for(Map.Entry<Character, Node> e : n.next.entrySet()) {
            if(e.getValue().isKey) {
                words.add(p + e.getKey());
            }
            if(e.getValue().next != null) {
                prefixHelper(words, p + e.getKey(), e.getValue());
            }
        }
        return words;
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    };
}

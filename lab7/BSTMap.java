import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    public class Node {
        public Node left;
        public Node right;
        public K key;
        public V value;
        public Node(K k, V v) {
            key = k;
            value = v;
            left = null;
            right = null;
        }

    }

    private int size;
    private Node root;

    public BSTMap() {
        size = 0;
        root = null;
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        size = 0;
        root = null;

    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        Node current = root;
        while(current != null) {
            if(current.key.compareTo(key) == 0) return true;
            if(current.key.compareTo(key) > 0) current = current.left;
            else current = current.right;
        }
        return false;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        Node current = root;
        while(current != null) {
            if(current.key.compareTo(key) == 0) return current.value;
            if(current.key.compareTo(key) > 0) current = current.left;
            else current = current.right;
        }
        return null;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    };

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if(containsKey(key)) return;
        if(root == null) {
            root = new Node(key, value);
            size += 1;
            return;
        }
        Node current = root;
        while(current != null) {
            if(current.key.compareTo(key) > 0) {
                if(current.left == null) {
                    current.left = new Node(key, value);
                    size += 1;
                    return;
                } else current = current.left;
            }
            if(current.key.compareTo(key) < 0) {
                if(current.right == null) {
                    current.right = new Node(key, value);
                    size += 1;
                    return;
                } else current = current.right;
            }
        }
    }

    /* Returns a Set view of the keys contained in this map. */
    //Set is not a class, it is an interface.
    //You can only instantiate classes implementing Set (HashSet, LinkedHashSet orTreeSet)

    private void keySetHelper(Set<K> keySet, Node node) {
        if(node == null) {
            return;
        }
        keySet.add(node.key);
        if(node.left != null) keySetHelper(keySet, node.left);
        if(node.right != null) keySetHelper(keySet, node.right);

    }

    @Override
    public Set<K> keySet() {
        Set<K> returnSet = new HashSet<>();
        keySetHelper(returnSet, root);
        return returnSet;

    }


    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        // search if the key exists
        if(!keySet().contains(key)) return null;
        // if any of the two children is null
        Node current = root;
        Node delete = current;
        if()


    }

    public V removeHelper(Node P, Node C, K key) {

    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    };

    public Iterator<K> iterator() {
        return keySet().iterator();
    }

}

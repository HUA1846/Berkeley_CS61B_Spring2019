import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V>{
    private int size; //numbers of items
    private int initialSize = 16;
    private double loadFactor = 1.5;
    private int resize = 2;
    private Entry[] buckets = new Entry[initialSize];
    private HashSet<K> keys = new HashSet<>();

    private static class Entry extends ArrayList {
        public Object key;
        public Object val;
        public Entry next;

        public Entry(Object k, Object v, Entry n) {
            key = k;
            val = v;
            next = n;
        }
    }

    public MyHashMap() {
        buckets = new Entry[initialSize];
        size = 0;
    }

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        buckets = new Entry[initialSize];
        size = 0;
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        buckets = new Entry[initialSize];
        size = 0;
    }

    @Override
    public void clear() {
        size = 0;
        buckets = new Entry[initialSize];
    }

    public int hash(K key) {
      return Math.floorMod(key.hashCode(), initialSize);
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        int h = hash(key);
        if(buckets[h] == null) return false;
        Entry current = buckets[h];
        while(current != null) {
            if(current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }
        return false;
    };

    @Override
    public V get(K key) {
        int h = hash(key);
        if(buckets[h] == null) return null;
        for(Entry e = buckets[h]; e != null; e = e.next) {
            if(e.key.equals(key)) return (V) e.val;
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    @Override
    // putting with existing key updates the value
    public void put(K key, V value) {
        int h = hash(key);
        for(Entry e = buckets[h]; e != null; e = e.next) {
            if(key.equals(e.key)) {
                e.val = value;
                return;
            }
        }
        buckets[h] = new Entry(key, value, buckets[h]);
        size += 1;
        keys.add(key);
        if((double)size/initialSize > loadFactor) {
            resize();
        }

    }

    public void resize() {
        int updatedSize = initialSize * resize;
        MyHashMap<K, V> hm = new MyHashMap<>(updatedSize, loadFactor);
        hm.putAll(this);
        copyFrom(hm);

    }
    public void putAll(MyHashMap<K, V> old) {
        for(Entry e : old.buckets) {
            if(e == null) continue;
            while (e != null) {
                put((K)e.key, (V)e.val);
                e = e.next;
            }
        }
    }

    public void copyFrom(MyHashMap<K, V> newMap) {
        size = newMap.size;
        buckets = newMap.buckets;
        loadFactor = newMap.loadFactor;
        initialSize = newMap.initialSize;
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }


    @Override
    public V remove(K key){
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }
}

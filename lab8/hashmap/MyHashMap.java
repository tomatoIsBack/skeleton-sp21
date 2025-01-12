package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        set.clear();
        size = 0;
        buckets = createTable(DEFAULT_LENGTH);
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        int originHash = Math.floorMod(key.hashCode(), buckets.length);
        for (Node c : buckets[originHash]) {
            if (c.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        int originHash = Math.floorMod(key.hashCode(), buckets.length);
        for (Node c : buckets[originHash]) {
            if (c.key.equals(key)) {
                return c.value;
            }
        }
        return null;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        int originHash = Math.floorMod(key.hashCode(), buckets.length);
        for (Node c : buckets[originHash]) {
            if (c.key.equals(key)) {
                c.value = value;
                return;
            }
        }
        buckets[originHash].add(new Node(key, value));
        size++;
        set.add(key);
        if ((double) (size / buckets.length) > maxload) {
            resize();
        }
    }

    private void resize() {
        Collection<Node>[] newBuckets  = createTable(buckets.length * 2);
        for(Collection<Node> c : buckets) {
            for (Node n : c) {
                int newLocation = Math.floorMod(n.key.hashCode(), buckets.length * 2);
                newBuckets[newLocation].add(n);
            }
        }
    }

    /**
     * Returns a Set view of the keys contained in this map.
     */
    @Override
    public Set<K> keySet() {
        return set;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        int originHash = Math.floorMod(key.hashCode(), buckets.length);
        for (Node c : buckets[originHash]) {
            if (c.key.equals(key)) {
                V result = c.value;
                buckets[originHash].remove(c);
                set.remove(key);
                size--;
                return result;
            }
        }
        return null;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     *
     * @param key
     * @param value
     */
    @Override
    public V remove(K key, V value) {
        int originHash = Math.floorMod(key.hashCode(), buckets.length);
        for (Node c : buckets[originHash]) {
            if (c.key.equals(key) && c.value.equals(value)) {
                buckets[originHash].remove(c);
                set.remove(key);
                size--;
                return value;
            }
        }
        return null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return set.iterator();
    }
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }

    }

    /* Instance Variables */
    private static final int DEFAULT_LENGTH = 16;
    private static final double DEFAULT_MAXLOAD = 0.75;
    private Collection<Node>[] buckets;
    private double maxload;
    private HashSet<K> set;
    private int size;

    /** Constructors */
    public MyHashMap() {
        this(DEFAULT_LENGTH);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, DEFAULT_MAXLOAD);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.maxload = maxLoad;
        buckets = createTable(initialSize);
        this.set = new HashSet<>();
        size = 0;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = new Collection[tableSize];
        for(int i = 0; i < tableSize; i++) {
            table[i] = createBucket();
        }
        return table;
    }

    // TODO: Implement the methods of the Map61B Interface below

}

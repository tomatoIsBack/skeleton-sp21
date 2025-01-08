package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K,V>, Iterable<K>{
    private int size;
    private BSTNode root;

    public Iterator<K> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<K> {
        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        private Iterator<K> iter;
        BSTIterator() {
            iter = keySet().iterator();
        }

        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return iter.next();
        }
    }

    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        addKey(root, set);
        return set;
    }
    private void addKey(BSTNode node, Set<K> set) {
        if (node != null) {
            addKey(node.left, set);
            set.add(node.key);
            addKey(node.right, set);
        }
    }
    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left;
        private BSTNode right;

        BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }

        BSTNode get(K key) {
            BSTNode cur = this;
            while (cur != null && !cur.key.equals(key)) {
                if (key.compareTo(cur.key) > 0) {
                    cur = cur.right;
                } else if (key.compareTo(cur.key) < 0) {
                    cur = cur.left;
                }
            }
            return cur;
        }
    }

    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return root != null && root.get(key) != null;
    }

    @Override
    public V get(K key) {
        if(root == null || root.get(key) == null) {
            return null;
        }
        return root.get(key).value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (root != null) {
            BSTNode cur = root;
            while (!cur.key.equals(key)) {
                if (key.compareTo(cur.key) > 0) {
                    if (cur.right == null) {
                        cur.right = new BSTNode(key, value);
                        size++;
                        return;
                    } else {
                        cur = cur.right;
                    }
                } else if (key.compareTo(cur.key) < 0) {
                    if (cur.left == null) {
                        cur.left = new BSTNode(key, value);
                        size++;
                        return;
                    } else {
                        cur = cur.left;
                    }
                }
            }
            cur.value = value;
        } else {
            root = new BSTNode(key, value);
            size++;
        }
    }

    public void printInOrder(BSTNode node) {
        if (node != null){
            printInOrder(node.left);
            System.out.println(node.key + " " + node.value);
            printInOrder(node.right);
        }
    }
    public BSTNode getRoot() {
        return this.root;
    }

    @Override
    public V remove(K key) {
        if (root == null) {
            return null;
        }
        return null;
    }

    public BSTNode remove (BSTNode node, K key) {
        return null;
    }
}

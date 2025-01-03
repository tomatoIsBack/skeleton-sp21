package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private Node sentinel;
    private int size;

    private class Node {
        private T value;
        private Node prev;
        private Node next;

        private Node(T value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private class DequeIterator implements Iterator<T> {
        private Node cur;

        private DequeIterator() {
            this.cur = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return cur != sentinel;
        }

        @Override
        public T next() {
            T result = cur.value;
            cur = cur.next;
            return result;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DequeIterator();
    }

    public LinkedListDeque() {
        this.sentinel = new Node(null, null, null);
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
        this.size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node first = new Node(item, this.sentinel, this.sentinel.next);
        this.sentinel.next.prev = first;
        this.sentinel.next = first;
        this.size += 1;
    }

    @Override
    public void addLast(T item) {
        Node last = new Node(item, this.sentinel.prev, this.sentinel);
        this.sentinel.prev.next = last;
        this.sentinel.prev = last;
        this.size += 1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        for (T i : this) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (this.sentinel.next == this.sentinel) {
            return null;
        }
        Node result = this.sentinel.next;
        this.sentinel.next.next.prev = this.sentinel;
        this.sentinel.next = this.sentinel.next.next;
        size -= 1;
        return result.value;
    }

    @Override
    public T removeLast() {
        if (this.sentinel.prev == this.sentinel) {
            return null;
        }
        Node result = this.sentinel.prev;
        this.sentinel.prev.prev.next = this.sentinel;
        this.sentinel.prev = this.sentinel.prev.prev;
        size -= 1;
        return result.value;
    }

    @Override
    public T get(int index) {
        Node result = this.sentinel.next;
        while (result != this.sentinel) {
            if (index == 0) {
                return result.value;
            } else {
                result = result.next;
                index -= 1;
            }
        }
        return null;
    }

    public boolean equals(Object o) {
        /*
        if (o instanceof Deque other) {
            if (this.size() == other.size()) {
                for (int i = 0; i < this.size(); i++) {
                    if (this.get(i) != other.get(i)) {
                        return false;
                    }
                }
                return true;
            }
        }
         */
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque other = (LinkedListDeque) o;
        if (this.size() == other.size()) {
            for (int i = 0; i < this.size(); i++) {
                if (this.get(i) != other.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    public T getRecursive(int index) {
        return recursive(sentinel.next, index);
    }

    private T recursive(Node node, int index){
        if (node == sentinel){
            return null;
        } else if (index == 0){
            return node.value;
        } else {
            return recursive(node.next, index - 1);
        }
    }
}

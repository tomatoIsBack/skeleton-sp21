package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T>{
    private T[] items;
    private int size;
    private int start;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    private void expandArrays() {
        if (size == items.length) {
            T[] newitems = (T[]) new Object[items.length * 4];
            int newStart = Math.floorMod(start, items.length);
            if (newStart == 0) {
                System.arraycopy(items, 0, newitems, 0, items.length);
            } else {
                System.arraycopy(items, newStart, newitems, 0, items.length - newStart);
                System.arraycopy(items, 0, newitems, items.length - newStart, newStart);
            }
            start = 0;
            items = newitems;
        }
    }
    private void shrinkArrays() {
        if (size < items.length / 4) {
            T[] newitems = (T[]) new Object[items.length / 2];
            int newStart = Math.floorMod(start, items.length);
            if (newStart + size <= items.length) {
                System.arraycopy(items, newStart, newitems, 0, size);
            } else {
                System.arraycopy(items, newStart, newitems, 0, items.length - newStart);
                System.arraycopy(items, 0, newitems, items.length - newStart, newStart);
            }
            start = 0;
            items = newitems;
        }
    }

    @Override
    public void addFirst(T item) {
        expandArrays();
        start = Math.floorMod(start - 1, items.length);
        items[start] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        expandArrays();
        items[Math.floorMod(start + size, items.length)] = item;
        size++;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (T i : this) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private class DequeIterator implements Iterator<T> {
        private int count;
        public DequeIterator() {
            count = size;
        }
        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public T next() {
            count--;
            return items[Math.floorMod(start + size - count - 1, items.length)];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDeque.DequeIterator();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = items[start];
        start = Math.floorMod(start + 1, items.length);
        size--;
        shrinkArrays();
        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = items[Math.floorMod(start + size - 1, items.length)];
        size--;
        shrinkArrays();
        return item;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[Math.floorMod(start + index, items.length)];
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
        Deque other = (ArrayDeque) o;
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
}

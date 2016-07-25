package neshdev.collections;

import java.util.Iterator;

/**
 * Created by admin on 7/23/2016.
 */
public class ResizingQueue<T> implements Iterable<T> {

    public static void main(String[] args) {

    }


    private T[] items;
    private int head;
    private int tail;
    private int N;

    public ResizingQueue() {
        this(1);
    }

    public ResizingQueue(int capacity) {
        items = (T[]) new Object[capacity];
        N = 0;
        head = 0;
        tail = 0;
    }

    public void enqueue(T item) {
        if (N == items.length) resize(2 * items.length);
        items[tail++] = item;
        if (tail == items.length) tail = 0;
        N++;
    }

    public T dequeue() {
        T item = items[head];
        items[head] = null;
        N--;
        head++;
        if (head == items.length) head = 0;
        if (N > 0 && N == items.length / 4) resize(items.length / 4);
        return item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            newItems[i] = items[(head + i) % items.length];
        }
        items = newItems;
        head = 0;
        tail = N;
    }


    @Override
    public Iterator<T> iterator() {
        return new DefaultIterator(items, tail);
    }

    private class DefaultIterator implements Iterator<T> {

        private T[] _items;
        private int _tail;
        private int p;

        public DefaultIterator(T[] items, int tail){
            _items = items;
            _tail = tail;
            this.p = head;
        }

        @Override
        public T next() {
            return _items[p++];
        }

        @Override
        public boolean hasNext() {
            return  p < tail;
        }
    }
}

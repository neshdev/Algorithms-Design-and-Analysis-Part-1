package neshdev.collections;

import java.util.Iterator;

/**
 * Created by admin on 7/23/2016.
 */
public class ResizingStack<T> implements Iterable<T> {

    public static void main(String[] args){
        ResizingStack<Integer> rs = new ResizingStack<>();
        rs.push(1);
        rs.push(2);
        rs.push(3);
        rs.push(4);
        System.out.println("Iterate 1");
        for (Integer i: rs) {
            System.out.println(i);
        }

        System.out.println("size: " + rs.size());
        System.out.println("empty: " + rs.isEmpty());
        System.out.println("pop 4: " + rs.pop());

        System.out.println("pop 3: " + rs.pop());
        System.out.println("pop 2: " + rs.pop());
        System.out.println("pop 1: " + rs.pop());
        System.out.println("size: " + rs.size());
        System.out.println("empty: " + rs.isEmpty());

        System.out.println("Iterate 2");
        for (Integer i: rs) {
            System.out.println(i);
        }

        System.out.println("Exiting...");
    }


    private T[] items;
    private int N;

    public ResizingStack() {
        this(1);
    }

    public ResizingStack(int capacity) {
        items = (T[]) new Object[capacity];
        N = 0;
    }

    public void push(T item) {
        if ( N == items.length ) resize(2 * items.length);
        items[N++] = item;
    }

    public T pop() {
        T item = items[--N];
        items[N] = null;
        if ( N > 0 && N == items.length / 4) resize(items.length / 2);
        return item;
    }


    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int capactiy) {
        T[] newItems = (T[]) new Object[capactiy];
        for (int i = 0; i < N; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }


    @Override
    public Iterator<T> iterator() {
        return new DefaultIterator(items, N);
    }

    public class DefaultIterator implements Iterator<T> {

        private T[] _items;
        private int _p;

        public DefaultIterator(T[] items, int p) {
            _items = (T[]) new Object[items.length];
            for (int i = 0; i < items.length; i++) {
                _items[i] = items[i];
            }
            _p = p;
        }

        @Override
        public boolean hasNext() {
            return  _p > 0;
        }

        @Override
        public T next() {
            T item = _items[--_p];
            _items[_p] = null;
            return item;
        }
    }
}

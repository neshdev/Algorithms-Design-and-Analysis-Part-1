package week3;

import java.util.Iterator;

/**
 * Created by admin on 7/23/2016.
 */
public class Queue<T> implements Iterable<T> {

    private class Node {
        T item;
        Node next;
    }

    private Node first;
    private Node last;
    private int N;

    public void enqueue(T item) {
        Node oldNode = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldNode.next = last;
        N++;
    }

    public T dequeue() {
        T item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null;
        return  item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public Iterator<T> iterator() {
        return new DefaultIterator(first);
    }

    private class DefaultIterator implements Iterator<T> {

        private Node _n;

        public DefaultIterator(Node n) {
            _n = n;
        }

        @Override
        public boolean hasNext() {
            return  _n != null;
        }

        @Override
        public T next() {
            T item = _n.item;
            _n = _n.next;
            return item;
        }
    }
}

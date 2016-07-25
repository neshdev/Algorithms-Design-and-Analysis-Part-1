package neshdev.collections;

import java.util.Iterator;

/**
 * Created by admin on 7/23/2016.
 */
public class Stack<T> implements  Iterable<T> {

    private Node first;
    private int N = 0;

    private class Node {
        T item;
        Node next;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void push(T item){
        Node newItem = new Node();
        newItem.item = item;
        newItem.next = first;
        first = newItem;
        N++;
    }

    public T pop(){
        if (first == null) throw new IllegalStateException("No items to dequeue");
        T item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public Iterator<T> iterator() {
        return new DefaultIterator(first);
    }

    private class DefaultIterator implements Iterator<T> {

        private  Node _n;

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

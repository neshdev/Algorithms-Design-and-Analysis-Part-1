package neshdev.collections;

import java.util.Iterator;

/**
 * Created by admin on 7/23/2016.
 */
public class Bag<T> implements  Iterable<T>{

    private class  Node {
        T item;
        Node next;
    }

    private Node root;

    public void insert(T item){
        Node n = new Node();
        n.item = item;
        n.next = root;
        root = n;
    }

    @Override
    public Iterator<T> iterator() {
        return new DefaultIterator(root);
    }

    private  class  DefaultIterator implements  Iterator<T>{

        private Node n;

        public DefaultIterator(Node n){
            this.n = n;
        }

        @Override
        public T next() {
            T item = n.item;
            n = n.next;
            return item;
        }

        @Override
        public boolean hasNext() {
            return n != null;
        }
    }
}

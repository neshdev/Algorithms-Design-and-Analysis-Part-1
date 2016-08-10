package week4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by admin on 8/6/2016.
 */
public class Bag<T> implements Iterable<T> {

    public static void main(String[] args){
        Bag<Integer> b = new Bag<>();
        b.add(10);
        b.add(5);
        b.add(20);
        b.add(1);

        for (Integer i: b ) {
            System.out.println(i);
        }
        System.out.println("Exiting...");
    }

    private class Node {
        Node next;
        T item;
    }

    private Node first;

    public void add(T item){
        Node oldNode = first;
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = oldNode;
        first = newNode;
    }

    @Override
    public Iterator<T> iterator() {
        return new DefaultEnumerator();
    }

    private class DefaultEnumerator implements Iterator<T>{

        private Node root = first;

        public DefaultEnumerator(){ }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException("does not have next element");
            T item = root.item;
            root = root.next;
            return item;
        }

        @Override
        public boolean hasNext() {
            return root != null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb  = new StringBuilder();
        for (T item: this ) {
            sb.append(item + " ");
        }
        return sb.toString();
    }
}

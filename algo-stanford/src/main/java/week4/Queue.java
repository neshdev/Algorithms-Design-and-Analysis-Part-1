package week4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by admin on 8/6/2016.
 */
public class Queue<T> implements Iterable<T> {

    public static void main(String[] args){
        Queue<Integer> q = new Queue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(10);
        q.enqueue(22);
        for (Integer i: q ) {
            System.out.println(i);
        }
        System.out.println("Dequeue:" + q.dequeue());
        System.out.println("Dequeue:" + q.dequeue());
        System.out.println("Dequeue:" + q.dequeue());
        System.out.println("Dequeue:" + q.dequeue());
        for (Integer i: q ) {
            System.out.println(i);
        }

        System.out.println("Exiting...");
    }

    private class Node {
        T item;
        Node next;
    }

    private Node head;
    private Node tail;
    private int n;

    public Queue(){
        head = null;
        tail = null;
        n = 0;
    }

    public void enqueue(T item){
        Node oldNode = tail;
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;
        tail = newNode;
        if ( head == null ) head = tail;
        else oldNode.next = tail;
        n++;
    }

    public T dequeue(){
        T item = head.item;
        head = head.next;
        n--;
        if (isEmpty()) tail=head;
        return item;
    }

    public int size(){
        return n;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new DefaultIterator();
    }

    private class DefaultIterator implements Iterator<T> {

        private Node root = head;

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T item  = root.item;
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

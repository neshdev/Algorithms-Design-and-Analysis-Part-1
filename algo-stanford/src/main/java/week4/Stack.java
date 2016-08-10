package week4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by admin on 8/6/2016.
 */
public class Stack<Item> implements Iterable<Item> {

    public static void main(String[] args){
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);

        for (Integer i:s ) {
            System.out.println(i);
        }
        System.out.println("pop: " +  s.pop());
        System.out.println("pop: " +  s.pop());
        System.out.println("pop: " +  s.pop());
        System.out.println("pop: " +  s.pop());
        System.out.println("Exiting...");
    }

    private class Node{
        Item item;
        Node next;
    }

    private Node head;
    private int n;

    public Item peek(){
        if ( head == null) throw new NoSuchElementException("Stack is empty");
        return head.item;
    }

    public Item pop(){
        if ( head == null) throw new NoSuchElementException("Stack is empty");
        Item item = head.item;
        head = head.next;
        n--;
        return item;
    }

    public void  push(Item item){
        if ( item == null) throw new NullPointerException("first argument to push is null");
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = head;
        head = newNode;
        n++;
    }

    public int size(){
        return n;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DefaultIterator();
    }

    public class DefaultIterator implements Iterator<Item>{

        private Node current = head;

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb  = new StringBuilder();
        for (Item item: this ) {
            sb.append(item + " ");
        }
        return sb.toString();
    }
}

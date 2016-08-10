package week3;

/**
 * Created by admin on 7/28/2016.
 */
public class SeperateChainingKeyValue<Key, Value>{

    public SeperateChainingKeyValue(){
        first= null;
        N=0;
    }

    private class Node<Key, Value> {
        private Node<Key, Value> next;
        private Key key;
        private Value value;
    }

    private int N;
    private Node<Key,Value> first;

    public Value get(Key key){
        if (key == null) throw new NullPointerException("argument to get() is null");
        Node<Key,Value> n = get(first, key);
        return n == null ? null : n.value;
    }

    public boolean contains(Key key){
        if (key == null) throw new NullPointerException("argument to contains() is null");
        Value v = get(key);
        return v != null;
    }

    private Node<Key,Value> get(Node<Key,Value> n, Key search){
        if (n == null) return null;
        else if ( n.key.equals(search)) return n;
        else return get(n.next, search);
    }

    public void put(Key key, Value value){
        if (key == null) throw new NullPointerException("first argument to put() is null");
        if (value == null) {
            //remote just the value
            delete(key);
            return;
        }

        //no value, so insert a value
        //verify if this value is just left over?
        for (Node<Key,Value> i = first; i != null ; i = i.next) {
            if (i.key.equals(key)){
                i.value = value;
                return;
            }
        }

        Node newNode = new Node();
        newNode.key = key;
        newNode.value = value;
        newNode.next = first;
        first = newNode;
        N++;
    }

    public void delete(Key key){
        if (key == null) throw new NullPointerException("argument to delete() is null");
        first = delete(first, key);
    }

    private Node<Key,Value> delete(Node<Key, Value> n, Key search){
        if ( n == null) return null;
        if ( n.key.equals(search)) {
            N--;
            return n.next;
        }
        n.next = delete(n.next, search);
        return n;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public Iterable<Key> keys(){
        Queue<Key> q = new Queue<Key>();
        for (Node<Key,Value> n = first; n != null; n = n.next) {
            q.enqueue(n.key);
        }
        return q;
    }
}
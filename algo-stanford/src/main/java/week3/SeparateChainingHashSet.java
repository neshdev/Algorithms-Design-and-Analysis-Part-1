package week3;

/**
 * Created by admin on 7/24/2016.
 */
public class SeparateChainingHashSet<Key, Value>{


    public static void main(String[] args) {
        SeparateChainingHashSet<String, String> set = new SeparateChainingHashSet<>();
        System.out.print("IsEmpty:" + set.isEmpty());
        set.put("a", "1");
        set.put("b", "2");
        set.put("c", "3");
        set.put("d", "2");
        set.put("d", "14");
        System.out.println("IsEmpty:" + set.isEmpty());
        System.out.println("size:" + set.size());
        for (String k : set.keys()) {
            System.out.println(k + "," + set.get(k));
        }
        set.remove("a");
        System.out.println("IsEmpty:" + set.isEmpty());
        System.out.println("size:" + set.size());
        for (String k : set.keys()) {
            System.out.println(k + "," + set.get(k));
        }
        set.put("1", "1");
        set.put("2", "2");
        set.put("3", "3");
        set.put("4", "2");
        set.put("5", "14");
        set.put("6", "14");
        System.out.println("IsEmpty:" + set.isEmpty());
        System.out.println("size:" + set.size());
        for (String k : set.keys()) {
            System.out.println(k + "," + set.get(k));
        }
        System.out.println("get2:" + set.get("2"));
    }

    private static int INITIAL_CAPACITY = 4;
    private SeperateChainingKeyValue<Key, Value>[] st;
    private int N;
    private int M;

    public SeparateChainingHashSet() {
        this(INITIAL_CAPACITY);
    }

    public SeparateChainingHashSet(int capacity) {
        M = capacity;
        st = (SeperateChainingKeyValue<Key, Value>[]) new SeperateChainingKeyValue[capacity];
        for (int i = 0; i < capacity; i++) {
            st[i] = new SeperateChainingKeyValue<>();
        }
    }

    private void resize(int chains) {
        SeparateChainingHashSet<Key, Value> temp = new SeparateChainingHashSet<>(chains);
        for (int i = 0; i < M; i++) {
            for (Key k: st[i].keys()) {
                temp.put(k, st[i].get(k));
            }
        }
        st = temp.st;
        N = temp.N;
        M = temp.M;
    }

    private int hash(Key k) {
        int i = (k.hashCode() & 0x7fffffff) % M;
        return i;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public Value get(Key key){
        if (key == null) throw new NullPointerException("first argument to get() is null");
        int idx = hash(key);
        return st[idx].get(key);
    }

    public void put(Key key, Value value) {
        if (key == null) throw new NullPointerException("first argument to put() is null");
        if (N > 10*M) resize(M * 2);
        int idx = hash(key);
        if ( !st[idx].contains(key)) N++;
        st[idx].put(key,value);
    }

    public void remove(Key key) {
        if (key == null) throw new NullPointerException("first argument to remove() is null");
        int idx = hash(key);
        if (st[idx].contains(key)) N--;
        st[idx].delete(key);

        if ( M > INITIAL_CAPACITY && N <= 2*M) resize(M/2);
    }

    public boolean contains(Key key) {
        int idx = hash(key);
        return st[idx].contains(key);
    }

    public Iterable<Key> keys(){
        Queue<Key> q = new Queue<>();
        for (int i = 0; i < M; i++) {
            for (Key k: st[i].keys()) {
                q.enqueue(k);
            }
        }
        return  q;
    }
}


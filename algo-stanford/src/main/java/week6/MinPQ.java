package week6;

/**
 * Created by admin on 10/9/2016.
 */
public class MinPQ<Key extends Comparable<Key>> {

    public static void main(String[] args) {
        MinPQ<Integer> pq = new MinPQ<>();
        pq.put(8);
        System.out.println(pq);
        pq.put(7);
        System.out.println(pq);
        pq.put(6);
        System.out.println(pq);
        pq.put(5);
        System.out.println(pq);
        pq.put(4);
        System.out.println(pq);
        pq.put(3);
        System.out.println(pq);
        pq.put(2);
        System.out.println(pq);
        pq.put(1);
        System.out.println(pq);
        pq.put(1);
        System.out.println(pq);

        System.out.println(pq.deleteMin() + " , " + pq);
        System.out.println(pq.deleteMin() + " , " + pq);
        System.out.println(pq.deleteMin() + " , " + pq);
        System.out.println(pq.deleteMin() + " , " + pq);
        System.out.println(pq.deleteMin() + " , " + pq);
        System.out.println(pq.deleteMin() + " , " + pq);
        System.out.println(pq.deleteMin() + " , " + pq);
        System.out.println(pq.deleteMin() + " , " + pq);
        System.out.println(pq.deleteMin() + " , " + pq);


        System.out.println("Exiting...");
    }

    private static int DEFAULT_INITIAL_CAPACITY = 2;

    public MinPQ() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public MinPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
        n = 0;
    }

    private Key[] pq;
    private int n;

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Key getMin() {
        if (isEmpty()) throw new IndexOutOfBoundsException("getmin.empty");
        return pq[1];
    }

    public void put(Key item) {
        if (item == null) throw new NullPointerException("item.null");
        if (n == (pq.length - 1)) resize(2 * pq.length);
        n++;
        pq[n] = item;
        swim(n);
    }

    public Key deleteMin() {
        if (isEmpty()) throw new IndexOutOfBoundsException("getmin.empty");
        Key min = pq[1];
        exch(1,n);
        pq[n--] = null;
        sink(1);
        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
        return min;
    }

    private void sink(int k) {
        while (2*k <= n){
            int j = 2*k;
            if ( j < n && greater(j,j+1)) j++;
            if (!greater(k,j)) break;
            exch(k,j);
            k = j;
        }
    }

    private void swim(int k) {
        while (k > 1 && greater(k/2,k)) {
            exch(k,k/2);
            k = k / 2;
        }
    }

    private boolean greater(int v, int w) {
        return pq[v].compareTo(pq[w]) > 0;
    }

    private void exch(int v, int w) {
        Key swap = pq[v];
        pq[v] = pq[w];
        pq[w] = swap;
    }

    private void resize(int capacity) {
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 0; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
//        sb.append("Size: " + size() + "\n");
        if (!isEmpty()) sb.append("Min:" + getMin() + " | ");
        for (int i = 1; i <= n; i++) {
            sb.append(pq[i] + " ");
        }
//        sb.append("\n");
        return sb.toString();
    }
}

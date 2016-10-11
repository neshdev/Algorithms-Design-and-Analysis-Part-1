package week6;

/**
 * Created by admin on 10/9/2016.
 */
public class MaxPQ<Key extends Comparable<Key>> {


    public static void main(String[] args) {
        MaxPQ<Integer> pq = new MaxPQ<Integer>();
        pq.put(1);
        System.out.println(pq);
        pq.put(2);
        System.out.println(pq);
        pq.put(3);
        System.out.println(pq);
        pq.put(4);
        System.out.println(pq);
        pq.put(8);
        System.out.println(pq);
        pq.put(7);
        System.out.println(pq);


        System.out.println(pq.deleteMax() + " , " + pq);
        System.out.println(pq.deleteMax() + " , " + pq);
        System.out.println(pq.deleteMax() + " , " + pq);
        System.out.println(pq.deleteMax() + " , " + pq);
        System.out.println(pq.deleteMax() + " , " + pq);
        System.out.println(pq.deleteMax() + " , " + pq);

        System.out.println("Exiting...");
    }

    private static int DEFAULT_INITIAL_CAPACITY = 2;

    private Key[] pq;
    int n;

    public MaxPQ() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public MaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
        this.n = 0;
    }

    public Key getMax() {
        if (isEmpty()) throw new IndexOutOfBoundsException("delete.max.isempty");
        return pq[1];
    }

    public Key deleteMax() {
        if (isEmpty()) throw new IndexOutOfBoundsException("delete.max.isempty");
        Key max = pq[1];
        pq[1] = pq[n];
        pq[n] = null;
        n--;
        sink(1);
        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
        return max;
    }

    public void put(Key item) {
        if (item == null) throw new NullPointerException("item.null");
        n++;
        if (n == (pq.length - 1)) resize(pq.length * 2);
        pq[n] = item;
        swim(n);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return n;
    }

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private void resize(int capacity) {
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 0; i <= n; i++) {
            temp[i] = pq[i];
        }
        this.pq = temp;
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
//        sb.append("Size: " + size() + "\n");
        if (!isEmpty()) sb.append("Max: " + getMax() + " | ");
        for (int i = 1; i <= n; i++) {
            sb.append(pq[i] + " ");
        }
//        sb.append("\n");
        return sb.toString();
    }
}

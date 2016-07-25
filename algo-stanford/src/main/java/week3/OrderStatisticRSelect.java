package week3;

import java.util.Random;

/**
 *Randomized Select for the ith Order Statistic in O(n) time.
 */
public class OrderStatisticRSelect {

    public static void main(String[] args) {
        Integer[] a = {9,3,2,8,5,4,1,6,7};
        int iThOrder = a.length - 1;
        Integer val = RSelect(a, iThOrder);
        System.out.println(iThOrder + "th Order : " + val);
        System.out.println("n : " + a.length);
        System.out.println("couter : " + couter);
    }

    public static <T extends Comparable> T RSelect(T[] a, int i) {
        shuffle(a);
        return RSelect(a, 0, a.length - 1, i);
    }

    static int couter = 0;

    private static <T extends Comparable> T RSelect(T[] a, int lo, int hi, int i) {
        couter++;
        if (hi <= lo) return a[i];
        int j = partition(a, lo, hi);
        if ( j == i) return a[j];
        else if (j > i) return RSelect(a, lo, j-1, i);
        else return RSelect(a, j+1, hi, i);
    }

    private static <T extends Comparable> int partition(T[] a, int lo, int hi) {
        int cp = choosePivot(a, lo, hi);
        exch(a, lo, cp);
        T p = a[lo];
        int i = lo + 1;
        for (int j = lo + 1; j <= hi; j++) {
            if (greater(p, a[j])) {
                exch(a, j, i);
                i++;
            }
        }
        exch(a, lo, i - 1);
        return i - 1;
    }

    /**
     * Need to choose a better pivot
     * @param a
     * @param lo
     * @param hi
     * @return the lowest pivot element
     */
    private static int choosePivot(Comparable[] a, int lo, int hi) {
        return lo;
    }

    private static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    private static void shuffle(Comparable[] a) {
        Random r = new Random();
        for (int i = 0; i < a.length; i++) {
            int rand = r.nextInt(a.length);
            exch(a, i, rand);
        }
    }

    private static void exch(Comparable[] a, int v, int w) {
        Comparable swap = a[v];
        a[v] = a[w];
        a[w] = swap;
    }

}

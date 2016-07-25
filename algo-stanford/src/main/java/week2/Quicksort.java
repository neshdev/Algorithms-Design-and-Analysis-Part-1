package week2;

import java.util.Random;

/**
 * Created by admin on 7/24/2016.
 */
public class Quicksort {

    public static void main(String[] args){

        Integer[] a = new Integer[new Random().nextInt(100)];
        for (int i = 0; i < a.length; i++) {
            a[i] = i + 1;
        }
        shuffle(a);

        System.out.println("initial...");
        for (Integer i: a) {
            System.out.println(i + "\t");
        }

        Quicksort.sort(a);

        System.out.println("Sorted...");
        for (Integer i: a) {
            System.out.println(i + "\t");
        }
        System.out.println("Exiting...");
    }


    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int p = partition(a, lo, hi);
        sort(a, lo, p - 1);
        sort(a, p + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int cp = choosePivot(a, lo, hi);
        exch(a, lo, cp);
        Comparable p = a[lo];
        int i = lo + 1;
        for (int j = lo + 1; j <= hi; j++) {
            if (greater(p, a[j])) {
                exch(a, j, i);
                i++;
            }
        }
        exch(a, lo, i - 1);
        return  i - 1;
    }

    private static int choosePivot(Comparable[] a, int lo, int hi) {
        return lo;
    }

    private static int chooseLo(Comparable[] a, int lo, int hi) {
        return lo;
    }
    private static int chooseHi(Comparable[] a, int lo, int hi) {
        return hi;
    }
    private static int chooseMed(Comparable[] a, int lo, int hi) {
        int med = lo + (hi - lo) / 2;

        Comparable val1 = a[lo];
        Comparable val2 = a[med];
        Comparable val3 = a[hi];

        //todo: maybe use insertion sort to find vs doing nested compares, need to finish implementation

        return lo;
    }

    private static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int v, int w) {
        Comparable swap = a[v];
        a[v] = a[w];
        a[w] = swap;
    }

    private static void shuffle(Comparable[] a){
        Random rand = new Random();
        for (int i = 0; i < a.length; i++) {
            int r = rand.nextInt(a.length);
            exch(a, i, r);
        }
    }
}

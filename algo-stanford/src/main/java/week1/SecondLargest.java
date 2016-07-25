package week1;

import java.io.Console;
import java.util.Random;

/**
 * Created by admin on 7/6/2016.
 */
public class SecondLargest {
    public static void main(String[] args){
        Integer[] stats = new Integer[8];
        for (int i = 0; i < stats.length; i++) {
            stats[i] = 0;
        }
        for (int i = 0; i < 1; i++) {
            int sl = getSecondLargestNumber();
            stats[sl] = stats[sl]+1;
        }
        print(stats);
        System.out.println("Exiting...");
        System.out.println("n:" + n);
    }

    public static int getSecondLargestNumber(){
        int length = 8;
        Integer[] a = new Integer[length];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        shuffle(a);
        int result = secondLargest(a);
        return result;
    }

    public static void shuffle(Integer[] a){
        Random rand = new Random();
        for (int i = 0; i < a.length; i++) {
            int r = rand.nextInt(8);
            exch(a, i, r);
        }
    }

    public static void exch(Integer[] a, int v, int w){
        Integer exch = a[v];
        a[v] = a[w];
        a[w] = exch;
    }

    public static void print(Integer[] a){
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + "\t");
        }
        System.out.println();
    }

    public static void print(Comparable[] a){
        for (int i = 0; i < a.length; i++) {
            System.out.print((Integer)a[i] + "\t");
        }
        System.out.println();
    }

    public static int secondLargest(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];
        secondLargest(a, aux, 0, a.length-1);
        return (Integer)aux[1];
    }

    public static void secondLargest(Comparable[] a, Comparable[] aux, int lo, int hi){
        if ( hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        secondLargest(aux, a, lo, mid);
        secondLargest(aux, a, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    ///Number of comparisons
    static int n = 0;

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= lo + 1; k++) {
            if ( i > mid)                       { aux[k] = a[j++]; n++; }
            else if (j > hi)                    { aux[k] = a[i++]; n++; }
            else if (greater(a[j], a[i]))       {   aux[k] = a[j++]; n++; }
            else                                { aux[k] = a[i++]; n++; }
        }
    }

    public static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) >= 0;
    }



}

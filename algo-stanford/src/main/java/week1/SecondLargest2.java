package week1;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by admin on 7/7/2016.
 */
public class SecondLargest2 {
    public static void main(String[] args){
        Integer[] stats = new Integer[8];
        for (int i = 0; i < stats.length; i++) {
            stats[i] = 0;
        }
        for (int i = 0; i < 20; i++) {
            int sl = getSecondLargestNumber();
            //System.out.println(sl);
            stats[sl] = stats[sl]+1;
        }
        print(stats);
        System.out.println("Exiting...");
    }

    public static int getSecondLargestNumber(){
        int length = 8;
        Integer[] a = new Integer[length];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        shuffle(a);
        Integer[] orig = Arrays.copyOf(a, a.length);
        int result = secondLargest(a, orig);
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

    public static void exch(Comparable[] a, int v, int w){
        Comparable exch = a[v];
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

    public static Integer secondLargest(Comparable[] a, Comparable[] orig){
        int quadrant = firstLargest(a, 0, a.length-1);
        Integer firstLargest = (Integer)a[0];
        int hi = quadrant + a.length/2;
        int lo = quadrant;
        int start = lo + (hi - lo) / 2;
        int increment = (hi - lo) / 2;
        int currentMaxIndex =  a.length/2;
        for (int i = start; i < hi; i = i + increment / 2) {
            System.out.println(i);
            if ( a[i].compareTo(a[currentMaxIndex]) > 0 ) currentMaxIndex = i;
        }
        System.out.println();

        Integer secondLargest = (Integer)a[currentMaxIndex];
        assert( secondLargest == 6);

        return secondLargest;
    }


    public static int firstLargest(Comparable[] a, int lo, int hi){
        if ( hi <= lo) return 0;
        int mid = lo + (hi - lo) / 2;
        firstLargest(a, lo, mid);
        firstLargest(a, mid+1, hi);
        return merge(a, lo, mid, hi);
    }

    public static int merge(Comparable[] a, int lo, int mid, int hi){
        if (greater(a[mid+1], a[lo])){
            exch(a, mid+1, lo);
            return mid+1;
        }
        return lo;
    }

    public static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) >= 0;
    }
}

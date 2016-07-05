package week1;

import java.util.Arrays;

/**
 * Created by admin on 7/5/2016.
 */
public class Inversions {
    public static int sortAndCount(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        return sortAndCount(a, aux, 0, a.length - 1);
    }

    private static int sortAndCount(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return 0;
        int mid = lo + (hi - lo) / 2;
        int x = sortAndCount(a, aux, lo, mid);
        int y = sortAndCount(a, aux, mid + 1, hi);
        int z = mergeAncCount(a, aux, lo, mid, hi);
        return x + y + z;
    }

    private static int mergeAncCount(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int count = 0;

        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];


        for (int k = lo; k <= hi; k++) {
            if (i > mid)        a[k] = aux[j++];
            else if (j > hi)    a[k] = aux[i++];
            else if ( less(aux[j], aux[i]) ) {
                                a[k] = aux[j++];
                count = count + (mid+1 - i);
            }
            else                a[k] = aux[i++];
        }
        return count;
    }

    private static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }
}

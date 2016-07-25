package week1;

import java.util.Arrays;

/**
 Description: Inversions with trace output
 */
public class Inversions2 {
    public static void main(String[] args) {
        Integer[] a = {6,5,4,3,2,1};

        prettyPrintDivider();
        prettyPrint(a);
        prettyPrintDivider();

        int inversionCount = sortAndCount(a);

        prettyPrintDivider();
        prettyPrint(a);
        prettyPrintDivider();

        System.out.println("Inversions: " + inversionCount);
    }


    private static void prettyPrint(Integer[] a){
        for (int i = 0; i < a.length; i++) {
            System.out.format("%-6s", a[i]);
        }
        System.out.println();
    }

    private static void prettyPrint(Comparable[] x, int m1, int m2, char x1, char x2){
        Integer[] a = (Integer[])x;
        for (int i = 0; i < a.length; i++) {
            if ( i == m1 ) {
                System.out.format("%-6s", x1);
            }
            else if ( i == m2) {
                System.out.format("%-6s", x2);
            }
            else {
                System.out.format("%-6s", "");
            }

        }
        System.out.println();
    }

    private static void prettyPrint(Comparable[] x, int m1, int m2, int m3, char x1, char x2, char x3){
        Integer[] a = (Integer[])x;
        for (int i = 0; i < a.length; i++) {
            if ( i == m1 ) {
                System.out.format("%-6s", x1);
            }
            else if ( i == m2) {
                System.out.format("%-6s", x2);
            }
            else if ( i == m3) {
                System.out.format("%-6s", x3);
            }
            else {
                System.out.format("%-6s", "");
            }

        }
        System.out.println();
    }

    private static void prettyPrint(Comparable[] x){
        Integer[] a = (Integer[])x;
        for (int i = 0; i < a.length; i++) {
            System.out.format("%-6s", a[i]);
        }
        System.out.println();
    }

    private static void prettyPrintDivider(){
        System.out.println("*************************");
    }

    private static int sortAndCount(Comparable[] a) {
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

        System.out.println("mergeAncCount");
        Comparable[] orig = Arrays.copyOf(a, a.length);
        prettyPrint(orig);
        prettyPrint(orig, lo, mid, hi, 'l', 'm', 'h' );
        prettyPrint(orig, i, j, 'i', 'j' );


        for (int k = lo; k <= hi; k++) {
            prettyPrint(a);
            prettyPrint(a, i, j, 'i', 'j' );

            if (i > mid)        a[k] = aux[j++];
            else if (j > hi)    a[k] = aux[i++];
            else if ( less(aux[j], aux[i]) ) {
                a[k] = aux[j++];
                count = count + (mid+1 - i);
            }
            else                a[k] = aux[i++];


        }
        prettyPrint(a);
        prettyPrint(a, i, j, 'i', 'j' );

        return count;
    }

    private static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

}

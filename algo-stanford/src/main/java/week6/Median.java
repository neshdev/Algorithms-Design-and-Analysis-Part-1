package week6;

import week4.Queue;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by admin on 10/9/2016.
 */
public class Median {

    public Median(){
        n = 0;
        heapHigh = new MinPQ<>();
        heapLow = new MaxPQ<>();
    }

    public Double getMedian() {
        return heapLow.getMax();
    }

    public int size() {
        return heapLow.size() + heapHigh.size();
    }

    public boolean isEmpty() {
        return heapLow.isEmpty() && heapHigh.isEmpty();
    }

    public void put(Double item) {
        n++;
        if (( n % 2) == 1) heapLow.put(item);
        else if (( n % 2) == 0) heapHigh.put(item);
        if ( (!heapHigh.isEmpty()) &&  heapLow.getMax() > heapHigh.getMin()){
            Double max = heapLow.deleteMax();
            Double min = heapHigh.deleteMin();
            heapLow.put(min);
            heapHigh.put(max);
        }
    }
    private int n;
    private MaxPQ<Double> heapLow;
    private MinPQ<Double> heapHigh;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(heapLow.size() + "," + heapHigh.size() + "  ");
        sb.append(heapLow + "   ||  " + heapHigh);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Exiting...");
    }

}

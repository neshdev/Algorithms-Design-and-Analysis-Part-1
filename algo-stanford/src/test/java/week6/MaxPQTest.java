package week6;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MaxPQTest {

    @Test
    public void EmptyCollectionReturnsNothing(){
        MaxPQ<Integer> maxPQ = new MaxPQ<>();
        Assert.assertTrue(maxPQ.isEmpty());
    }

    @Test
    public void SingleElementInListReturnsAsMaxElement(){

        Integer maxElement = 1;
        MaxPQ<Integer> maxPQ = new MaxPQ<>();
        maxPQ.put(maxElement);
        Integer actualMaxElement = maxPQ.getMax();
        Assert.assertEquals(maxElement,actualMaxElement);
    }

    @Test
    public void ElementInsertSizeReturnsSameSize(){

    }


    private <T> void shuffle(T[] items){
        Random r = new Random();
        int size = items.length;
        for (int i = 0; i < size ; i++) {
            int swapIndex = r.nextInt(size);
            T swap = items[i];
            items[i] = items[swapIndex];
            items[swapIndex] = swap;
        }
    }


    @Test
    public void AnyNumberElementInListWithRepeatedMaxDeleteReturnsListInDescendingOrder(){

        //arrange
        Random r = new Random();
        int size = r.nextInt(100) + 2;
        Integer[] unsorted = new Integer[size];
        for (int i = 0; i < size ; i++) {
            unsorted[i] = i;
        }
        shuffle(unsorted);

        MaxPQ<Integer> maxPQ = new MaxPQ<>();
        for (int i = 0; i < unsorted.length; i++) {
            maxPQ.put(unsorted[i]);
        }

        //act
        Integer[] sorted = new Integer[size];
        for (int i = 0; i < unsorted.length; i++) {
            sorted[i] = maxPQ.deleteMax();
        }

        //assert
        Arrays.sort(sorted,(o1, o2) -> {
                    if (o1 < o2) return 1;
                    else if (o1 > o2) return -1;
                    else return 0;
        });
        for (int i = 0; i < maxPQ.size() ; i++) {
            if ( sorted[i] != unsorted[i]) {
                Assert.fail("Array of size: " + size +"/nMismatch at index " + i + ": " + sorted[i] + " != " + unsorted[i]);
            }
        }


    }

}

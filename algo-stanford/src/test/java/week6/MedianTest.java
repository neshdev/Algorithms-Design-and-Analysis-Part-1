package week6;

import org.junit.Assert;
import org.junit.Test;
import week4.Queue;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by admin on 10/9/2016.
 */
public class MedianTest {

    @Test
    public void testcase1(){
        Queue<Double> q = new Queue<>();
        q.enqueue(9.0);
        q.enqueue(9.0);
        q.enqueue(7.0);
        q.enqueue(1.0);
        q.enqueue(2.0);
        q.enqueue(3.0);
        q.enqueue(4.0);
        q.enqueue(5.0);
        q.enqueue(6.0);
        q.enqueue(7.0);
        q.enqueue(8.0);
        q.enqueue(9.0);

        Double runningMed = 0.0;
        Median m = new Median();

        int i = 1;
        for (Double n: q) {
            m.put(n);
            runningMed = (runningMed + m.getMedian());
            System.out.println(i++ + ":" + m.getMedian());
        }

        System.out.println(q.size());

        Double actual = runningMed % q.size();
        Double expected = 2.0;

        Assert.assertEquals(expected,actual,.001);
    }

    @Test
    public void testcase2(){
        Queue<Double> q = new Queue<>();
        q.enqueue(2.0);
        q.enqueue(8.0);
        q.enqueue(9.0);
        q.enqueue(7.0);
        q.enqueue(3.0);
        q.enqueue(1.0);
        q.enqueue(4.0);

        Double runningMed = 0.0;
        Median m = new Median();

        int i = 1;
        for (Double n: q) {
            m.put(n);
            runningMed = (runningMed + m.getMedian());
            System.out.println(i++ + ":" + m.getMedian());
        }

        System.out.println(q.size());

        Double actual = runningMed % q.size();
        Double expected = 5.0;

        Assert.assertEquals(expected,actual,.001);
    }

    @Test
    public void progassignment(){
        try {
            Queue<Double> q = new Queue<>();
            String fileName = "c:/nesh/Median.txt";
            Scanner s = new Scanner(new File(fileName));

            while (s.hasNext()) {
                Double number = s.nextDouble();
                q.enqueue(number);
            }

            Double runningMed = 0.0;
            Median m = new Median();

            int i = 1;
            for (Double n : q) {
                m.put(n);
                runningMed = (runningMed + m.getMedian());
                System.out.println(i++ + " , " + m.getMedian());
            }

            System.out.println(q.size());

            Double actual = runningMed % q.size();
            Double expected = 1213.0;
            System.out.println("answer: " + actual);
            Assert.assertEquals(expected,actual,.001);
        }
        catch (Exception ex){

        }
    }

    @Test
    public void progassignment2(){
        try {
            int[] list = new int[10000];
            String fileName = "c:/nesh/Median.txt";
            Scanner sc = new Scanner(new File(fileName));
            long sum = 0;

            for (int i = 0; i < list.length; i++) {

                list[i] = sc.nextInt();
                Arrays.sort(list, 0, i + 1);
                sum += list[i / 2];
                System.out.println(i+1 + " , " + list[i / 2]);
            }
            System.out.println(sum % 10000);
        }
        catch (Exception ex){

        }
    }

    @Test
    public void progassignment3(){
        Queue<Double> q = new Queue<>();
        q.enqueue(6331.0);
        q.enqueue(2793.0);
        q.enqueue(1640.0);
        q.enqueue(9290.0);
        q.enqueue(225.0);
        q.enqueue(625.0);
        q.enqueue(6195.0);
        q.enqueue(2303.0);
        q.enqueue(5685.0);
        q.enqueue(1354.0);
        q.enqueue(4292.0);
        q.enqueue(7600.0);
        q.enqueue(6447.0);
        q.enqueue(4479.0);
        q.enqueue(9046.0);
        q.enqueue(7293.0);
        q.enqueue(5147.0);
        q.enqueue(1260.0);
        q.enqueue(1386.0);
        q.enqueue(6193.0);
        q.enqueue(4135.0);
        q.enqueue(3611.0);

        Double runningMed = 0.0;
        Median m = new Median();

        int i = 1;
        for (Double n: q) {
            m.put(n);
            runningMed = (runningMed + m.getMedian());
            //System.out.println(i++ + ":" + m.getMedian());
            System.out.println(m);
        }

        System.out.println(q.size());

        Double actual = runningMed;
        Double expected = 79818.0;

        Assert.assertEquals(expected,actual,.001);
    }
}

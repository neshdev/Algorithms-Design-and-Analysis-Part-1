package week6;

import week4.Queue;

import java.io.File;
import java.util.*;

/**
 * Created by admin on 9/16/2016.
 */
public class TwoSumOptimized {

    public static void main(String[] args) {

        try {
            String fileName = "c:/nesh/algo1-programming_prob-2sum.txt";
            Queue<Double> raw = new Queue<>();
            HashMap<Double, Double> set = new HashMap<Double, Double>();
            Scanner s = new Scanner(new File(fileName));
            while (s.hasNext()) {
                Double number = s.nextDouble();
                set.put(number, number);
                raw.enqueue(number);
            }
            System.out.println("Count:" + set.size());

            Double targetLow = -10000.0;
            Double targetHigh = 10000.0;

            List<Double> answers = new ArrayList<>();

            for (Double target = targetLow; target <= targetHigh; target++) {
                for (Double number : set.keySet()) {
                    Double answer = target - number;
                    if (set.containsKey(answer) && target != 2 * number) {
                        answers.add(target);
                    }
                }
            }

            HashSet<Double> uniqueAnswers = new HashSet<Double>(answers);
            System.out.println("Count:" + uniqueAnswers.size());
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        System.out.println("Exiting...");


    }


}

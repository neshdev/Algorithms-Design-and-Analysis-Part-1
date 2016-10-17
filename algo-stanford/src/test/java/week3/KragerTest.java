package week3;

import org.junit.Assert;
import org.junit.Test;
import week4.*;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by admin on 10/16/2016.
 */
public class KragerTest {
    //@Test
    public void AllCutsloadassignment() {
        try{
            int V = 201;
            DirectedGraph g = new DirectedGraph(V);
            String fileName = "C:/nesh/kargerMinCut.txt";
            Scanner s = new Scanner(new File(fileName));

            while (s.hasNext()) {
                int from = s.nextInt();
                String line = s.nextLine();
                String[] items = line.split("\t");
                for (int i = 1; i < items.length; i++) {
                    int to = Integer.parseInt(items[i]);
                    Edge e = new Edge(from, to);
                    g.addEdge(e);
                }
            }
        } catch (Exception ex){
            System.out.println(ex.toString());
            Assert.fail("error");
        }
    }


    @Test
    public void loadassignment() {
        try{
            int V = 201;
            DirectedGraph g = new DirectedGraph(V);
            String fileName = "C:/nesh/kargerMinCut.txt";
            Scanner s = new Scanner(new File(fileName));

            while (s.hasNext()) {
                int from = s.nextInt();
                String line = s.nextLine();
                String[] items = line.split("\t");
                for (int i = 1; i < items.length; i++) {
                    int to = Integer.parseInt(items[i]);
                    Edge e = new Edge(from, to);
                    g.addEdge(e);
                }
            }

            Map<Integer, Integer> counts = new HashMap<>();

            week4.Queue<Edge> globalMinCut = null;
            int globalMin = Integer.MAX_VALUE;
            for (int i = 0; i < g.V() * g.V(); i++) {
                ContractionMinimumCut c = new ContractionMinimumCut(g);
                week4.Queue<Edge> localMinCut = new week4.Queue<>(c.mincut());
                if ( localMinCut.size() < globalMin) {
                    globalMin = localMinCut.size();
                    globalMinCut = localMinCut;
                }
            }

            System.out.println("Mincut:" + globalMinCut.size());
            for (Edge e : globalMinCut) {
                System.out.print( "{" + e + "} ");
            }
            System.out.print("\n");
            //System.out.println(g);
            int actualMinCutSize = 17;
            Assert.assertEquals(globalMinCut.size(), 17);


        } catch (Exception ex){
            System.out.println(ex.toString());
            Assert.fail("error");
        }
    }
}

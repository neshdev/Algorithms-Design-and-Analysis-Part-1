package week5;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Scanner;

/**
 * Created by admin on 10/11/2016.
 */
public class DijkstraShortestPathTest {

    @Test
    public void loadassignment() {
        try{
            int V = 201;
            EdgeDirectedGraph g = new EdgeDirectedGraph(V);
            String fileName = "C:/nesh/dijkstraData.txt";
            Scanner s = new Scanner(new File(fileName));

            while (s.hasNext()) {
                int from = s.nextInt();
                String line = s.nextLine();
                String[] items = line.split("\t");
                for (String item: items) {
                    String[] kvs = item.split(",");
                    if (kvs.length == 1) continue;
                    int to = Integer.parseInt(kvs[0]);
                    Double weight =  Double.parseDouble(kvs[1]);
                    DirectedEdge e = new DirectedEdge(from, to,weight);
                    g.addEdge(e);
                }
            }
            //System.out.println(g);

            DijkstraShortestPath sp = new DijkstraShortestPath(g, 1);
            Integer[] distFor = {7,37,59,82,99,115,133,165,188,197};
            for (int i = 0; i < distFor.length; i++) {
                int dist = sp.weightTo(distFor[i]).intValue();
                if (dist == Integer.MAX_VALUE){
                    dist = 1000000;
                }
                System.out.print( dist + ",");
            }

        } catch (Exception ex){
            System.out.println(ex.toString());
            Assert.fail("error");
        }
        Assert.fail("error");

    }

}

package week4;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by admin on 8/9/2016.
 */
public class Assignment4Test {

    private static String INPUT_FILENAME = "C:\\coursera\\algo-stanford\\algo-stanford\\src\\test\\java\\week4\\SCC.txt";
    private int INPUT_MAX_VERTICIES = 875714 + 1;

    @Test
    public void loadInputFileTest() throws FileNotFoundException {
        Scanner s = new Scanner(new File(INPUT_FILENAME));
        DirectedGraph g = new DirectedGraph(INPUT_MAX_VERTICIES);
        while (s.hasNext()){
            int v = s.nextInt();
            int w = s.nextInt();
            g.addEdge(v,w);
        }
        Assert.assertTrue(g.V() == INPUT_MAX_VERTICIES);
        Assert.assertTrue(g.reverse().V() == INPUT_MAX_VERTICIES);

        SCC scc = new SCC(g);
        for (Integer i : scc.sumComponents()) {
            System.out.println(i);
        }
    }

    //@Test
    public void directedGraphSCCOnSampleProblem(){
        int v = 10;
        DirectedGraph g = new DirectedGraph(v);
        g.addEdge(1, 5);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 2);
        g.addEdge(4, 5);
        g.addEdge(5, 6);
        g.addEdge(6, 1);
        g.addEdge(6, 9);
        g.addEdge(7, 8);
        g.addEdge(8, 9);
        g.addEdge(9, 7);

    }
}

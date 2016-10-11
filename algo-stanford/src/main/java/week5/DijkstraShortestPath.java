package week5;

import week4.Queue;
import week4.Stack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by admin on 10/10/2016.
 */
public class DijkstraShortestPath {

    public static void main(String[] args) {
        int V = 9;
        EdgeDirectedGraph g = new EdgeDirectedGraph(V);
        g.addEdge(new DirectedEdge(1, 2, 1));
        g.addEdge(new DirectedEdge(1, 8, 2));

        g.addEdge(new DirectedEdge(2, 1, 1));
        g.addEdge(new DirectedEdge(2, 3, 1));

        g.addEdge(new DirectedEdge(3, 2, 1));
        g.addEdge(new DirectedEdge(3, 4, 1));

        g.addEdge(new DirectedEdge(4, 3, 1));
        g.addEdge(new DirectedEdge(4, 5, 1));

        g.addEdge(new DirectedEdge(5, 4, 1));
        g.addEdge(new DirectedEdge(5, 6, 1));

        g.addEdge(new DirectedEdge(6, 5, 1));
        g.addEdge(new DirectedEdge(6, 7, 1));

        g.addEdge(new DirectedEdge(7, 6, 1));
        g.addEdge(new DirectedEdge(7, 8, 1));

        g.addEdge(new DirectedEdge(8, 7, 1));
        g.addEdge(new DirectedEdge(8, 1, 2));

        System.out.println(g);

        int s = 1;
        DijkstraShortestPath sp = new DijkstraShortestPath(g, s);
        for (int v = 0; v < g.V(); v++) {
            System.out.print(v + ":" + "(" + sp.weightTo(v)  + ")" +"[");
            for (Integer w : sp.pathTo(v)) {
                System.out.print(w + " ");
            }
            System.out.print("]\n");
        }

        System.out.println("Exiting...");
    }

    private int V;
    private int s;
    private Integer[] pathTo;
    private Double[] distTo;

    public DijkstraShortestPath(EdgeDirectedGraph g, int s) {
        if (g == null) throw new NullPointerException("graph.isnull");
        if (s < 0) throw new IndexOutOfBoundsException("s.is.outofbounds");
        this.s = s;
        this.V = g.V();
        pathTo = new Integer[g.V()];
        distTo = new Double[g.V()];
        for (int i = 0; i < g.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        shortestPath(g, s);
    }

    private List<DirectedEdge> toList(Iterable<DirectedEdge> items) {
        List<DirectedEdge> arr = new ArrayList<>();
        for (DirectedEdge e : items) {
            arr.add(e);
        }
        return arr;
    }

    private void shortestPath(EdgeDirectedGraph g, int s) {

        distTo[s] = 0.0;
        List<DirectedEdge> vMinusX = toList(g.edges());

        List<DirectedEdge> x = new ArrayList<>();
        vMinusX = vMinusX.stream().filter(z -> z.to() != s).collect(Collectors.toList());

        while (!vMinusX.isEmpty()) {
            double lowestWeight = Double.POSITIVE_INFINITY;
            DirectedEdge lowestEdge = null;
            for (DirectedEdge e : vMinusX) {
                Double totalWeight = distTo[e.from()] + e.weight();
                if (totalWeight < lowestWeight) {
                    lowestWeight = totalWeight;
                    lowestEdge = e;
                }
            }
            x.add(lowestEdge);
            distTo[lowestEdge.to()] = distTo[lowestEdge.from()] + lowestEdge.weight();
            pathTo[lowestEdge.to()] = lowestEdge.from();
            final int to = lowestEdge.to();
            vMinusX = vMinusX.stream().filter(z -> z.to() != to).collect(Collectors.toList());
        }


    }

    private void validateVertex(int v) {
        if (v < 0) throw new IndexOutOfBoundsException("v.is.outofbounds.lower");
        if (v >= V) throw new IndexOutOfBoundsException("v.is.outofbounds.upper");
    }

    public Iterable<Integer> pathTo(int v) {
        Stack<Integer> stack = new Stack<>();
        if (distTo[v] == Double.POSITIVE_INFINITY) return stack;
        for (int x = v; x != s; x = pathTo[x]) {
            stack.push(x);
        }
        stack.push(s);
        return stack;
    }

    public Double weightTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

}

package week4;

import java.util.Iterator;

/**
 * Created by admin on 8/9/2016.
 */
public class DirectedGraph {

    public static void main(String[] args){
        int v = 4;
        DirectedGraph g = new DirectedGraph(v);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(0,3);
        g.addEdge(2,1);
        g.addEdge(2,3);
        g.addEdge(3,0);
        System.out.println(g);
        System.out.println(g.reverse());

    }

    private Bag<Integer>[] adjTo;

    public DirectedGraph(int v) {
        adjTo = new Bag[v];
        for (int i = 0; i < v; i++) {
            adjTo[i] = new Bag<>();
        }
    }

    public void addEdge(int v, int w) {
        adjTo[v].add(w);
    }

    public Iterable<Integer> adj(int v) {
        return adjTo[v];
    }

    public int E() {
        int count = 0;
        for (int v = 0; v < V(); v++) {
            for (Integer w : adj(v)) {
                count++;
            }
        }
        return count;
    }

    public int V() {
        return adjTo.length;
    }

    public DirectedGraph reverse() {
        DirectedGraph g = new DirectedGraph(V());
        for (int v = 0; v < V(); v++) {
            for (Integer w : adj(v)) {
                g.addEdge(w, v);
            }
        }
        return g;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("V: " + V() + "\n");
        sb.append("E: " + E() + "\n");
        for (int v = 0; v < V(); v++) {
            sb.append(v + ": ");
            for (Integer w: adj(v)) {
                sb.append(w + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

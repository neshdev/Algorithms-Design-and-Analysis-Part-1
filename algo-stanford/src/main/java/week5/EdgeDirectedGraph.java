package week5;

import week4.Bag;
import week4.Queue;

/**
 * Created by admin on 10/10/2016.
 */
public class EdgeDirectedGraph {

    public static void main(String[] args){

        int v = 9;
        EdgeDirectedGraph e = new EdgeDirectedGraph(9);
        e.addEdge(new DirectedEdge(1, 2, 1));
        e.addEdge(new DirectedEdge(1, 8, 2));

        e.addEdge(new DirectedEdge(2, 1, 1));
        e.addEdge(new DirectedEdge(2, 3, 1));

        e.addEdge(new DirectedEdge(3, 2, 1));
        e.addEdge(new DirectedEdge(3, 4, 1));

        e.addEdge(new DirectedEdge(4, 3, 1));
        e.addEdge(new DirectedEdge(4, 5, 1));

        e.addEdge(new DirectedEdge(5, 4, 1));
        e.addEdge(new DirectedEdge(5, 6, 1));

        e.addEdge(new DirectedEdge(6, 5, 1));
        e.addEdge(new DirectedEdge(6, 7, 1));

        e.addEdge(new DirectedEdge(7, 6, 1));
        e.addEdge(new DirectedEdge(7, 8, 1));

        e.addEdge(new DirectedEdge(8, 7, 1));
        e.addEdge(new DirectedEdge(8, 1, 2));

        System.out.println(e);
        System.out.println("Exiting...");
    }


    private int v;
    private int e;
    private Bag<DirectedEdge>[] adj;

    public EdgeDirectedGraph(int v){
        if ( v < 0) throw new IndexOutOfBoundsException("v.index.outofbounds");
        this.e = 0;
        this.v = v;
        adj = new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<>();
        }
    }

    private void validateVertex(int v){
        if ( v < 0 || v >= V()) throw new IndexOutOfBoundsException("v.index.outofbounds");
    }

    public void addEdge(DirectedEdge edge){
        if ( edge == null) throw new NullPointerException("edge.isnull");
        validateVertex(edge.from());
        validateVertex(edge.to());
        adj[edge.from()].add(edge);
        e++;
    }

    public Iterable<DirectedEdge> adjTo(int v){
        validateVertex(v);
        return adj[v];
    }

    public int V(){
        return v;
    }

    public int E(){
        return e;
    }

    public Iterable<DirectedEdge> edges() {
        Queue<DirectedEdge> q = new Queue<>();
        for (int v = 0; v < V(); v++) {
            for (DirectedEdge w: adjTo(v) ) {
                q.enqueue(w);
            }
        }
        return q;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("V: " + v + "  E:" + e + "\n");
        for (int v = 0; v < V(); v++) {
            sb.append(v + ":");
            for (DirectedEdge w: adjTo(v)) {
                sb.append(w + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

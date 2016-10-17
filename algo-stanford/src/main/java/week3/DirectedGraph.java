package week3;

import week4.Bag;

/**
 * Created by admin on 8/2/2016.
 */
public class DirectedGraph {

    public static void main(String[] args) {
        int V = 9;
        DirectedGraph g = new DirectedGraph(V);
        g.addEdge(1,2);
        g.addEdge(1,3);
        g.addEdge(1,4);
        g.addEdge(1,7);

        g.addEdge(2,1);
        g.addEdge(2,3);
        g.addEdge(2,4);

        g.addEdge(3,1);
        g.addEdge(3,2);
        g.addEdge(3,4);

        g.addEdge(4,1);
        g.addEdge(4,2);
        g.addEdge(4,3);
        g.addEdge(4,5);

        g.addEdge(5,4);
        g.addEdge(5,6);
        g.addEdge(5,7);
        g.addEdge(5,8);

        g.addEdge(6,5);
        g.addEdge(6,7);
        g.addEdge(6,8);

        g.addEdge(7,1);
        g.addEdge(7,5);
        g.addEdge(7,6);
        g.addEdge(7,8);

        g.addEdge(8,5);
        g.addEdge(8,6);
        g.addEdge(8,7);

        for (Edge e: g.distinctEdges() ) {
            System.out.println(e);
        }

        System.out.println(g.toString());
        System.out.println("Exiting...");
    }

    private int v;
    private Bag<Edge>[] adj;

    public DirectedGraph(int V) {
        if ( v < 0) throw new IndexOutOfBoundsException("index.outofbounds.lower");
        this.v = V;
        adj = new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    private void validateVertex(int x) {
        if (x < 0) throw new IndexOutOfBoundsException("index.outofbounds.lower");
        if (x >= v) throw new IndexOutOfBoundsException("index.outofbounds.upper");
    }

    public void addEdge(Integer v, Integer w){
        addEdge(new Edge(v,w));
    }

    public void addEdge(Edge e) {
        if (e == null) throw new NullPointerException("e.isnull");

        Integer v = e.v();
        Integer w = e.w();
        validateVertex(v);
        validateVertex(w);

        adj[v].add(e);
    }

    public Iterable<Edge> adjTo(int v){
        validateVertex(v);
        return adj[v];
    }

    public int V() {
        return v;
    }

    public int E(){
        int count = 0;
        for (int v = 0; v < V(); v++) {
            for (Edge w: adjTo(v) ) {
                count++;
            }
        }
        return count/2;
    }

    public Iterable<Edge> distinctEdges(){
        Queue<Edge> edges = new Queue<>();
        for (int v = 0; v < V(); v++) {
            for (Edge e: adjTo(v) ) {
                int vstar = e.v();
                int wstar = e.w();
                if ( wstar > v ){
                    edges.enqueue(e);
                }
            }
        }
        return edges;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Edges:" + E() + "\n");
        sb.append("Edges:" + V() + "\n");
        for (int v = 0; v < V(); v++) {
            sb.append(v + ": ");
            for (Edge w : adjTo(v)) {
                sb.append(w + " ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}

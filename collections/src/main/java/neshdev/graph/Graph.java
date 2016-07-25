package neshdev.graph;

import neshdev.collections.Bag;

/**
 * Created by admin on 7/23/2016.
 */
public class Graph {

    public static void main(String[] args){
        int v = 4;
        Graph g = new Graph(v);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(2,3);
        g.addEdge(1,3);
        System.out.println(g.toString());
    }


    private Bag<Integer>[] adj;
    private int V;
    private int E;

    public Graph(int v){
        V = v;
        adj = (Bag<Integer>[])new Bag[v];
        for (int i = 0; i < v ; i++) {
            adj[i] = new Bag<>();
        }
    }

    public void addEdge(int v, int w){
        validate(v);
        validate(w);
        adj[v].insert(w);
        adj[w].insert(v);
        E++;
    }

    private void validate(int v){
        if ( v < 0 || v > V) throw new IllegalArgumentException("Invalid Vertex");
    }

    public Iterable<Integer> adj(int v){
        validate(v);
        return adj[v];
    }

    public int Verticies(){
        return V;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int v = 0; v < V; v++) {
            sb.append("Vertex " + v + ": ");
            for (Integer w: adj(v)) {
                sb.append(w + "\t");
            }
            sb.append("\n");
        }
        return  sb.toString();
    }
}

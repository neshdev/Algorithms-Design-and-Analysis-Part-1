package neshdev.graph;

import neshdev.collections.Stack;

/**
 * Created by admin on 7/23/2016.
 */
public class DepthFirstPath {

    public static void main(String[] args){
        Graph g = new Graph(4);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(2,3);
        g.addEdge(1,3);

        System.out.println("Graph");
        System.out.println(g);

        int s = 1;
        DepthFirstPath dfs = new DepthFirstPath(g, s);

        for (int v = 0; v < g.Verticies(); v++) {
            if ( dfs.hasPathTo(v)){
                System.out.print(s + " to " + v + ": ");
                for (Integer p: dfs.pathTo(v) ) {
                    if ( p == s) System.out.print(p);
                    else  System.out.print("-" + p);
                }
                System.out.print("\n");
            }
        }

    }

    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DepthFirstPath(Graph g, int s){
        this.s = s;
        marked = new boolean[g.Verticies()];
        edgeTo = new int[g.Verticies()];
        dfs(g, s);
    }

    private void dfs(Graph g, int v){
        marked[v] = true;
        for (Integer w: g.adj(v)) {
            if ( !marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }


}

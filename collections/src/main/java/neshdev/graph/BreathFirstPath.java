package neshdev.graph;

import neshdev.collections.Queue;
import neshdev.collections.Stack;

/**
 * Created by admin on 7/24/2016.
 */
public class BreathFirstPath {
    public static void main(String[] args){
        Graph g = new Graph(4);
        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(2,3);
        g.addEdge(1,3);

        System.out.println("Graph");
        System.out.println(g);

        int s = 1;
        BreathFirstPath bfs = new BreathFirstPath(g, s);

        for (int v = 0; v < g.Verticies(); v++) {
            if ( bfs.hasPathTo(v)){
                System.out.print(s + " to " + v + ": ");
                for (Integer p: bfs.pathTo(v) ) {
                    if ( p == s) System.out.print(p);
                    else  System.out.print("-" + p);
                }
                System.out.print("\n");
            }
        }
    }

    private int s;
    private boolean[] marked;
    private int[] edgeTo;

    public BreathFirstPath(Graph g, int s){
        this.s = s;
        marked = new boolean[g.Verticies()];
        edgeTo = new int[g.Verticies()];
        bfs(g, s);
    }

    private void bfs(Graph g, int s){
        marked[s] = true;
        Queue<Integer> q = new Queue<>();
        q.enqueue(s);
        while (!q.isEmpty()){
            int v = q.dequeue();
            for (Integer w: g.adj(v) ) {
                if (!marked[w]){
                    q.enqueue(w);
                    marked[w] =true;
                    edgeTo[w] = v;
                }
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if ( !hasPathTo(v)) return null;
        Stack<Integer> stack = new Stack<>();
        for (int x = v; s != x; x = edgeTo[x]){
            stack.push(x);
        }
        stack.push(s);
        return stack;
    }
}

package week4;

import java.util.NoSuchElementException;

/**
 * Created by admin on 8/6/2016.
 */
public class BreathFirstSearch {

    public static void main(String[] args){
        UndirectedGraph g = new UndirectedGraph(11);
        g.addEdge(1,5);
        g.addEdge(3,5);
        g.addEdge(5,7);
        g.addEdge(5,9);
        g.addEdge(2,4);
        g.addEdge(6,8);
        g.addEdge(6,10);
        System.out.println(g);

        int s = 1;
        BreathFirstSearch bfs = new BreathFirstSearch(g, s);
        System.out.println("Paths from source " + s);
        for (int v = 0; v < g.V(); v++) {
            if (bfs.hasPathTo(v)){
                for (Integer p : bfs.path(v)) {
                    System.out.print(p + " ");
                }
                System.out.println();
            }
        }

        System.out.println("Distance from source " + s);
        for (int v = 0; v < g.V(); v++) {
            if ( bfs.hasPathTo(v))
                System.out.println("dist to " + v + ": " + bfs.dist(v));
        }

        System.out.println("Exiting");
    }

    private int s;
    private boolean[] marked;
    private Integer[] pathTo;
    private int[] distTo;
    private static int INFINITY = Integer.MAX_VALUE;

    public BreathFirstSearch(UndirectedGraph g, int s){
        this.s = s;
        marked = new boolean[g.V()];
        pathTo = new Integer[g.V()];
        distTo = new int[g.V()];
        for (int i = 0; i < g.V(); i++) {
            pathTo[i] = null;
            marked[i] = false;
            distTo[i] = INFINITY;
        }
        bfs(g, s);
    }

    private void bfs(UndirectedGraph g, int s){
        marked[s] = true;
        distTo[s] = 0;
        Queue<Integer> q = new Queue<>();
        q.enqueue(s);
        while (!q.isEmpty()){
            int v = q.dequeue();
            for (Integer w: g.adj(v)) {
                if ( !marked[w]){
                    marked[w] = true;
                    pathTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    q.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> path(int v){
        if (!hasPathTo(v)) return null;
        Stack<Integer> q = new Stack<>();
        for(int i = v; i != s; i = pathTo[i])
            q.push(i);
        q.push(s);
        return q;
    }

    public int dist(int v){
        return distTo[v];
    }
}

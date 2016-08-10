package week4;

import com.sun.corba.se.impl.orbutil.graph.Graph;

/**
 * Created by admin on 8/9/2016.
 */
public class DirectedTopologicalSort {


    public static void main(String[] args){
        int v = 6;
        DirectedGraph g = new DirectedGraph(v);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);

        DirectedTopologicalSort ts = new DirectedTopologicalSort(g);
        for (Integer o: ts.order() ) {
            System.out.print(o + " ");
        }
    }

    private boolean[] marked;
    private Integer currentLabel;
    private int[] finishing;

    public DirectedTopologicalSort(DirectedGraph g) {
        marked = new boolean[g.V()];
        currentLabel = g.V() - 1;
        finishing = new int[g.V()];

        for (int v = 0; v < g.V(); v++) {
            if (!marked[v]) dfs(g, v);
        }
    }

    private void dfs(DirectedGraph g, int s) {
        marked[s] = true;
        for (Integer w : g.adj(s)) {
            if (!marked[s]) {
                dfs(g, w);
            }
        }
        finishing[s] = currentLabel;
        currentLabel--;
    }

    private Iterable<Integer> order(){
        Queue<Integer> q = new Queue<>();
        for (int i = 0; i < finishing.length ; i++) {
            q.enqueue(finishing[i]);
        }
        return q;
    }
}

package week4;

import week3.SeparateChainingHashSet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by admin on 8/10/2016.
 */
public class SCC {

    public static void main(String[] args) {
        int v = 10;
        DirectedGraph g = new DirectedGraph(v);
        g.addEdge(1, 4);
        g.addEdge(4, 7);
        g.addEdge(7, 1);

        g.addEdge(9, 3);
        g.addEdge(9, 7);
        g.addEdge(3, 6);
        g.addEdge(6, 9);

        g.addEdge(8,5);
        g.addEdge(8,6);
        g.addEdge(2, 8);
        g.addEdge(5, 2);

        SCC scc = new SCC(g);

        for (Integer i: scc.sumComponents()) {
            System.out.print(i + ",");
        }
    }

    private boolean marked[];
    private int globalT = 0;
    private int globalS;
    private int[] leader;
    private int[] finishing;

    public SCC(DirectedGraph g) {
        marked = new boolean[g.V()];
        leader = new int[g.V()];
        finishing = new int[g.V()];
        DirectedGraph rev = g.reverse();
        post = new Queue<>();
        for (int i = g.V() - 1; i > 0; i--) {
            if (!marked[i]) {
                globalS = i;
                dfs(rev, i);
            }
        }

        marked = new boolean[g.V()];
        leader = new int[g.V()];
        finishing = new int[g.V()];
        globalT = 0;
        Stack<Integer> order = new Stack<>();
        for (Integer p : post) {
            order.push(p);
        }
        post = new Queue<>();
        for (Integer i : order) {
            if (!marked[i]) {
                globalS = i;
                dfs(g, i);
            }
        }
    }

    public Iterable<Integer> sumComponents(){
        week3.SeparateChainingHashSet<Integer, Integer> map = new SeparateChainingHashSet<>();
        for (int i = 1; i < leader.length; i++) {
            if (!map.contains(leader[i])) map.put(leader[i], 1);
            else map.put(leader[i], map.get(leader[i]) + 1);
        }

        List<Integer> allEntries = new ArrayList<>();
        for (Integer key: map.keys()) {
            allEntries.add(map.get(key));
        }
        allEntries.sort((o1, o2) -> o2.compareTo(o1) );

        return  allEntries;
    }

    private Queue<Integer> post;

    private void dfs(DirectedGraph g, int s) {
        marked[s] = true;
        leader[s] = globalS;
        for (Integer w : g.adj(s)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }

        globalT++;
        finishing[s] = globalT;
        post.enqueue(s);

    }
}

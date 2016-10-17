package week3;


import week4.*;
import week4.Queue;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by admin on 8/2/2016.
 */
public class ContractionMinimumCut {

    public static void main(String[] args) {
        int V = 5;
        DirectedGraph g = new DirectedGraph(V);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 4);


        Queue<Edge> globalMinCut = null;
        int globalMin = Integer.MAX_VALUE;
        for (int i = 0; i < g.V() * g.V(); i++) {
            ContractionMinimumCut c = new ContractionMinimumCut(g);
            Queue<Edge> localMinCut = new Queue<>(c.mincut());
            if ( localMinCut.size() < globalMin) {
                globalMin = localMinCut.size();
                globalMinCut = localMinCut;
            }
        }

        System.out.println("Mincut:" + globalMinCut.size());
        for (Edge e : globalMinCut) {
            System.out.print( "{" + e + "} ");
        }
        System.out.print("\n");

    }

    public static void main1(String[] args) {
        int V = 9;
        DirectedGraph g = new DirectedGraph(V);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(1, 7);

        g.addEdge(2, 1);
        g.addEdge(2, 3);
        g.addEdge(2, 4);

        g.addEdge(3, 1);
        g.addEdge(3, 2);
        g.addEdge(3, 4);

        g.addEdge(4, 1);
        g.addEdge(4, 2);
        g.addEdge(4, 3);
        g.addEdge(4, 5);

        g.addEdge(5, 4);
        g.addEdge(5, 6);
        g.addEdge(5, 7);
        g.addEdge(5, 8);

        g.addEdge(6, 5);
        g.addEdge(6, 7);
        g.addEdge(6, 8);

        g.addEdge(7, 1);
        g.addEdge(7, 5);
        g.addEdge(7, 6);
        g.addEdge(7, 8);

        g.addEdge(8, 5);
        g.addEdge(8, 6);
        g.addEdge(8, 7);

        Queue<Edge> globalMinCut = null;
        int globalMin = Integer.MAX_VALUE;
        for (int i = 0; i < g.V() * g.V(); i++) {
            ContractionMinimumCut c = new ContractionMinimumCut(g);
            Queue<Edge> localMinCut = new Queue<>(c.mincut());
            if ( localMinCut.size() < globalMin) {
                globalMin = localMinCut.size();
                globalMinCut = localMinCut;
            }
        }

        System.out.println("Mincut:" + globalMinCut.size());
        for (Edge e : globalMinCut) {
            System.out.print( "{" + e + "} ");
        }
        System.out.print("\n");

        //cuts are [(1,7), (4,5)]
    }

    private Edge[] edges;
    private int n;
    private DirectedGraph g;

    private Edge[] toArray(Iterable<Edge> edges) {
        List<Edge> list = new ArrayList<Edge>();
        for (Edge e : edges) {
            list.add(e);
        }
        Edge[] arr = new Edge[list.size()];
        return list.toArray(arr);
    }

    public ContractionMinimumCut(DirectedGraph g) {
        if (g == null) throw new NullPointerException("g.isnull");
        this.g = g;
        parent = new int[g.V()];
        rank = new int[g.V()];
        for (int i = 0; i < g.V(); i++) {
            parent[i] = i;
            rank[i] = 0;
        }
        count = g.V();

        Random r = new Random();
        edges = toArray(g.distinctEdges());
        n = edges.length;
        while (count > 3) {
            int i = r.nextInt(n);
            Edge e = edges[i];

            int p = find(e.v());
            int q = find(e.w());

            if (p == q) continue;
            union(p, q);
        }
    }

    private int[] parent;
    private int[] rank;
    private int count;

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];    // path compression by halving
            p = parent[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make root of smaller rank point to root of larger rank
        if (rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
        else if (rank[rootP] > rank[rootQ]) parent[rootQ] = rootP;
        else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
        count--;
    }

    public Iterable<Edge> mincut() {
        Bag<Edge> bag = new Bag<>();
        for (int i = 0; i < edges.length; i++) {
            Edge e = edges[i];
            if (!connected(e.v(), e.w())) {
                bag.add(e);
            }
        }
        return bag;
    }

    public HashMap<Integer, Bag<Integer>> minGroup() {
        HashMap<Integer, Bag<Integer>> groups = new HashMap<>();

        for (Integer i = 0; i < parent.length; i++) {
            int component = find(parent[i]);
            if (groups.containsKey(component)) {
                Bag<Integer> b = groups.get(component);
                b.add(i);
                groups.put(component, b);
            } else {
                Bag<Integer> b = new Bag<>();
                b.add(i);
                groups.put(component, b);
            }
        }

        return groups;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Edge e : mincut()) {
            sb.append("{" + e.v() + "," + e.w() + "} ");
        }
        return sb.toString();

    }

    public String toString1() {
        StringBuilder sb = new StringBuilder();
        ContractionMinimumCut c = new ContractionMinimumCut(g);
        HashMap<Integer, Bag<Integer>> cuts = c.minGroup();
        for (Integer v : cuts.keySet()) {
            for (Integer w : cuts.get(v)) {
                sb.append(w + " ");
            }
            sb.append(" | ");
        }
        return sb.toString();
    }
}

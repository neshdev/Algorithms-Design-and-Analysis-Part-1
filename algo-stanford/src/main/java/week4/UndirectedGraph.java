package week4;

/**
 * Created by admin on 8/6/2016.
 */
public class UndirectedGraph {

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
    }

    private Bag<Integer>[] adjTo;

    public UndirectedGraph(int v) {
        adjTo = new Bag[v];
        for (int i = 0; i < v; i++) {
            adjTo[i] = new Bag<Integer>();
        }
    }

    public void addEdge(int v, int w) {
        adjTo[v].add(w);
        adjTo[w].add(v);
    }

    public Iterable<Integer> adj(int v)
    {
        return adjTo[v];
    }

    public int E()
    {
        int sum = 0;
        for (int v = 0; v < V(); v++) {
            for (Integer w : adj(v)) {
                sum = sum+1;
            }
        }
        return sum /2;
    }

    public int V()
    {
        return adjTo.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Verticies: " + V() + "\n");
        sb.append("Edges: " + E() + "\n");
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

package week3;


/**
 * Created by admin on 8/2/2016.
 */
public class ContractionMinimumCut {

    public static void main(String[] args) {

    }

    private UndirectedGraph graph;

    public ContractionMinimumCut(UndirectedGraph g) {
        this.graph = g;
        contract(graph);
    }

    public Iterable<Integer> contract(UndirectedGraph graph) {
        Queue<Integer> q = new Queue<>();

        while (graph.V() != 2) {
            int u = graph.randomUniformEdge();
            int v = graph.randomUniformEdge();

        }

        for (Integer v : graph.verticies()) q.enqueue(v);

        return q;
    }
}

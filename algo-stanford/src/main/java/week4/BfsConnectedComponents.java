package week4;

/**
 * Created by admin on 8/7/2016.
 */
public class BfsConnectedComponents {


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

        BfsConnectedComponents cc = new BfsConnectedComponents(g);
        for (int v = 0; v < g.V(); v++) {
            for (int w = v+1; w < g.V(); w++) {
                if( cc.connected(v,w)){
                    System.out.print(v + " " + w + " connected" + cc.connected(v,w));
                    System.out.print("Path: " );
                    for (Integer i: cc.path(w)) {
                        System.out.print(i + " ");
                    }
                    System.out.println();
                }

            }
        }

        System.out.println("Exiting");
    }

    private boolean[] marked;
    private int[] connected;
    private int[] pathTo;
    private int[] distTo;
    private int count;
    private static int INFINITY = Integer.MAX_VALUE;

    public BfsConnectedComponents(UndirectedGraph g){
        marked = new boolean[g.V()];
        connected = new int[g.V()];
        pathTo = new int[g.V()];
        distTo = new int[g.V()];
        for (int i = 0; i < g.V(); i++) {
            distTo[i] =INFINITY;
        }
        for (int s = 0; s < g.V(); s++) {
            if (!marked[s]){
                bfs(g, s);
                count++;
            }
        }
    }

    private void bfs(UndirectedGraph g, int s){
        marked[s] =true;
        distTo[s] = 0;
        connected[s] = count;
        Queue<Integer> q = new Queue<>();
        q.enqueue(s);
        while (!q.isEmpty()){
            int v = q.dequeue();
            for (Integer w: g.adj(v)) {
                if (!marked[w]){
                    marked[w] = true;
                    connected[w] = count;
                    pathTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    q.enqueue(w);
                }
            }
        }
    }

    public boolean visited(int v){
        return marked[v];
    }

    public Iterable<Integer> path(int v){
        Stack<Integer> s = new Stack<>();
        int x;
        for (x = v; distTo[x] != 0 ; x = pathTo[x]) {
            s.push(x);
        }
        s.push(x);
        return s;
    }

    public boolean connected(int v, int w){
        return connected[v] == connected[w];
    }



}

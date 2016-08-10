package week3;

import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by admin on 8/2/2016.
 */
public class UndirectedGraph {

    public static void main(String[] args){
        UndirectedGraph g = new UndirectedGraph();

        int v = 5;
        for (int i = 0; i < v; i++) {
            for (int j = i; j < v; j++) {
                g.addEdge(i,j);
            }
        }

        for (int i = 0; i < v; i++) {
            g.removeEdge(i,i);
        }

        System.out.println(g.toString());
        System.out.println("Exiting...");
    }

    private SeparateChainingHashSet<Integer, TreeMap<Integer, Integer>> adjTo;

    public UndirectedGraph(){
        adjTo = new SeparateChainingHashSet<>();
    }

    public void addEdge(int v, int w){
        if ( !adjTo.contains(v)) adjTo.put(v,new TreeMap<>());
        if ( !adjTo.contains(w)) adjTo.put(w,new TreeMap<>());

        adjTo.get(v).put(w,w);
        adjTo.get(w).put(v,v);
    }

    public void removeEdge(int v, int w){
        TreeMap<Integer, Integer> V = adjTo.get(v);
        TreeMap<Integer, Integer> W = adjTo.get(w);

        if (V.containsKey(w)) V.remove(w);
        if (W.containsKey(v)) W.remove(v);

        if (V.isEmpty()) adjTo.remove(v);
        if (W.isEmpty()) adjTo.remove(v);
    }

    public int randomUniformEdge(){
        Random rand = new Random();
        int r = rand.nextInt(V());
        int i = 0;
        for (Integer v : verticies()  ) {
             if (i == r) break;
             i++;
        }
        return i;
    }

    public Iterable<Integer> adj(int v){
        return adjTo.get(v).keySet();
    }

    public Iterable<Integer> verticies(){
        return adjTo.keys();
    }

    public int V(){
        return adjTo.size();
    }

    public int E(){
        int sum = 0;
        for (Integer v: verticies() ) {
            sum = sum + adjTo.get(v).size();
        }
        return sum/2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Edges:" + E() + "\n");
        sb.append("Edges:" + V() + "\n");
        for (Integer v : verticies() ) {
            sb.append(v + ": ");
            for (Integer w : adj(v) ) {
                sb.append(w + " ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}

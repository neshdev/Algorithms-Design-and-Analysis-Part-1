package week5;

/**
 * Created by admin on 10/10/2016.
 */
public class DirectedEdge {
    private int v;
    private int w;
    private double weight;

    public DirectedEdge(int v, int w, double weight) {
        if (v < 0) throw new IndexOutOfBoundsException("v.index.outofbound");
        if (w < 0) throw new IndexOutOfBoundsException("w.index.outofbound");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("weight.nan");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public double weight() {
        return weight;
    }

    @Override
    public String toString() {
        return v + "->" + w + " " + String.format("%5.2f", weight);
    }
}
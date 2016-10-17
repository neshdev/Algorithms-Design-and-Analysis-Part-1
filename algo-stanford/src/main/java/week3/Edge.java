package week3;

/**
 * Created by admin on 10/11/2016.
 */
public class Edge {

    private int _v;
    private int _w;

    public Edge(int v, int w){
        this._v = v;
        this._w = w;
    }

    public int v(){
        return _v;
    }

    public int w(){
        return _w;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( _v + "->" + _w);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge that = (Edge) o;
        if( this._v != that._v) return false;
        if( this._w != that._w) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + ((Integer) _v).hashCode();
        hash = 31 * hash + ((Integer) _w).hashCode();
        return hash;
    }
}

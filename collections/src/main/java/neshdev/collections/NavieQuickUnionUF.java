package neshdev.collections;

/**
 * | initalize | union | find |
 * |    N      |   N*  |  N   |
 * (*) -includes cost of finding roots
 * Notes:
 * Trees can get tall
 * Find too expensive (could be N array accesses)
 * Created by admin on 7/31/2016.
 */
public class NavieQuickUnionUF {

    public static void main(String[] args) {
        int N = 10;
        NavieQuickUnionUF uf = new NavieQuickUnionUF(N);
        uf.union(4, 3);
        uf.union(3, 8);
        uf.union(6, 5);
        uf.union(9, 4);
        uf.union(2, 1);
        uf.union(5, 0);
        uf.union(7, 2);
        uf.union(6, 1);
        System.out.println("8 connected to 9: " + uf.connected(8, 9));
        System.out.println("9 connected to 5: " + uf.connected(9, 5));
        System.out.println("Exting...");
    }

    private int[] ids;

    public NavieQuickUnionUF(int N) {
        ids = new int[N];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = i;
        }
    }

    public void union(int p, int q) {
        int proot = root(p);
        int qroot = root(q);
        ids[proot] = qroot;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    private int root(int p) {
        if (ids[p] == p) return p;
        else return root(ids[p]);
    }
}

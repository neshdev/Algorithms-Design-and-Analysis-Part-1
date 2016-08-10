package neshdev.collections;

/**
 *
 * |initalize| union | find |
 * |    N    |   N   |   1  |
 * # Notes
 * For every N, this takes N time to run
 * union(1,2) : N
 * union(2,3) : N
 * union(3,4) : N
 * N^3 for three unions
 * Created by admin on 7/31/2016.
 */
public class NaiveQuickFindUF
{

    public static void main(String[] args){
        int N = 10;
        NaiveQuickFindUF uf = new NaiveQuickFindUF(N);
        uf.union(4,3);
        uf.union(3,8);
        uf.union(6,5);
        uf.union(9,4);
        System.out.println("8 connected to 9: " + uf.connected(8,9));
        System.out.println("9 connected to 5: " + uf.connected(9,5));
        System.out.println("Exting...");
    }

    private int[] ids;

    public NaiveQuickFindUF(int N){
        ids = new int[N];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = i;
        }
    }

    /**
     * connects p to q
     * @param p
     * @param q
     */
    public void union(int p, int q){
        int pid = ids[p];
        int qid = ids[q];
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == pid) ids[i] = qid;
        }
    }

    /**
     * Finds if p is connected to q
     * @param p
     * @param q
     * @return
     */
    public boolean connected(int p, int q){
        return ids[p] == ids[q];
    }
}

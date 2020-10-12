package search;

import java.util.LinkedList;

/**
 * @author fah
 * @Date 2020/10/12 16:41
 */
public class Kahn {

    /**
     * 顶点个数
     */
    private int v;

    /**
     * 邻接表
     */
    private LinkedList<Integer> adj[];

    public Kahn(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    /**
     * 添加边，s先于t，边s->t
     * @param s
     * @param t
     */
    public void addEdge(int s, int t) {
        adj[s].add(t);
    }

    public void topoSortByKahn() {
        int[] inDegree = new int[v];
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                int w = adj[i].get(j);
                inDegree[w]++;
            }
        }

        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int i = queue.remove();
            System.out.print("->" + i);
            for (int j = 0; j < adj[i].size(); j++) {
                int k = adj[i].get(j);
                inDegree[k]--;
                if (inDegree[k] == 0) {
                    queue.add(k);
                }
            }
        }
    }

    public static void main(String[] args) {
        Kahn kahn = new Kahn(5);
        kahn.addEdge(0, 1);
        kahn.addEdge(1, 2);
        kahn.addEdge(2, 3);
        kahn.addEdge(2, 4);
        kahn.topoSortByKahn();
    }
}

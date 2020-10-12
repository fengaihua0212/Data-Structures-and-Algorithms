package search;

import java.util.LinkedList;

/**
 * @author fah
 * @Date 2020/10/12 16:41
 */
public class DFS {

    /**
     * 顶点个数
     */
    private int v;

    /**
     * 邻接表
     */
    private LinkedList<Integer> adj[];

    public DFS(int v) {
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

    public void topoSortByDFS() {
        // 先构建逆邻接表，边s->t表示，s依赖于t，t先于s
        LinkedList<Integer> inverseAdj[] = new LinkedList[v];
        // 申请空间
        for (int i = 0; i < v; i++) {
            inverseAdj[i] = new LinkedList<>();
        }
        // 通过邻接表生成逆邻接表
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                // i->w
                int w = adj[i].get(j);
                // w->i
                inverseAdj[w].add(i);
            }
        }

        boolean[] visited = new boolean[v];

        // 深度优先遍历图
        for (int i = 0; i < v; i++) {
            if (visited[i] == false) {
                visited[i] = true;
                dfs(i, inverseAdj, visited);
            }
        }
    }

    /**
     * 深度优先遍历图
     * @param vertex
     * @param inverseAdj
     * @param visited
     */
    private void dfs(int vertex, LinkedList<Integer>[] inverseAdj, boolean[] visited) {
        for (int i = 0; i < inverseAdj[vertex].size(); i++) {
            int w = inverseAdj[vertex].get(i);
            if (visited[w] == true) {
                continue;
            }
            visited[w] = true;
            dfs(w, inverseAdj, visited);
        }
        // 先把vertex这个顶点可达的所有顶点都打印出来之后，再打印它自己
        System.out.print("->" + vertex);
    }

    public static void main(String[] args) {
        DFS dfs = new DFS(5);
        dfs.addEdge(0, 1);
        dfs.addEdge(1, 2);
        dfs.addEdge(2, 3);
        dfs.addEdge(2, 4);
        dfs.topoSortByDFS();
    }
}

import java.util.Arrays;
import java.util.Scanner;
public class MSTAlgorithms{
    static final int INF = 99999;
    // Prim's Algorithm
    static void primMST(int[][] graph, int V) {
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] mstSet = new boolean[V];
        Arrays.fill(key, INF);
        key[0] = 0;
        parent[0] = -1;
        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet, V);
            mstSet[u] = true;
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 &&
                        !mstSet[v] &&
                        graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }
        System.out.println("\nPrim's Minimum Spanning Tree:");
        int total = 0;
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i +
                    " : " + graph[i][parent[i]]);
            total += graph[i][parent[i]];
        }
        System.out.println("Total Cost = " + total);
    }
    static int minKey(int[] key, boolean[] mstSet, int V) {
        int min = INF;
        int minIndex = -1;
        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }
    // Kruskal's Algorithm
    static int find(int[] parent, int i) {
        while (parent[i] != i)
            i = parent[i];
        return i;
    }
    static void union(int[] parent, int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        parent[xset] = yset;
    }
static void kruskalMST(int[][] graph, int V) {
        int[] parent = new int[V];
        for (int i = 0; i < V; i++)
            parent[i] = i;
        System.out.println("\nKruskal's Minimum Spanning Tree:");
        int edgeCount = 0;
        int total = 0;
        while (edgeCount < V - 1) {
            int min = INF;
            int a = -1;
            int b = -1;
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (find(parent, i) != find(parent, j)
                            && graph[i][j] != 0
                            && graph[i][j] < min) {
                        min = graph[i][j];
                        a = i;
                        b = j;
                    }
                }
            }
            union(parent, a, b);
            System.out.println(a + " - " + b + " : " + min);
            total += min;
            edgeCount++;
        }
        System.out.println("Total Cost = " + total);
    }

 public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();
        int[][] graph = new int[V][V];
        System.out.println("Enter adjacency matrix:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
        primMST(graph, V);
        kruskalMST(graph, V);
        sc.close();
    }
}

//Program:
import java.util.*;
public class BellmanFord{
    static class Edge {
        int source;
        int destination;
        int weight;
        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
    static void bellmanFord(Edge[] edges, int vertices, int source) {
        int[] distance = new int[vertices];
        Arrays.fill(distance, 99999);
        distance[source] = 0;
        for (int i = 1; i <= vertices - 1; i++) {
            for (Edge edge : edges) {
                if (distance[edge.source] != 99999 &&
                    distance[edge.source] + edge.weight < distance[edge.destination]) {
                    distance[edge.destination] =
                        distance[edge.source] + edge.weight;
                }
            }
        }
        for (Edge edge : edges) {
            if (distance[edge.source] != 99999 &&
                distance[edge.source] + edge.weight < distance[edge.destination]) {
                System.out.println("Graph contains a negative weight cycle.");
                return;
            }
        }
        System.out.println("Shortest distances from vertex " + source + ":");
        for (int i = 0; i < vertices; i++) {
            System.out.println("To " + i + " = " + distance[i]);
        }
    }
    public static void main(String[] args) {
        int vertices = 5;
        Edge[] edges = {
            new Edge(0, 1, 4),
            new Edge(0, 2, 1),
            new Edge(2, 1, 2),
            new Edge(1, 3, 5),
            new Edge(2, 3, 8),
            new Edge(2, 4, 10),
            new Edge(3, 4, 2)
        };
        int source = 0;
        bellmanFord(edges, vertices, source);
    }
}
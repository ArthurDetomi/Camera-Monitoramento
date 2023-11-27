package api.datastructure.graph;

import java.util.Comparator;

public class VertexComparator implements Comparator<Vertex> {
    private Graph<Vertex, Edge> graph;

    public VertexComparator(Graph<Vertex, Edge> graph) {
        this.graph = graph;
    }

    @Override
    public int compare(Vertex v1, Vertex v2) {
        return Long.compare(graph.getNeighborsCount(v2), graph.getNeighborsCount(v1));
    }
}

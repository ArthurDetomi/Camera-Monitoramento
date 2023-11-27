package api.datastructure.graph;

import java.util.Objects;

public class Edge {

    private final Vertex source;

    private final Vertex destination;

    private Vertex vertexMonitoring;

    private String name;

    public Edge(Vertex source, Vertex destination) {
        this.source = source;
        this.destination = destination;
    }

    public Edge(Vertex source, Vertex destination, String name) {
        this.source = source;
        this.destination = destination;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(source, edge.source) && Objects.equals(destination, edge.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination);
    }

    public boolean isValid() {
        return getDestination() != null && getSource() != null;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getDestination() {
        return destination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vertex getVertexMonitoring() {
        return vertexMonitoring;
    }

    public void setVertexMonitoring(Vertex vertexMonitoring) {
        this.vertexMonitoring = vertexMonitoring;
    }
}

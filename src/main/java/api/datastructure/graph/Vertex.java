package api.datastructure.graph;

import java.util.Objects;

public class Vertex {

    private long id;

    private String label;

    private Graph<Vertex, Edge> graph;

    private boolean securityCamera;

    public Vertex(long id) {
        this.id = id;
    }

    public Vertex(Graph<Vertex, Edge> graph) {
        this.graph = graph;
    }

    public Vertex(long id, Graph<Vertex, Edge> graph) {
        this.id = id;
        this.graph = graph;
    }

    public Vertex(long id, String label, Graph<Vertex, Edge> graph) {
        this.id = id;
        this.label = label;
        this.graph = graph;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return id == vertex.id;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", securityCamera=" + securityCamera +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public long getNeighborsCount(Graph<Vertex, Edge> graph) {
        return graph.getNeighborsCount(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Graph<Vertex, Edge> getGraph() {
        return graph;
    }

    public void setGraph(Graph<Vertex, Edge> graph) {
        this.graph = graph;
    }

    public boolean hasSecurityCamera() {
        return securityCamera;
    }

    public void setSecurityCamera(boolean securityCamera) {
        this.securityCamera = securityCamera;
    }
}

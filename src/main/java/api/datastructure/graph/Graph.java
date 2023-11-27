package api.datastructure.graph;

import java.util.*;

public class Graph<K extends Vertex, V extends Edge> {
    private Map<K, Set<V>> elements = new HashMap<>();

    public Graph() {

    }

    public int getSize() {
        return elements.size();
    }

    public long getNeighborsCount(K vertex) {
        Set<V> vertexEdges = getEdges(vertex);

        if (vertexEdges == null) {
            return 0;
        }

        return vertexEdges.size();
    }

    public void addVertex(K vertex) {
        if (vertex == null) {
            throw new IllegalArgumentException("Tentativa de inserção de vértice nulo no grafo");
        }
        Set<V> edges = new HashSet<>();
        elements.put(vertex, edges);
    }

    public void addEdge(K origin, V edge) {
        Set<V> edges = getEdges(origin);

        if (origin == null || !edge.getSource().equals(origin) || edge == null) {
            return;
        }

        Edge tempEdge = new Edge(edge.getDestination(), edge.getSource());
        tempEdge.setName(edge.getName());

        Set<V> neighborEdges = getEdges((K) edge.getDestination());
        if (neighborEdges == null) {
            neighborEdges = new HashSet<>();
        }
        neighborEdges.add((V) tempEdge);

        edges.add(edge);
    }

    public Set<V> getEdges(K vertex) {
        return elements.get(vertex);
    }

    public void removeVertex(K vertex) {
        Set<V> vertexEdges = getEdges(vertex);

        vertexEdges.forEach(currentEdge -> {
            K neighbor = (K) currentEdge.getDestination();
            Set<V> neighborEdges = getEdges(neighbor);

            // Cria uma nova lista para evitar ConcurrentModificationException
            List<V> neighborEdgesList = new ArrayList<>(neighborEdges);
            List<V> edgesToRemove = new ArrayList<>();

            for (V edge : neighborEdgesList) {
                if (edge.getDestination().equals(vertex)) {
                    edgesToRemove.add(edge);
                }
            }

            neighborEdges.removeAll(edgesToRemove);
        });

        elements.remove(vertex);
    }


    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public boolean isPresentVertex(K vertex) {
        return elements.get(vertex) != null;
    }

    public Set<K> getAllVertexes() {
        return elements.keySet();
    }

    public K findVertexById(long id) {
        for (K currentVertex : elements.keySet()) {
            if (currentVertex.getId() == id) {
                return currentVertex;
            }
        }
        return null;
    }

    public Graph<K, V> copy() {
        Graph<K, V> copy = new Graph<>();

        for (K vertex : elements.keySet()) {
            copy.addVertex(vertex);

            for (V edge : getEdges(vertex)) {
                copy.addEdge(vertex, edge);
            }
        }

        return copy;
    }

}

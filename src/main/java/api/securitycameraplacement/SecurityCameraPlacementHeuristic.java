package api.securitycameraplacement;

import api.datastructure.graph.Edge;
import api.datastructure.graph.Graph;
import api.datastructure.graph.Vertex;
import api.datastructure.graph.VertexComparator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SecurityCameraPlacementHeuristic {

    public final Graph<Vertex, Edge> graph;

    public SecurityCameraPlacementHeuristic(Graph<Vertex, Edge> graph) {
        this.graph = graph;
    }

    public void greedyAlgorithmSolution() {
        Graph<Vertex, Edge> residualGraph = graph.copy();
        Set<Long> markedIds = new HashSet<>();


        for (int i = 0; i < graph.getSize(); i++) {
            List<Vertex> vertexs = new ArrayList<>(residualGraph.getAllVertexes());
            vertexs.sort(new VertexComparator(residualGraph));

            Vertex firstToRemove = residualGraph.findVertexById(vertexs.get(0).getId());

            if (residualGraph.getNeighborsCount(firstToRemove) == 0) {
                break;
            }

            Vertex monitoredVertex = graph.findVertexById(vertexs.get(0).getId());
            monitoredVertex.setSecurityCamera(true);
            Set<Edge> allEdgesFromMonitored = graph.getEdges(monitoredVertex);
            for (Edge edge : allEdgesFromMonitored) {
                if (edge.isValid() && !markedIds.contains(edge.getDestination().getId())) {
                    edge.setVertexMonitoring(monitoredVertex);
                    markedIds.add(edge.getSource().getId());
                }
            }

            residualGraph.removeVertex(firstToRemove);
        }
    }

    public void getDisplayInformation() {
        Integer camsPositionedCount = 0;
        Integer monitoringStreetCount = 0;
        System.out.println("Vertices com cameras posicionadas");

        Set<Vertex> allVertexes = graph.getAllVertexes();
        for (Vertex currentVertex : allVertexes) {
            if (currentVertex.hasSecurityCamera()) {
                System.out.printf("Vertice id = %d, label = %s\n", currentVertex.getId(), currentVertex.getLabel());
                System.out.println("Monitorando ruas:");
                Set<Edge> vertexEdges = graph.getEdges(currentVertex);
                for (Edge currentEdge : vertexEdges) {
                    if (currentEdge.getVertexMonitoring() != null &&
                            currentEdge.getVertexMonitoring().equals(currentVertex) &&
                            currentEdge.getSource().equals(currentVertex)) {
                        System.out.printf("\tRua:%s\n", currentEdge.getName());
                        monitoringStreetCount++;
                    }
                }
                camsPositionedCount++;
            }
        }
        System.out.println("Total de cameras posicionadas = " + camsPositionedCount);
        System.out.println("Total de ruas monitoradas = " + monitoringStreetCount);
    }

}

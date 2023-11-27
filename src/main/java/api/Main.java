package api;

import api.datastructure.graph.Edge;
import api.datastructure.graph.Graph;
import api.datastructure.graph.Vertex;
import api.datastructure.graph.record.ReadTextFile;
import api.securitycameraplacement.SecurityCameraPlacementHeuristic;

public class Main {

    public static void main(String[] args) {
        ReadTextFile readTextFile = new ReadTextFile("src/main/java/input/sjdr.gml");

        Graph<Vertex, Edge> graph = readTextFile.getReadedGraph();

        SecurityCameraPlacementHeuristic securityPlacement = new SecurityCameraPlacementHeuristic(graph);

        securityPlacement.greedyAlgorithmSolution();

        securityPlacement.getDisplayInformation();
    }
}

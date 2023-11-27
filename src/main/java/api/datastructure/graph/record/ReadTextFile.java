package api.datastructure.graph.record;

import api.datastructure.graph.Edge;
import api.datastructure.graph.Graph;
import api.datastructure.graph.Vertex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadTextFile {

    private Scanner input;

    public ReadTextFile(String filePath) {
        try {
            input = new Scanner(new File(filePath));
        } catch (FileNotFoundException fex) {
            System.out.println("Arquivo enviado não existe");
            System.exit(1);
        }
    }

    public Graph<Vertex, Edge> getReadedGraph() {
        Graph<Vertex, Edge> graph = new Graph<>();

        Pattern patternVertex = Pattern.compile("id (\\d+)\\s+label \"(\\d+)\"");
        Pattern patternEdge = Pattern.compile("source (\\d+)\\s+target (\\d+)\\s+name \"(.+)\"");

        try {
            while (input.hasNext()) {
                String currentLine = input.nextLine();

                if (currentLine.contains("node")) {
                    String nextLine = input.nextLine() + input.nextLine();

                    Matcher matcher = patternVertex.matcher(nextLine);

                    if (matcher.find()) {
                        Vertex currentVertex = new Vertex(graph);

                        long id = Long.parseLong(matcher.group(1));

                        currentVertex.setId(id);

                        currentVertex.setLabel(matcher.group(2));

                        graph.addVertex(currentVertex);
                    }
                } else if (currentLine.contains("edge")) {
                    String nextLine = input.nextLine() + input.nextLine() + input.nextLine();

                    Matcher matcher = patternEdge.matcher(nextLine);

                    if (matcher.find()) {
                        long sourceVertexId = Long.parseLong(matcher.group(1));
                        long targetVertexId = Long.parseLong(matcher.group(2));

                        Vertex sourceVertex = graph.findVertexById(sourceVertexId);
                        Vertex targetVertex = graph.findVertexById(targetVertexId);
                        if (sourceVertex == null || targetVertex == null) {
                            System.out.println("Aresta informada invalida:");
                            System.out.println("Linha:");
                            System.out.println(nextLine);
                            System.exit(1);
                        }

                        Edge edge = new Edge(sourceVertex, targetVertex);

                        edge.setName(matcher.group(3));

                        graph.addEdge(sourceVertex, edge);
                    }
                }
            }
        } catch (NoSuchElementException nex) {
            System.out.println("Arquivo em formato impróprio");
            System.exit(1);
        } catch (IllegalArgumentException iex) {
            System.out.println("Erro durante leitura do arquivo");
            System.exit(1);
        }

        return graph;
    }

    public void closeFile() {
        if (input != null) {
            input.close();
        }
    }

}

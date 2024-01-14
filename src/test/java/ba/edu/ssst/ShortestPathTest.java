package ba.edu.ssst;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class ShortestPathTest extends TestCase {

    public void testIfThereIsAPathToDestinationNode() {
        List<Constraints> constraintsList = new ArrayList<>();
        constraintsList.add(new Constraints("A", "B", 0.0));

        Graph graph = new Graph();
        graph.addEdge("A", "B", 10, constraintsList);
        graph.addEdge("B", "C", 15, constraintsList);
        graph.addEdge("E", "D", 20, constraintsList);

        assertEquals(-1, ShortestPath.dijkstra(graph, "A", "D"));
        assertEquals(10, ShortestPath.dijkstra(graph, "A", "B"));
        assertEquals(15, ShortestPath.dijkstra(graph, "B", "C"));
    }

    public void testIfAllNodesAreConnected(){
        List<Constraints> constraintsList = new ArrayList<>();
        constraintsList.add(new Constraints("A", "B", 0.0));

        Graph graph = new Graph();
        graph.addEdge("A", "B", 10, constraintsList);
        graph.addEdge("B", "C", 15, constraintsList);
        graph.addEdge("E", "D", 20, constraintsList);
        graph.addEdge("A", "D", 20, constraintsList);
        graph.addEdge("E", "A", 20, constraintsList);
        graph.addEdge("B", "E", 20, constraintsList);
        graph.addEdge("C", "D", 20, constraintsList);
        graph.addEdge("D", "E", 20, constraintsList);

        assertEquals(10, ShortestPath.dijkstra(graph, "A", "B"));
        assertEquals(25, ShortestPath.dijkstra(graph, "A", "C"));
        assertEquals(20, ShortestPath.dijkstra(graph, "A", "D"));
        assertEquals(30, ShortestPath.dijkstra(graph, "A", "E"));
        assertEquals(40, ShortestPath.dijkstra(graph, "B", "A"));
        assertEquals(15, ShortestPath.dijkstra(graph, "B", "C"));
        assertEquals(35, ShortestPath.dijkstra(graph, "B", "D"));
        assertEquals(20, ShortestPath.dijkstra(graph, "B", "E"));
        assertEquals(60, ShortestPath.dijkstra(graph, "C", "A"));
        assertEquals(70, ShortestPath.dijkstra(graph, "C", "B"));
        assertEquals(20, ShortestPath.dijkstra(graph, "C", "D"));
        assertEquals(40, ShortestPath.dijkstra(graph, "C", "E"));
        assertEquals(40, ShortestPath.dijkstra(graph, "D", "A"));
        assertEquals(50, ShortestPath.dijkstra(graph, "D", "B"));
        assertEquals(65, ShortestPath.dijkstra(graph, "D", "C"));
        assertEquals(20, ShortestPath.dijkstra(graph, "D", "E"));
        assertEquals(20, ShortestPath.dijkstra(graph, "E", "A"));
        assertEquals(30, ShortestPath.dijkstra(graph, "E", "B"));
        assertEquals(45, ShortestPath.dijkstra(graph, "E", "C"));
        assertEquals(20, ShortestPath.dijkstra(graph, "E", "D"));
    }

    public void testInvalidSourceNode() {
        List<Constraints> constraintsList = new ArrayList<>();
        constraintsList.add(new Constraints("A", "B", 0.0));

        Graph graph = new Graph();
        graph.addEdge("A", "B", 10, constraintsList);
        graph.addEdge("B", "C", 15, constraintsList);
        graph.addEdge("E", "D", 20, constraintsList);

        assertEquals(-1, ShortestPath.dijkstra(graph, "F", "D"));
    }

    public void testInvalidDestinationNode() {
        List<Constraints> constraintsList = new ArrayList<>();
        constraintsList.add(new Constraints("A", "B", 0.0));

        Graph graph = new Graph();
        graph.addEdge("A", "B", 10, constraintsList);
        graph.addEdge("B", "C", 15, constraintsList);
        graph.addEdge("E", "D", 20, constraintsList);

        assertEquals(-1, ShortestPath.dijkstra(graph, "A", "F"));
    }

    public void testWithNegativeTime() {
        List<Constraints> constraintsList = new ArrayList<>();
        constraintsList.add(new Constraints("A", "B", 0.0));

        Graph graph = new Graph();
        graph.addEdge("A", "B", -10, constraintsList);
        graph.addEdge("B", "C", 15, constraintsList);
        graph.addEdge("C", "D", 20, constraintsList);

        assertEquals(-1, ShortestPath.dijkstra(graph, "A", "D"));
    }

    public void testGraphWithSelfLoop() {
        List<Constraints> constraintsList = new ArrayList<>();
        constraintsList.add(new Constraints("A", "B", 0.0));

        Graph graph = new Graph();
        graph.addEdge("A", "B", 10, constraintsList);
        graph.addEdge("B", "C", 15, constraintsList);
        graph.addEdge("C", "D", 20, constraintsList);
        graph.addEdge("D", "D", -5, constraintsList);

        assertEquals(45, ShortestPath.dijkstra(graph, "A", "D"));
        assertEquals(0, ShortestPath.dijkstra(graph, "D", "D"));
    }

    public void testWithCertainProbability() {
        List<Constraints> constraintsList = new ArrayList<>();
        constraintsList.add(new Constraints("A", "B", 1.0));
        constraintsList.add(new Constraints("B", "D", 1.0));
        constraintsList.add(new Constraints("B", "M", 1.0));
        constraintsList.add(new Constraints("C", "I", 1.0));
        constraintsList.add(new Constraints("G", "I", 1.0));

        Graph graph = new Graph();
        graph.addEdge("A", "B", 10, constraintsList);
        graph.addEdge("B", "C", 15, constraintsList);
        graph.addEdge("B", "D", 20, constraintsList);
        graph.addEdge("C", "I", 20, constraintsList);

        assertEquals(-1, ShortestPath.dijkstra(graph, "A", "B"));
        assertEquals(15, ShortestPath.dijkstra(graph, "B", "C"));
        assertEquals(-1, ShortestPath.dijkstra(graph, "B", "D"));
        assertEquals(-1, ShortestPath.dijkstra(graph, "C", "I"));
        assertEquals(-1, ShortestPath.dijkstra(graph, "A", "C"));
    }

    public void testWithNoConstraints() {
        List<Constraints> constraintsList = new ArrayList<>();
        constraintsList.add(new Constraints("A", "B", 0.0));
        constraintsList.add(new Constraints("B", "D", 0.0));
        constraintsList.add(new Constraints("B", "M", 0.0));

        Graph graph = new Graph();
        graph.addEdge("A", "B", 10, constraintsList);
        graph.addEdge("B", "C", 15, constraintsList);
        graph.addEdge("B", "D", 20, constraintsList);
        graph.addEdge("C", "I", 20, constraintsList);

        assertEquals(10, ShortestPath.dijkstra(graph, "A", "B"));
        assertEquals(15, ShortestPath.dijkstra(graph, "B", "C"));
        assertEquals(20, ShortestPath.dijkstra(graph, "B", "D"));
        assertEquals(20, ShortestPath.dijkstra(graph, "C", "I"));
    }
}

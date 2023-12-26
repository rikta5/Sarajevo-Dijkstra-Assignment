package ba.edu.ssst;

import junit.framework.TestCase;
import java.util.List;

public class ShortestPathTest extends TestCase {

    public void testIfThereIsAPathToDestinationNode() {
        fillPlaces();
        List<Constraints> constraintsList = getConstraints();
        Graph graph = new Graph();
        graph.addEdge("A", "B", 10, constraintsList);
        graph.addEdge("B", "C", 15, constraintsList);
        graph.addEdge("E", "D", 20, constraintsList);

        assertEquals(-1, ShortestPath.dijkstra(graph, "A", "D"));
        assertEquals(30, ShortestPath.dijkstra(graph, "A", "B")); // will be 30 because of the constraint
        assertEquals(15, ShortestPath.dijkstra(graph, "B", "C"));
    }

    public void testIfAllNodesAreConnected(){
        fillPlaces();
        List<Constraints> constraintsList = getConstraints();
        Graph graph = new Graph();
        graph.addEdge("A", "B", 10, constraintsList);
        graph.addEdge("B", "C", 15, constraintsList);
        graph.addEdge("E", "D", 20, constraintsList);
        graph.addEdge("A", "D", 20, constraintsList);
        graph.addEdge("E", "A", 20, constraintsList);
        //graph.addEdge("B", "D", 20, constraintsList);
        graph.addEdge("B", "E", 20, constraintsList);
        graph.addEdge("C", "D", 20, constraintsList);
        //graph.addEdge("C", "E", 20, constraintsList);
        graph.addEdge("D", "E", 20, constraintsList);

        assertEquals(30, ShortestPath.dijkstra(graph, "A", "B"));
        assertEquals(45, ShortestPath.dijkstra(graph, "A", "C"));
        assertEquals(20, ShortestPath.dijkstra(graph, "A", "D"));
        assertEquals(40, ShortestPath.dijkstra(graph, "A", "E"));
        assertEquals(40, ShortestPath.dijkstra(graph, "B", "A"));
        assertEquals(15, ShortestPath.dijkstra(graph, "B", "C"));
        assertEquals(35, ShortestPath.dijkstra(graph, "B", "D"));
        assertEquals(20, ShortestPath.dijkstra(graph, "B", "E"));
        assertEquals(60, ShortestPath.dijkstra(graph, "C", "A"));
        assertEquals(90, ShortestPath.dijkstra(graph, "C", "B"));
        assertEquals(20, ShortestPath.dijkstra(graph, "C", "D"));
        assertEquals(40, ShortestPath.dijkstra(graph, "C", "E"));
        assertEquals(40, ShortestPath.dijkstra(graph, "D", "A"));
        assertEquals(70, ShortestPath.dijkstra(graph, "D", "B"));
        assertEquals(85, ShortestPath.dijkstra(graph, "D", "C"));
        assertEquals(20, ShortestPath.dijkstra(graph, "D", "E"));
        assertEquals(20, ShortestPath.dijkstra(graph, "E", "A"));
        assertEquals(50, ShortestPath.dijkstra(graph, "E", "B"));
        assertEquals(65, ShortestPath.dijkstra(graph, "E", "C"));
        assertEquals(20, ShortestPath.dijkstra(graph, "E", "D"));
    }

    public void testInvalidSourceNode() {
        fillPlaces();
        List<Constraints> constraintsList = getConstraints();
        Graph graph = new Graph();
        graph.addEdge("A", "B", 10, constraintsList);
        graph.addEdge("B", "C", 15, constraintsList);
        graph.addEdge("E", "D", 20, constraintsList);

        assertEquals(-1, ShortestPath.dijkstra(graph, "F", "D"));
    }

    public void testInvalidDestinationNode() {
        fillPlaces();
        List<Constraints> constraintsList = getConstraints();
        Graph graph = new Graph();
        graph.addEdge("A", "B", 10, constraintsList);
        graph.addEdge("B", "C", 15, constraintsList);
        graph.addEdge("E", "D", 20, constraintsList);

        assertEquals(-1, ShortestPath.dijkstra(graph, "A", "F"));
    }

    public void testWithNegativeTime() {
        fillPlaces();
        List<Constraints> constraintsList = getConstraints();
        Graph graph = new Graph();
        graph.addEdge("A", "B", -10, constraintsList);
        graph.addEdge("B", "C", 15, constraintsList);
        graph.addEdge("C", "D", 20, constraintsList);

        assertEquals(-1, ShortestPath.dijkstra(graph, "A", "D"));
    }

    public void testGraphWithSelfLoop() {
        fillPlaces();
        List<Constraints> constraintsList = getConstraints();
        Graph graph = new Graph();
        graph.addEdge("A", "B", 10, constraintsList);
        graph.addEdge("B", "C", 15, constraintsList);
        graph.addEdge("C", "D", 20, constraintsList);
        graph.addEdge("D", "D", -5, constraintsList);

        assertEquals(65, ShortestPath.dijkstra(graph, "A", "D"));
        assertEquals(0, ShortestPath.dijkstra(graph, "D", "D"));
    }

    private void fillPlaces() {
        Main.fillPlacesClass();
    }

    private List<Constraints> getConstraints() {
        return Main.readConstraints();
    }

}
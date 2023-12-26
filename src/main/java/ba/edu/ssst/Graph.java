package ba.edu.ssst;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private final Map<String, Node> nodesMap;

    public Graph() {
        this.nodesMap = new HashMap<>();
    }

    public void addNode(String nodeName) {
        nodesMap.putIfAbsent(nodeName, new Node(nodeName));
    }

    public void addEdge(String source, String destination, int timeNeededToTravel, List<Constraints> constraintsList) {
        addNode(source);
        addNode(destination);

        Node sourceNode = nodesMap.get(source);
        Node destinationNode = nodesMap.get(destination);

        Constraints constraint = findConstraint(source, destination, constraintsList);

        sourceNode.addNeighbor(destinationNode, timeNeededToTravel, constraint);
    }
    private Constraints findConstraint(String source, String destination, List<Constraints> constraintsList) {
        for (Constraints constraint : constraintsList) {
            if (constraint.getPlaceA().equals(source) && constraint.getPlaceB().equals(destination)) {
                return constraint;
            }
        }

        return new Constraints(source, destination, "none", 0.0);
    }

    public Map<String, Node> getNodesMap() {
        return nodesMap;
    }

    public Node getNode(String nodeName) {
        return nodesMap.get(nodeName);
    }
}

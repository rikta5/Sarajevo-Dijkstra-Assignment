package ba.edu.ssst;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

        int modifiedTime = checkConstraint(source, destination, constraintsList);
        if(modifiedTime == 0) {
            sourceNode.addNeighbor(destinationNode, timeNeededToTravel);
            return;
        }
        sourceNode.addNeighbor(destinationNode, modifiedTime);
    }
    private Integer checkConstraint(String source, String destination, List<Constraints> constraintsList) {
        for(Constraints constraint : constraintsList) {
            if(constraint.getPlaceA().equals(source) && constraint.getPlaceB().equals(destination)) {
                double probability = constraint.getProbability();
                if (probability == 0.0) {
                    return 0;
                }
                Random random = new Random();
                double randomDouble = random.nextDouble();
                randomDouble = Math.round(randomDouble * 100.0) / 100.0;
                if (randomDouble <= probability) {
                    return -1;
                }
            }
        }
        return 0;
    }

    public Map<String, Node> getNodesMap() {
        return nodesMap;
    }

    public Node getNode(String nodeName) {
        return nodesMap.get(nodeName);
    }
}

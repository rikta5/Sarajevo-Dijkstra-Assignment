package ba.edu.ssst;

import java.util.HashMap;
import java.util.Map;

public class Node {
    private final String nodeName;
    private final Map<Node, Integer> neighborsMap;

    public Node(String name) {
        this.nodeName = name;
        this.neighborsMap = new HashMap<>();
    }

    public void addNeighbor(Node neighbor, int timeNeededToTravel) {
        neighborsMap.put(neighbor, timeNeededToTravel);
    }

    public Map<Node, Integer> getNeighborsMap() {
        return neighborsMap;
    }

    @Override
    public String toString() {
        return nodeName;
    }
}

package ba.edu.ssst;

public class NodeDistancePair {
    private final Node node;
    private final int distance;

    public NodeDistancePair(Node node, int distance) {
        this.node = node;
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public Node getNode() {
        return node;
    }
}

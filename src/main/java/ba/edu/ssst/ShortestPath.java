package ba.edu.ssst;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ShortestPath {
    public static int dijkstra(Graph graph, String startNode, String endNode) {
        Map<String, Integer> distances = new HashMap<>();
        PriorityQueue<NodeDistancePair> minHeap = new PriorityQueue<>(Comparator.comparingInt(NodeDistancePair::getDistance));

        distances.put(startNode, 0);
        minHeap.offer(new NodeDistancePair(graph.getNode(startNode), 0));

        while (!minHeap.isEmpty()) {
            NodeDistancePair current = minHeap.poll();
            Node currentNode = current.getNode();
            int currentDistance = current.getDistance();

            if (currentNode != null) { // In case the node does not exist in the graph

                if (currentNode.toString().equals(endNode)) {
                    return currentDistance; // Found the shortest path to the end node
                }

                for (Map.Entry<Node, Integer> neighborEntry : currentNode.getNeighborsMap().entrySet()) {
                    Node neighborNode = neighborEntry.getKey();
                    int distanceToNeighbor = neighborEntry.getValue();

                    if (distanceToNeighbor < 0) { // Check if distance is negative, i.e. invalid distance
                        continue; // Skip this neighbor and try finding a different path
                    }
                    int newDistance = currentDistance + neighborEntry.getValue();

                    if (!distances.containsKey(neighborNode.toString()) || newDistance < distances.get(neighborNode.toString())) {
                        distances.put(neighborNode.toString(), newDistance);
                        minHeap.offer(new NodeDistancePair(neighborNode, newDistance));
                    }
                }
            }
        }
        return -1; // No path found between the start and end node
    }
}
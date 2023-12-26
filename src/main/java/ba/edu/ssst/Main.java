package ba.edu.ssst;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        fillPlacesClass();
        List<Constraints> constraintsList = readConstraints();

        Graph graph1 = createGraph("src/simple.txt", constraintsList);
        Graph graph2 = createGraph("src/five_places.txt", constraintsList);
        Graph graph3 = createGraph("src/complex.txt", constraintsList);
        Graph graph4 = createGraph("src/ten_places.txt", constraintsList);
        Graph graph5 = createGraph("src/all_places_a.txt", constraintsList);
        Graph graph6 = createGraph("src/all_places_b.txt", constraintsList);

        writeOptimalTravelTimes(graph1, "Simple");
        writeOptimalTravelTimes(graph2, "FivePlaces");
        writeOptimalTravelTimes(graph3, "Complex");
        writeOptimalTravelTimes(graph4, "TenPlaces");
        writeOptimalTravelTimes(graph5, "AllPlacesA");
        writeOptimalTravelTimes(graph6, "AllPlacesB");

    }

    public static void fillPlacesClass() {
        try {
            Scanner scanner = new Scanner(new File("src/places.txt"));
            if (scanner.hasNextLine()) scanner.nextLine();
            while (scanner.hasNext()) {
                String[] parts = scanner.nextLine().split(",");
                String shortcode = parts[0];
                String name = parts[1].trim();

                Places.addPlace(shortcode, name);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Graph createGraph(String filePath, List<Constraints> constraintsList) {
        Graph graph = new Graph();

        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNext()) {
                String[] parts = scanner.nextLine().split(" ");
                String source = parts[0];
                String destination = parts[1];
                int timeNeededToTravel = Integer.parseInt(parts[2]);

                graph.addEdge(source, destination, timeNeededToTravel, constraintsList);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return graph;
    }

    public static List<Constraints> readConstraints() {
        List<Constraints> constraintsList = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File("src/constraints.txt"));
            if (scanner.hasNextLine()) scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                String placeA = parts[0];
                String placeB = parts[1];
                String constraint = parts[2];
                Double probability = Double.parseDouble(parts[3]);

                constraintsList.add(new Constraints(placeA, placeB, constraint, probability));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return constraintsList;
    }

    private static void writeOptimalTravelTimes(Graph graph, String outputFile) throws IOException {
        FileWriter fileWriter = new FileWriter("OptimalTravelTimesFor" + outputFile + "File.txt");

        for (String sourceNodeShortcode : graph.getNodesMap().keySet()) {
            String sourceNodeName = Places.getPlaceName(sourceNodeShortcode);

            if (!"Unknown".equals(sourceNodeName)) {
                for (String destinationNodeShortcode : graph.getNodesMap().keySet()) {
                    if (!sourceNodeShortcode.equals(destinationNodeShortcode)) {
                        String destinationNodeName = Places.getPlaceName(destinationNodeShortcode);

                        if (!"Unknown".equals(destinationNodeName)) {
                            int optimalTravelTime = ShortestPath.dijkstra(graph, sourceNodeShortcode, destinationNodeShortcode);
                            fileWriter.write(sourceNodeName + " -> " + destinationNodeName + ": " + optimalTravelTime + " seconds \n");
                        }
                    }
                }
            }
        }
        fileWriter.close();
    }
}
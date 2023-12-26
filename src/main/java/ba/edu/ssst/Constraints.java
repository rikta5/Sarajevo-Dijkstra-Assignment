package ba.edu.ssst;

import java.util.Objects;

public class Constraints {
    private final String placeA;
    private final String placeB;
    private final String constraint;
    private final Double probability;

    public Constraints(String placeA, String placeB, String constraint, Double probability) {
        this.placeA = placeA;
        this.placeB = placeB;
        this.constraint = constraint;
        this.probability = probability;
    }

    public String getPlaceA() {
        return placeA;
    }

    public String getPlaceB() {
        return placeB;
    }

    public double getDelay() {
        if(Objects.equals(constraint, "medics helicopter")){
            return 100 * probability;
        }
        if (Objects.equals(constraint, "foggy")){
            return 100 * probability;
        }
        if (Objects.equals(constraint, "high constructions")){
            return 100 * probability;
        }
        return 0.0;
    }
}

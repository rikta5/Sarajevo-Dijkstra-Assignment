package ba.edu.ssst;

import java.util.HashMap;
import java.util.Map;

public class Places {
    private static final Map<String, String> placeNamesMap = new HashMap<>();

    public static void addPlace(String shortcode, String name) {
        placeNamesMap.put(shortcode, name);
    }

    public static String getPlaceName(String shortcode) {
        return placeNamesMap.getOrDefault(shortcode, shortcode);
    }

}

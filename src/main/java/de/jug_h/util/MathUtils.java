package de.jug_h.util;

public class MathUtils {

    public static double clamp(double value, double min, double max) {
        return Math.min(Math.max(value, min), max);
    }

}

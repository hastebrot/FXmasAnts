package de.jug_h.util;

import javafx.geometry.Point2D;

public class MathUtils {

    public static double clamp(double value, double min, double max) {
        return Math.min(Math.max(value, min), max);
    }

    public static Point2D rotate(double angle) {
        double theta = Math.toRadians(angle);
        return new Point2D(Math.cos(theta), Math.sin(theta));
    }

}

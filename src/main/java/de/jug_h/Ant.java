package de.jug_h;

import java.util.HashMap;
import java.util.Map;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;

public class Ant {

    //---------------------------------------------------------------------------------------------
    // FIELDS.
    //---------------------------------------------------------------------------------------------

    public final int id;

    private final Map<String, Object> memory = new HashMap<>();

    public final DoubleProperty angleProperty = new SimpleDoubleProperty();
    public final DoubleProperty xProperty = new SimpleDoubleProperty();
    public final DoubleProperty yProperty = new SimpleDoubleProperty();
    public final DoubleProperty distanceProperty = new SimpleDoubleProperty();

    //---------------------------------------------------------------------------------------------
    // CONSTRUCTORS.
    //---------------------------------------------------------------------------------------------

    public Ant(int id) {
        this.id = id;
    }

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    public double getX() { return xProperty.get(); }
    public double getY() { return yProperty.get(); }

    @SuppressWarnings("unchecked")
    public <T> T memory(String key) {
        return (T) memory.get(key);
    }

    public <T> void memory(String key, T value) {
        memory.put(key, value);
    }

    public void move(double distance) {
        distanceProperty.set(distance);
    }

    public void tick() {
        distanceProperty.set(distanceProperty.get() - 1);

        Point2D position = new Point2D(getX(), getY());
        double theta = Math.toRadians(angleProperty.get() - 90);
        Point2D vector = new Point2D(Math.cos(theta), Math.sin(theta));

        Point2D newPosition = position.add(vector);
        xProperty.set(MathUtils.clamp(newPosition.getX(), 0, 500 - 25 - 40));
        yProperty.set(MathUtils.clamp(newPosition.getY(), 0, 500 - 25 - 40));
    }

    public void setAngle(double angle) {
        angleProperty.set(angle);
    }
    public double getAngle() {
        return angleProperty.get();
    }

}

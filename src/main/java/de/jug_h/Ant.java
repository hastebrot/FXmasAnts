package de.jug_h;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;

public class Ant {

    public final DoubleProperty angleProperty = new SimpleDoubleProperty();
    public final DoubleProperty xProperty = new SimpleDoubleProperty();
    public final DoubleProperty yProperty = new SimpleDoubleProperty();
    public final DoubleProperty distanceProperty = new SimpleDoubleProperty();

    public void move(double distance) {
        distanceProperty.set(distance);
    }

    public void tick() {
        distanceProperty.set(distanceProperty.get() - 1);

        Point2D position = new Point2D(getX(), getY());
        double theta = Math.toRadians(angleProperty.get() - 90);
        Point2D vector = new Point2D(Math.cos(theta), Math.sin(theta));

        Point2D newPosition = position.add(vector);
        xProperty.set(newPosition.getX());
        yProperty.set(newPosition.getY());
    }

    public void setAngle(double angle) {
        angleProperty.set(angle);
    }
    public double getAngle() {
        return angleProperty.get();
    }

    public double getX() { return xProperty.get(); }
    public double getY() { return yProperty.get(); }

}

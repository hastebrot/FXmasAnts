package de.jug_h.entity;

import javafx.geometry.Point2D;

import de.jug_h.util.MathUtils;

public class Behavior {

    //---------------------------------------------------------------------------------------------
    // PRIVATE FIELDS.
    //---------------------------------------------------------------------------------------------

    private Sprite sprite;

    private boolean moves = false;

    //---------------------------------------------------------------------------------------------
    // CONSTRUCTORS.
    //---------------------------------------------------------------------------------------------

    public Behavior(Sprite sprite) {
        this.sprite = sprite;
    }

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    public Point2D position() {
        return new Point2D(sprite.xProperty().get(), sprite.yProperty().get());
    }

    public double angle() {
        return sprite.angleProperty().get();
    }

    public void turnTo(double angle) {
        sprite.angleProperty().set(angle);
    }

    public void move() {
        moves = true;
    }

    public void stop() {
        moves = false;
    }

    public void internalTick() {
        if (moves) {
            Point2D position = position();
            double angle = angle();

            double theta = Math.toRadians(angle - 90);
            Point2D vector = new Point2D(Math.cos(theta), Math.sin(theta));

            Point2D newPosition = position.add(vector);
            sprite.xProperty().set(MathUtils.clamp(newPosition.getX(), 0, 500 - 25 - 40));
            sprite.yProperty().set(MathUtils.clamp(newPosition.getY(), 0, 500 - 25 - 40));
        }
    }

}

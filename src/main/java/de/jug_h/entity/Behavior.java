package de.jug_h.entity;

import java.util.List;
import javafx.geometry.Point2D;

public class Behavior {

    //---------------------------------------------------------------------------------------------
    // PRIVATE FIELDS.
    //---------------------------------------------------------------------------------------------

    private Sprite sprite;

    private boolean moves = false;

    private double visionRange = 0.0;

    private double actionRange = 0.0;

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
        return sprite.angleProperty().get() % 360.0;
    }

    public void turnTo(double angle) {
        sprite.angleProperty().set(angle);
    }

    public void turnBy(double angle) {
        turnTo(angle() + angle);
    }

    public void moveWalk() {
        moves = true;
    }

    public void moveStop() {
        moves = false;
    }

    public boolean moves() {
        return moves;
    }

    public void transportCarry(Entity entity) {}
    public void transportDrop() {}

    public void fightAttack(Entity entity) {}
    public void fightFlee() {}

    public List<Entity> visionLook() { return null; }
    public void visionMark() {}

}

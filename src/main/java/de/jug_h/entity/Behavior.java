package de.jug_h.entity;

import java.util.List;
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

    public void transportCarry(Entity entity) {}
    public void transportDrop() {}

    public void fightAttack(Entity entity) {}
    public void fightFlee() {}

    public List<Entity> visionLook() { return null; }
    public void visionMark() {}

    public void internalTick() {
        if (moves) {
            Point2D position = position();
            double angle = angle();

            Point2D direction = MathUtils.rotate(angle - 90);
            Point2D newPosition = position.add(direction);

            sprite.xProperty().set(MathUtils.clamp(newPosition.getX(), 0, 500 - 25 - 40));
            sprite.yProperty().set(MathUtils.clamp(newPosition.getY(), 0, 500 - 25 - 40));
        }
    }

}

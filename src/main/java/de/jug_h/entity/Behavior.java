package de.jug_h.entity;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javafx.geometry.Point2D;

public class Behavior {

    //---------------------------------------------------------------------------------------------
    // PRIVATE FIELDS.
    //---------------------------------------------------------------------------------------------

    private Sprite sprite;

    private List<Entity> entities;

    private boolean walks = false;

    private double visionRange = 50.0;

    private double actionRange = 0.0;

    //---------------------------------------------------------------------------------------------
    // CONSTRUCTORS.
    //---------------------------------------------------------------------------------------------

    public Behavior(Sprite sprite,
                    List<Entity> entities) {
        this.sprite = sprite;
        this.entities = entities;
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

    public boolean walks() {
        return walks;
    }

    // MOVEMENT.

    public void turnAt(double angle) {
        sprite.angleProperty().set(angle);
    }

    public void turnBy(double angle) {
        turnAt(angle() + angle);
    }

    public void turnTo(Entity target) {
        Point2D targetPosition = target.behavior().position();
        turnAt(position().angle(targetPosition));
    }

    public void turnAwayFrom(Entity target) {
        Point2D targetPosition = target.behavior().position();
        turnAt(position().angle(targetPosition));
    }

    public void walk() {
        walks = true;
    }

    public void stop() {
        walks = false;
    }

    public double distanceTo(Entity entity) {
        return entity.behavior().position().distance(position());
    }

    // PERCEPTION.

    public List<Entity> look() {
        return entities.stream().filter((entity) -> {
            return entity.behavior() != this &&
                position().distance(entity.behavior().position()) <= visionRange;
        }).collect(Collectors.toList());
    }

    public Entity lookFor(Predicate<Entity> predicate) {
        return look().stream().filter(predicate).findAny().orElse(null);
    }

    public Entity lookFor(String sprite) {
        return lookFor((entity) -> sprite.equals(entity.sprite().getName()));
    }

    public void mark() {
        throw new UnsupportedOperationException();
    }

    // COMBAT.

    public void attack(Entity entity) {
        throw new UnsupportedOperationException();
    }

    public void flee() {
        throw new UnsupportedOperationException();
    }

    // TRANSPORTATION.

    public void carry(Entity entity) {
        throw new UnsupportedOperationException();
    }

    public void drop() {
        throw new UnsupportedOperationException();
    }

}

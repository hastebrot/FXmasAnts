package de.jug_h.entity;

import javafx.geometry.Point2D;

import de.jug_h.util.MathUtils;

public class Entity {

    //---------------------------------------------------------------------------------------------
    // FIELDS.
    //---------------------------------------------------------------------------------------------

    private final int id;

    private Sprite sprite;

    private Memory memory;

    private Behavior behavior;

    //---------------------------------------------------------------------------------------------
    // CONSTRUCTORS.
    //---------------------------------------------------------------------------------------------

    public Entity(int id) {
        this.id = id;
    }

    //---------------------------------------------------------------------------------------------
    // GETTER AND SETTER.
    //---------------------------------------------------------------------------------------------

    public int id() {
        return id;
    }

    public Sprite sprite() {
        return sprite;
    }

    public Memory memory() {
        return memory;
    }

    public Behavior behavior() {
        return behavior;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    public void tick() {
        if (behavior.walks()) {
            Point2D position = behavior.position();
            double angle = behavior.angle();

            Point2D direction = MathUtils.rotate(angle - 90);
            Point2D newPosition = position.add(direction);

            sprite.xProperty().set(MathUtils.clamp(newPosition.getX(), 0, 500 - 25 - 40));
            sprite.yProperty().set(MathUtils.clamp(newPosition.getY(), 0, 500 - 25 - 40));
        }
    }

    public String toString() {
        return "Entity id=" + id;
    }

}

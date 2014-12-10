package de.jug_h.entity;

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

}

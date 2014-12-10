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

    public int getId() {
        return id;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public Behavior getBehavior() {
        return behavior;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

}

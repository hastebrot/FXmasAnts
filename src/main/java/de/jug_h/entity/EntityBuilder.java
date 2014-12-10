package de.jug_h.entity;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import de.jug_h.Playfield;

public class EntityBuilder {

    //---------------------------------------------------------------------------------------------
    // PRIVATE STATIC FIELDS.
    //---------------------------------------------------------------------------------------------

    private static Playfield playfield;

    //---------------------------------------------------------------------------------------------
    // STATIC METHODS.
    //---------------------------------------------------------------------------------------------

    public static void register(Playfield playfield) {
        EntityBuilder.playfield = playfield;
    }

    public static EntityBuilder build() {
        return new EntityBuilder();
    }

    public static EntityBuilder buildAnt() {
        return EntityBuilder.build().name("ant").image(Resources.antImage());
    }

    public static EntityBuilder buildBug() {
        return EntityBuilder.build().name("bug").image(Resources.bugImage());
    }

    public static EntityBuilder buildFruit() {
        return EntityBuilder.build().name("fruit").image(Resources.fruitImage());
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE FIELDS.
    //---------------------------------------------------------------------------------------------

    private int id;
    private String name;
    private Image image;
    private Point2D position;

    //---------------------------------------------------------------------------------------------
    // CONSTRUCTORS.
    //---------------------------------------------------------------------------------------------

    private EntityBuilder() {}

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    public EntityBuilder id(int id) {
        this.id = id;
        return this;
    }

    public EntityBuilder name(String name) {
        this.name = name;
        return this;
    }

    public EntityBuilder image(Image image) {
        this.image = image;
        return this;
    }

    public EntityBuilder position(double x, double y) {
        this.position = new Point2D(x, y);
        return this;
    }

    public Entity getEntity() {
        Entity entity = new Entity(id);
        Sprite sprite = new Sprite(name, image);
        sprite.xProperty().set(position.getX());
        sprite.yProperty().set(position.getY());
        entity.setSprite(sprite);
        entity.setMemory(new Memory());
        entity.setBehavior(new Behavior(sprite, playfield.getEntities()));
        return entity;
    }

}

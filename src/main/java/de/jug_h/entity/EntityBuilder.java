package de.jug_h.entity;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class EntityBuilder {

    private int id;
    private String name;
    private Image image;
    private Point2D position;

    private EntityBuilder() {}

    public static EntityBuilder build() {
        return new EntityBuilder();
    }

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
        entity.setBehavior(new Behavior(sprite));
        return entity;
    }

}

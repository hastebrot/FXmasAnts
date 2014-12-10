package de.jug_h.entity;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sprite {

    //---------------------------------------------------------------------------------------------
    // FIELDS.
    //---------------------------------------------------------------------------------------------

    private final String name;
    private final Image image;
    private final ImageView imageView;

    private final DoubleProperty xProperty = new SimpleDoubleProperty(0.0);
    private final DoubleProperty yProperty = new SimpleDoubleProperty(0.0);

    private final DoubleProperty angleProperty = new SimpleDoubleProperty(0.0);

    //---------------------------------------------------------------------------------------------
    // CONSTRUCTORS.
    //---------------------------------------------------------------------------------------------

    public Sprite(String name,
                  Image image) {
        this.name = name;
        this.image = image;
        this.imageView = this.buildImageView();
    }

    //---------------------------------------------------------------------------------------------
    // GETTER AND SETTER.
    //---------------------------------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public DoubleProperty xProperty() {
        return xProperty;
    }

    public DoubleProperty yProperty() {
        return yProperty;
    }

    public DoubleProperty angleProperty() {
        return angleProperty;
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE METHODS.
    //---------------------------------------------------------------------------------------------

    private ImageView buildImageView() {
        ImageView imageView = new ImageView(image);
        imageView.getStyleClass().add(name);
        imageView.xProperty().bind(xProperty);
        imageView.yProperty().bind(yProperty);
        imageView.rotateProperty().bind(angleProperty);
        return imageView;
    }

}

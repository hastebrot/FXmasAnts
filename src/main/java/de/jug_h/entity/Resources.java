package de.jug_h.entity;

import java.io.InputStream;
import javafx.scene.image.Image;

public class Resources {

    //---------------------------------------------------------------------------------------------
    // CONSTANTS.
    //---------------------------------------------------------------------------------------------

    public static final String ANT_RESOURCE = "./res/ant2.png";

    public static final String BUG_RESOURCE = "./res/bug.png";

    public static final String FRUIT_RESOURCE = "./res/apple.png";

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    public static Image antImage() {
        InputStream inputStream = Resources.class.getResourceAsStream(ANT_RESOURCE);
        return new Image(inputStream, 25, 25, true, false);
    }

    public static Image bugImage() {
        InputStream inputStream = Resources.class.getResourceAsStream(BUG_RESOURCE);
        return new Image(inputStream, 40, 40, true, false);
    }

    public static Image fruitImage() {
        InputStream inputStream = Resources.class.getResourceAsStream(FRUIT_RESOURCE);
        return new Image(inputStream, 40, 40, true, false);
    }

}

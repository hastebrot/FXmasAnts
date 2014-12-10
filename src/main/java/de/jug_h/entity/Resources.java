package de.jug_h.entity;

import java.io.InputStream;
import javafx.scene.image.Image;

public class Resources {

    //---------------------------------------------------------------------------------------------
    // CONSTANTS.
    //---------------------------------------------------------------------------------------------

    public static final String ANT_RESOURCE = "./res/ant2.png";

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    public static Image antImage() {
        InputStream inputStream = Resources.class.getResourceAsStream(ANT_RESOURCE);
        return new Image(inputStream, 25, 25, true, false);
    }

}

package de.jug_h;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import org.junit.Before;
import org.junit.Test;

import static org.testfx.api.FxToolkit.setupStage;

public class PlayfieldTest extends FxRobotTestBase {

    public Playfieldd playfield;

    @Before
    public void setup() throws Exception {
        playfield = new Playfieldd();
        setupStage(stage -> {
            Pane playfieldPane = playfield.buildPane();
            playfield.run();
            stage.setScene(new Scene(playfieldPane, 500, 500));
            stage.show();
        });
    }

    @Test
    public void test() {
        sleep(5000);
    }



}

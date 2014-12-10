package de.jug_h.cases.acceptance;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import de.jug_h.Playfield;
import de.jug_h.cases.FxRobotTestBase;
import de.jug_h.entity.Entity;
import de.jug_h.entity.EntityBuilder;
import de.jug_h.entity.Resources;
import org.junit.Before;
import org.junit.Test;

import static org.testfx.api.FxToolkit.setupStage;

public class PerceptionTest extends FxRobotTestBase {

    //---------------------------------------------------------------------------------------------
    // FIELDS.
    //---------------------------------------------------------------------------------------------

    public Playfield playfield;

    //---------------------------------------------------------------------------------------------
    // FIXTURE METHODS.
    //---------------------------------------------------------------------------------------------

    @Before
    public void setup() throws Exception {
        playfield = new Playfield();
        setupStage((stage) -> {
            Pane pane = playfield.buildPane();
            stage.setScene(new Scene(pane, 500, 500));
            stage.setOnShown((windowEvent) -> playfield.run());
            stage.show();
        });
    }

    //---------------------------------------------------------------------------------------------
    // FEATURE METHODS.
    //---------------------------------------------------------------------------------------------

    @Test
    public void should_list_entities() {
        // given:
        Entity ant = EntityBuilder.build()
            .id(0).name("ant").image(Resources.antImage()).position(200, 100)
            .getEntity();
        Entity bug = EntityBuilder.build()
            .id(1).name("bug").image(Resources.bugImage()).position(350, 350)
            .getEntity();
        Entity fruit = EntityBuilder.build()
            .id(2).name("fruit").image(Resources.fruitImage()).position(50, 350)
            .getEntity();
        playfield.getEntities().addAll(ant, bug, fruit);

        sleep(10000);
    }

}

package de.jug_h.cases.acceptance;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import de.jug_h.Playfield;
import de.jug_h.cases.FxRobotTestBase;
import de.jug_h.entity.Entity;
import de.jug_h.entity.EntityBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
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
        EntityBuilder.register(playfield);
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
    public void should_look() {
        // given:
        Entity ant = EntityBuilder.buildAnt().id(0).position(100, 100).getEntity();
        Entity bug = EntityBuilder.buildBug().id(1).position(150, 100).getEntity();
        Entity fruit = EntityBuilder.buildFruit().id(2).position(200, 100).getEntity();
        playfield.getEntities().addAll(ant, bug, fruit);

        // expect:
        assertThat(ant.behavior().look(), contains(bug));
        assertThat(bug.behavior().look(), contains(ant, fruit));
    }

}

package de.jug_h.cases.acceptance;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import de.jug_h.Playfield;
import de.jug_h.cases.FxRobotTestBase;
import de.jug_h.entity.Behavior;
import de.jug_h.entity.Entity;
import de.jug_h.entity.Memory;
import de.jug_h.entity.Resources;
import de.jug_h.entity.Sprite;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxService;
import org.testfx.service.finder.NodeFinder;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.loadui.testfx.Assertions.verifyThat;
import static org.testfx.api.FxToolkit.setupStage;
import static org.testfx.util.WaitForAsyncUtils.waitForFxEvents;

public class PlayfieldTest extends FxRobotTestBase {

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
    public void should_show_window() {
        // expect:
        verifyThat("#playfield", (node) -> node != null);
    }

    @Test
    public void should_contain_entity() {
        // when:
        Entity entity0 = new Entity(0);
        playfield.getEntities().add(entity0);

        // then:
        assertThat(playfield.getEntities(), contains(entity0));
    }

    @Test
    public void should_display_entity_sprite() {
        // given:
        Entity entity0 = new Entity(0);
        Sprite sprite0 = new Sprite("ant", Resources.antImage());
        entity0.setSprite(sprite0);

        // when:
        playfield.getEntities().add(entity0);
        waitForFxEvents();

        // then:
        NodeFinder nodeFinder = FxService.serviceContext().getNodeFinder();
        assertThat(nodeFinder.nodes(".ant"), contains(sprite0.getImageView()));
    }

    @Test
    public void should_have_entity_memory() {
        // given:
        Entity entity0 = new Entity(0);
        entity0.setMemory(new Memory());

        // when:
        entity0.memory().put("home", new Point2D(0, 0));
        entity0.memory().put("home", new Point2D(50, 50));

        // then:
        assertThat(entity0.memory().get("home"), is(new Point2D(50, 50)));
    }

    @Test
    public void should_have_entity_behavior() {
        // given:
        Entity entity0 = new Entity(0);
        Sprite sprite0 = new Sprite("ant", Resources.antImage());
        entity0.setSprite(sprite0);
        entity0.setBehavior(new Behavior(entity0.sprite(), null));

        playfield.getEntities().add(entity0);
        waitForFxEvents();

        // when:
        entity0.behavior().turnAt(90);

        // then:
        assertThat(sprite0.angleProperty().get(), is(90.0));
    }

}

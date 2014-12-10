package de.jug_h;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import de.jug_h.entity.Entity;
import de.jug_h.entity.Resources;
import de.jug_h.entity.Sprite;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxService;
import org.testfx.api.FxToolkit;
import org.testfx.service.finder.NodeFinder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.loadui.testfx.Assertions.verifyThat;
import static org.testfx.api.FxToolkit.setupStage;

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
    public void should_display_entity_image() throws Exception {
        // given:
        Entity entity0 = new Entity(0);
        Sprite antSprite = new Sprite("ant", Resources.antImage());
        entity0.setSprite(antSprite);

        // when:
        playfield.getEntities().add(entity0);
        FxToolkit.setup(() -> {
            playfield.playfieldPane.getChildren().add(antSprite.getImageView());
        });

        // then:
        NodeFinder nodeFinder = FxService.serviceContext().getNodeFinder();
        assertThat(nodeFinder.nodes(".ant"), contains(antSprite.getImageView()));
    }

}

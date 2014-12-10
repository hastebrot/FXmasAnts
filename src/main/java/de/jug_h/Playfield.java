package de.jug_h;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import de.jug_h.entity.Behavior;
import de.jug_h.entity.Entity;
import de.jug_h.entity.Memory;
import de.jug_h.entity.Resources;
import de.jug_h.entity.Sprite;

public class Playfield {

    //---------------------------------------------------------------------------------------------
    // PRIVATE FIELDS.
    //---------------------------------------------------------------------------------------------

    private StackPane rootPane;
    private AnchorPane playfieldPane;

    private List<Entity> entities = new ArrayList<>();
    private Consumer<Entity> entityConsumer;

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    public Pane buildPane() {
        initRootPane();
        initPlayfieldPane();
        return rootPane;
    }

    public void buildEntities() {
        initEntities();
        initEntityBehavior();
        refresh();
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void refresh() {
        Platform.runLater(() -> {
            playfieldPane.getChildren().clear();
            for (Entity entity : entities) {
                ImageView imageView = entity.sprite().getImageView();
                playfieldPane.getChildren().add(imageView);
            }
        });
    }

    public void run() {
        Timeline timeline = initTimeline(Duration.millis(5), (event) -> {
            updateEntities();
        });
        timeline.play();
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE METHODS.
    //---------------------------------------------------------------------------------------------

    private void initRootPane() {
        rootPane = new StackPane();
        rootPane.setStyle("-fx-background-color: gray;");
        rootPane.setPadding(new Insets(20));
    }

    private void initPlayfieldPane() {
        playfieldPane = new AnchorPane();
        playfieldPane.setId("playfield");
        playfieldPane.setStyle("-fx-background-color: white;");

        rootPane.getChildren().add(playfieldPane);
    }

    private void initEntities() {
        for (int index = 0; index <= 4; index++) {
            Entity entity = new Entity(index);
            Sprite sprite = new Sprite("ant", Resources.antImage());
            sprite.xProperty().set(150);
            sprite.yProperty().set(150);
            entity.setSprite(sprite);
            entity.setMemory(new Memory());
            entity.setBehavior(new Behavior(sprite));
            entities.add(entity);
        }
    }

    private void initEntityBehavior() {
        Random random = new Random();
        defineEntityConsumer((Entity entity) -> {
            // move in circle.
            if (entity.id() == 0) {
                if (entity.memory().recall("distance", 0.0) == 0.0) {
                    entity.memory().learn("distance", 10.0);
                    entity.behavior().turnTo(entity.behavior().angle() + 10);
                    entity.behavior().move();
                }
                entity.memory().learn("distance", entity.memory().recall("distance", 0.0) - 1);
            }

            // move randomly.
            if (entity.id() == 1) {
                if (entity.memory().recall("distance", 0.0) == 0.0) {
                    entity.memory().learn("distance", 50.0);
                    entity.behavior().turnTo(random.nextDouble() * 360);
                    entity.behavior().move();
                }
                entity.memory().learn("distance", entity.memory().recall("distance", 0.0) - 1);
            }

            // move back and forth.
            if (entity.id() == 2) {
                if (entity.memory().recall("distance", 0.0) == 0.0) {
                    double value = entity.memory().recall("backwards", false) ? 180 : 0;
                    entity.behavior().turnTo(90 + value);
                    entity.behavior().move();
                    if (entity.sprite().xProperty().get() >= (500 - 25 - 40)) {
                        entity.memory().learn("backwards", true);
                    }
                    if (entity.sprite().xProperty().get() <= 0) {
                        entity.memory().learn("backwards", false);
                    }
                    entity.memory().learn("distance", 10.0);
                }
                entity.memory().learn("distance", entity.memory().recall("distance", 0.0) - 1);
            }
        });
    }

    private void defineEntityConsumer(Consumer<Entity> entityConsumer) {
        this.entityConsumer = entityConsumer;
    }

    private void updateEntities() {
        if (entityConsumer != null) {
            for (Entity entity : entities) {
                entityConsumer.accept(entity);
                entity.behavior().internalTick();
            }
        }
    }

    private Timeline initTimeline(Duration duration,
                                  EventHandler<ActionEvent> eventHandler) {
        KeyFrame keyFrame = new KeyFrame(duration, eventHandler);
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        return timeline;
    }

}

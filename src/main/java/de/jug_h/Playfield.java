package de.jug_h;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import de.jug_h.entity.Entity;

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

    public List<Entity> getEntities() {
        return entities;
    }

    public Bounds bounds() {
        return playfieldPane.getLayoutBounds();
    }

    public void define(Consumer<Entity> entityConsumer) {
        this.entityConsumer = entityConsumer;
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

    private void updateEntities() {
        if (entityConsumer != null) {
            for (Entity entity : entities) {
                entityConsumer.accept(entity);
                entity.tick();
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

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import de.jug_h.entity.Entity;
import de.jug_h.entity.Resources;

public class Playfield {

    //---------------------------------------------------------------------------------------------
    // PRIVATE FIELDS.
    //---------------------------------------------------------------------------------------------

    private StackPane rootPane;
    public AnchorPane playfieldPane;

    private List<Entity> entities = new ArrayList<>();

    private List<Ant> antList = new ArrayList<>();
    private Consumer<Ant> antConsumer;

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    public Pane buildPane() {
        initRootPane();
        initPlayfieldPane();
        return rootPane;
    }

    public void buildSprites() {
        initAnts();
        initAntBehaviour();
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void refresh() {
        Platform.runLater(() -> {
            playfieldPane.getChildren().clear();
            for (Entity entity : entities) {
                ImageView imageView = entity.getSprite().getImageView();
                playfieldPane.getChildren().add(imageView);
            }
        });
    }

    public void run() {
        Timeline timeline = initTimeline(Duration.millis(5), (event) -> {
            callAntConsumer();
        });
        timeline.play();
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE METHODS.
    //---------------------------------------------------------------------------------------------

    // https://color.adobe.com/Theme-1-color-theme-4814237/

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

    private void initAnts() {
        for (int index = 0; index <= 4; index++) {
            Ant ant = new Ant(index);
            ant.xProperty.set(150);
            ant.yProperty.set(150);
            antList.add(ant);
        }

        Image antImage = Resources.antImage();
        for (Ant ant : antList) {
            ImageView imageView = new ImageView(antImage);
            imageView.rotateProperty().bind(ant.angleProperty);
            imageView.xProperty().bind(ant.xProperty);
            imageView.yProperty().bind(ant.yProperty);
            playfieldPane.getChildren().add(imageView);
        }
    }

    private void initAntBehaviour() {
        Random rng = new Random();
        defineAntConsumer((Ant ant) -> {
            if (ant.id == 0) {
                ant.setAngle(ant.getAngle() + 10);
                ant.move(10);
            }
            if (ant.id == 1 || ant.id >= 3) {
                ant.setAngle(rng.nextDouble() * 360);
                ant.move(50);
            }
            if (ant.id == 2) {
                if (ant.memory("backwards") == null) {
                    ant.memory("backwards", false);
                }
                double value = ant.memory("backwards") ? 180 : 0;
                ant.setAngle(90 + value);
                ant.move(10);
                if (ant.getX() >= (500 - 25 - 40)) {
                    ant.memory("backwards", true);
                }
                if (ant.getX() <= 0) {
                    ant.memory("backwards", false);
                }
            }
        });
    }

    private Timeline initTimeline(Duration duration,
                                  EventHandler<ActionEvent> eventHandler) {
        KeyFrame keyFrame = new KeyFrame(duration, eventHandler);
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        return timeline;
    }

    private void callAntConsumer() {
        for (Ant ant : antList) {
            if (ant.distanceProperty.get() == 0) {
                this.antConsumer.accept(ant);
            }
            if (ant.distanceProperty.get() > 0) {
                ant.tick();
            }
        }
    }

    private void defineAntConsumer(Consumer<Ant> antConsumer) {
        this.antConsumer = antConsumer;
    }

}

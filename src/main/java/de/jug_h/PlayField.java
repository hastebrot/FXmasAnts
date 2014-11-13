package de.jug_h;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PlayField extends Application {

    //---------------------------------------------------------------------------------------------
    // MAIN METHOD.
    //---------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        Application.launch(PlayField.class, args);
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE FIELDS.
    //---------------------------------------------------------------------------------------------

    private InputStream antResource = getClass().getResourceAsStream("res/ant.png");

    private List<Ant> antList = new ArrayList<>();
    private Consumer<Ant> antConsumer;

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    public void start(Stage primaryStage) {
        Pane root = new AnchorPane();

        initAnts();
        initAntBehaviour();
        initPlayfield(root);

        Timeline timeline = initTimeline();
        timeline.play();

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    //---------------------------------------------------------------------------------------------
    // PRIVATE METHODS.
    //---------------------------------------------------------------------------------------------

    private void initAnts() {
        Ant ant0 = new Ant(0);
        ant0.xProperty.set(150);
        ant0.yProperty.set(150);
        antList.add(ant0);
        Ant ant1 = new Ant(1);
        ant1.xProperty.set(150);
        ant1.yProperty.set(150);
        antList.add(ant1);
        Ant ant2 = new Ant(2);
        ant2.xProperty.set(150);
        ant2.yProperty.set(150);
        antList.add(ant2);
    }

    private void initAntBehaviour() {
        Random rng = new Random();
        defineAntConsumer((Ant ant) -> {
            if (ant.id == 0) {
                ant.setAngle(ant.getAngle() + 10);
                ant.move(10);
            }
            if (ant.id == 1) {
                ant.setAngle(rng.nextDouble() * 360);
                ant.move(25);
            }
            if (ant.id == 2) {
                if (ant.data("backwards") == null) {
                    ant.data("backwards", false);
                }
                double value = ant.data("backwards") ? 180 : 0;
                ant.setAngle(90 + value);
                ant.move(25);
                if (ant.getX() > 500) {
                    ant.data("backwards", true);
                }
                if (ant.getX() < 0) {
                    ant.data("backwards", false);
                }
            }
        });
    }

    private Timeline initTimeline() {
        KeyFrame keyFrame = new KeyFrame(Duration.millis(10), (event) -> {
            callAntConsumer();
        });
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

    private void initPlayfield(Pane rootPane) {
        Image antImage = new Image(antResource, 40, 40, true, false);
        for (Ant ant : antList) {
            ImageView imageView = new ImageView(antImage);
            imageView.rotateProperty().bind(ant.angleProperty);
            imageView.xProperty().bind(ant.xProperty);
            imageView.yProperty().bind(ant.yProperty);
            rootPane.getChildren().add(imageView);
        }
    }

}

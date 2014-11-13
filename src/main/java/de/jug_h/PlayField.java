package de.jug_h;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class PlayField {

    //---------------------------------------------------------------------------------------------
    // PRIVATE FIELDS.
    //---------------------------------------------------------------------------------------------

    private StackPane rootPane;
    private AnchorPane playfieldPane;

    private InputStream antResource = getClass().getResourceAsStream("res/ant2.png");

    private List<Ant> antList = new ArrayList<>();
    private Consumer<Ant> antConsumer;

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    public Pane buildPane() {
        initRootPane();
        initPlayfieldPane();

        initAnts();
        initAntBehaviour();
        return rootPane;
    }

    public void run() {
        Timeline timeline = initTimeline();
        timeline.play();
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE METHODS.
    //---------------------------------------------------------------------------------------------

    // https://color.adobe.com/Theme-1-color-theme-4814237/

    private void initRootPane() {
        rootPane = new StackPane();
        rootPane.setStyle("-fx-background-color: #BFD2BF;");
        rootPane.setPadding(new Insets(20));
    }

    private void initPlayfieldPane() {
        playfieldPane = new AnchorPane();
        playfieldPane.setStyle("-fx-background-color: #EDDBBC;");

        rootPane.getChildren().add(playfieldPane);
    }

    private void initAnts() {
        for (int index = 0; index <= 4; index++) {
            Ant ant = new Ant(index);
            ant.xProperty.set(150);
            ant.yProperty.set(150);
            antList.add(ant);
        }

        Image antImage = new Image(antResource, 25, 25, true, false);
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
                if (ant.data("backwards") == null) {
                    ant.data("backwards", false);
                }
                double value = ant.data("backwards") ? 180 : 0;
                ant.setAngle(90 + value);
                ant.move(10);
                if (ant.getX() >= (500 - 25 - 40)) {
                    ant.data("backwards", true);
                }
                if (ant.getX() <= 0) {
                    ant.data("backwards", false);
                }
            }
        });
    }

    private Timeline initTimeline() {
        KeyFrame keyFrame = new KeyFrame(Duration.millis(5), (event) -> {
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

}

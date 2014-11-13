package de.jug_h;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PlayField extends Application {

    private InputStream antResource = getClass().getResourceAsStream("res/ant.png");

    private List<Ant> antList = new ArrayList<>();
    private Consumer<Ant> antConsumer;

    public static void main(String[] args) {
        Application.launch(PlayField.class, args);
    }

    public void start(Stage primaryStage) {
        Pane root = new AnchorPane();

        Ant ant0 = new Ant();
        ant0.xProperty.set(150);
        ant0.yProperty.set(150);
        antList.add(ant0);
        Ant ant1 = new Ant();
        ant1.xProperty.set(150);
        ant1.yProperty.set(150);
        antList.add(ant1);
        Ant ant2 = new Ant();
        ant2.xProperty.set(150);
        ant2.yProperty.set(150);
        antList.add(ant2);

        initRootPane(root);

        Random rng = new Random();
        BooleanProperty backwards = new SimpleBooleanProperty(false);
        defineAntConsumer((Ant ant) -> {
            if (ant == ant0) {
                ant.setAngle(ant.getAngle() + 10);
                ant.move(10);
            }
            if (ant == ant1) {
                ant.setAngle(rng.nextDouble() * 360);
                ant.move(25);
            }
            if (ant == ant2) {
                double value = backwards.get() ? 180 : 0;
                ant.setAngle(90 + value);
                ant.move(25);
                if (ant.getX() > 500) {
                    backwards.set(true);
                }
                if (ant.getX() < 0) {
                    backwards.set(false);
                }
            }
        });

        KeyFrame keyFrame = new KeyFrame(Duration.millis(10), (event) -> {
            callAntConsumer();
        });
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
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

    private void initRootPane(Pane rootPane) {
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

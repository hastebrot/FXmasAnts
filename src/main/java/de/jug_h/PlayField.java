package de.jug_h;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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

        initRootPane(root);

        final DoubleProperty angle = new SimpleDoubleProperty(90 + 45);

        defineAntConsumer((Ant ant) -> {
            ant.setAngle(angle.get());
            ant.move(10);
            angle.set(angle.get() + 10);
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
                System.out.println("ant.accept");
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

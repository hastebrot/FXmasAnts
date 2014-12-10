package de.jug_h.demo;

import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import de.jug_h.Playfield;
import de.jug_h.entity.Entity;
import de.jug_h.entity.EntityBuilder;
import de.jug_h.entity.Resources;

public class FXmasAnts extends Application {

    //---------------------------------------------------------------------------------------------
    // MAIN METHOD.
    //---------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        Application.launch(FXmasAnts.class, args);
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE FIELDS.
    //---------------------------------------------------------------------------------------------

    private Playfield playfield;

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    public void start(Stage primaryStage) {
        playfield = new Playfield();
        Pane playfieldPane = playfield.buildPane();
        playfield.run();

        initEntities();
        initEntityBehavior();

        Scene scene = new Scene(playfieldPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle(getClass().getSimpleName());
        primaryStage.show();
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE METHODS.
    //---------------------------------------------------------------------------------------------

    private void initEntities() {
        //Entity nest = EntityBuilder.build()
        //    .id(0).name("nest").image(null).position(200, 100)
        //    .getEntity();

        for (int index = 1; index <= 5; index += 1) {
            Entity ant = EntityBuilder.build()
                .id(index).name("ant").image(Resources.antImage()).position(200, 100)
                .getEntity();
            playfield.getEntities().add(ant);
        }

        Entity bug = EntityBuilder.build()
            .id(10).name("bug").image(Resources.bugImage()).position(350, 350)
            .getEntity();

        Entity fruit = EntityBuilder.build()
            .id(11).name("fruit").image(Resources.fruitImage()).position(50, 350)
            .getEntity();
        playfield.getEntities().addAll(bug, fruit);
    }

    private void initEntityBehavior() {
        Random random = new Random();
        playfield.define((Entity entity) -> {
            // move in circle.
            if (entity.id() == 0) {
                if (entity.memory().getDouble("distance") == 0.0) {
                    entity.memory().put("distance", 10.0);
                    entity.behavior().turnBy(10);
                    entity.behavior().moveWalk();
                }
                entity.memory().putDouble("distance", value -> value - 1);
            }

            // move randomly.
            if (entity.id() == 1) {
                if (entity.memory().getDouble("distance") == 0.0) {
                    entity.memory().put("distance", 50.0);
                    entity.behavior().turnTo(random.nextDouble() * 360);
                    entity.behavior().moveWalk();
                }
                entity.memory().putDouble("distance", value -> value - 1);
            }

            // move left and right.
            if (entity.id() == 2) {
                if (entity.memory().getDouble("angle") == 0.0) {
                    entity.memory().put("angle", 90.0);
                }
                if (entity.memory().getDouble("distance") == 0.0) {
                    entity.behavior().turnTo(entity.memory().getDouble("angle"));
                    entity.behavior().moveWalk();
                    if (entity.behavior().position().getX() >= (500 - 25 - 40)) {
                        entity.memory().put("angle", -90.0);
                    }
                    if (entity.behavior().position().getX() <= 0) {
                        entity.memory().put("angle", 90.0);
                    }
                    entity.memory().put("distance", 10.0);
                }
                entity.memory().putDouble("distance", value -> value - 1);
            }
        });
    }

}
